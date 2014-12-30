package com.charmyin.cmstudio.demos.loadproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="demos/properties")
public class LoadPropertiesController {
	@Value("#{sysproperties['filepath']}")
    private String filePath;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "The loaded properties is " + filePath;
    }
	
}
