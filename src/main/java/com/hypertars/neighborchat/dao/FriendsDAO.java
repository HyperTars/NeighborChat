package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendsDAO {
    Friends selectFriends(@Param("uidA") int uidA, @Param("uidB") int uidB);
}

