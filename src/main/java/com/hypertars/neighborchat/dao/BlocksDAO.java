package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Blocks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlocksDAO {

    Blocks selectByBid(@Param("bid") int bid);
}
