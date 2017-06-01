package com.sanker.service.utils;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class TimestampTag extends TagSupport {

	private static final long serialVersionUID = 1023582580129558400L;
	private StringBuffer hiddenInput = new StringBuffer();

	@Override
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			Calendar c = Calendar.getInstance();
			String lastRequestTime = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", c);
//			System.out.println(lastRequestTime);
			this.pageContext.getSession().setAttribute("lastRequestTime", lastRequestTime);
			hiddenInput.delete(0, hiddenInput.length());
			hiddenInput.append("<input type='hidden' id='lastRequestTime' name='lastRequestTime' value='")
				.append(lastRequestTime).append("' />");
			this.pageContext.getOut().write(hiddenInput.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

}
