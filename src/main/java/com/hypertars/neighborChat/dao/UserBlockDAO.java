package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.UserBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserBlockDAO {

    List<UserBlock> getUserBlocksByBid(@Param("bid") int bid);

    UserBlock getUserBlocksByUid(@Param("uid") int uid);

    List<Blocks> getBlocksByHid(@Param("hid") int hid);

}
