package com.leo.boot.updown.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UpdownController {
	
	@Value("${folder}")
    private String folder;
    
    @GetMapping("/index")
    public List<?> index() throws IOException {
    	File file = new File(folder);
    	if (!file.exists()) {
    		file.mkdirs();
		}
    	List<String> list = Arrays.stream(file.listFiles())
    			.filter(File::isFile)
    			.map(File::getName)
    			.collect(Collectors.toList());
        return list;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, file.getBytes());
        return "success";
    }
    
    @GetMapping("/download")
    public void download(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String encodeName = URLEncoder.encode(fileName, "UTF-8");
    	response.addHeader("Content-Disposition", "attachment;fileName=" + encodeName+";fileName*=utf-8''" + encodeName);// 设置文件名
        File file = new File(folder+fileName);
        Files.copy(file.toPath(), response.getOutputStream());
    }

}