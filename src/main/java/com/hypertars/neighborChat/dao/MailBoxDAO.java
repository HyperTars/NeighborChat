package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.MailBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MailBoxDAO {

    /**
     * get all mailbox by uid
     * @param uid uid
     * @return List<MailBox> mailbox models
     */
    List<MailBox> getAllMsgthreadByUid(@Param("uid") int uid);

    /**
     * get new message thread based on timestamp comparison
     * @param uid
     * @return
     */
    List<MailBox> notifyNewMsgthreadByUid(@Param("uid") int uid);

    /**
     * set messages unread
     * @param mailBox mailbox models
     * @return 1 or 0
     */
    boolean setMsgthreadUnread(MailBox mailBox);

    /**
     * set messages read
     * @param mailBox mailbox models
     * @return 1 or 0
     */
    boolean setMsgthreadRead(MailBox mailBox);


}
