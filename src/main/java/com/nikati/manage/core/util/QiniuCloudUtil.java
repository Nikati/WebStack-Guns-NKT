package com.nikati.manage.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 
 *@Title QiniuCloudUtil.java
 *@description 七牛云工具类
 *@time 2019年12月22日 下午5:44:33
 *@author Nikati
 *@version 1.0
*
 */
public class QiniuCloudUtil {
	public static String accessKey = "";
	public static String secretKey = "";
	public static String bucket = "";
	static Set<String> ps = new HashSet<>();
	static {
		ps.add(".BMP");
		ps.add(".JPG");
		ps.add(".JPEG");
		ps.add(".PNG");
		ps.add(".GIF");
		ps.add(".bmp");
		ps.add(".jpg");
		ps.add(".jpeg");
		ps.add(".png");
		ps.add(".gif");
	}
	public static Object[] deleteSubString(String str1, String str2) {
		StringBuffer sb = new StringBuffer(str1);
		int delCount = 0;
		Object[] obj = new Object[2];
		while (true) {
			int index = sb.indexOf(str2);
			if (index == -1) {
				break;
			}
			sb.delete(index, index + str2.length());
			delCount++;
		}
		if (delCount != 0) {
			obj[0] = sb.toString();
			obj[1] = delCount;
		} else { // 不存在返回-1
			obj[0] = -1;
			obj[1] = -1;
		}
		return obj;
	}

	public static void upload2Qiniu(String filePath, String fileName) {
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Region.region2());
		UploadManager uploadManager = new UploadManager(cfg);
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			Response response = uploadManager.put(filePath, fileName, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		} catch (QiniuException ex) {
			Response r = ex.response;
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
	}

	// 上传文件
	public static String upload2Qiniu(byte[] bytes,String FileEnd) {
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Region.region2());
		// …其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		String string;
		if(ps.contains(FileEnd)) {
			 string = "images/"+UUID.randomUUID().toString()+FileEnd;
		}else {
			string = UUID.randomUUID().toString();
		}
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			Response response = uploadManager.put(bytes, string, upToken);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			return   deleteSubString(putRet.key, "images/")[0].toString();
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				// ignore
			}
		}
		return null;
	}

	// 删除文件
	public static void deleteFileFromQiniu(String fileName) {
		String ff = fileName.substring(33);
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Region.region2());
		String key = ff;
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
			bucketManager.delete(bucket, key);
		} catch (QiniuException ex) {
			// 如果遇到异常，说明删除失败
			System.err.println(ex.code());
			System.err.println(ex.response.toString());
		}
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static byte[] downLoadFromUrl(String urlStr) throws IOException {
		URL url = new URL("http://" + urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		// 得到输入流
		InputStream inputStream = conn.getInputStream();
		// 获取自己数组
		byte[] getData = readInputStream(inputStream);

		if (inputStream != null) {
			inputStream.close();
		}
		System.out.println("info:" + url + " download success");
		return getData;
	}

}