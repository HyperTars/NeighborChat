package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.UserBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserBlockDAO {
    UserBlock selectUserBlock(@Param("uid") int uid, @Param("ubTime") Date ubTime);
}
