package com.thairshop.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thairshop.demo.service.UploadService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class UploadController {
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	ServletContext context;
	
	@PostMapping("/files/{folder}")
	public JsonNode upload(@PathParam("files") MultipartFile files,@PathVariable("folder") String folder) {
		File saveFile = uploadService.save(files,folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", saveFile.getName());
		node.put("size", saveFile.length());
		return node;
	} 
	
	@GetMapping("/files/{folder}/{file}")
	public byte[] dowload(@PathVariable("folder") String folder, @PathVariable("file") String file,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "attachment;filename=" + file);
		return uploadService.read(folder,file);
	}
	
	@GetMapping("/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder){
		return uploadService.list(folder);
	}
	
	@GetMapping("/getImage")
	public ResponseEntity<List<String>> getImages(){
		List<String> images = new ArrayList<String>();
		String filePath = context.getRealPath("/assets/image");
		File fileFoder = new File(filePath);
		if(fileFoder != null) {
			for(final File file: fileFoder.listFiles()) {
				if(!file.isDirectory()) {
					String encode = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream input = new FileInputStream(file);
						byte[] bytes = new byte[(int)file.length()];
						input.read(bytes);
						encode = Base64.getEncoder().encodeToString(bytes);
						images.add("data:image/"+extension+";base64,"+encode);
						input.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
}
