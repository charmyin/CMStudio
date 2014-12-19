package com.charmyin.cmstudio.common.utils;

import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.Test;

public class MD5Util {
    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/*    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("111111"));
        
    }
    
    @Test
	public void encodeSha512(){
		Integer it = new Integer(1024); 
		String str = new Sha512Hash("96E79218965EB72C92A549DD5A330112", "nqwernasdlfuyitlkjasldfkjti:qpOvViSVIY7XyYMpAsJHnQ==",1024).toBase64();
		System.out.println(str);
	}*/
}