package com.mit.pawin.dao.impl;

import com.mit.pawin.config.CustomJwtAuthenticationFilter;
import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dao.CommonDao;
import com.mit.pawin.dto.ValuePassingDto;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.util.TransactionLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommonDaoImpl implements CommonDao {

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

    private static final Logger log = LoggerFactory.getLogger(CommonDaoImpl.class);

    /**
     * 1
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public Object addObject(Object obj, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ methodAction={}}", methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance +"( " + obj + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if (null != obj) {
                session.save(obj);
            }

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + obj + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return obj;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;
        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/
        }
    }

    /**
     * 2
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public Object getObjectByColumnName(String tblName, String colName, Object value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " +
                    value + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            String hql = "from " + tblName + " where " + colName + " =  :value";

            List object = session.createQuery(hql)
                    .setParameter("value", value)
                    .list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + object + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if(0 != object.size()) {
                return object.get(0);
            }else {
                return null;
            }

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

                log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/
        }

    }

    /**
     * 3
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public List<Object> getObjectListByColumnName(String tblName, String colName, Object value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " +
                    value + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List result = null;

            String hql = "from " + tblName + " where " + colName + " =  :value";

            result = session.createQuery(hql)
                    .setParameter("value", value)
                    .list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 4
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public boolean updateObject(Object obj, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ methodAction={}}",  methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + obj + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if (null != obj) {
                session.saveOrUpdate(obj);
            }

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + true + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return true;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 5
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public Object checkTableEmpty(String tblName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Class<?> clazz = Class.forName(tblName);

            Object object = session.get(clazz, new Long(1));

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + object + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return object;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            session.close();

        }
    }

    /**
     * 6
     *
     * @param tblName
     * @param colName
     * @param methodAction
     * @return
     */
    public long getLastRecordId(String tblName, String colName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, methodAction={}}",  tblName, colName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String hql = "select " + colName + " from " + tblName + " order by " + colName + " desc";

            result = session.createQuery(hql)
                    .setMaxResults(1)
                    .getSingleResult();

            session.getTransaction().commit();

            long id = Long.parseLong(String.valueOf(result));

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + id + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return id;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return 0;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return 0;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            session.close();

        }
    }

    /**
     * 7
     *
     * @param tblName
     * @param colName1
     * @param colName2
     * @param value1
     * @param value2
     * @param methodAction
     * @return
     */
    public int getRecordCount(String tblName, String colName1, String colName2, Object value1, Object value2, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}",  tblName, colName1, colName2,
                    value1, value2, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName1 + ", " + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String hql = "select count(*) from " + tblName + " where " + colName1 + " = :value1 and " + colName2 + " = :value2";

            result = session.createQuery(hql)
                    .setParameter("value1", value1)
                    .setParameter("value2", value2)
                    .getSingleResult();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return Integer.parseInt(String.valueOf(result));

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            session.close();
        }
    }

    /**
     * 8
     *
     * @param tblName
     * @param colName1
     * @param colName2
     * @param value1
     * @param value2
     * @param methodAction
     * @return
     */
    public Object getRecordCountExceptOne(String tblName, String colName1, String colName2, Object value1, String value2, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}",  tblName, colName1, colName2,
                    value1, value2, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName1 + ", " + colName2 + ", " + value1 + ", " +
                    value2 + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String isActive = "isActive";

            String hql = "select count(*) from " + tblName + " where " + colName1 + " = :value1 and " + colName2 + " != :value2 and " + isActive + " = :value3";

            result = session.createQuery(hql)
                    .setParameter("value1", value1)
                    .setParameter("value2", value2)
                    .setParameter("value3", true)
                    .getSingleResult();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            session.close();

        }
    }

    /**
     * 9
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public int deleteRecords(String tblName, String colName, String value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}",  tblName, colName, colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " + value + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            int result = 0;

            String hql = "delete from " + tblName + " where " + colName + " = :value";

            result = session.createQuery(hql)
                    .setParameter("value", value)
                    .executeUpdate();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            session.close();

        }
    }

    /**
     * 10
     *
     * @param tblName
     * @param colName1
     * @param colName2
     * @param value1
     * @param value2
     * @param methodAction
     * @return
     */
    public int updateRecords(String tblName, String colName1, String colName2, Object value1, String value2, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}",  tblName,
                    colName1, colName2, value1, value2, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName1 + ", " + colName2 + ", " + value1 + ", " + value2 + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            int result = 0;

            String hql = "update " + tblName + " set " + colName1 + " = :value1 where " + colName2 + " = :value2";

            if ("true".equals(value1) || "false".equals(value1)) {
                value1 = Boolean.parseBoolean(String.valueOf(value1));
            }

            result = session.createQuery(hql)
                    .setParameter("value1", value1)
                    .setParameter("value2", value2)
                    .executeUpdate();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 11
     *
     * @param tblName
     * @param colName
     * @param date
     * @param methodAction
     * @return
     */
    public List<Object> getObjectsBeforeDate(String tblName, String colName, Date date, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, date={}, methodAction={}}",  tblName,
                    colName, date, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " + date + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List result = null;

            String hql = "from " + tblName + " where " + colName + " <= :value";

            result = session.createQuery(hql)
                    .setParameter("value", date)
                    .list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 12
     *
     * @param tblName
     * @param colList
     * @param valueList
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public int updateMultiRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String colName, String value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}",  tblName,
                    colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colList + ", " + valueList + ", " + colName + ", " + value + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            String setString = "";
            int result = 0;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    setString += colList.get(i) + " = :" + "value" + (i + 1) + ", ";
                } else {
                    setString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "update " + tblName + " set " + setString + " where " + colName + " = :value";

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            query = query.setParameter("value", value);

            result = query.executeUpdate();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 13
     *
     * @param tblName
     * @param colList
     * @param valueList
     * @param methodAction
     * @return
     */
    public List searchRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colList + ", " + valueList + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " and ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString;

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 14
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public List getAllObjects(String tblName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List result = null;

            String hql = "from " + tblName;

            result = session.createQuery(hql)
                    .list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 15
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public List<Object> getRecordsNotEqualColumn(String tblName, String colName, String value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}",  tblName, colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " + value + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List<Object> result = null;

            String hql = "from " + tblName + " where " + colName + " != :value";

            result = session.createQuery(hql)
                    .setParameter("value", value)
                    .list();

            session.getTransaction().commit();
            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    /**
     * 16
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public Object getRecordCountFromSingleValue(String tblName, String colName, Object value, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}",  tblName, colName, value, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + ", " + value + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String hql = "select count(*) from " + tblName + " where " + colName + " = :value";

            result = session.createQuery(hql)
                    .setParameter("value", value)
                    .getSingleResult();

            session.getTransaction().commit();
            session.close();

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public List<Object> getAllObjectsInDateRange(Date fromDate, Date toDate, String tblName, String colName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ fromDate={}, toDate={}, tblName={}, colName={}, methodAction={}}",  fromDate, toDate, tblName, colName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + fromDate + ", " + toDate + ", " + tblName + ", " + colName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List result = null;

            String hql = "from " + tblName + " where " + colName + " between :value1 and :value2";

            result = session.createQuery(hql)
                    .setParameter("value1", fromDate)
                    .setParameter("value2", toDate)
                    .list();

            session.getTransaction().commit();
            session.close();

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }

    }

    public Object addObjectList(ArrayList<Object> objects, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ objects={}, methodAction={}}", objects.size(), methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance +"( " + objects + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if (null != objects) {

                //System.out.println(objects.get(49));

                for(int i=0 ; i<objects.size(); i++) {
                    session.save(objects.get(i));

                    System.out.println("i = " + i);

                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            }

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + true + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return true;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;
        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/
        }
    }

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colList + ", " + valueList + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " and ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString;

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if(0 != result.size()) {
                return result.get(0);
            }else {
                return null;
            }

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public Object updateObjectList(ArrayList<Object> objects, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ objects={}, methodAction={}}", objects.size(), methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance +"( " + objects + " )" + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            if (null != objects) {

                for(int i=0 ; i<objects.size(); i++) {
                    session.saveOrUpdate(objects.get(i));

                    if (i % 50 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
            }

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + true + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return true;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return false;
        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/
        }
    }

    public int searchMaxRecord(String tblName, String colName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String hql = "select max(" + colName + ")" + "from " + tblName + " where isActive = :val";

            Query query = session.createQuery(hql)
                          .setParameter("val", true);

            result = query.getSingleResult();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return Integer.parseInt(String.valueOf(result));

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public List searchRecordsOrderByDesc(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String orderByCol, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}, orderByCol={}}",  tblName, methodAction, orderByCol);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colList + ", " + valueList + "," + orderByCol + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " and ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString + " order by " + orderByCol + " desc";

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public int searchMaxRecordActiveInactive(String tblName, String colName, String conditionCol, Object conditionVal, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;

            String hql = "select max(" + colName + ")" + "from " + tblName + " where " + conditionCol + " = :val";

            Query query = session.createQuery(hql)
                    .setParameter("val", conditionVal);

            result = query.getSingleResult();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return Integer.parseInt(String.valueOf(result));

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public List<Object> getAllObjectsOfOneColumn(String tblName, String colName, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, methodAction={}}",  tblName, colName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            List result = null;

            String hql = "select " + colName + " from " + tblName;

            result = session.createQuery(hql)
                    .list();

            session.getTransaction().commit();
            session.close();

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public int getFullCount(String tblName, String colName, String methodAction){

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, methodAction={}}",  tblName, colName, methodAction);

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + methodNameUsingClassInstance + "( " + tblName + ", " + colName + " )" +
                    " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            Object result = null;
            String hql = null;

            if(colName.equals("ALL")) {
                hql = "select count(*) from " + tblName;
                result = session.createQuery(hql)
                        .getSingleResult();
            }else if(colName.equals("ACTIVE")){
                hql = "select count(*) from " + tblName + " where isActive = :value";
                result = session.createQuery(hql)
                        .setParameter("value", true)
                        .getSingleResult();
            }else if(colName.equals("INACTIVE")){
                hql = "select count(*) from " + tblName + " where isActive = :value";
                result = session.createQuery(hql)
                        .setParameter("value", false)
                        .getSingleResult();
            }

            session.getTransaction().commit();
            session.close();

            return Integer.parseInt(String.valueOf(result));

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return -1;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }

    }

    public Object getObjectByColumnName(String tblName, String colName, Object value) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}}", tblName, colName, value);

            String hql = "from " + tblName + " where " + colName + " =  :value";

            List object = session.createQuery(hql)
                    .setParameter("value", value)
                    .list();

            session.getTransaction().commit();

            if(0 != object.size()) {
                return object.get(0);
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

    public Object addObject(Object obj) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()");

            if (null != obj) {
                session.save(obj);
            }

            session.getTransaction().commit();

            return obj;

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

    public boolean updateObject(Object obj) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()");

            if (null != obj) {
                session.saveOrUpdate(obj);
            }

            session.getTransaction().commit();

            return true;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            return false;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            return false;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

        }
    }

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName);

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " and ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString;

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            if(0 != result.size()) {
                return result.get(0);
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

    public List searchDistinctRecords(String tblName, String colName) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName);

            List result = null;

            String hql = "select distinct " + colName + " from " + tblName;

            Query query = session.createQuery(hql);

            result = query.list();

            session.getTransaction().commit();

            if(0 != result.size()) {
                return result;
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

    public List<Object> getObjectListByDateAndStatus(String tblName, String colName1, Object value1, String colName2, Object value2, String methodAction) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            List result = null;

            String hql = "from " + tblName + " where " + colName1 + " =  :value1 and " + colName2 + " = :value2";

            result = session.createQuery(hql)
                    .setParameter("value1", value1)
                    .setParameter("value2", value2)
                    .list();

            session.getTransaction().commit();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "return data => " + result + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return result;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + jce.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "Exception => " + e.getMessage() + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

            return null;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

            /*_________________________________________________________transaction log_________________________________________________________________*/
            transactionLog.write(username + " | " + methodAction + " | " + "finally => " + session + " | " + simpleDateFormatWithTime.format(new Date()) + " | " + clientIp);
            /*__________________________________________________________________________________________________________________________________________*/

        }
    }

    public List<Object> searchRecordByColumnsOr(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName);

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " or ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString;

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            if(0 != result.size()) {
                return result;
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

    public List<Object> searchRecordList(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName);

            String whereString = "";
            List result = null;

            for (int i = 0; i < colList.size(); i++) {
                if (i != colList.size() - 1) {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1) + " and ";
                } else {
                    whereString += colList.get(i) + " = :" + "value" + (i + 1);
                }
            }

            String hql = "from " + tblName + " where " + whereString;

            Query query = session.createQuery(hql);

            for (int i = 0; i < colList.size(); i++) {
                query = query.setParameter("value" + (i + 1), valueList.get(i));
            }

            result = query.list();

            session.getTransaction().commit();

            if(0 != result.size()) {
                return result;
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

    public List searchRecordByName(String tblName, String colName, String value) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}",  tblName);

            List result = null;

            String hql = "from " + tblName + " where " + colName + " like :value";

            Query query = session.createQuery(hql)
                            .setParameter("value", "%" + value + "%");

            result = query.list();

            session.getTransaction().commit();

            if(0 != result.size()) {
                return result;
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

    public boolean deleteObject(Object obj) {

        String username = valuePassingDto.getUsername();
        String clientIp = valuePassingDto.getClientIp();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();

            String methodNameUsingClassInstance = new CommonDaoImpl() {}.getClass().getEnclosingMethod().getName();
            log.info("Calling  " + methodNameUsingClassInstance + "()");

            if (null != obj) {
                session.delete(obj);
            }

            session.getTransaction().commit();

            return true;

        } catch (JDBCConnectionException jce) {

            log.info("Exception {}", jce.getMessage());

            commonService.checkDbConnection();

            return false;

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            return false;

        }finally {

            log.info("finally {}", session.getJdbcBatchSize());

            session.close();

        }
    }

}