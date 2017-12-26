package com.bocom.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 解压文件
	 * 
	 * @param filePath
	 */
	public static void unZipFiles(String fileName, String destFilePath,
			boolean isDelete) throws Exception {
		File zip = new File(fileName);
		ZipFile zipFile = new ZipFile(fileName, "UTF-8");
		Enumeration emu = zipFile.getEntries();
		while (emu.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) emu.nextElement();
			if (entry.isDirectory()) {
				File dir = new File(destFilePath + entry.getName());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				continue;
			}
			BufferedInputStream bis = new BufferedInputStream(
					zipFile.getInputStream(entry));

			File file = new File(destFilePath + File.separator
					+ entry.getName());
			File parent = file.getParentFile();
			if (parent != null && (!parent.exists())) {
				parent.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf, 0, 1024)) != -1) {
				fos.write(buf, 0, len);
			}
			bos.flush();
			bos.close();
			bis.close();
		}
		zipFile.close();
		if (isDelete) {
			zip.delete();
		}

	}

	/**
	 * 读取json文件
	 * 
	 * @param jsonFile
	 *            json文件的地址
	 * @return
	 */
	public static String readJsonFile(String jsonFile) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String str = "";
			String str1 = "";
			fis = new FileInputStream(jsonFile);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				str1 += str;
			}
			return str1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 校验部署包或升级包中的文件的完整性
	 * 
	 * @param filePath
	 *            文件路径
	 * @param type
	 *            上级还是部署，1：部署，2升级
	 * @return
	 */

	public static boolean checkFile(String filePath, String type) {
		File file = new File(filePath);
		Map<String, String> map = new HashMap<String, String>();
		map = listFile(file, map);
		List<String> list = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject();
		// 对部署包进行检查
		if (type.equals("1")) {
			// 获取该部署包中的文件名有哪些
			String jsonFilePath = map.get("deploy.json");
			// 判断压缩包中是否含有json文件
			if (null == jsonFilePath) {
				return false;
			} else {
				jsonObject = JSONObject.fromObject(readJsonFile(jsonFilePath));
				// 获取db的文件名
				JSONArray array = jsonObject.getJSONArray("db");
				String dbName = ((JSONObject) array.get(0))
						.getString("fileName");
				if (StringUtils.isNotEmpty(dbName)) {
					list.add(dbName);
				}
				// 获取war包的文件名
				String warName = jsonObject.getJSONObject("app").getString(
						"fileName");
				list.add(warName);
				// 获取logo图片的文件名
				String logo = jsonObject.getJSONObject("app").getString(
						"logoApp");
				list.add(logo);
			}

			for (String str : list) {
				if (null == map.get(str)) {
					return false;
				}
			}
			return true;

		}
		// 对升级包校验
		if (type.equals("2")) {
			// 获取该部署包中的文件名有哪些
			String jsonFilePath = map.get("upgrade.json");
			// 判断压缩包中是否含有json文件
			if (null == jsonFilePath) {
				return false;
			} else {
				jsonObject = JSONObject.fromObject(readJsonFile(jsonFilePath));
				// 获取db的文件名
				JSONArray array = jsonObject.getJSONObject("module")
						.getJSONArray("db");
				String dbName = ((JSONObject) array.get(0))
						.getString("fileName");
				if (StringUtils.isNotEmpty(dbName)) {
					list.add(dbName);
				}
				// 获取war包的文件名
				JSONObject warArray = jsonObject.getJSONObject("module")
						.getJSONObject("app");
				String warName = warArray.getString("fileName");
				if (StringUtils.isNotEmpty(warName)) {
					list.add(warName);
				}

			}
			for (String str : list) {
				if (null == map.get(str)) {
					return false;
				}
			}
			return true;

		}
		return false;
	}

	/**
	 * 遍历出一个文件夹下所有的文件名
	 * 
	 * @param file
	 * @return
	 */
	public static Map<String, String> listFile(File file,
			Map<String, String> map) {
		if (file.isDirectory()) {
			File[] f = file.listFiles();
			for (File f1 : f) {
				if (f1.isDirectory()) {
					listFile(f1, map);
				} else {
					String fileName = f1.getName();
					map.put(fileName, f1.getPath());
				}
			}
		}
		return map;

	}

	// 删除文件夹
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除文件下的所有文件
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	// 获取sql文件的创建的数据空间名称
	public static String loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();
		InputStream sqlFileIn = new FileInputStream(sqlFile);
		try {

			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}
			String[] sqlArr = sqlSb.toString().split(";");
			List<String> list = new ArrayList<String>();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (sql.indexOf("create database") == -1) {
					continue;
				}
				String[] str = sql.split(" ");
				sb.append(str[2] + ",");

			}
			return sb.toString();

		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		} finally {
			sqlFileIn.close();
		}
		
	}

}
