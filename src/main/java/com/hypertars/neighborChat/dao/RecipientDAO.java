package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Recipient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecipientDAO {
    Recipient selectByMsgid(@Param("msgid") int msgid);
}
