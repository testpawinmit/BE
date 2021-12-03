package com.mit.pawin.service;

import com.mit.pawin.dto.CommonCodeDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface CommonService {

    /**
     * 1
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public Object addObject(Object obj, String methodAction);

    /**
     * 2
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public Object getObjectByColumnName(String tblName, String colName, Object value, String methodAction);

    /**
     * 3
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public List<Object> getObjectListByColumnName(String tblName, String colName, Object value, String methodAction);

    /**
     * 4
     *
     * @param obj
     * @param methodAction
     * @return
     */
    public boolean updateObject(Object obj, String methodAction);

    /**
     * 5
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public Object checkTableEmpty(String tblName, String methodAction);

    /**
     * 6
     *
     * @param tblName
     * @param colName
     * @param methodAction
     * @return
     */
    public long getLastRecordId(String tblName, String colName, String methodAction);

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
    public int getRecordCount(String tblName, String colName1, String colName2, Object value1, Object value2, String methodAction);

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
    public Object getRecordCountExceptOne(String tblName, String colName1, String colName2, Object value1, String value2, String methodAction);

    /**
     * 9
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public int deleteRecords(String tblName, String colName, String value, String methodAction);

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
    public int updateRecords(String tblName, String colName1, String colName2, Object value1, String value2, String methodAction);

    /**
     * 11
     *
     * @param tblName
     * @param colName
     * @param date
     * @param methodAction
     * @return
     */
    public List<Object> getObjectsBeforeDate(String tblName, String colName, Date date, String methodAction);

    /**
     * 12
     *
     * @param fullEntity
     * @param entity
     * @param column
     * @param methodAction
     * @return
     */
    public long generateId(String fullEntity, String entity, String column, String methodAction);

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
    public LinkedHashMap updateSingleDataOfRecords(CommonCodeDto commonCodeDto, String response1, String response2, String tblName, String methodAction);

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
    public int updateMultiRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String colName2, String value2, String methodAction);

    /**
     * 15
     *
     * @param tblName
     * @param colList
     * @param valueList
     * @param methodAction
     * @return
     */
    public List searchRecords(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction);

    /**
     * 16
     *
     * @param tblName
     * @param methodAction
     * @return
     */
    public List getAllObjects(String tblName, String methodAction);

    /**
     * 17
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public List<Object> getRecordsNotEqualColumn(String tblName, String colName, String value, String methodAction);

    /**
     * 18
     *
     * @param tblName
     * @param colName
     * @param value
     * @param methodAction
     * @return
     */
    public Object getRecordCountFromSingleValue(String tblName, String colName, Object value, String methodAction);

    /**
     * 19
     *
     */
    public void checkDbConnection();

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
    public List<Object> getAllObjectsInDateRange(Date fromDate, Date toDate, String tblName, String colName, String methodAction);

    public Object addObjectList(ArrayList<Object> objects, String methodAction);

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList, String methodAction);

    public Object updateObjectList(ArrayList<Object> objects, String methodAction);

    public void modifyArrList();

    public double distance(double lat1, double lat2, double lon1, double lon2);

    public void calculateDistance();

    public List<Object> getAllObjectsOfOneColumn(String tblName, String colName, String methodAction);

    public int getFullCount(String tblName, String colName, String methodAction);

    public Object getObjectByColumnName(String tblName, String colName, Object value);

    public Object addObject(Object obj);

    public boolean updateObject(Object obj);

    public Object searchRecord(String tblName, ArrayList<String> colList, ArrayList<Object> valueList);

    public List searchDistinctRecords(String tblName, String colName);

    public List<Object> getObjectListByDateAndStatus(String tblName, String colName1, Object value1, String colName2, Object value2, String methodAction);

    public List<Object> searchRecordByColumnsOr(String tblName, ArrayList<String> colList, ArrayList<Object> valueList);

    public List<Object> searchRecordList(String tblName, ArrayList<String> colList, ArrayList<Object> valueList);

    public List searchRecordByName(String tblName, String colName, String value);

    public LinkedHashMap updateObject(CommonCodeDto commonCodeDto);

    public LinkedHashMap deleteObject(CommonCodeDto commonCodeDto);

}
