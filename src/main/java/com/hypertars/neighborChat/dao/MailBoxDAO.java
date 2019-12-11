package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.MailBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MailBoxDAO {

    /**
     * get whole mailbox by uid
     * @param uid uid
     * @return List<MailBox> mailbox models
     */
    List<MailBox> getMailBoxByUid(@Param("uid") int uid);

    boolean setMsgUnread(MailBox mailBox);

    boolean setMsgRead(MailBox mailBox);

    List<MailBox> getNewMsgthreadByUid(@Param("uid") int uid);
}
