package com.mit.pawin.dao.impl;

import com.mit.pawin.config.CustomJwtAuthenticationFilter;
import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dao.AppointmentDao;
import com.mit.pawin.dto.ValuePassingDto;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.util.TransactionLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private TransactionLog transactionLog;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private AuthenticationController authenticationController;

    SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ValuePassingDto valuePassingDto;

    @Autowired
    private CommonService commonService;

    private static final Logger log = LoggerFactory.getLogger(AppointmentDaoImpl.class);

    public List getAllPetAppointments(String tblName, Date today){
        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            //log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}}", tblName, colName, value);

            String hql = "from " + tblName + " where ( serviceCatCode =  :value1 or serviceCatCode =  :value2 ) and checkInDate = :value3";

            List object = session.createQuery(hql)
                    .setParameter("value1", "Daycare")
                    .setParameter("value2", "Boarding")
                    .setParameter("value3", today)
                    .list();

            session.getTransaction().commit();

            if(0 != object.size()) {
                return object;
            }else {
                return null;
            }

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

        }
    }

}