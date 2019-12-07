package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.BlockApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface BlockApplicationDAO {
    BlockApplication selectBA(@Param("applicant") int applicant, @Param("baTime") Date baTime);
    int insert(BlockApplication blockApplication);
}
