package com.hypertars.neighborChat.service.pullInfo;

import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.Hoods;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;

import java.util.List;

public interface basicInfoService {

    /** membership */
    Blocks getBlockByBid (int bid);
    Blocks getBlockByUid (int uid);
    List<Blocks> getAllBlocks ();
    Hoods getHoodByUid (int uid);
    Hoods getHoodByBid (int bid);
    Hoods getHoodByHid (int hid);
    List<Hoods> getAllHoods();
    List<Blocks> getBlocksInSameHoodByUid (int uid);
    UserBlock getCurrentMember (int uid);

    /** Users */
    List<Users> getUsersByBid (int bid);
    List<Users> getSameBuildingByUid (int uid);
    List<Users> getUsersInSameBlockByUid (int uid);
    List<Users> getUsersInSameHoodByUid (int uid);

    /** Relationship */
    List<Users> getFriendsByUid (int uid);
    List<Users> getFriendsFromBlockByUid (int uid);
    List<Users> getFriendsFromHoodByUid (int uid);
    List<Users> getFriendsFromOldByUid (int uid);

    List<Users> getNeighborsByUid (int uid);
    List<Users> getNeighborsFromBlockByUid (int uid);
    List<Users> getNeighborsFromHoodByUid (int uid);

    List<Users> getStrangersFromSameBuilding (int uid);
    List<Users> getStrangersFromSameBlock (int uid);
    List<Users> getStrangersFromSameHood (int uid);
}
