package com.lhx.util;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ReadFile {

	/**
	 * 读取指定路径xml配置文件
	 * @param fileUrl 文件路径
	 * @author Seven	time:2015-10-20
	 */
	public static Element readXml(String fileUrl) {
		try {
			File f = new File(fileUrl);
			
			if (f.exists()) {
				SAXReader reader = new SAXReader();
				Document doc;
				doc = reader.read(f);
				Element root = doc.getRootElement();
				Element data;
				Iterator<?> itr = root.elementIterator("VALUE");
				data = (Element) itr.next();
				return data;
			}
			
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	/**
	 * 获取xml配置文件里面的某个属性值
	 * @param fileUrl 文件路径
	 * @param paramsName  获取值的属性名称
	 * @author Seven	time:2015-10-20
	 */
	public static String readXml(String fileUrl, String paramsName) {
		try {
			File f = new File(fileUrl);
			
			if (f.exists()) {
				SAXReader reader = new SAXReader();
				Document doc;
				doc = reader.read(f);
				Element root = doc.getRootElement();
				Element data;
				Iterator<?> itr = root.elementIterator("VALUE");
				data = (Element) itr.next();
				String value = data.elementText(paramsName).trim();
				return value;
			}
			
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
