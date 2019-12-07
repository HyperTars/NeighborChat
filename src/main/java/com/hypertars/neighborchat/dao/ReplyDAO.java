package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface ReplyDAO {
    Reply selectReply(@Param("msgid") int msgid, @Param("uid") int uid, @Param("rTime") Date rTime);
}