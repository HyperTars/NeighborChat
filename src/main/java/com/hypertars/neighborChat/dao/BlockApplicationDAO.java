package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.BlockApplication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlockApplicationDAO {

    int insert(BlockApplication blockApplication);
}
