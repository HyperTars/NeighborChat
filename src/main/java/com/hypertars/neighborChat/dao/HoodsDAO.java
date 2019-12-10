package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Hoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HoodsDAO {
    Hoods selectByHid(@Param("hid") int hid);

}
