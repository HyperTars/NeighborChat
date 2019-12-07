package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Block;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlockDAO {

    Block selectByBid(@Param("bid") int bid);
}
