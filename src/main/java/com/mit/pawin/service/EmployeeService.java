package com.mit.pawin.service;

import com.mit.pawin.dto.*;
import com.mit.pawin.entity.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface EmployeeService {

    /**
     * 1
     *
     * @param employee
     * @return
     */
    public boolean validateEmployee(Employee employee);

    /**
     * 2
     *
     * @param empNic
     * @param telResidence
     * @param telMobile
     * @param empEmail
     * @return
     */
    public LinkedHashMap validateEmployeeInput(String empNic, String telResidence, String telMobile, String empEmail);

    /**
     * 3
     *
     * @param tblName
     * @param attrList
     * @param employeeDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfEmployeeInput(String tblName, LinkedList<String> attrList, EmployeeDto employeeDto, String methodAction);

    /**
     * 4
     *
     * @param tblName
     * @param attrList
     * @param employeeDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfEmployeeInputForUpdation(String tblName, LinkedList<String> attrList, EmployeeDto employeeDto, String methodAction);


    /**
     * 8
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param username
     * @param utilName
     * @param employeeDto
     * @param methodAction
     * @return
     */
    public Employee saveEmployee(String fullEntity, String entity, String column, String username, String utilName, EmployeeDto employeeDto, String methodAction);

    /**
     * 9
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param username
     * @param employee
     * @param methodAction
     * @return
     */
    public EmployeeH saveEmployeeH(String fullEntity, String entity, String column, String username, Employee employee, String methodAction);

    /**
     * 10
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param username
     * @param utilName
     * @param roleDto
     * @param methodAction
     * @return
     */
    public UserRole saveUserRole(String fullEntity, String entity, String column, String username, String utilName, RoleDto roleDto, String methodAction);

    /**
     * 11
     *
     * @param tblName
     * @param attrList
     * @param systemUserDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfSystemUserInput(String tblName, LinkedList<String> attrList, SystemUserDto systemUserDto, String methodAction);

    /**
     * 12
     *
     * @param tblName
     * @param attrList
     * @param systemUserDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfSystemUserInputForUpdation(String tblName, LinkedList<String> attrList, SystemUserDto systemUserDto, String methodAction);

    /**
     * 13
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param username
     * @param utilName
     * @param userPrivilegeDto
     * @param methodAction
     * @return
     */
    public UserPrivilege saveUserPrivilege(String fullEntity, String entity, String column, String username, String utilName, UserPrivilegeDto userPrivilegeDto, String methodAction);

    /**
     * 14
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param username
     * @param utilName
     * @param userPrivilegeDto
     * @param isAdded
     * @param methodAction
     * @return
     */
    public UserWithPrivilege saveUserWithPrivilege(String fullEntity, String entity, String column, String username, String utilName, UserWithPrivilegeDto userPrivilegeDto, boolean isAdded, String methodAction);

    /**
     * 15
     *
     * @param tblName
     * @param attrList
     * @param roleDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfUserRoleInput(String tblName, LinkedList<String> attrList, RoleDto roleDto, String methodAction);

    /**
     * 16
     *
     * @param tblName
     * @param attrList
     * @param roleDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfUserRoleInputForUpdation(String tblName, LinkedList<String> attrList, RoleDto roleDto, String methodAction);

    public void createUsrRoleStructure();

    public LinkedHashMap createUsrList();

    //public Object createUserRole(RoleDto roleDto, String username);

    public LinkedHashMap createPrivilegeList();


}
