package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.MailBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MailBoxDAO {
    /**
     * notify new msg or reply by scanning MailBox and find msg from read becomes unread or new msg
     * @param uid
     * @return
     */
    List<MailBox> getMailBoxByUid(@Param("uid") int uid);

    boolean setMsgUnread(MailBox mailBox);

    boolean setMsgRead(MailBox mailBox);
}
