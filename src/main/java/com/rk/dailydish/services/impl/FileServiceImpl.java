package com.rk.dailydish.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rk.dailydish.services.FileService;

@Service
public class FileServiceImpl implements FileService {
	

	@Override
	public String uploadImage(String path, MultipartFile file) {
		// TODO Auto-generated method stub

		
		// file name
		String filename = file.getOriginalFilename();

		// createing full path
		String filePath = path + File.separator + filename;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		
	    // Check if file already exists
	    File existingFile = new File(filePath);
	    if (existingFile.exists()) {
	        return filename; // Return the existing filename without overwriting
	    }

		// file copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filename;
	}

}
