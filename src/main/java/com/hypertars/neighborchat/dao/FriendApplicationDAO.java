package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.FriendApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendApplicationDAO {
    FriendApplication selectFA(@Param("applicant") int applicant, @Param("recipient") int recipient);
}
