package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.BlockApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BlockApplicationDAO {

    int insert(BlockApplication blockApplication);

    void update(BlockApplication blockApplication);

    List<BlockApplication> selectByUidStatus(@Param("applicant") int uid, @Param("status") boolean status);

    List<BlockApplication> selectByBidStatus(@Param("bid") int bid, @Param("status") boolean status);

}
