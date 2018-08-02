package com.leo.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class testMail {

	@Autowired
	private MailSendService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("717626659@qq.com","test simple mail"," hello this is simple mail");
	}
	
	@Test
	public void sendAttachmentsMail() {
	    String filePath="src/test/java/com/leo/mail/1.jpg";
	    mailService.sendAttachmentsMail("717626659@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
	public void sendInlineResourceMail() {
	    String rscId = "leo";
	    String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
	    String imgPath = "src/test/java/com/leo/mail/1.jpg";

	    mailService.sendInlineResourceMail("717626659@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
	}
	
	@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("id", "006");
	    String emailContent = templateEngine.process("emailTemplate", context);

	    mailService.sendHtmlMail("717626659@qq.com","主题：这是模板邮件",emailContent);
	}
	
}
