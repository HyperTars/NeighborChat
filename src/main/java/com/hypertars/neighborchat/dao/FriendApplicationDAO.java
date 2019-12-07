package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.FriendApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendApplicationDAO {
    FriendApplication selectFA(@Param("applicant") int applicant, @Param("recipient") int recipient);
}
