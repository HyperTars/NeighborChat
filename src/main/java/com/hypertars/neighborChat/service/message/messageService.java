package com.hypertars.neighborChat.service.message;

import com.hypertars.neighborChat.model.Message;
import com.hypertars.neighborChat.model.Reply;
import com.hypertars.neighborChat.model.Users;

import java.util.List;

public interface messageService {

    /** message */
    List<Users> possibleRecipient (int uid);
    boolean addNewMsg (Message msg);
    List<Message> getMessageByMsgid (int msgid);
    List<Message> getMessageByRecipient (int recipient);
    List<Message> getMessageByAuthor (int author);
    List<Message> getMessageThreadUnread (int uid);

    /** reply */
    boolean addReply (Reply reply);
    List<Message> notifyNewReply (int uid);
    List<Reply> getReplyByMsgid (int msgid);

    /** notification */
    List<Message> notifyNewMessage (int uid);
    List<Message> notifyNewReply (int uid);

}
