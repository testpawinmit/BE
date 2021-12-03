package com.mit.pawin.service.impl;

import com.mit.pawin.dao.AppointmentDao;
import com.mit.pawin.dto.EmployeeScheduleDto;
import com.mit.pawin.entity.Appointment;
import com.mit.pawin.entity.EmployeeSchedule;
import com.mit.pawin.entity.Pet;
import com.mit.pawin.entity.PetAppointment;
import com.mit.pawin.service.AppointmentService;
import com.mit.pawin.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private AppointmentDao appointmentDao;

    public LinkedHashMap getAppointments(Appointment appointment, PetAppointment petAppointment){

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        List list = new ArrayList();
        Pet pet = null;
        com.mit.pawin.entity.Service service = null;

        //PetAppointment petAppointment = (PetAppointment) commonService.getObjectByColumnName("PetAppointment", "appCode", appointment.getAppCode());
        if(null != petAppointment) {
            pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode());
            if(null != pet){
                service = (com.mit.pawin.entity.Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode());
            }
        }

        linkedHashMap.put("appCode", appointment.getAppCode());
        linkedHashMap.put("petCode", pet.getPetCode());
        linkedHashMap.put("petName", pet.getPetName());
        linkedHashMap.put("serviceName", service.getServiceName());
        linkedHashMap.put("dateRange", simpleDateFormatWithTime.format(appointment.getCheckInDate())
                + " / " + simpleDateFormatWithTime.format(appointment.getCheckOutDate()));

        return linkedHashMap;
    }

    public List getAllPetAppointments(String tblName, Date today){
        return appointmentDao.getAllPetAppointments(tblName, today);
    }

    public Object addEmpSchedule(String day, EmployeeScheduleDto employeeScheduleDto){

        EmployeeSchedule employeeSchedule = new EmployeeSchedule();

        Object object = null;
        Object objectEmployeeSchedule = commonService.checkTableEmpty("com.mit.pawin.entity.EmployeeSchedule", "Create Employee Schedule");

        if (null == objectEmployeeSchedule) {
            employeeSchedule.setEmpScheduleId(1);

        } else {
            long empScheduleId = commonService.getLastRecordId("com.mit.pawin.entity.EmployeeSchedule", "empScheduleId", "Create Employee Schedule");
            employeeSchedule.setEmpScheduleId(empScheduleId + 1);

        }

        employeeSchedule.setCreatedBy("super");
        employeeSchedule.setEmpCode(employeeScheduleDto.getEmpCode());

        employeeSchedule.setDay(day);

        if(day.equals("SUNDAY")) {
            employeeSchedule.setStartTime(employeeScheduleDto.getSunStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getSunEndTime());
        }else if(day.equals("MONDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getMonStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getMonEndTime());
        }else if(day.equals("TUESDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getTueStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getTueEndTime());
        }else if(day.equals("WEDNESDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getWedStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getWedEndTime());
        }else if(day.equals("THURSDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getThuStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getThuEndTime());
        }else if(day.equals("FRIDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getFriStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getFriEndTime());
        }else if(day.equals("SATURDAY")){
            employeeSchedule.setStartTime(employeeScheduleDto.getSatStartTime());
            employeeSchedule.setEndTime(employeeScheduleDto.getSatEndTime());
        }

        object = commonService.addObject(employeeSchedule, "Employee Schedule Creation");

        return object;
    }
}
