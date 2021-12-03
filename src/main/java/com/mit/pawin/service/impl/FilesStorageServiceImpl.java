package com.mit.pawin.service.impl;

import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.service.FilesStorageService;
import com.mit.pawin.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FilesStorageServiceImpl extends CommonUtil implements FilesStorageService {

    @Value("${mit.pawin.profile.img.save}")
    private String profImgTarget;

    private static final Logger log = LoggerFactory.getLogger(FilesStorageServiceImpl.class);

    /**
     * 1
     *
     * @param file
     * @param fileName
     */
    @Override
    public void save(MultipartFile file, String fileName) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" +" { file={}, fileName={}}", file.getName(), fileName);

        try {

            File file1 = new File(profImgTarget + "/" + fileName + ".jpg");

            file1.createNewFile();

            file.transferTo(file1);

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
