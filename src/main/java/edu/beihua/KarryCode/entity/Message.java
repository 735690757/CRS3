package edu.beihua.KarryCode.entity;

import java.io.Serializable;

public class Message {
    private int message_id;
    private String customer_name;
    private String message_date;
    private String message_content;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getMessage_date() {
        return message_date;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public boolean isMessage_state() {
        return message_state;
    }

    public void setMessage_state(boolean message_state) {
        this.message_state = message_state;
    }

    public Message(int message_id, String customer_name, String message_date, String message_content, boolean message_state) {
        this.message_id = message_id;
        this.customer_name = customer_name;
        this.message_date = message_date;
        this.message_content = message_content;
        this.message_state = message_state;
    }

    private boolean message_state;
}
