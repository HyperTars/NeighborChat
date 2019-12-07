package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Recipient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecipientDAO {
    Recipient selectByMsgid(@Param("msgid") int msgid);
}
