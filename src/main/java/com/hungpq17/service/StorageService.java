package com.hungpq17.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void delete(String storageFileName) throws IOException;

	Path load(String fileName);

	Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storeFileName);

	String getStoragedFilename(MultipartFile file, String id);

}
