package com.charmyin.cmstudio.common.utils;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

public class GetFilePath {	
	public static String getFilePath(HttpServletRequest request){
		
		File file= new File(request.getServletContext().getRealPath("/") + "resources" + File.separator + "download");
		if(!file .exists()  || !file .isDirectory()){
			file.mkdir();
		}
		
		
//		request.getServletContext().getRealPath("/");
		
//		System.out.println("**************************************************"+request.getServletContext().getRealPath("/"));
		return request.getServletContext().getRealPath("/") + File.separator + "resources" + File.separator + "download";
	}
}
