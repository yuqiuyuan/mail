package com.drebander.mail.domain;

import sun.applet.Main;

public class MainTemplateVO {

    public MainTemplateVO(String message) {
        this.message = message;
    }

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
