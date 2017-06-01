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

import com.wust.domain.Draworder;
import com.wust.domain.DraworderPage;
import com.wust.factory.BasicFactory;
import com.wust.service.DraworderService;

public class PrintDraworderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int from = Integer.parseInt(request.getParameter("from"));
		int to = Integer.parseInt(request.getParameter("to"));
		DraworderService service = BasicFactory.getFactory().getInstance(DraworderService.class);
		DraworderPage draworderPage = service.pageDraworder(from, to*10);
		List<Draworder> draworders = draworderPage.getList();
		
		//String fileNameF = request.getParameter("disk")+":\\ManageSystem\\temp";
		String fileNameF = this.getServletContext().getRealPath("/WEB-INF")+"/print/";
		String name="draworderList.xls";
		String fileName = fileNameF+name;		
		//判断父文件夹是否存在，不存在则创建一个
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
            
            // 创建一个工作表
            WritableSheet ws = wwb.createSheet("开票单", 1);       

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            wcf.setWrap(true);

            //表头
            ws.addCell(new Label(1, 1, "订单号", wcf));
            ws.addCell(new Label(2, 1, "经销商", wcf));
            ws.addCell(new Label(3, 1, "业务员", wcf));
            ws.addCell(new Label(4, 1, "发票号", wcf));
            ws.addCell(new Label(5, 1, "开票单位", wcf));
            ws.addCell(new Label(6, 1, "开票日期", wcf));
            ws.addCell(new Label(7, 1, "规格", wcf));
            ws.addCell(new Label(8, 1, "等级", wcf));
            ws.addCell(new Label(9, 1, "单价(元/片)", wcf));
            ws.addCell(new Label(10, 1, "数量(片)", wcf));
            ws.addCell(new Label(11, 1, "总额(元)", wcf));
            wcf = new WritableCellFormat();
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setWrap(true);
            //    填充数据的内容
            for (int i = 0; i < draworders.size(); i++){
            	ws.addCell(new Label(1, i + 2, draworders.get(i).getOrderid(), wcf));
                ws.addCell(new Label(2, i + 2, draworders.get(i).getDistributor(), wcf));
                ws.addCell(new Label(3, i + 2, draworders.get(i).getSalesman(), wcf));
                ws.addCell(new Label(4, i + 2, draworders.get(i).getId()+"", wcf));
                ws.addCell(new Label(5, i + 2, draworders.get(i).getCustomer(), wcf));
                ws.addCell(new Label(6, i + 2, draworders.get(i).getTime(), wcf));
                ws.addCell(new Label(7, i + 2, draworders.get(i).getFormat(), wcf));
                ws.addCell(new Label(8, i + 2, draworders.get(i).getLevel(), wcf));
                ws.addCell(new jxl.write.Number(9, i+2, draworders.get(i).getPrice()));
                ws.addCell(new jxl.write.Number(10, i+2, draworders.get(i).getNumber()));
                ws.addCell(new jxl.write.Number(11, i+2, draworders.get(i).getSumprice()));
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
        //设置文件只读属性   
        file.setWritable(false);
        //让客户端下载这个excel文件
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
