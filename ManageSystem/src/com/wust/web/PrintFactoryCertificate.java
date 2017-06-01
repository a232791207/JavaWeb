package com.wust.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintFactoryCertificate extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.reset();
		response.setContentType("application/msword;charset=GBK");
		response.setHeader("Content-Disposition", "inline;filename=temp.doc");
		Writer writer = response.getWriter();
		
		String detail=request.getParameter("detail");
		writer.write("<html>");
		writer.write("<head>");
		writer.write("</head>");
		writer.write("<body>");
		writer.write("<center><b>���˼��ų���ƾ֤</b></center><br/>");
		writer.write("<p>" +"�����ţ�"+request.getParameter("orderid")+"</p>");
		writer.write("<p>" +"�������ڣ�"+request.getParameter("time")+"</p>");
		writer.write("<p>" +"��ַ��"+request.getParameter("area")+"</p>");
		writer.write("<p>" +"��ϵ�ˣ�"+request.getParameter("contacts")+"</p>");
		writer.write("<p>" +"�绰��"+request.getParameter("phone")+"</p>");
		writer.write("<p>" +"���ţ�"+request.getParameter("truck")+"</p>");
		String[] details=detail.split("\n");
		System.out.println(details.length);
		for (int i = 0; i < details.length; i++) {
			writer.write(details[i]+"<br/>");
		}
		writer.write("</body>");
		writer.write("</html>");
		writer.flush();
		writer.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
