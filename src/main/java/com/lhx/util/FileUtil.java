package com.lhx.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lhx.util.FileUtil.FileTypeEnum;

/**
 * @Title : 文件处理工具类
 * @Description : TODO
 * @author : Seven
 * @date : 2017年12月7日
 */
public class FileUtil {

	/**
	 * @Description : 获取网络文件类型
	 * @postscript : 
	 * @author : Seven
	 * @date : 2017年12月7日
	 * @param fileUrl 文件地址
	 * @return	Mime Type
	 */
	@SuppressWarnings("finally")
	public static List<Map<String, String>> networkFileType(String fileUrl) throws Exception {
		List<Map<String, String>> fileType = null;
		try {
			BufferedInputStream bis = null;
			HttpURLConnection urlconnection = null;
			URL url = null;
			url = new URL(fileUrl);
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.connect();
			bis = new BufferedInputStream(urlconnection.getInputStream());
			String mimeType = urlconnection.getContentType();
			if(mimeType == null) {
				mimeType = HttpURLConnection.guessContentTypeFromStream(bis);
			}
			if(mimeType != null && mimeType != "") {
				fileType = FileTypeEnum.getSuffixByType(mimeType);
			}
//			System.out.println("fileType:" + fileType);
//			System.out.println("guessContentTypeFromStream():" + HttpURLConnection.guessContentTypeFromStream(bis));
//			System.out.println("getContentType():" + urlconnection.getContentType());
//			System.out.println("getHeaderFields():" + urlconnection.getHeaderFields().toString());
//			System.out.println("getHeaderField(6):" + urlconnection.getHeaderField(6));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return fileType;
		}
	}
	
	/**
	 * @Description : 下载文件（待完善）
	 * @postscript : 
	 * @author : Seven
	 * @date : 2017年12月7日
	 * @param urlString	文件地址
	 * @param filename	下载后文件名称
	 * @param savePath	保存路径
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static String downloadFile(String urlString, String filename, String savePath) {
		InputStream is = null;
		OutputStream os = null;
		String rmsg = "SUCCESS";
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为
			con.setConnectTimeout(5 * 10000);
			// 输入流
			is = con.getInputStream();
	
			// 的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			os = new FileOutputStream(sf.getPath() + "\\" + filename);
			
			//获取文件大小
//			int fileLength = con.getContentLength();
//			int progressLen = 0;
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
				
				// 打印下载百分比
//				progressLen += len;
//				System.out.println("下载了-------> " + progressLen * 100 / fileLength + "%\n");
			}
		} catch(Exception e) {
			e.printStackTrace();
			rmsg = "ERROR";
		} finally {
			// 完毕，关闭所有链接
			try {
				if(is != null) {
					is.close();
				}
				if(os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rmsg = "IO not close";
			}
			return rmsg;
		}
	}
	
	/**
	 * @Description : 文件类型枚举
	 * @postscript : 
	 * @author : Seven
	 * @date : 2017年12月7日
	 * @return
	 */
	public static enum FileTypeEnum {
		TXT(".txt", "text/plain"),
		PROP(".prop", "text/plain"),
		SH(".sh", "text/plain"),
		RC(".rc", "text/plain"),
		//(".xml", "text/xml"),
		XML(".xml", "text/plain"),
		JAVA(".java", "text/plain"),
		LOG(".log", "text/plain"),
		C(".c",  "text/plain"),
		CONF(".conf", "text/plain"),
		CPP(".cpp", "text/plain"),
		H(".h", "text/plain"),
		DOC(".doc", "application/msword"),
		BMP(".bmp", "image/bmp"),
		GIF(".gif", "image/gif"),
		PNG(".png", "image/png"),
		JPG(".jpg", "image/jpeg"),
		JPEG(".jpeg", "image/jpeg"),
		WEBP(".webp", "image/webp"),
		GTAR(".gtar", "application/x-gtar"),
		GZ(".gz", "application/x-gzip"),
		HTML(".html", "text/html"),
		HTM(".htm", "text/html"),
		JAR(".jar", "application/java-archive"),
		JS(".js", "application/x-javascript"),
		MTHREEU(".m3u", "audio/x-mpegurl"),
		MFA(".m4a", "audio/x-m4a"),
		MFOURA(".m4a", "audio/mp4a-latm"),
		MFOURB(".m4b", "audio/mp4a-latm"),
		MFOURP(".m4p", "audio/mp4a-latm"),
		WAV(".wav", "audio/x-wav"),
		WMA(".wma", "audio/x-ms-wma"),
		WMV(".wmv", "audio/x-ms-wmv"),
		MP3(".mp3", "audio/mp3"),
		MPTHREE(".mp3", "audio/x-mpeg"),
		MPTWO(".mp2", "audio/x-mpeg"),		
		OGG(".ogg", "audio/ogg"),
		MPGA(".mpga", "audio/mpeg"),
		MPFOUR(".mp4", "video/mp4"),
		THREEGP(".3gp", "video/3gpp"),
		MFOURU(".m4u", "video/vnd.mpegurl"),
		MFOURV(".m4v", "video/x-m4v"),
		RMVB(".rmvb", "audio/x-pn-realaudio"),
		ASF(".asf", "video/x-ms-asf"),
		AVI(".avi", "video/x-msvideo"),
		MOV(".mov", "video/quicktime"),
		MPE(".mpe", "video/mpeg"), 
		MPEG(".mpeg", "video/mpeg"), 
		MPG(".mpg", "video/mpeg"), 
		MPGFOUR(".mpg4", "video/mp4"), 
		APK(".apk", "application/vnd.android.package-archive"),
		EXE(".exe", "application/octet-stream"),
		BIN(".bin", "application/octet-stream"),
		CLASS(".class", "application/octet-stream"),
		MPC(".mpc", "application/vnd.mpohun.certificate"), 
		MSG(".msg", "application/vnd.ms-outlook"),
		PDF(".pdf", "application/pdf"),
		PPS(".pps", "application/vnd.ms-powerpoint"),
		PPT(".ppt", "application/vnd.ms-powerpoint"),
		RAR(".rar", "application/x-rar-compressed"),
		RTF(".rtf", "application/rtf"),
		TAR(".tar", "application/x-tar"), 
		TGZ(".tgz", "application/x-compressed"), 
		WPS(".wps", "application/vnd.ms-works"),
		Z(".z", "application/x-compress"),
		ZIP(".zip", "application/zip"),
		SWF(".swf", "application/x-shockwave-flash"),
		CAB(".cab", "application/x-shockwave-flash"),
		FLV(".flv", "flv-application/octet-stream"),
		EMPTY("", "*/*");
		
		private String suffix;
		private String type;
		FileTypeEnum(String suffix, String type) {
			this.suffix = suffix;
			this.type = type;
		}
		
		public String getSuffix() {
			return suffix;
		}

		public String getType() {
			return type;
		}

		public static List<Map<String, String>> getSuffixByType(String typeStr) {
			List<Map<String, String>> typeArray = new ArrayList<Map<String,String>>();
			if(null != typeStr && "" != typeStr) {
				Map<String, String> typeInfo;
				typeStr = typeStr.toLowerCase();
				for(FileTypeEnum typeEnum : FileTypeEnum.values()) {
					if(typeEnum.type.equals(typeStr)) {
						typeInfo = new HashMap<String, String>();
						typeInfo.put("name", typeEnum.name());
						typeInfo.put("suffix", typeEnum.suffix);
						typeInfo.put("type", typeEnum.type);
						typeArray.add(typeInfo);
					}
				}
			}
			return typeArray;
		}
		
		public static List<Map<String, String>> getEnumListBySuffix(String typeStr) {
			List<Map<String, String>> typeArray = new ArrayList<Map<String, String>>();
			if(null != typeStr && "" != typeStr) {
				Map<String, String> typeInfo;
				typeStr = typeStr.toLowerCase();
				for(FileTypeEnum typeEnum : FileTypeEnum.values()) {
					if(typeEnum.suffix.equals(typeStr)) {
						typeInfo = new HashMap<String, String>();
						typeInfo.put("name", typeEnum.name());
						typeInfo.put("suffix", typeEnum.suffix);
						typeInfo.put("type", typeEnum.type);
						typeArray.add(typeInfo);
					}
				}
			}
			return typeArray;
		}
		
		public static FileTypeEnum getValueOf(Object ordinal) {
			int num = Integer.valueOf(ordinal.toString());
			return FileTypeEnum.values()[num];
		}
	}
}
