package com.drebander.mail.domain;

import org.thymeleaf.context.IContext;

import javax.validation.constraints.NotEmpty;
import java.io.File;

public class SjtyMessage {
    @NotEmpty(message = "to is required ~!")
    private String[] to;
    @NotEmpty(message = "subject is required ~!")
    private String subject;
    /**
     * 抄送
     */
    private String[] cc;
    private String text;
    private String html;
    private String resourceName;
    private IContext context;
    private File attach;
    private File resource;


    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public void setResource(File resource) {
        this.resource = resource;
    }

    public File getResource() {
        return resource;
    }

    private String attachName;
    private String templateName;

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {

        return templateName;
    }

    public enum Types {TEXT, HTML, ATTACH, TEMPLATE, STATICRESOURCE}


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setContect(IContext context) {
        this.context = context;
    }

    public void setAttach(File attach) {
        this.attach = attach;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getHtml() {
        return html;
    }

    public String getResourceName() {
        return resourceName;
    }

    public IContext getContext() {
        return context;
    }

    public File getAttach() {
        return attach;
    }

    public String getAttachName() {
        return attachName;
    }
}
