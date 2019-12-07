package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageDAO {
    Message selectByMsgid(@Param("msgid") int msgid);
}
