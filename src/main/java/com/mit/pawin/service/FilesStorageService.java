package com.mit.pawin.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    public void save(MultipartFile file, String fileName);
}
