package com.mit.pawin.dao;

import java.util.Date;
import java.util.List;

public interface AppointmentDao {

    public List getAllPetAppointments(String tblName, Date today);

}
