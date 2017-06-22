package com.gjyl.appserver.utils;

import okhttp3.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 众合泰短信服务相关信息
 * @author LiD
 *
 */
public class SMSUtils {

	public static final String USERID="3303";
	public static final String ACCOUNT="giy20160909";
	public static final String PASSWORD="123456";
//	public static final String MANAGERPHONE="18011492792";

	/**
	 * 众合泰,发送短信
	 * @param msgCode
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static Boolean SendMsg(String phone,String content) throws Exception{
		OkHttpClient client = new OkHttpClient();
		MediaType MEDIA_TYPE = MediaType
				.parse("application/x-www-form-urlencoded;charset=utf-8");
		String builder="action=send&userid=" + SMSUtils.USERID
				+ "&account=" + SMSUtils.ACCOUNT
				+ "&password=" + SMSUtils.PASSWORD
				+ "&mobile=" + phone
				+ "&content=" + content
				+ "&sendTime=&extno=";
		Request req = new Request.Builder()
				.url("http://121.43.192.197:8888/sms.aspx")
				.post(RequestBody.create(MEDIA_TYPE, builder))
				.build();
		Response resp = client.newCall(req).execute();
		String result = resp.body().string();
		// 解析返回数据
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(
				result)));
		NodeList returnsms = document.getChildNodes();
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < returnsms.getLength(); i++) {
			Node node = returnsms.item(i);
			NodeList nodeList = node.getChildNodes();
			for (int j = 0; j < nodeList.getLength(); j++) {
				Node nodeValue = nodeList.item(j);
				map.put(nodeValue.getNodeName(), nodeValue.getTextContent());
			}
		}
		return  "ok".equals(map.get("message").toLowerCase());
	}
}
