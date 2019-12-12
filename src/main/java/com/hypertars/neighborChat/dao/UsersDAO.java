package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDAO {

    Users selectByUid(@Param("uid") int uid);

    void insert(Users users);

    void update(Users users);

    Users selectByUName(@Param("uname") String uname);

}


