package com.drebander.mail.service;

import com.drebander.mail.domain.SjtyMessage;


public interface SjtyMailService {


    String sendTextEmail(SjtyMessage message);

    String sendHtmlEmail(SjtyMessage message);

    String sendStaticResourceEmail(SjtyMessage message);

    String sendAttachEmail(SjtyMessage message);

    String sendEmailByTemplete(SjtyMessage message);
}
