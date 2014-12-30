package com.charmyin.cmstudio.demos.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/fileio")
public class FileDownloadController {
	
	@Value("#{sysproperties['filepath']}")
    private String filePath;
	
	@RequestMapping(value = "/img", method = RequestMethod.GET)
	public void downloadImage(HttpServletResponse response, String fileName) throws IOException {
		File file = new File(filePath+fileName);
		InputStream inputStream = new FileInputStream(file);
 
		try {
			String mimeType = Files.probeContentType(FileSystems.getDefault().getPath(filePath+fileName));
			response.setContentType(mimeType);
			IOUtils.copy(inputStream, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
