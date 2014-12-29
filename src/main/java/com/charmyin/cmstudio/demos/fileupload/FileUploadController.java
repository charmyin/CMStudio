package com.charmyin.cmstudio.demos.fileupload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/fileupload")
public class FileUploadController {
	 @RequestMapping(value="/upload", method=RequestMethod.GET)
	    public @ResponseBody String provideUploadInfo() {
	        return "You can upload a file by posting to this same URL.";
	    }

	    @RequestMapping(value="/index", method=RequestMethod.GET)
	    public String fileuploadPage() {
	        return "demos/fileupload/index";
	    }
	    
	    @RequestMapping(value="/uploadSingleFile", method=RequestMethod.POST)
	    public @ResponseBody Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file){
	    	Map<String, Object> resultMapResponse = new HashMap<String, Object>();
			resultMapResponse.put("success",false);
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("D:/ttttt.jpg")));
	                stream.write(bytes);               
	                stream.close();
	                resultMapResponse.put("success",true);
	                return resultMapResponse;
	            } catch (Exception e) {
	            	resultMapResponse.put("message", "You failed to upload => " + e.getMessage());
	            	return resultMapResponse;
	                //return "You failed to upload => " + e.getMessage();
	            }
	        } else {
	        	resultMapResponse.put("message","You failed to upload because the file was empty.");
	            return resultMapResponse;
	        }
	    }
	    
	    /**
	     * Upload multiple file using Spring Controller
	     */
	    @RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	    public @ResponseBody
	    String uploadMultipleFileHandler(@RequestParam("name") String[] names,
	            @RequestParam("file") MultipartFile[] files) {
	 
	        if (files.length != names.length)
	            return "Mandatory information missing";
	 
	        String message = "";
	        for (int i = 0; i < files.length; i++) {
	            MultipartFile file = files[i];
	            String name = names[i];
	            try {
	                byte[] bytes = file.getBytes();
	 
	                // Creating the directory to store file
	                String rootPath = System.getProperty("catalina.home");
	                File dir = new File(rootPath + File.separator + "tmpFiles");
	                if (!dir.exists())
	                    dir.mkdirs();
	 
	                // Create the file on server
	                File serverFile = new File(dir.getAbsolutePath()
	                        + File.separator + name);
	                BufferedOutputStream stream = new BufferedOutputStream(
	                        new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();

	 
	                message = message + "You successfully uploaded file=" + name
	                        + "<br />";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        }
	        return message;
	    }
}
