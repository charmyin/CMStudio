package com.charmyin.cmstudio.basic.authorize.jcaptcha;

import java.awt.Color;
import java.awt.Font;

import org.junit.Test;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.SimpleTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.DictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

/**
 * Jcaptcha test
 * @author YinCM
 * @since 2013-10-3 8:33:15
 */
public class JcaptchaTest {
	
	@Test
	public void testImageEngine(){
		
	    String test = "<img src=\"/house365-klf-web/attached/image/20150728/20150728201819_546.jpg\" alt=\"\" />";
	    String stt = test.replaceAll("img src=\"/", "img src=\"https://www.baidu.com/");
	    System.out.println(stt);
	    
		/*Color colorBlack = new Color(0, 0, 0);
		SimpleTextPaster simpleWhitePaster = new SimpleTextPaster(3, 5, colorBlack);
		UniColorBackgroundGenerator backGenUni = new UniColorBackgroundGenerator(300, 100);
		Font fontArial = new Font("Arial", 0, 10);
		RandomFontGenerator fontGenRandom = new RandomFontGenerator(40, 50, new Font[]{fontArial});
		ComposedWordToImage wordtoimage = new ComposedWordToImage(fontGenRandom,backGenUni,simpleWhitePaster);
		FileDictionary filedict = new FileDictionary("toddlist");
		DictionaryWordGenerator wordgen = new DictionaryWordGenerator(filedict);
		CaptchaFactory cf = new GimpyFactory(wordgen,wordtoimage);
		CaptchaFactory[] cfa = new CaptchaFactory[]{cf};
		GenericCaptchaEngine imageEngine = new GenericCaptchaEngine(cfa);*/
		
		
		//GenericManageableCaptchaService captchaService = new GenericManageableCaptchaService(imageEngine, 180, 1800,1);
	}
	
	
	/*
 

 
 

 <bean id="colorGreen" class="java.awt.Color" >
    <constructor-arg index="0"><value>0</value></constructor-arg>
    <constructor-arg index="1"><value>255</value></constructor-arg>
    <constructor-arg index="2"><value>0</value></constructor-arg>
</bean>

<bean id="colorBlack" class="java.awt.Color" >
    <constructor-arg index="0"><value>0</value></constructor-arg>
    <constructor-arg index="1"><value>0</value></constructor-arg>
    <constructor-arg index="2"><value>0</value></constructor-arg>
</bean>

<bean id="captchaService" class="com.octo.captcha.service.multitype.GenericManageableCaptchaService">
    <constructor-arg index="0"><ref bean="imageEngine"/></constructor-arg>
    <constructor-arg index="1"><value>180</value></constructor-arg>
    <constructor-arg index="2"><value>180000</value></constructor-arg>
</bean>*/
}
