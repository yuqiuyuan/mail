package com.drebander.mail;

import com.drebander.mail.domain.MainTemplateVO;
import com.drebander.mail.domain.SjtyMessage;
import com.drebander.mail.service.SjtyMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Autowired
    SjtyMailService emailService;

    @Autowired
    ServletContext servletContext;
    static String[] to;
    static String[] cc;

    /**
     * 这个信息应该存储到数据库中，以便后期维护
     */
    static {
//        收件地址
        to = new String[]{"drebander@163.com"};
//        抄送地址
        cc = new String[]{"shan_dong1992@163.com"};
    }


    @Test
    public void contextLoads() {
        SjtyMessage m = new SjtyMessage();
        m.setTo(to);
        m.setSubject("test");
        m.setText("Sending email test in single point～！");
        System.out.println(emailService.sendTextEmail(m));
    }

    @Test
    public void testTemplateEmail() {
        Context context = new Context();
        SjtyMessage m = new SjtyMessage();
        m.setCc(cc);
        m.setTo(to);
        m.setSubject("Template");
        m.setContect(context);
        m.setTemplateName("mailTemplate.html");
        context.setVariable("VO", new MainTemplateVO("Drebander ~!"));
        System.out.println(emailService.sendEmailByTemplete(m));
    }

}
