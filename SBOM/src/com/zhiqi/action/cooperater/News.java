package com.zhiqi.action.cooperater;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zhiqi.bean.NewsInfo;
import com.zhiqi.service.NewsInfoService;

public class News extends ActionSupport implements RequestAware{
	private static final long serialVersionUID = 1L;

	private File file;
	private String fileFileName;
	private String fileContentType;
	private String comment;
	
	private Map request;
	private NewsInfoService newsInfoService;
	
	public String uploadADS(){
		if(fileFileName.endsWith(".png")){
			try {
                String realPath = ServletActionContext.getServletContext().getRealPath("/images/ads/tmp");
                // 创建一个文件
                File diskFile = new File(realPath + File.separator +"ads"+fileFileName.substring(fileFileName.length()-4));
                // 文件上传,使用FileUtils工具类
                FileUtils.copyFile(file, diskFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(6);
			comment = comment.replaceAll("\n", "<br />");
			comment = comment.replaceAll("\r", "<br />");
			newsInfo.setComment(comment);
			newsInfo.setState((short) 0);
			newsInfoService.update(newsInfo);
			request.put("info", "已提交审核！");
		}else{
			request.put("info", "您上传的图片格式有误！");
		}
		return "ads";
	}
	
	public String upload(){
		if(fileFileName.endsWith(".png")){
			try {
                String realPath = ServletActionContext.getServletContext().getRealPath("/images/news/tmp");
                // 创建一个文件
                File diskFile = new File(realPath + File.separator +"news"+fileFileName.substring(fileFileName.length()-4));
                // 文件上传,使用FileUtils工具类
                FileUtils.copyFile(file, diskFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setId(2);
			comment = comment.replaceAll("\n", "<br />");
			comment = comment.replaceAll("\r", "<br />");
			newsInfo.setComment(comment);
			newsInfo.setState((short) 0);
			newsInfoService.update(newsInfo);
			request.put("info", "已提交审核！");
		}else{
			request.put("info", "您上传的图片格式有误！");
		}
		return "news";
	}
	
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Map getRequest() {
		return request;
	}

	public NewsInfoService getNewsInfoService() {
		return newsInfoService;
	}

	public void setNewsInfoService(NewsInfoService newsInfoService) {
		this.newsInfoService = newsInfoService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
