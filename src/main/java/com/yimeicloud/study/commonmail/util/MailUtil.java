/**
 * common mail发送邮件
 * 2016/09/05 ming
 */
package com.yimeicloud.study.commonmail.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class MailUtil {
	
	// host
	private static final String MAIL_HOST = "smtp.126.com";
	
	// username
	private static final String MAIL_USERNAME = "***@126.com";
	
	// password
	private static final String MAIL_PASSWORD = "***";
	
	// charset
	private static final String MAIL_CHARSET = "utf-8";
	
	// from
	private static final String MAIL_FROM = "***@126.com";
	
	public static boolean send(String subject, String content, String to) {
		SimpleEmail email = new SimpleEmail();
        try {
	        // 封装mail
	        encapMail(email, subject, content, to);
	        // 发送邮件
	        email.send();
		} catch (EmailException e) {
			System.out.println("邮件发送失败");
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static boolean send(String subject, String content, String to, List<Map<String, String>> attachs) {
		MultiPartEmail email = new MultiPartEmail();
        try {
			// 封装mail
        	encapMail(email, subject, content, to);
	        // 添加附件
        	encapAttach(email, attachs);
        	// 发送邮件
	        email.send();
		} catch (EmailException e) {
			System.out.println("邮件发送失败");
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * 封装mail信息
	 * @param email
	 * @param subject
	 * @param content
	 * @param to
	 * @throws EmailException
	 */
	private static void encapMail(Email email, String subject, String content, String to) throws EmailException {
		email.setHostName(MAIL_HOST); // 发送服务器
        email.setAuthentication(MAIL_USERNAME, MAIL_PASSWORD); // 发送邮件的用户名和密码  
        email.setCharset(MAIL_CHARSET); //邮件编码方式
		email.addTo(to);
        email.setFrom(MAIL_FROM); // 发送邮箱
        email.setSubject(subject); // 主题
        email.setMsg(content); // 内容
	}
	
	/**
	 * 封装附件
	 * @param email
	 * @param attachs
	 * @throws EmailException
	 */
	private static void encapAttach(MultiPartEmail email, List<Map<String, String>> attachs) throws EmailException {
		if(null != attachs && attachs.size() > 0) {
			EmailAttachment att = null;
        	for(Map<String, String> attach : attachs) {
        		att = new EmailAttachment();
        		att.setName(attach.get("name"));
        		att.setPath(attach.get("path"));
        		email.attach(att);
        	}
        }
	}
}
