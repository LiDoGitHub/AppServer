package com.gjyl.appserver.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class FileUploadUtils {

	//图片上传,保存到本地
	public static List<String> uploadImage(HttpServletRequest request) {
		String prePath=getPrePath(request);
		List<String> filePath=new ArrayList<>();
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getServletContext());
		if (resolver.isMultipart(request)) {
			//将request变成多部分request
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			//获取multiRequest 中所有的文件名
			Iterator<String> iter=multiRequest.getFileNames();
			while(iter.hasNext()) {
				//一次遍历所有文件
				MultipartFile file=multiRequest.getFile(iter.next());
				if(!file.isEmpty()) {
					String name = file.getOriginalFilename();
					String filename = getRealName(name);
					// 得到随机名称
					String uuidname = getUUIDFileName(filename);
					// 得到随机目录
					String randomDirectory = getRandomDirectory(filename);
					/*
					if (file.getName().equals("video")){
						request.getServletContext().setAttribute("videoPath",randomDirectory+"/"+uuidname);
					}*/
					//父目录
					String parentPath = request.getServletContext().getRealPath("/images");
					File rd = new File(parentPath, randomDirectory);
					// 注意:随机目录可能不存在，需要创建.
					if (!rd.exists()) {
						rd.mkdirs();
					}
					//上传
					try {
						file.transferTo(new File(rd,uuidname));
						if (file.getName().equals("imgIcon")) {
							filePath.add(0,prePath+randomDirectory+"/"+uuidname);
						}else if (file.getName().equals("imgCover")) {
							filePath.add(1,prePath+randomDirectory+"/"+uuidname);
						}else {
							filePath.add(prePath+randomDirectory+"/"+uuidname);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filePath;
	}

	// 得到上传文件真实名称 c:\a.txt a.txt
	private static String getRealName(String filename) {

		int index = filename.lastIndexOf("\\") + 1;

		return filename.substring(index);

	}

	// 获取随机名称 a.txt
	private static String getUUIDFileName(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {

			return UUID.randomUUID().toString().replace("-", "") + filename.substring(index);
		} else {
			return UUID.randomUUID().toString().replace("-", "");
		}
	}

	// 目录分离算法
	private static String getRandomDirectory(String filename) {

		// int hashcode = filename.hashCode();
		//
		// // System.out.println(hashcode);
		//
		// // int类型数据在内存中占32位。转换成16进制数，就得到8个16进制数
		// String hex = Integer.toHexString(hashcode);
		//
		// // System.out.println(hex); // 056d9363
		//
		// return "/" + hex.charAt(0) + "/" + hex.charAt(1);

		int hashcode = filename.hashCode();

		//System.out.println(Integer.toBinaryString(hashcode));

		int a = hashcode & 0xf;

		hashcode = hashcode >>> 4;

		int b = hashcode & 0xf;

		return "/" + a + "/" + b;
	}

	//图片路径前缀
	private static String getPrePath(HttpServletRequest request) {
		// 拼接可供网络访问的图片地址
		String preName = request.getContextPath();
		StringBuffer sb = new StringBuffer();
		sb.append(request.getScheme() + "://");
		sb.append(request.getServerName() + ":");
		sb.append(request.getServerPort());
		sb.append(preName + "/images");
		return sb.toString();
	}
}
