package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Blocks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlocksDAO {

    Blocks selectByBid(@Param("bid") int bid);

}
