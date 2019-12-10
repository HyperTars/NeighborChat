package com.hypertars.neighborChat.model;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    /** msg id */
    private int msgid;

    /** author */
    private int author;

    /** rRange */
    private int rRange;

    /** mtime */
    private Date mtime;

    /** title */
    private String title;

    /** sub */
    private String sub;

    /** text */
    private String txt;

    /** coordinates */
    private String coord;

}
