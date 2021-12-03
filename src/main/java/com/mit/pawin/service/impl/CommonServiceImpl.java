package com.mit.pawin.service.impl;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dao.CommonDao;
import com.mit.pawin.dao.impl.CommonDaoImpl;
import com.mit.pawin.dto.CommonCodeDto;
import com.mit.pawin.dto.CommonResponseDto;
import com.mit.pawin.dto.ResponseDto;
import com.mit.pawin.entity.*;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.*;

@Service
public class CommonServiceImpl extends CommonUtil implements CommonService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ResponseDto responseDto;

    @Value("${cba.restart.uri}")
    private String restartUri;

    private static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);

    /**
     * 1
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public Object addObject(Object obj, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ methodAction={}}", methodAction);

        return commonDao.addObject(obj, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

        return commonDao.getObjectByColumnName(tblName, colName, value, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

        return commonDao.getObjectListByColumnName(tblName, colName, value, methodAction);
    }

    /**
     * 4
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public boolean updateObject(Object obj, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ methodAction={}}", methodAction);

        return commonDao.updateObject(obj, methodAction);
    }

    /**
     * 5
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public Object checkTableEmpty(String tblName, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}", tblName, methodAction);

        return commonDao.checkTableEmpty(tblName, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, methodAction={}}", tblName, colName, methodAction);

        return commonDao.getLastRecordId(tblName, colName, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}", tblName, colName1,
                colName2, value1, value2, methodAction);

        return commonDao.getRecordCount(tblName, colName1, colName2, value1, value2, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}", tblName, colName1,
                colName2, value1, value2, methodAction);

        return commonDao.getRecordCountExceptOne(tblName, colName1, colName2, value1, value2, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

        return commonDao.deleteRecords(tblName, colName, value, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName1={}, colName2={}, value1={}, value2={}, methodAction={}}", tblName, colName1,
                colName2, value1, value2, methodAction);

        return commonDao.updateRecords(tblName, colName1, colName2, value1, value2, methodAction);
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

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, date={} methodAction={}}", tblName, colName, date, methodAction);

        return commonDao.getObjectsBeforeDate(tblName, colName, date, methodAction);
    }

    /**
     * 12
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param methodAction
     * @return
     */
    public long generateId(String fullEntity, String entity, String column, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ fullEntity={}, entity={}, column={} methodAction={}}", fullEntity, entity, column, methodAction);

        Object object = checkTableEmpty(fullEntity, methodAction);

        if (null == object) {
            return 1;
        } else {
            long id = getLastRecordId(entity, column, methodAction);
            return (id + 1);
        }
    }

    /**
     * 13
     *
     * @param commonCodeDto
     * @param response1
     * @param response2
     * @param tblName
     * @param methodAction
     * @return
     */
    public LinkedHashMap updateSingleDataOfRecords(CommonCodeDto commonCodeDto, String response1, String response2, String tblName, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ response1={}, response2={}, tblName={} methodAction={}}", response1, response2, tblName, methodAction);

        LinkedHashMap response = new LinkedHashMap();
        int record = 0;
        int index = -1;

        try {

            Object[] inputs = new Object[4];

            for (Map.Entry me : commonCodeDto.getCode().entrySet()) {
                String key = String.valueOf(me.getKey());
                String value = String.valueOf(me.getValue());

                inputs = insertDataToArray(key, inputs, index);
                index++;
                inputs = insertDataToArray(value, inputs, index);
                index++;
            }

            record = updateRecords(tblName, String.valueOf(inputs[2]), String.valueOf(inputs[0]), inputs[3], String.valueOf(inputs[1]), methodAction);

            if (0 != record) {
                response.put("response", responseDto.getOk());
                response.put("message", response1);
                return response;
            } else {
                response.put("response", responseDto.getFail());
                response.put("message", response2);
                return response;
            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return response;
        }
    }

    /**
     * 14
     *
     * @param tblName
     * @param colList
     * @param valueList
     * @param colName2
     * @param value2
     * @param methodAction
     * @return
     */
    public int updateMultiRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String colName2, String value2, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName2={}, value2={} methodAction={}}", tblName, colName2, value2, methodAction);

        return commonDao.updateMultiRecords(tblName, colList, valueList, colName2, value2, methodAction);
    }

    /**
     * 15
     *
     * @param tblName
     * @param colList
     * @param valueList
     * @param methodAction
     * @return
     */
    public List searchRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}", tblName, methodAction);

        return commonDao.searchRecords(tblName, colList, valueList, methodAction);
    }

    /**
     * 16
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public List getAllObjects(String tblName, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, methodAction={}}", tblName, methodAction);

        return commonDao.getAllObjects(tblName, methodAction);
    }

    /**
     * 17
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public List<Object> getRecordsNotEqualColumn(String tblName, String colName, String value, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

        return commonDao.getRecordsNotEqualColumn(tblName, colName, value, methodAction);
    }

    /**
     * 18
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public Object getRecordCountFromSingleValue(String tblName, String colName, Object value, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ tblName={}, colName={}, value={}, methodAction={}}", tblName, colName, value, methodAction);

        return commonDao.getRecordCountFromSingleValue(tblName, colName, value, methodAction);
    }

    /**
     * 19
     */
    public void checkDbConnection() {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        try {

            CommonResponseDto commonResponseDto = new CommonResponseDto();

            String pName = ManagementFactory.getRuntimeMXBean().getName();

            commonResponseDto.setResponse("OK");
            commonResponseDto.setMessage(pName.split("@")[0]);

            RestTemplate restTemplate = new RestTemplate();

            CommonResponseDto customerResponseDto = restTemplate.postForObject(restartUri,
                    commonResponseDto, CommonResponseDto.class);

            //restart(commonResponseDto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 20
     *
     * @param fromDate
     * @param toDate
     * @param tblName
     * @param colName
     * @param methodAction
     * @return
     */
    public List<Object> getAllObjectsInDateRange(Date fromDate, Date toDate, String tblName, String colName, String methodAction) {

        String methodNameUsingClassInstance = new AuthenticationController() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        return commonDao.getAllObjectsInDateRange(fromDate, toDate, tblName, colName, methodAction);

    }

    public Object addObjectList(ArrayList<Object> objects, String methodAction) {

        String methodNameUsingClassInstance = new CommonDaoImpl() {
        }.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ objects={}, methodAction={}}", objects.size(), methodAction);

        return commonDao.addObjectList(objects, methodAction);
    }

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction) {

        return commonDao.searchRecord(tblName, colList, valueList, methodAction);
    }

    public Object updateObjectList(ArrayList<Object> objects, String methodAction) {

        return commonDao.updateObjectList(objects, methodAction);
    }

    public void modifyArrList() {

        ArrayList<String> data = new ArrayList<>();
        data.add("1,2");
        data.add("3,4");
        /*data.add("5,6");
        data.add("7,8");*/

        modifyArrayList(data);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    public double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        System.out.println(Math.sqrt(distance));

        return Math.sqrt(distance);
    }

    public void calculateDistance() {
        ArrayList<String> list = (ArrayList<String>) getFileIntoList("E:/dev/cba/HRIS_2020_12_16/data/FileNumber_1.csv");
        ArrayList<String> list1 = modifyArrayList(list);
        List<Double> disList = new ArrayList<>();

        for (String in : list1) {
            String[] inArr = in.split(",");
            double lat1 = Double.parseDouble(inArr[0]);
            double lat2 = Double.parseDouble(inArr[2]);
            double lon1 = Double.parseDouble(inArr[1]);
            double lon2 = Double.parseDouble(inArr[3]);
            double dis = distance(lat1, lat2, lon1, lon2);
            disList.add(dis);
        }

        Double totalDistance = disList.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        int i = 0;
    }

    public List<Object> getAllObjectsOfOneColumn(String tblName, String colName, String methodAction) {
        return commonDao.getAllObjectsOfOneColumn(tblName, colName, methodAction);
    }

    public int getFullCount(String tblName, String colName, String methodAction) {
        return commonDao.getFullCount(tblName, colName, methodAction);
    }

    public Object getObjectByColumnName(String tblName, String colName, Object value) {
        return commonDao.getObjectByColumnName(tblName, colName, value);
    }

    public Object addObject(Object obj) {
        return commonDao.addObject(obj);
    }

    public boolean updateObject(Object obj) {
        return commonDao.updateObject(obj);
    }

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {
        return commonDao.searchRecord(tblName, colList, valueList);
    }

    public List searchDistinctRecords(String tblName, String colName) {
        return commonDao.searchDistinctRecords(tblName, colName);
    }

    public List<Object> getObjectListByDateAndStatus(String tblName, String colName1, Object value1, String colName2, Object value2, String methodAction) {
        return commonDao.getObjectListByDateAndStatus(tblName, colName1, value1, colName2, value2, methodAction);
    }

    public List<Object> searchRecordByColumnsOr(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {
        return commonDao.searchRecordByColumnsOr(tblName, colList, valueList);
    }

    public List<Object> searchRecordList(String tblName, ArrayList<String> colList, ArrayList<Object> valueList) {
        return commonDao.searchRecordList(tblName, colList, valueList);
    }

    public List searchRecordByName(String tblName, String colName, String value) {
        return commonDao.searchRecordByName(tblName, colName, value);
    }

    public LinkedHashMap updateObject(CommonCodeDto commonCodeDto) {

        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();

        String table = String.valueOf(commonCodeDto.getCode().get("table"));

        try {

            if (table.equals("Veterinary")) {

                String vetCode = String.valueOf(commonCodeDto.getCode().get("vetCode"));
                String vetName = String.valueOf(commonCodeDto.getCode().get("vetName"));
                String vetPhone = String.valueOf(commonCodeDto.getCode().get("vetPhone"));
                String vetAddress = String.valueOf(commonCodeDto.getCode().get("vetAddress"));

                Veterinary veterinary = (Veterinary) getObjectByColumnName(table, "vetCode", vetCode);
                veterinary.setVetName(vetName);
                veterinary.setVetPhone(vetPhone);
                veterinary.setVetAddress(vetAddress);
                veterinary.setUpdatedAt(new Date());
                veterinary.setUpdatedBy("super");
                updateObject(veterinary);

            } else if (table.equals("Vaccination")) {

                String vaccCode = String.valueOf(commonCodeDto.getCode().get("vaccCode"));
                String vaccName = String.valueOf(commonCodeDto.getCode().get("vaccName"));
                String expirationDate = String.valueOf(commonCodeDto.getCode().get("expirationDate"));

                Vaccination vaccination = (Vaccination) getObjectByColumnName(table, "vaccCode", vaccCode);
                vaccination.setVaccName(vaccName);
                vaccination.setExpirationDate(convertStringDateToDate(expirationDate));
                vaccination.setUpdatedAt(new Date());
                vaccination.setUpdatedBy("super");
                updateObject(vaccination);


            } else if (table.equals("ServiceDog")) {

                String serviceDogCode = String.valueOf(commonCodeDto.getCode().get("serviceDogCode"));
                String serviceDogName = String.valueOf(commonCodeDto.getCode().get("serviceDogName"));
                String serviceDogDob = String.valueOf(commonCodeDto.getCode().get("serviceDogDob"));
                String breedCode = String.valueOf(commonCodeDto.getCode().get("breedCode"));
                String colorCode = String.valueOf(commonCodeDto.getCode().get("colorCode"));
                String note = String.valueOf(commonCodeDto.getCode().get("note"));

                ServiceDog serviceDog = (ServiceDog) getObjectByColumnName(table, "serviceDogCode", serviceDogCode);
                serviceDog.setServiceDogName(serviceDogName);
                serviceDog.setServiceDogDob(convertStringDateToDate(serviceDogDob));
                serviceDog.setBreedCode(breedCode);
                serviceDog.setColorCode(colorCode);
                serviceDog.setNote(note);
                serviceDog.setUpdatedAt(new Date());
                serviceDog.setUpdatedBy("super");
                updateObject(serviceDog);

            } else if (table.equals("Employee")) {

                String empCode = String.valueOf(commonCodeDto.getCode().get("empCode"));
                String empNo = String.valueOf(commonCodeDto.getCode().get("empNo"));
                String empName = String.valueOf(commonCodeDto.getCode().get("empName"));
                String empNic = String.valueOf(commonCodeDto.getCode().get("empNic"));
                String empPhone = String.valueOf(commonCodeDto.getCode().get("empPhone"));
                String empAddress = String.valueOf(commonCodeDto.getCode().get("empAddress"));

                Employee employee = (Employee) getObjectByColumnName(table, "empCode", empCode);
                employee.setEmpName(empName);
                employee.setEmpNo(empNo);
                employee.setEmpNic(empNic);
                employee.setEmpPhone(empPhone);
                employee.setEmpAddress(empAddress);
                employee.setUpdatedAt(new Date());
                employee.setUpdatedBy("super");
                updateObject(employee);

            } else if (table.equals("Retail")) {

                String retailCode = String.valueOf(commonCodeDto.getCode().get("retailCode"));
                String retailCatCode = String.valueOf(commonCodeDto.getCode().get("retailCatCode"));
                String retailName = String.valueOf(commonCodeDto.getCode().get("retailName"));
                String retailPrice = String.valueOf(commonCodeDto.getCode().get("retailPrice"));

                Retail retail = (Retail) getObjectByColumnName(table, "retailCode", retailCode);
                retail.setRetailCode(retailCode);
                retail.setRetailCatCode(retailCatCode);
                retail.setRetailName(retailName);
                retail.setRetailPrice(retailPrice);
                retail.setUpdatedAt(new Date());
                retail.setUpdatedBy("super");
                updateObject(retail);

            } else if (table.equals("Resources")) {

                String roomCode = String.valueOf(commonCodeDto.getCode().get("roomCode"));
                String roomName = String.valueOf(commonCodeDto.getCode().get("roomName"));
                String maxWeight = String.valueOf(commonCodeDto.getCode().get("maxWeight"));
                String cleanNeeded = String.valueOf(commonCodeDto.getCode().get("cleanNeeded"));

                Resources resources = (Resources) getObjectByColumnName(table, "roomCode", roomCode);
                resources.setRoomName(roomName);
                resources.setMaxWeight(maxWeight);
                resources.setCleanNeeded(cleanNeeded);
                resources.setUpdatedAt(new Date());
                resources.setUpdatedBy("super");
                updateObject(resources);

            } else if (table.equals("Breed")) {

                String breedCode = String.valueOf(commonCodeDto.getCode().get("breedCode"));
                String breedName = String.valueOf(commonCodeDto.getCode().get("breedName"));

                Breed breed = (Breed) getObjectByColumnName(table, "breedCode", breedCode);
                breed.setBreedName(breedName);
                breed.setUpdatedAt(new Date());
                breed.setUpdatedBy("super");
                updateObject(breed);

            } else if (table.equals("Color")) {

                String colorCode = String.valueOf(commonCodeDto.getCode().get("colorCode"));
                String colorName = String.valueOf(commonCodeDto.getCode().get("colorName"));

                Color color = (Color) getObjectByColumnName(table, "colorCode", colorCode);
                color.setColorName(colorName);
                color.setUpdatedAt(new Date());
                color.setUpdatedBy("super");
                updateObject(color);

            } else if (table.equals("Service")) {

                String serviceCode = String.valueOf(commonCodeDto.getCode().get("serviceCode"));
                String serviceCatCode = String.valueOf(commonCodeDto.getCode().get("serviceCatCode"));
                String serviceName = String.valueOf(commonCodeDto.getCode().get("serviceName"));
                String servicePrice = String.valueOf(commonCodeDto.getCode().get("servicePrice"));

                com.mit.pawin.entity.Service service = (com.mit.pawin.entity.Service) getObjectByColumnName(table, "serviceCode", serviceCode);
                service.setServiceCatCode(serviceCatCode);
                service.setServiceName(serviceName);
                service.setServicePrice(servicePrice);
                service.setUpdatedAt(new Date());
                service.setUpdatedBy("super");
                updateObject(service);

            } else if (table.equals("Allergy")) {

                String allergyCode = String.valueOf(commonCodeDto.getCode().get("allergyCode"));
                String allergyName = String.valueOf(commonCodeDto.getCode().get("allergyName"));
                String allergyTreatment = String.valueOf(commonCodeDto.getCode().get("allergyTreatment"));

                Allergy allergy = (Allergy) getObjectByColumnName(table, "allergyCode", allergyCode);
                allergy.setAllergyName(allergyName);
                allergy.setAllergyTreatment(allergyTreatment);
                allergy.setUpdatedAt(new Date());
                allergy.setUpdatedBy("super");
                updateObject(allergy);

            } else if (table.equals("MedicalPhysicalDisabilities")) {

                String disableCode = String.valueOf(commonCodeDto.getCode().get("disableCode"));
                String disableName = String.valueOf(commonCodeDto.getCode().get("disableName"));
                String disableTreatment = String.valueOf(commonCodeDto.getCode().get("disableTreatment"));

                MedicalPhysicalDisabilities medicalPhysicalDisabilities = (MedicalPhysicalDisabilities) getObjectByColumnName(table, "disableCode", disableCode);
                medicalPhysicalDisabilities.setDisableName(disableName);
                medicalPhysicalDisabilities.setDisableTreatment(disableTreatment);
                medicalPhysicalDisabilities.setUpdatedAt(new Date());
                medicalPhysicalDisabilities.setUpdatedBy("super");
                updateObject(medicalPhysicalDisabilities);

            } else if (table.equals("Injury")) {

                String injuryCode = String.valueOf(commonCodeDto.getCode().get("injuryCode"));
                String injuryDes = String.valueOf(commonCodeDto.getCode().get("injuryDes"));
                String injuryOccurred = String.valueOf(commonCodeDto.getCode().get("injuryOccurred"));
                String injuryImpact = String.valueOf(commonCodeDto.getCode().get("injuryImpact"));
                String injuryUntil = String.valueOf(commonCodeDto.getCode().get("injuryUntil"));

                Injury injury = (Injury) getObjectByColumnName(table, "injuryCode", injuryCode);
                injury.setInjuryDes(injuryDes);
                injury.setInjuryOccurred(convertStringDateToDate(injuryOccurred));
                injury.setInjuryImpact(injuryImpact);
                injury.setInjuryUntil(convertStringDateToDate(injuryUntil));
                injury.setUpdatedAt(new Date());
                injury.setUpdatedBy("super");
                updateObject(injury);

            } else if (table.equals("Medication")) {

                String medCode = String.valueOf(commonCodeDto.getCode().get("medCode"));
                String medDosage = String.valueOf(commonCodeDto.getCode().get("medDosage"));
                String medName = String.valueOf(commonCodeDto.getCode().get("medName"));
                String medFrequency = String.valueOf(commonCodeDto.getCode().get("medFrequency"));
                String medAM = String.valueOf(commonCodeDto.getCode().get("medAM"));
                String medMid = String.valueOf(commonCodeDto.getCode().get("medMid"));
                String medPM = String.valueOf(commonCodeDto.getCode().get("medPM"));
                String medUntil = String.valueOf(commonCodeDto.getCode().get("medUntil"));

                Medication medication = (Medication) getObjectByColumnName(table, "medCode", medCode);
                medication.setMedDosage(medDosage);
                medication.setMedName(medName);
                medication.setMedFrequency(medFrequency);
                medication.setMedAM(medAM);
                medication.setMedMid(medMid);
                medication.setMedPM(medPM);
                medication.setMedUntil(convertStringDateToDate(medUntil));
                medication.setUpdatedAt(new Date());
                medication.setUpdatedBy("super");
                updateObject(medication);

            } else if (table.equals("EmployeeSchedule")) {

                String empScheduleId = String.valueOf(commonCodeDto.getCode().get("empScheduleId"));
                String startTime = String.valueOf(commonCodeDto.getCode().get("startTime"));
                String endTime = String.valueOf(commonCodeDto.getCode().get("endTime"));

                EmployeeSchedule employeeSchedule = (EmployeeSchedule) getObjectByColumnName(table, "empScheduleId", Long.parseLong(empScheduleId));
                employeeSchedule.setStartTime(startTime);
                employeeSchedule.setEndTime(endTime);
                employeeSchedule.setUpdatedAt(new Date());
                employeeSchedule.setUpdatedBy("super");
                updateObject(employeeSchedule);

            } else if (table.equals("Pet")) {

                String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));
                String petName = String.valueOf(commonCodeDto.getCode().get("petName"));

                Pet pet = (Pet) getObjectByColumnName(table, "petCode", petCode);
                pet.setPetName(petName);
                pet.setUpdatedAt(new Date());
                pet.setUpdatedBy("super");
                updateObject(pet);
            }

            response.put("response", "Ok");
            response.put("message", "Updated " + table + " Record");
            return response;

        } catch (Exception e) {
            response.put("response", "Fail");
            response.put("message", "Error Occurred");
            return response;
        }
    }

    public LinkedHashMap deleteObject(CommonCodeDto commonCodeDto) {

        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();

        String table = String.valueOf(commonCodeDto.getCode().get("table"));

        try {

            if (table.equals("Veterinary")) {

                String vetCode = String.valueOf(commonCodeDto.getCode().get("vetCode"));

                Veterinary veterinary = (Veterinary) getObjectByColumnName(table, "vetCode", vetCode);
                commonDao.deleteObject(veterinary);

            } else if (table.equals("Vaccination")) {

                String vaccCode = String.valueOf(commonCodeDto.getCode().get("vaccCode"));

                Vaccination vaccination = (Vaccination) getObjectByColumnName(table, "vaccCode", vaccCode);
                commonDao.deleteObject(vaccination);


            } else if (table.equals("ServiceDog")) {

                String serviceDogCode = String.valueOf(commonCodeDto.getCode().get("serviceDogCode"));

                ServiceDog serviceDog = (ServiceDog) getObjectByColumnName(table, "serviceDogCode", serviceDogCode);
                commonDao.deleteObject(serviceDog);

            } else if (table.equals("Employee")) {

                String empCode = String.valueOf(commonCodeDto.getCode().get("empCode"));

                Employee employee = (Employee) getObjectByColumnName(table, "empCode", empCode);
                commonDao.deleteObject(employee);

            } else if (table.equals("Retail")) {

                String retailCode = String.valueOf(commonCodeDto.getCode().get("retailCode"));

                Retail retail = (Retail) getObjectByColumnName(table, "retailCode", retailCode);
                commonDao.deleteObject(retail);

            } else if (table.equals("Resources")) {

                String roomCode = String.valueOf(commonCodeDto.getCode().get("roomCode"));

                Resources resources = (Resources) getObjectByColumnName(table, "roomCode", roomCode);
                commonDao.deleteObject(resources);

            } else if (table.equals("Breed")) {

                String breedCode = String.valueOf(commonCodeDto.getCode().get("breedCode"));

                Breed breed = (Breed) getObjectByColumnName(table, "breedCode", breedCode);
                commonDao.deleteObject(breed);

            } else if (table.equals("Color")) {

                String colorCode = String.valueOf(commonCodeDto.getCode().get("colorCode"));

                Color color = (Color) getObjectByColumnName(table, "colorCode", colorCode);
                commonDao.deleteObject(color);

            } else if (table.equals("Service")) {

                String serviceCode = String.valueOf(commonCodeDto.getCode().get("serviceCode"));

                com.mit.pawin.entity.Service service = (com.mit.pawin.entity.Service) getObjectByColumnName(table, "serviceCode", serviceCode);
                commonDao.deleteObject(service);

            } else if (table.equals("Allergy")) {

                String allergyCode = String.valueOf(commonCodeDto.getCode().get("allergyCode"));

                Allergy allergy = (Allergy) getObjectByColumnName(table, "allergyCode", allergyCode);
                commonDao.deleteObject(allergy);

            } else if (table.equals("MedicalPhysicalDisabilities")) {

                String disableCode = String.valueOf(commonCodeDto.getCode().get("disableCode"));

                MedicalPhysicalDisabilities medicalPhysicalDisabilities = (MedicalPhysicalDisabilities) getObjectByColumnName(table, "disableCode", disableCode);
                commonDao.deleteObject(medicalPhysicalDisabilities);

            } else if (table.equals("Injury")) {

                String injuryCode = String.valueOf(commonCodeDto.getCode().get("injuryCode"));

                Injury injury = (Injury) getObjectByColumnName(table, "injuryCode", injuryCode);
                commonDao.deleteObject(injury);

            } else if (table.equals("Medication")) {

                String medCode = String.valueOf(commonCodeDto.getCode().get("medCode"));

                Medication medication = (Medication) getObjectByColumnName(table, "medCode", medCode);
                commonDao.deleteObject(medication);

            } else if (table.equals("EmployeeSchedule")) {

                String empScheduleId = String.valueOf(commonCodeDto.getCode().get("empScheduleId"));

                EmployeeSchedule employeeSchedule = (EmployeeSchedule) getObjectByColumnName(table, "empScheduleId", Long.parseLong(empScheduleId));
                commonDao.deleteObject(employeeSchedule);
            }

            response.put("response", "Ok");
            response.put("message", "Deleted " + table + " Record");
            return response;

        } catch (Exception e) {
            response.put("response", "Fail");
            response.put("message", "Error Occurred");
            return response;
        }
    }

}
