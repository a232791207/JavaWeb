package com.wust.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.wust.domain.Fare;
import com.wust.domain.FarePage;
import com.wust.factory.BasicFactory;
import com.wust.service.FareService;

public class PrintFareListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		FareService service = BasicFactory.getFactory().getInstance(FareService.class);
		FarePage farePage = service.getFareByPage(from, to*10);
		List<Fare> fares = farePage.getList();

		//String fileNameF = request.getParameter("disk")+":\\ManageSystem\\temp";
		String fileNameF = this.getServletContext().getRealPath("/WEB-INF")+"/print/";
		String name="fareList.xls";
		String fileName = fileNameF+name;
		//�жϸ��ļ����Ƿ���ڣ��������򴴽�һ��
		File fileF = new File(fileNameF);
		if(!fileF.exists() && !fileF.isDirectory()){
			fileF.mkdirs();
		}
		File file = new File(fileName);
		if(file.exists()){
			file.delete();
		}
		WritableWorkbook wwb;
        FileOutputStream fos;
        try {    
            fos = new FileOutputStream(fileName);
            wwb = Workbook.createWorkbook(fos);
            
            // ����һ��������
            WritableSheet ws = wwb.createSheet("���ȷ���Ϣ��ѯ��", 1);       

            //    ���õ�Ԫ������ָ�ʽ
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            wcf.setWrap(true);

            //��ͷ
            ws.addCell(new Label(1, 1, "������", wcf));
            ws.addCell(new Label(2, 1, "����", wcf));
            ws.addCell(new Label(3, 1, "���ȷѵ���(Ԫ/������)", wcf));
            ws.addCell(new Label(4, 1, "���(������)", wcf));
            ws.addCell(new Label(5, 1, "�ܵ��ȷ�(Ԫ)", wcf));
            wcf = new WritableCellFormat();
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setWrap(true);
            //    ������ݵ�����
            for (int i = 0; i < fares.size(); i++){
                ws.addCell(new Label(1, i + 2, fares.get(i).getId(), wcf));
                ws.addCell(new Label(2, i + 2, fares.get(i).getArea(), wcf));
                ws.addCell(new jxl.write.Number(3, i+2, fares.get(i).getFare()));
                ws.addCell(new jxl.write.Number(5, i+2, fares.get(i).getNum()));
                ws.addCell(new jxl.write.Number(6, i+2, fares.get(i).getSumfare()));
            }

            wwb.write();
            wwb.close();
            fos.close();

        } catch (IOException e){
        	e.printStackTrace();
        } catch (RowsExceededException e){
        	e.printStackTrace();
        } catch (WriteException e){
        	e.printStackTrace();
        }        
        //�����ļ�ֻ������   
        file.setWritable(false);
        //�ÿͻ����������excel�ļ�
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(name, "UTF-8"));
 		
        InputStream in = new FileInputStream(fileName);
        OutputStream out = response.getOutputStream();
        int len = -1;
        byte b[] = new byte[1024];
        while((len=in.read(b))!=-1){
     	   out.write(b, 0, len);
        }
        in.close(); 
        file.delete();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
