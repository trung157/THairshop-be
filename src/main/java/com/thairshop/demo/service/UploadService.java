package com.thairshop.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
	
	@Autowired
	ServletContext servletContext;
	
	private Path getPath(String folder,String filename) {
		File dir = Paths.get(servletContext.getRealPath("/assets/"), folder).toFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return Paths.get(dir.getAbsolutePath(), filename);
	}
	
	public File save(MultipartFile file,String folder) {
		try {
			String s = System.currentTimeMillis() + file.getOriginalFilename();
			String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
			Path path = this.getPath(folder,name);
			file.transferTo(path);
			File file2 = new File(path.toString());
			
			return file2;
		} catch (

		Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public byte[] read(String folder, String filename) {
		try {
			Path path = this.getPath(folder,filename);
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<String> list(String folder) {
		List<String> filename = new ArrayList<String>();
		File dir = Paths.get(servletContext.getRealPath("/assets/"), folder).toFile();
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				filename.add(file.getName());
			}
		}
		return filename;
	}
	
}
