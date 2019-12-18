package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.UserBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserBlockDAO {

    List<UserBlock> getUserBlocksByBid(@Param("bid") int bid);

    List<UserBlock> getUserBlocksByUid(@Param("uid") int uid);

    List<Blocks> getBlocksByHid(@Param("hid") int hid);

    void update(UserBlock userBlock);

    void insert(UserBlock userBlock);

    /**
     * user exists block
     * @param uid user id
     * @return 1 or 0
     */
    boolean exitBlock(@Param("uid") int uid);

    void setAllUserBlocksInactive(@Param("uid") int uid);

    /**
     * notify new block member
     * @param uid current user id
     * @return List<UserBlock> UserBlock models
     */
    List<UserBlock> notifyNewBlockMember(@Param("uid") int uid);

    /**
     * add user to new userblock
     * @param userBlock userBlock model
     */
    void addUserBlock(UserBlock userBlock);

    int countUsersByBid(@Param("bid") int bid);
}
