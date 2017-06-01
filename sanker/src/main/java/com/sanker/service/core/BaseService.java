package com.sanker.service.core;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sanker.comms.page.PageRequest;
import com.sanker.comms.page.PageResponse;
import com.sanker.service.utils.JSONHelper;
import com.sanker.service.utils.PageVO;


public class BaseService extends HibernateDaoSupport{
	

	public String genWhereOrAnd(boolean hasWhere){
		String str = "";
		if(hasWhere){
			str = " and ";
		}else{
			str = " where ";
		}
		return str;
	}
	
	public String generateSearchParamters(Map<String, String> params){
		StringBuilder sb = new StringBuilder();
		boolean hasWhere = false;
		Set<String> keySet = params.keySet();
		if(keySet != null && keySet.size() > 0){
			String value = null;
			for(String key: keySet){
				value = params.get(key);
				if(value != null && value != ""){
					if(hasWhere){
						sb.append(" and ");
					}else{
						sb.append(" where ");
					}
					sb.append(key + "='" + value + "'");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 使用String.format格式化时间
	 * @param date
	 * @param pattern
	 * @return
	 */
	public String formatDate(Date date, String pattern){
		if(date == null || pattern == null){
			return "";
		}else{
			return String.format(pattern, date);
		}
	}
	
	/**
	 * 根据数字生成B,KB,MB,GB
	 * @param l
	 * @return
	 */
	public String generateSizeString(long l){
		String s = null;
		double d = 0;
		if(l > 0){
			DecimalFormat fnum = new DecimalFormat("##0.00");  
			if(l > 1024){
				d = l / 1024;
				if(d > 1024){
					double d1 = d / 1024;
					if(d1 > 1024){
						double d2 = d1 /1024;
						if(d2 > 1024){
							
						}else{
							s = fnum.format(d2) + "GB";
						}
					}else{
						s = fnum.format(d1) + "MB";
					}
				}else{
					s = fnum.format(d) + "KB";
				}
			}else{
				s = l + "B";
			}
		}else{
			s = String.valueOf(l);
		}
		return s;
	}
	

	

	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	protected String getJson(PageVO pageVO){
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("page", pageVO.getPage());
		obj.put("pageSize", pageVO.getPageSize());
		obj.put("rows", pageVO.getRows());
		obj.put("records", pageVO.getRecords());
		obj.put("total", pageVO.getTotal());
		return JSONHelper.SerializeWithNeedAnnotation(obj);
	}
	
	@SuppressWarnings("rawtypes")
	protected String getJson(PageVO pageVO, String datePattern){
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("page", pageVO.getPage());
		obj.put("pageSize", pageVO.getPageSize());
		obj.put("rows", pageVO.getRows());
		obj.put("records", pageVO.getRecords());
		obj.put("total", pageVO.getTotal());
		return JSONHelper.toJson(obj, datePattern);
	}
	
	/**
	 * 给文件名加一个时间戳，以防文件重名
	 * @param filename
	 * @return
	 */
	protected String generateFileName(String filename,String reName){
		String fileName = filename;
		if(filename != null){
			int index = filename.lastIndexOf('.');
			if(index != -1){
				fileName = reName+ "_" + System.currentTimeMillis() + ".jpg";//filename.substring(index);
			}
		}
		
		return fileName;
	}
	/**
	 * 给文件名加一个时间戳，以防文件重名
	 * @param filename
	 * @return
	 */
	protected String generateFileNameVideo(String filename,String reName){
		String fileName = filename;
		if(filename != null){
			int index = filename.lastIndexOf('.');
			if(index != -1){
				fileName = reName+ "_" + System.currentTimeMillis() + ".mp4";//filename.substring(index);
			}
		}
		
		return fileName;
	}
	
	@SuppressWarnings("rawtypes")
	protected PageResponse getResponse(PageRequest page, List list, Long totalCount) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();
		int totalPage = totalCount % pageSize==0?(int)(totalCount/pageSize):(int)(totalCount/pageSize)+1;
		return new PageResponse(pageIndex, totalPage, totalCount, list);
	}

	protected String getSqlCount(String hql) {
		StringBuffer sb = new StringBuffer("select count(*) ");
		hql = hql.toLowerCase().substring(hql.indexOf("from"));
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	@SuppressWarnings("rawtypes")
	public <T> PageResponse getPage(Class<T> clazz, String sql, PageRequest page) {
		String sqlCount = getSqlCount(sql);
		Long totalCount = Long.parseLong(getSession().createSQLQuery(sqlCount).uniqueResult().toString());
		List list = null;

		if (totalCount > 0) {
			try {
				list =   getSession().createSQLQuery(sql).addEntity(clazz).setFirstResult(page.getStartNumber()).setMaxResults(
						page.getPageSize()).list();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return getResponse(page, list, totalCount);
	}
	
}





