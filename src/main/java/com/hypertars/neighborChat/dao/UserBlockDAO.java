package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.UserBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserBlockDAO {

    /**
     * get userBlock by bid
     * @param bid block id
     * @return List<UserBlock> userBlock models
     */
    List<UserBlock> getUserBlockByBid(@Param("bid") int bid);

    /**
     * get userBlock by uid
     * @param uid user id
     * @return List<UserBlock> userBlock models
     */
    List<UserBlock> getUserBlocksByUid(@Param("uid") int uid);

    /**
     * get userBlocks by hid
     * @param hid hood id
     * @return List<UserBlock> userBlock models
     */
    List<UserBlock> getUserBlocksByHid(@Param("hid") int hid);

    /**
     * user exists block
     * @param uid user id
     * @return 1 or 0
     */
    boolean exitBlock(@Param("uid") int uid);

    /**
     * get members from same building
     * @param uid user id
     * @return List<UserBlock> userblock model
     */
    List<UserBlock> getSameBuilding(@Param("uid") int uid);

    /**
     * get members from same block
     * @param uid user id
     * @return List<UserBlock> userblock model
     */
    List<UserBlock> getSameBlock(@Param("uid") int uid);

    /**
     * get members from same hood
     * @param uid user id
     * @return List<UserBlock> userblock model
     */
    List<UserBlock> getSameHood(@Param("uid") int uid);

    /**
     * notify new block member
     * @param uid current user id
     * @return List<UserBlock> UserBlock models
     */
    List<UserBlock> notifyNewBlockMember(@Param("uid") int uid);

    /**
     * add user to new userblock
     * @param userBlock userBlock model
     * @return 1 or 0
     */
    boolean addUserBlock(UserBlock userBlock);
}
