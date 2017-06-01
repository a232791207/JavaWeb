package com.sanker.service.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	private final static Log logger = LogFactory.getLog(FileUtil.class);
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	InputStreamReader reader;

	/**
	 * 生成数据文件
	 * 
	 * @param filePath
	 *            写入文件的路径
	 * @param content
	 *            写入的字符串内容
	 * @return
	 */
	public boolean string2File(String content, String filePath) {
		boolean flag = true;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			bufferedReader = new BufferedReader(new StringReader(content));
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			char buffer[] = new char[1024];
			int len;
			while ((len = bufferedReader.read(buffer)) != -1) {
				bufferedWriter.write(buffer, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (IOException e) {
			logger.error(e);
			flag = false;
			return flag;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		return flag;
	}

	/**
	 * 读取数据文件
	 * 
	 * @param filePath
	 *            读取的文件路径
	 * @param encoding
	 *            读取后的字符串编码集设置
	 * @return
	 */
	public String file2String(String filePath, String encoding) {
		StringWriter writer = new StringWriter();
		File file = new File(filePath);
		try {
			if (encoding == null || "".equals(encoding.trim())) {
				reader = new InputStreamReader(new FileInputStream(file));
			} else {
				reader = new InputStreamReader(new FileInputStream(file), encoding);
			}
			// 将输入流写入输出流
			char[] buffer = new char[1024];
			int n = 0;
			while (-1 != (n = reader.read(buffer))) {
				writer.write(buffer, 0, n);
			}
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e);
				}
		}
		return writer.toString();
	}

	public static void main(String[] args) {
		new FileUtil().string2File("测试文件写入中文，TEST STRING TO FILE", "F:/c.txt");
		System.out.println(new FileUtil().file2String("F:/c.txt", null));
		System.out.println(new FileUtil().file2String("F:/c.txt", "GBK"));
	}
}