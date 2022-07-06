package com.hungpq17.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hungpq17.config.StorageProperties;
import com.hungpq17.exception.StorageException;
import com.hungpq17.exception.StorageFileNotFoundException;
import com.hungpq17.service.StorageService;

@Service
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path ROOT_LOCATION;

	@Override
	public String getStoragedFilename(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}

	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.ROOT_LOCATION = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String storeFileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file");
			}

			Path destinationFile = this.ROOT_LOCATION.resolve(Paths.get(storeFileName)).normalize().toAbsolutePath();

			if (!destinationFile.getParent().equals(this.ROOT_LOCATION.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside curent directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file", e);
		}
	}

	@Override
	public Resource loadAsResource(String fileName) {
		try {
			Path file = load(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundException("Could not read file: " + fileName);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file: " + fileName);
		}
	}

	@Override
	public Path load(String fileName) {
		return ROOT_LOCATION.resolve(fileName);
	}

	@Override
	public void delete(String storageFileName) throws IOException {
		Path destinationFile = ROOT_LOCATION.resolve(Paths.get(storageFileName).normalize().toAbsolutePath());
		Files.delete(destinationFile);
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(ROOT_LOCATION);
			System.out.println(ROOT_LOCATION.toString());
		} catch (Exception e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}
