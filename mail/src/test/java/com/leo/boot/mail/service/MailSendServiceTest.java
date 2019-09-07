package com.leo.boot.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSendServiceTest {

    @Autowired
    private MailSendService mailService;

    @Autowired
    private TemplateEngine templateEngine;
    
    private static final String TO = "2296244979@qq.com";
    
    private static final String ATTACHMENT = "src/test/resources/attachment/head.jpg";

    @Test
    public void testSendSimpleMail() {
        mailService.sendSimpleMail(TO, "test simple mail", " hello this is simple mail");
    }

    @Test
    public void testSendAttachmentsMail() {
        mailService.sendAttachmentsMail(TO, "主题：带附件的邮件", "有附件，请查收！", ATTACHMENT);
    }

    @Test
    public void testSendInlineResourceMail() {
        String rscId = "leo";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailService.sendInlineResourceMail(TO, "主题：这是有图片的邮件", content, ATTACHMENT, rscId);
    }

    @Test
    public void testSendHtmlMail() {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail(TO, "主题：这是模板邮件", emailContent);
    }

}
