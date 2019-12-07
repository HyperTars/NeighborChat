package com.hypertars.neighborchat.dao;

import com.hypertars.neighborchat.model.Hoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HoodsDAO {
    Hoods selectByHid(@Param("hid") int hid);
}
