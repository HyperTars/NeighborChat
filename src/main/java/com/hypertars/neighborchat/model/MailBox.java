package com.hypertars.neighborchat.model;

import lombok.Data;


@Data
public class MailBox {

    /** uid */
    private int uid;

    /** msgid */
    private int msgid;

    /** read */
    private boolean rd;

}