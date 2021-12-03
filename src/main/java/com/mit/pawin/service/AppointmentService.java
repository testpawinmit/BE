package com.mit.pawin.service;

import com.mit.pawin.dto.EmployeeScheduleDto;
import com.mit.pawin.entity.Appointment;
import com.mit.pawin.entity.PetAppointment;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface AppointmentService {

    public LinkedHashMap getAppointments(Appointment appointment, PetAppointment petAppointment);

    public List getAllPetAppointments(String tblName, Date today);

    public Object addEmpSchedule(String day, EmployeeScheduleDto employeeScheduleDto);

}
