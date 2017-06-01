package com.sanker.service.inform;

import java.util.Date;
import java.util.List;

import com.sanker.entity.inform.Mail;
import com.sanker.service.core.BaseService;

/**
 * 邮件
 * @author 滕洁
 * @date 2016-11-17
 */
public class MailService extends BaseService{
	
	/**
	 * 新建
	 */
	public boolean addMail(String senderId,String senderName,String receiverId,String title,String content,String type){
		Mail entity = new Mail();
		entity.setSenderId(senderId);
		entity.setSenderName(senderName);
		entity.setReceiverId(receiverId);
		entity.setTitle(title);
		entity.setContent(content);
		entity.setType(type);
		entity.setStatus("0");
		Date date = new Date();
		entity.setSendTime(new java.sql.Timestamp(date.getTime()));
		
		
		try {
			this.getSession().save(entity);
			return true;
		} catch (Exception e) {
			System.out.println("error_addMail!");
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	/**
	 * 查询未读的邮件
	 */
	@SuppressWarnings("unchecked")
	public List<Mail> getMailByReceiverId(String receiverId){
		
		try {
			return this
					.getSession()
					.createQuery(
							"from Mail m where m.receiverId = ? and m.status = ? order by sendTime desc").setString(0, receiverId)
							.setString(1, "0").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 根据Id查询邮件
	 */
	public Mail getMailByMailId(String id){
		
		try {
			return (Mail) this.getSession().get(Mail.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 *更新邮件状态
	 */
	public boolean updateMail(String mailId){
		
		try {
			int result = this.getSession().createQuery("update Mail m set status = ? where m.id = ?").setString(0, "1").setString(1, mailId).executeUpdate();
			if(result>0){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	

}