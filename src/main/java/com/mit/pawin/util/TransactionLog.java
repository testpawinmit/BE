package com.mit.pawin.util;

import com.mit.pawin.config.CustomJwtAuthenticationFilter;
import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dto.PrefixDto;
import com.mit.pawin.entity.TransactionLogCount;
import com.mit.pawin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class TransactionLog extends CommonUtil{

    @Value("${cba.hris.tranaction.log}")
    private String trnsLog;

    @Autowired
    private PrefixDto prefixDto;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Value("${cba.hris.tranaction.log.key}")
    private String trnsLogKey;

    private static final Logger log = LoggerFactory.getLogger(TransactionLog.class);

    public void write(String message){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String username = message.split("\\|")[0].replace(" ", "");

            String fileName = prefixDto.getTransLog() + username + "_" + simpleDateFormat.format(new Date());
            String encryFileName = AES.encrypt(fileName, trnsLogKey).replace("/", "");
            String encryMessage = AES.encrypt(message, trnsLogKey);

            writeFileWithAppend(trnsLog + encryFileName + ".cba", encryMessage);

            ArrayList<String> col = new ArrayList<>();
            col.add("username");
            col.add("logDate");

            ArrayList<Object> val = new ArrayList<>();
            val.add(username);
            val.add(convertDateToString(new Date()));

            Object objTrnsLogCount = commonService.searchRecord("TransactionLogCount", col, val);

            if(null == objTrnsLogCount){
                TransactionLogCount transactionLogCount = new TransactionLogCount();
                transactionLogCount.setUsername(username);
                transactionLogCount.setFileName(encryFileName + ".cba");
                transactionLogCount.setRecordCount(1);
                transactionLogCount.setLogDate(convertDateToString(new Date()));

                Object objTrnsLogCountSave = commonService.addObject(transactionLogCount);
            }else {
                TransactionLogCount transactionLogCount = (TransactionLogCount) objTrnsLogCount;
                int newCount = transactionLogCount.getRecordCount() + 1;
                transactionLogCount.setRecordCount(newCount);

                Object objTrnsLogCountUpdate = commonService.updateObject(transactionLogCount);
            }

        }catch (Exception e){

            log.info("Exception {}", e.getMessage());

        }
    }
}
