package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Hoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HoodsDAO {

    /**
     * get hood by hid
     * @param hid hood id
     * @return hoods hood model
     */
    Hoods getHoodByHid(@Param("hid") int hid);

    /**
     * get hood by bid
     * @param bid block id
     * @return hoods hood model
     */
    Hoods getHoodByBid(@Param("bid") int bid);
}
