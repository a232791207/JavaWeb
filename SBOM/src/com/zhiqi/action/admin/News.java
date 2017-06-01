package com.zhiqi.action.admin;

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
                String realPath = ServletActionContext.getServletContext().getRealPath("/images/ads");
                // 创建一个文件
                File diskFile1 = new File(realPath + File.separator +"ads1"+fileFileName.substring(fileFileName.length()-4));

                File diskFile2 = new File(realPath + File.separator +"ads2"+fileFileName.substring(fileFileName.length()-4));

                File diskFile3 = new File(realPath + File.separator +"ads3"+fileFileName.substring(fileFileName.length()-4));
                // 文件上传,使用FileUtils工具类
                FileUtils.copyFile(diskFile2, diskFile3);
                FileUtils.copyFile(diskFile1, diskFile2);
                FileUtils.copyFile(file, diskFile1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			NewsInfo newsInfo3 = newsInfoService.getNewsInfo(5);
			NewsInfo newsInfo2 = newsInfoService.getNewsInfo(4);
			NewsInfo newsInfo1 = newsInfoService.getNewsInfo(3);
			
			newsInfo3.setComment(newsInfo2.getComment());
			newsInfo2.setComment(newsInfo1.getComment());
			
			comment = comment.replaceAll("\n", "<br />");
			comment = comment.replaceAll("\r", "<br />");
			newsInfo1.setComment(comment);
			newsInfoService.update(newsInfo1);
			newsInfoService.update(newsInfo2);
			newsInfoService.update(newsInfo3);
			request.put("info", "已发布！");
		}else{
			request.put("info", "您上传的图片格式有误！");
		}
		return "ads";
	}
	
	public String upload(){
		if(fileFileName.endsWith(".png")){
			try {
                String realPath = ServletActionContext.getServletContext().getRealPath("/images/news");
                // 创建一个文件
                File diskFile = new File(realPath + File.separator +"news"+fileFileName.substring(fileFileName.length()-4));
                // 文件上传,使用FileUtils工具类
                FileUtils.copyFile(file, diskFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			NewsInfo newsInfo = newsInfoService.getNewsInfo(1);
			comment = comment.replaceAll("\n", "<br />");
			comment = comment.replaceAll("\r", "<br />");
			newsInfo.setComment(comment);
			newsInfoService.update(newsInfo);
			request.put("info", "已发布！");
		}else{
			request.put("info", "您上传的图片格式有误！");
		}
		return "news";
	}
	
	@Override
	public void setRequest(Map request) {
		this.request = request;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Map getRequest() {
		return request;
	}
	
}
