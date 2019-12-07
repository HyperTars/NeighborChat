package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.UserBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface UserBlockDAO {
    UserBlock selectUserBlock(@Param("uid") int uid, @Param("ubTime") Date ubTime);
}
