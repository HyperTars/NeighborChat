package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    /**
     * msg id
     */
    private int msgid;

    /**
     * author
     */
    private int author;

    /**
     * rRange
     */
    private int rRange;

    /**
     * mtime
     */
    private Date mtime;

    private String title;

    private String sub;

    private String txt;

    private String coord;

}
