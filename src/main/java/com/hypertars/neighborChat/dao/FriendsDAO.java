package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Friends;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FriendsDAO {
    Friends selectFriends(@Param("uidA") int uidA, @Param("uidB") int uidB);
}
