package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.MailBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MailBoxDAO {
    MailBox selectMailBox(@Param("msgid") int msgid, @Param("uid") int uid);
}
