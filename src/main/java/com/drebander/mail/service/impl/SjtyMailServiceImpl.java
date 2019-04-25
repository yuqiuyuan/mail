package com.drebander.mail.service.impl;

import com.drebander.mail.domain.SjtyMessage;
import com.drebander.mail.service.SjtyMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class SjtyMailServiceImpl implements SjtyMailService {

    @Autowired
    private JavaMailSender jms;

    @Autowired
    private TemplateEngine templateEngine;
    /**
     * 从配置文件中获取发送邮件的源
     */
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendTextEmail(SjtyMessage message) {
        return sendEmail(message.getTo(), message.getCc(), message.getSubject(), message.getText(), null, SjtyMessage.Types.TEXT, null, null, null);
    }

    @Override
    public String sendHtmlEmail(SjtyMessage message) {
        return sendEmail(message.getTo(), message.getCc(), message.getSubject(), message.getHtml(), null, SjtyMessage.Types.HTML, null, null, null);
    }

    @Override
    public String sendStaticResourceEmail(SjtyMessage message) {
        return sendEmail(message.getTo(), message.getCc(), message.getSubject(), message.getHtml(), message.getResource(), SjtyMessage.Types.STATICRESOURCE, message.getResourceName(), null, null);
    }

    @Override
    public String sendAttachEmail(SjtyMessage message) {
        return sendEmail(message.getTo(), message.getCc(), message.getSubject(), message.getText(), message.getAttach(), SjtyMessage.Types.ATTACH, null, null, null);
    }

    @Override
    public String sendEmailByTemplete(SjtyMessage message) {
        return sendEmail(message.getTo(), message.getCc(), message.getSubject(), message.getText(), null, SjtyMessage.Types.TEMPLATE, message.getResourceName(), message.getContext(), message.getTemplateName());
    }

    //    **********************私有方法*******************************
    private String sendEmail(String[] to, String[] cc, String subject, String text, File file, SjtyMessage.Types type, String resourceName, IContext context, String templateName) {
        MimeMessage message;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);//收件人
            if (null != cc) {
                helper.setCc(cc);//抄送
            }
            helper.setSubject(subject);
            if (SjtyMessage.Types.STATICRESOURCE.equals(type)) {
                //  示例：<html><body><img src=\"cid:resourceName\" ></body></html>
                FileSystemResource fileReource = new FileSystemResource(file);
                helper.addInline(resourceName, fileReource);
                helper.setText(text, true);
                //  附件类型
            } else if (SjtyMessage.Types.ATTACH.equals(type) && file != null) {
                helper.addAttachment(resourceName, file);
                helper.setText(text);
                //  模版加载
            } else if (SjtyMessage.Types.TEMPLATE.equals(type)) {
                helper.setText(templateEngine.process(templateName, context), true);
            } else {
                helper.setText(text);
            }
            jms.send(helper.getMimeMessage());
            return "发送成功～！";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
