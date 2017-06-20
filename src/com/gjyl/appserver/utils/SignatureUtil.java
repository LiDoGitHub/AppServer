package com.gjyl.appserver.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class SignatureUtil {
	private static final String APPKEY = "7c2a0e6211914830a77efa41";
	private static final String SECRET = "23df288a081ee007a9accebe";
	private static String RANDOM_STR = "abcdefghijklmnopq0123456789";
	
	public static   Map<String,Object>  getSignature(){
		 long  timestamp = new Date().getTime();
		 String random_str = getRandomString(35);
		 String signature =  MD5Utils.md5("appkey="+APPKEY+"&timestamp="+timestamp+"&random_str="+random_str +"&key="+SECRET);
		 Map<String,Object> result = new HashMap<>();
		 result.put("timestamp", timestamp);
		 result.put("signature", signature);
		 result.put("random_str", random_str);
		 result.put("appkey", APPKEY);
		 return result;
	}
	
		private static   int getRandom(int count) {
			return (int) Math.round(Math.random() * (count));
		}

		public static  String getRandomString(int length){
			StringBuffer sb = new StringBuffer();
			int len = RANDOM_STR.length();
			for (int i = 0; i < length; i++) {
				sb.append(RANDOM_STR.charAt(getRandom(len-1)));
			}
			return sb.toString();
		}
    

}
