package com.gjyl.appserver.utils;

/**
 * 产生发送短信时,需要使用的验证码
 * @author LiD
 *
 */
public class MsgCodeUtils {
	
	/**
	 * 获取随机6位验证码
	 * @return
	 */
	public static String RandomCode() {
		StringBuffer str = new StringBuffer();
        str.append((int)(Math.random()*9+1));
        for(int i = 0; i < 5; i++){
            str.append((int)(Math.random()*10));
        }
		return str.toString();
	}
}
