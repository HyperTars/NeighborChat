package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface ReplyDAO {
    Reply selectReply(@Param("msgid") int msgid, @Param("uid") int uid, @Param("rTime") Date rTime);
}
