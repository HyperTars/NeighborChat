package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Neighbors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NeighborsDAO {
    Neighbors selectNeighbors(@Param("uidA") int uidA, @Param("uidB") int uidB);
}
