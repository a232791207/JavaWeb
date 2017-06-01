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
		//����
		DiskFileItemFactory Factory = new DiskFileItemFactory();//����DiskFileItemFactory
		ServletFileUpload sfu = new ServletFileUpload(Factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
		}
		/*������
		 *�����ϴ��ֶ�
		 */
		for (FileItem fileItem : items) {
			//�����ϴ��ֶ�
			//�ļ��� 
			if(!fileItem.isFormField()){
				String fileName = fileItem.getName();//����������·����������Ҫ��ȡ
				if(fileName!=null){
					fileName = FilenameUtils.getName(fileName);
				}
				//���λ��
				File storePath = new File(getServletContext().getRealPath("/WEB-INF/files"));
				if(!storePath.exists()){
					storePath.mkdirs();
				}
				try {
					fileItem.write(new File(storePath,fileName));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//�ϴ��꿪ʼ���뵽price��
				Workbook book = null;
				try {
					book = Workbook.getWorkbook(new File(storePath,fileName));
					// ��õ�һ�����������
					Sheet sheet = book.getSheet(0);
					int rows = sheet.getRows();
					int columns = sheet.getColumns();
					// ����ÿ��ÿ�еĵ�Ԫ��
					// getCell(col,row);û�����ݵĵ�Ԫ��Ϊ""����null
					Cell cell = sheet.findCell("���");
					int c = cell.getColumn();
					int r = cell.getRow();
					List<Price> prices = new ArrayList<Price>(); 
					String time = getPriceTime(sheet);
					if(time==""){
						System.out.println("excel����û��ʱ��");
						return;
					}
					time=time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8);
					for (int i = r + 1; i < rows; i++) {
						String format = sheet.getCell(c, i).getContents().trim();
						String level = sheet.getCell(c + 1, i).getContents().trim();
						for (int j = c + 2; j < columns; j++) {
							if (getNumbers(sheet.getCell(j, i).getContents()) != "") {
								// ������
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
					request.setAttribute("success", "����ɹ�!");
					request.getRequestDispatcher("/servlet/SelectPriceServlet?thispage=1&area=&format=&level=").forward(request, response);
					// String.format("������%s, ���䣺%s", name ,age);
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
				if(sheet.getCell(j, i).getContents().contains("��")){
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
