package com.wust.web;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.wust.domain.Price;
import com.wust.factory.BasicFactory;
import com.wust.service.BasicFareService;
import com.wust.service.PriceService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class ImportPriceByXlsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(!ServletFileUpload.isMultipartContent(request)){
			return;
		}	
		//解析
		DiskFileItemFactory Factory = new DiskFileItemFactory();//产生DiskFileItemFactory
		ServletFileUpload sfu = new ServletFileUpload(Factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		/*遍历：
		 *处理上传字段
		 */
		for (FileItem fileItem : items) {
			//处理上传字段
			//文件名 
			if(!fileItem.isFormField()){
				String fileName = fileItem.getName();//可能是整个路径，所以需要截取
				if(fileName!=null){
					fileName = FilenameUtils.getName(fileName);
				}
				//存放位置
				File storePath = new File(getServletContext().getRealPath("/WEB-INF/files"));
				if(!storePath.exists()){
					storePath.mkdirs();
				}
				try {
					fileItem.write(new File(storePath,fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//上传完开始导入到price中
				Workbook book = null;
				try {
					book = Workbook.getWorkbook(new File(storePath,fileName));
					// 获得第一个工作表对象
					Sheet sheet = book.getSheet(0);
					int rows = sheet.getRows();
					int columns = sheet.getColumns();
					// 遍历每行每列的单元格
					// getCell(col,row);没有内容的单元格为""不是null
					Cell cell = sheet.findCell("规格");
					int c = cell.getColumn();
					int r = cell.getRow();
					List<Price> prices = new ArrayList<Price>(); 
					String time = getPriceTime(sheet);
					if(time==""){
						System.out.println("excel表里没有时间");
						return;
					}
					time=time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8);
					for (int i = r + 1; i < rows; i++) {
						String format = sheet.getCell(c, i).getContents().trim();
						String level = sheet.getCell(c + 1, i).getContents().trim();
						for (int j = c + 2; j < columns; j++) {
							if (getNumbers(sheet.getCell(j, i).getContents()) != "") {
								// 插入表格
								String area = sheet.getCell(j, r).getContents().trim();
								double d = Double.parseDouble(getNumbers(sheet
										.getCell(j, i).getContents()));
								Price price = new Price(time,area,format,level,d,0+"");
								prices.add(price);
							}
						}
					}
					
					PriceService service = BasicFactory.getFactory().getInstance(PriceService.class);
					
					service.batchAdd(prices);
					request.setAttribute("success", "导入成功!");
					request.getRequestDispatcher("/servlet/SelectPriceServlet?thispage=1&area=&format=&level=").forward(request, response);
					// String.format("姓名：%s, 年龄：%s", name ,age);
				} catch (Exception e) {
					System.out.println(e);
				} finally {
					if (book != null) {
						book.close();
					}
					new File(storePath,fileName).delete();
					storePath.delete();
				}
			}
		}
		
		
		
	}
	public String getNumbers(String content){
		 Pattern pattern = Pattern.compile("(\\d*\\.)?\\d+");  
		    Matcher matcher = pattern.matcher(content);  
		    while (matcher.find()) {  
		       return matcher.group(0);  
		    }
		    return "";
	 }
	public String getPriceTime(Sheet sheet){
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(sheet.getCell(j, i).getContents().contains("表")){
					return getNumbers(sheet.getCell(j, i).getContents());
				}
			}
		}
		return "";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
