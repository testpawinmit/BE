package com.mit.pawin.service.impl;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dao.CommonDao;
import com.mit.pawin.dto.*;
import com.mit.pawin.entity.*;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.service.EmployeeService;
import com.mit.pawin.util.AES;
import com.mit.pawin.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EmployeeServiceImpl extends CommonUtil implements EmployeeService {

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UtilDto utilDto;

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    /**
     * 1
     *
     * @param employee
     * @return
     */
    @Override
    public boolean validateEmployee(Employee employee) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { employee={}}", employee.getEmpCode());

        try {
            if (employee.getIsActive()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 2
     *
     * @param empNic
     * @param telResidence
     * @param telMobile
     * @param empEmail
     * @return
     */
    public LinkedHashMap validateEmployeeInput(String empNic, String telResidence, String telMobile, String empEmail) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { empNic={}, telResidence={}, telMobile={}, empEmail={}}", empNic, telResidence, telMobile, empEmail);

        LinkedHashMap response = new LinkedHashMap();

        try {

            if (!validateNic(empNic)) {
                response.put("response", responseDto.getFail());
                response.put("message", "Employee NIC is not validated.");
                return response;
            } else if (!validatePhoneNo(telResidence)) {
                response.put("response", responseDto.getFail());
                response.put("message", "Employee Residence Telephone is not validated.");
                return response;
            } else if (!validatePhoneNo(telMobile)) {
                response.put("response", responseDto.getFail());
                response.put("message", "Employee Mobile Telephone is not validated.");
                return response;
            } else if (!validateEmail(empEmail)) {
                response.put("response", responseDto.getFail());
                response.put("message", "Employee Email is not validated.");
                return response;
            } else {
                response.put("response", responseDto.getOk());
                response.put("message", "All input is validated.");
                return response;
            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", "Error Occurred.");

            return response;
        }
    }

    /**
     * 3
     *
     * @param tblName
     * @param attrList
     * @param employeeDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfEmployeeInput(String tblName, LinkedList<String> attrList, EmployeeDto employeeDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, employeeDto={}, methodAction={}}", tblName, employeeDto.getEmpCode(), methodAction);

        Object objCountEmpNo = commonService.getRecordCount(tblName, attrList.get(0), attrList.get(5), employeeDto.getEmpNo(), true, methodAction);
        Object objCountEmpNic = commonService.getRecordCount(tblName, attrList.get(1), attrList.get(5), employeeDto.getEmpNic(), true, methodAction);
        Object objCountEpfNo = commonService.getRecordCount(tblName, attrList.get(2), attrList.get(5), employeeDto.getEpfNo(), true, methodAction);
        Object objCountEmpEmail = commonService.getRecordCount(tblName, attrList.get(3), attrList.get(5), employeeDto.getEmpEmail(), true, methodAction);
        Object objCountBankAcntNo = commonService.getRecordCount(tblName, attrList.get(4), attrList.get(5), employeeDto.getBankAcntNo(), true, methodAction);

        int empNoCount = Integer.parseInt(String.valueOf(objCountEmpNo));
        int empNicCount = Integer.parseInt(String.valueOf(objCountEmpNic));
        int epfNoCount = Integer.parseInt(String.valueOf(objCountEpfNo));
        int empEmailCount = Integer.parseInt(String.valueOf(objCountEmpEmail));
        int bankAcntNoCount = Integer.parseInt(String.valueOf(objCountBankAcntNo));

        LinkedHashMap response = new LinkedHashMap();

        if (empNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee No. is duplicate.");
            return response;
        } else if (empNicCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee NIC is duplicate.");
            return response;
        } else if (epfNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee EPF No. is duplicate.");
            return response;
        } else if (empEmailCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee Email is duplicate.");
            return response;
        } else if (bankAcntNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee Bank Account No. is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }

    /**
     * 4
     *
     * @param tblName
     * @param attrList
     * @param employeeDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfEmployeeInputForUpdation(String tblName, LinkedList<String> attrList, EmployeeDto employeeDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, employeeDto={}, methodAction={}}", tblName, employeeDto.getEmpCode(), methodAction);

        Object objCountEmpNo = commonService.getRecordCountExceptOne(tblName, attrList.get(0), attrList.get(5), employeeDto.getEmpNo(), employeeDto.getEmpCode(), methodAction);
        Object objCountEmpNic = commonService.getRecordCountExceptOne(tblName, attrList.get(1), attrList.get(5), employeeDto.getEmpNic(), employeeDto.getEmpCode(), methodAction);
        Object objCountEpfNo = commonService.getRecordCountExceptOne(tblName, attrList.get(2), attrList.get(5), employeeDto.getEpfNo(), employeeDto.getEmpCode(), methodAction);
        Object objCountEmpEmail = commonService.getRecordCountExceptOne(tblName, attrList.get(3), attrList.get(5), employeeDto.getEmpEmail(), employeeDto.getEmpCode(), methodAction);
        Object objCountBankAcntNo = commonService.getRecordCountExceptOne(tblName, attrList.get(4), attrList.get(5), employeeDto.getBankAcntNo(), employeeDto.getEmpCode(), methodAction);

        int empNoCount = Integer.parseInt(String.valueOf(objCountEmpNo));
        int empNicCount = Integer.parseInt(String.valueOf(objCountEmpNic));
        int epfNoCount = Integer.parseInt(String.valueOf(objCountEpfNo));
        int empEmailCount = Integer.parseInt(String.valueOf(objCountEmpEmail));
        int bankAcntNoCount = Integer.parseInt(String.valueOf(objCountBankAcntNo));

        LinkedHashMap response = new LinkedHashMap();

        if (empNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee No. is duplicate.");
            return response;
        } else if (empNicCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee NIC is duplicate.");
            return response;
        } else if (epfNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee EPF No. is duplicate.");
            return response;
        } else if (empEmailCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee Email is duplicate.");
            return response;
        } else if (bankAcntNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Employee Bank Account No. is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }


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
    public Employee saveEmployee(String fullEntity, String entity, String column, String username, String utilName, EmployeeDto employeeDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { fullEntity={}, entity={}, column={}, username={}, utilName={}, employeeDto={}, methodAction={}}", fullEntity,
                entity, column, username, utilName, employeeDto.getEmpCode(), methodAction);

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);

        //enum set
       /* switch (employeeDto.getEmpStatus()) {
            case "ACTIVE":
                employee.setEmpStatus(Employee.EmpStatus.ACTIVE);
                break;
            case "INACTIVE":
                employee.setEmpStatus(Employee.EmpStatus.INACTIVE);
                break;
            case "SUSPENDED":
                employee.setEmpStatus(Employee.EmpStatus.SUSPENDED);
                break;
            case "RESIGN":
                employee.setEmpStatus(Employee.EmpStatus.RESIGN);
                break;
        }

        switch (employeeDto.getEmpConfirmation()) {
            case "PROBATION":
                employee.setEmpConfirmation(Employee.EmpConfirmation.PROBATION);
                break;
            case "PERMANENT":
                employee.setEmpConfirmation(Employee.EmpConfirmation.PERMANENT);
                break;
            case "INTERNSHIP":
                employee.setEmpConfirmation(Employee.EmpConfirmation.INTERNSHIP);
                break;
            case "CONTRACT_BASIS":
                employee.setEmpConfirmation(Employee.EmpConfirmation.CONTRACT_BASIS);
                break;
        }*/

        Object objectEmployee = commonService.checkTableEmpty(fullEntity, methodAction);

        if (null == objectEmployee) {
            employee.setEmpId(1);
            employee.setEmpCode(utilName + "1");
        } else {
            long empId = commonService.getLastRecordId(entity, column, methodAction);
            employee.setEmpId(empId + 1);
            employee.setEmpCode(utilName + (empId + 1));
        }

        //employee.setIsActive(true);
        employee.setCreatedBy(username);

        return employee;
    }

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
    public EmployeeH saveEmployeeH(String fullEntity, String entity, String column, String username, Employee employee, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { fullEntity={}, entity={}, column={}, username={}, employee={}, methodAction={}}", fullEntity,
                entity, column, username, employee.getEmpCode(), methodAction);

        EmployeeH employeeH = new EmployeeH();
        BeanUtils.copyProperties(employee, employeeH);

        Object objectEmployeeH = commonService.checkTableEmpty(fullEntity, methodAction);

        if (null == objectEmployeeH) {
            employeeH.setEmpHId(1);
        } else {
            long empId = commonService.getLastRecordId(entity, column, methodAction);
            employeeH.setEmpHId(empId + 1);
        }

        employeeH.setIsActive(true);
        employeeH.setCreatedBy(username);

        return employeeH;
    }

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
    public UserRole saveUserRole(String fullEntity, String entity, String column, String username, String utilName, RoleDto roleDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { fullEntity={}, entity={}, column={}, username={}, utilName={}, roleDto={}, methodAction={}}", fullEntity,
                entity, column, username, utilName, roleDto.getUserRoleCode(), methodAction);

        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(roleDto, userRole);

        Object objectUserRole = commonService.checkTableEmpty(fullEntity, methodAction);

        if (null == objectUserRole) {
            userRole.setUserRoleId(1);
            userRole.setUserRoleCode(utilName + "1");
        } else {
            long ueRoleId = commonService.getLastRecordId(entity, column, methodAction);
            userRole.setUserRoleId(ueRoleId + 1);
            userRole.setUserRoleCode(utilName + (ueRoleId + 1));
        }

        userRole.setIsActive(true);
        userRole.setCreatedBy(username);

        return userRole;
    }

    /**
     * 11
     *
     * @param tblName
     * @param attrList
     * @param systemUserDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfSystemUserInput(String tblName, LinkedList<String> attrList, SystemUserDto systemUserDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, systemUserDto={}, methodAction={}}", tblName,
                systemUserDto.getUsername(), methodAction);

        Object objCountUsername = commonService.getRecordCount(tblName, attrList.get(0), attrList.get(1), systemUserDto.getUsername(), true, methodAction);

        int usernameCount = Integer.parseInt(String.valueOf(objCountUsername));

        LinkedHashMap response = new LinkedHashMap();

        if (usernameCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Username is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }

    /**
     * 12
     *
     * @param tblName
     * @param attrList
     * @param systemUserDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfSystemUserInputForUpdation(String tblName, LinkedList<String> attrList, SystemUserDto systemUserDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, systemUserDto={}, methodAction={}}", tblName,
                systemUserDto.getUsername(), methodAction);

        Object objCountEmpNo = commonService.getRecordCountExceptOne(tblName, attrList.get(0), attrList.get(1), systemUserDto.getUsername(), systemUserDto.getSysUsrCode(), methodAction);

        int usernameNoCount = Integer.parseInt(String.valueOf(objCountEmpNo));

        LinkedHashMap response = new LinkedHashMap();

        if (usernameNoCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Username is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }

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
    public UserPrivilege saveUserPrivilege(String fullEntity, String entity, String column, String username, String utilName, UserPrivilegeDto userPrivilegeDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { fullEntity={}, entity={}, column={}, username={}, utilName={}, userPrivilegeDto={}, methodAction}", fullEntity,
                entity, column, username, utilName, userPrivilegeDto.getUserPrivName(), methodAction);

        UserPrivilege userPrivilege = new UserPrivilege();
        BeanUtils.copyProperties(userPrivilegeDto, userPrivilege);

        Object objectUserPrivilege = commonService.checkTableEmpty(fullEntity, methodAction);

        if (null == objectUserPrivilege) {
            userPrivilege.setUserPrivilegeId(1);
            userPrivilege.setUserPrivilegeCode(utilName + "1");
        } else {
            long userPrivilegeId = commonService.getLastRecordId(entity, column, methodAction);
            userPrivilege.setUserPrivilegeId(userPrivilegeId + 1);
            userPrivilege.setUserPrivilegeCode(utilName + (userPrivilegeId + 1));
        }

        userPrivilege.setIsActive(true);
        userPrivilege.setCreatedBy(username);

        return userPrivilege;
    }

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
    public UserWithPrivilege saveUserWithPrivilege(String fullEntity, String entity, String column, String username, String utilName, UserWithPrivilegeDto userPrivilegeDto,
                                                   boolean isAdded, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { fullEntity={}, entity={}, column={}, username={}, utilName={}, userPrivilegeDto={}, methodAction}", fullEntity,
                entity, column, username, utilName, userPrivilegeDto.getUserRoleCode(), methodAction);

        UserWithPrivilege userWithPrivilege = new UserWithPrivilege();
        BeanUtils.copyProperties(userPrivilegeDto, userWithPrivilege);

        Object objectUserWithPrivilege = commonService.checkTableEmpty(fullEntity, methodAction);

        if (null == objectUserWithPrivilege) {
            userWithPrivilege.setUsrWithPrivId(1);
            userWithPrivilege.setUsrWithPrivCode(utilName + "1");
        } else {
            long userWithPrivilegeId = commonService.getLastRecordId(entity, column, methodAction);
            userWithPrivilege.setUsrWithPrivId(userWithPrivilegeId + 1);
            userWithPrivilege.setUsrWithPrivCode(utilName + (userWithPrivilegeId + 1));
        }

        userWithPrivilege.setIsAdded(isAdded);
        userWithPrivilege.setCreatedBy(username);

        return userWithPrivilege;
    }

    /**
     * 15
     *
     * @param tblName
     * @param attrList
     * @param roleDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfUserRoleInput(String tblName, LinkedList<String> attrList, RoleDto roleDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, roleDto={}, methodAction}", tblName, roleDto.getUserRoleCode(), methodAction);

        Object objUserRoleName = commonService.getRecordCount(tblName, attrList.get(0), attrList.get(1), roleDto.getUserRoleName(), true, methodAction);

        int RoleNameCount = Integer.parseInt(String.valueOf(objUserRoleName));

        LinkedHashMap response = new LinkedHashMap();

        if (RoleNameCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "Role Name is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }

    /**
     * 16
     *
     * @param tblName
     * @param attrList
     * @param roleDto
     * @param methodAction
     * @return
     */
    public LinkedHashMap checkDuplicatesOfUserRoleInputForUpdation(String tblName, LinkedList<String> attrList, RoleDto roleDto, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + " { tblName={}, roleDto={}, methodAction}", tblName, roleDto.getUserRoleCode(), methodAction);

        Object objUserRoleName = commonService.getRecordCountExceptOne(tblName, attrList.get(0), attrList.get(1), roleDto.getUserRoleName(), roleDto.getUserRoleCode(), methodAction);

        int userRoleNameCount = Integer.parseInt(String.valueOf(objUserRoleName));

        LinkedHashMap response = new LinkedHashMap();

        if (userRoleNameCount != 0) {
            response.put("response", responseDto.getFail());
            response.put("message", "User Role Name is duplicate.");
            return response;
        } else {
            response.put("response", responseDto.getOk());
            response.put("message", "No duplicates.");
            return response;
        }
    }

    public void createUsrRoleStructure() {

        List<Object> objectListUR = commonDao.getObjectListByColumnName("UserRole", "isActive", true, "test");
        Object[] alMain = new Object[objectListUR.size()];
        ArrayList<Object> roleMap = new ArrayList<>();
        ArrayList<Object> aRoleMap = new ArrayList<>();

        for (Object object : objectListUR) {
            UserRole userRole = (UserRole) object;

            Object[] strings = new Object[objectListUR.size()];

            RoleStructDto roleStructDto = new RoleStructDto();
            roleStructDto.setText(userRole.getUserRoleName());
            roleStructDto.setValue(userRole.getUserRoleCode());

            strings[userRole.getLevel()] = roleStructDto;

            //alMain[userRole.getIndex()] = Arrays.asList(strings);
        }

        String s = null;
        System.out.println(Arrays.asList(alMain));

        LinkedHashMap<Object, Object> pcMap = new LinkedHashMap<>(); //parent child map

        ArrayList<ArrayList> listOfList = new ArrayList<>();

        for (int i = 0; i < Arrays.asList(alMain).size(); i++) {
            List list = (List) Arrays.asList(alMain).get(i);

            /*Object value = list.stream()
                    .filter(number -> number != null)
                    .findFirst();*/

            /*System.out.println(((Optional) value).get());

            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("role", ((Optional) value).get());
            map.put("children", null);

            roleMap.add(map);*/

            //pcMap.put(((Optional) value).get(), "");

            ArrayList<Object> list1 = new ArrayList<>();
            int in = 0;

            for (int k = 0; k < list.size(); k++) {
                if (list.get(k) != null) {
                    list1.add(list.get(k));
                    in = k;
                }
            }

            for (int j = i + 1; j < Arrays.asList(alMain).size(); j++) {

                List listt = (List) Arrays.asList(alMain).get(j);
                if (listt.get(in + 1) != null) {
                    list1.add(listt.get(in + 1));
                }
            }

            if (list1.size() > 1) {
                listOfList.add(list1);
            }

        }

        System.out.println("listOfList " + listOfList);

        //create json format
        for (int i = listOfList.size() - 1; i >= 0; i--) {

            ArrayList<Object> li1 = new ArrayList<>();
            ArrayList<Object> li2 = new ArrayList<>();

            for (int j = 1; j < listOfList.get(i).size(); j++) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("role", listOfList.get(i).get(j));
                li1.add(linkedHashMap);
            }

            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("role", listOfList.get(i).get(0));
            linkedHashMap.put("children", li1);

            aRoleMap.add(linkedHashMap);
        }

        System.out.println("aRoleMap " + aRoleMap);

    }

    public LinkedHashMap createUsrList() {
        ArrayList<String> col = new ArrayList<>();
        col.add("level");
        col.add("isActive");

        ArrayList<LinkedHashMap> listOfRoles = new ArrayList<>();

        int maxLevel = commonDao.searchMaxRecord("UserRole", "level", "createUsrList");

        for (int i = 0; i <= maxLevel; i++) {
            ArrayList<Object> val = new ArrayList<>();
            val.add(i);
            val.add(true);
            UserRole userRole = (UserRole) commonDao.searchRecord("UserRole", col, val, "createUsrList");
            LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();

            if (null != userRole) {
                mainMap.put("text", userRole.getUserRoleName());
                mainMap.put("value", userRole.getUserRoleCode());
                //mainMap.put("level", userRole.getLevel());
                listOfRoles.add(mainMap);
            } else {
                LinkedHashMap<String, Object> mainMap1 = new LinkedHashMap<>();
                listOfRoles.add(mainMap1);
            }
        }

        System.out.println("listOfRoles" + listOfRoles);

        int maxParent = commonDao.searchMaxRecord("UserRole", "parentLevel", "createUsrList");

        for (int i = maxParent; i >= 0; i--) {

            ArrayList<String> col2 = new ArrayList<>();
            col2.add("parentLevel");
            col2.add("isActive");
            ArrayList<Object> val2 = new ArrayList<>();
            val2.add(i);
            val2.add(true);
            List<Object> children = commonDao.searchRecords("UserRole", col2, val2, "createUsrList");

            if (children.size() != 0) {
                ArrayList<Object> objects = new ArrayList<>();
                for (Object o : children) {
                    UserRole userRole = (UserRole) o;
                    objects.add(listOfRoles.get(userRole.getLevel()));
                }
                listOfRoles.get(i).put("children", objects);
            }
        }

        System.out.println("listOfRoles" + listOfRoles);

        return listOfRoles.get(0);

    }

    /*public Object createUserRole(RoleDto roleDto, String username) {

        ArrayList<String> col1 = new ArrayList<>();
        col1.add("userRoleCode");
        col1.add("isActive");
        ArrayList<Object> val1 = new ArrayList<>();
        val1.add(roleDto.getParentRoleCode());
        val1.add(true);

        //parent
        UserRole userRole = (UserRole) commonDao.searchRecord("UserRole", col1, val1, "createUserRole");

        int parentLevel = userRole.getLevel();

        //get max index
        int maxLevl = 0;

        ArrayList<Integer> maxLevelList = new ArrayList<>();
        maxLevelList.add(userRole.getLevel());

        while (maxLevl != -1) {
            maxLevl = commonDao.searchMaxRecordActiveInactive("UserRole", "level", "parentLevel", userRole.getLevel(), "createUserRole");
            userRole = (UserRole) commonDao.getObjectByColumnName("UserRole", "level", maxLevl, "createUserRole");
            maxLevelList.add(maxLevl);
        }

        int newLevel = 0;

        newLevel = maxLevelList.get(maxLevelList.size() - 2);

        int maxLevel = commonDao.searchMaxRecord("UserRole", "level", "createUserRole");

        ArrayList<UserRole> urList1 = new ArrayList<>();
        ArrayList<UserRole> urList2 = new ArrayList<>();

        //check db connection
        for (int i = newLevel + 1; i <= maxLevel; i++) {
            ArrayList<String> col3 = new ArrayList<>();
            col3.add("level");
            col3.add("isActive");
            ArrayList<Object> val3 = new ArrayList<>();
            val3.add(i);
            val3.add(true);

            UserRole ur = (UserRole) commonDao.searchRecord("UserRole", col3, val3, "createUserRole");

            urList1.add(ur);
        }

        for (UserRole ur : urList1) {
            if (null != ur) {
                ur.setLevel(ur.getLevel() + 1);
                ur.setUpdatedBy(username);
                ur.setUpdatedAt(new Date());
                commonDao.updateObject(ur, "createUserRole");
            }
        }

        for (int i = newLevel; i <= maxLevel; i++) {
            ArrayList<String> col3 = new ArrayList<>();
            col3.add("parentLevel");
            col3.add("isActive");
            ArrayList<Object> val3 = new ArrayList<>();
            val3.add(i);
            val3.add(true);

            List ur = commonDao.searchRecords("UserRole", col3, val3, "createUserRole");

            if (0 != ur.size()) {
                urList2.add((UserRole) ur.get(0));
            }
        }

        for (UserRole ur : urList2) {
            ur.setLevel(ur.getLevel() + 1);
            commonDao.updateObject(ur, "createUserRole");
        }

        UserRole userRole1 = new UserRole();
        long id = commonService.generateId("com.mit.pawin.entity.UserRole", "UserRole", "userRoleId", "createUserRole");
        userRole1.setUserRoleId(id);
        userRole1.setUserRoleCode(utilDto.getRole() + id);
        userRole1.setUserRoleName(roleDto.getUserRoleName().toUpperCase());
        userRole1.setCreatedBy(username);
        userRole1.setIsActive(true);
        userRole1.setLevel(newLevel + 1);
        userRole1.setParentLevel(parentLevel);
        UserRole object = (UserRole) commonDao.addObject(userRole1, "createUserRole");

        UserRole ur = object;

        while (ur.getParentLevel() != -1) {
            UserRoleMap userRoleMap = saveUsrRoleParentAndChild(ur, object);
            ur = (UserRole) commonDao.getObjectByColumnName("UserRole", "userRoleCode", userRoleMap.getParentUsrRoleCode(), "createUserRole");
        }

        return object;
    }*/

    public LinkedHashMap createPrivilegeList() {
        ArrayList<String> col = new ArrayList<>();
        col.add("level");
        col.add("isActive");

        ArrayList<LinkedHashMap> listOfPrivis = new ArrayList<>();

        int maxLevel = commonDao.searchMaxRecord("UserPrivilege", "level", "Create Privilege List");

        for (int i = 0; i <= maxLevel; i++) {
            ArrayList<Object> val = new ArrayList<>();
            val.add(i);
            val.add(true);
            UserPrivilege userPrivilege = (UserPrivilege) commonDao.searchRecord("UserPrivilege", col, val, "Create Privilege List");
            LinkedHashMap<String, Object> mainMap = new LinkedHashMap<>();

            if (null != userPrivilege) {
                mainMap.put("text", userPrivilege.getUserPrivName());
                mainMap.put("value", userPrivilege.getUserPrivilegeCode());
                //mainMap.put("level", userRole.getLevel());
                listOfPrivis.add(mainMap);
            } else {
                LinkedHashMap<String, Object> mainMap1 = new LinkedHashMap<>();
                listOfPrivis.add(mainMap1);
            }
        }

        System.out.println("listOfRoles" + listOfPrivis);

        int maxParent = commonDao.searchMaxRecord("UserPrivilege", "parentLevel", "Create Privilege List");

        for (int i = maxParent; i >= 0; i--) {

            ArrayList<String> col2 = new ArrayList<>();
            col2.add("parentLevel");
            col2.add("isActive");
            ArrayList<Object> val2 = new ArrayList<>();
            val2.add(i);
            val2.add(true);
            List<Object> children = commonDao.searchRecords("UserPrivilege", col2, val2, "Create Privilege List");

            if (children.size() != 0) {
                ArrayList<Object> objects = new ArrayList<>();
                for (Object o : children) {
                    UserPrivilege userPrivilege = (UserPrivilege) o;
                    objects.add(listOfPrivis.get(userPrivilege.getLevel()));
                }
                listOfPrivis.get(i).put("children", objects);
            }
        }

        System.out.println("listOfRoles" + listOfPrivis);

        return listOfPrivis.get(0);

    }

}
