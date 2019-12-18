package com.hypertars.neighborChat.service.pullInfo.impl;

import com.hypertars.neighborChat.dao.*;
import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.Hoods;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.pullInfo.basicInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class basicInfoServiceImpl implements basicInfoService {

    @Resource
    private BlockApplicationDAO blockApplicationDAO;

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private FriendApplicationDAO friendApplicationDAO;

    @Resource
    private FriendsDAO friendsDAO;

    @Resource
    private HoodsDAO hoodsDAO;

    @Resource
    private MailBoxDAO mailBoxDAO;

    @Resource
    private MessageDAO messageDAO;

    @Resource
    private NeighborsDAO neighborsDAO;

    @Resource
    private RecipientDAO recipientDAO;

    @Resource
    private ReplyDAO replyDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @Resource
    private UsersDAO usersDAO;

    /**
     * get block by bid
     * @param bid block id
     * @return block
     */
    @Override
    public Blocks getBlockByBid(int bid) {
        return blocksDAO.getBlockByBid(bid);
    }

    /**
     * get block info by uid
     * @param uid user id
     * @return blocks
     */
    @Override
    public Blocks getBlockByUid(int uid) {
        int bid = 0;
        List<UserBlock>ubs = userBlockDAO.getUserBlocksByUid(uid);
        for (UserBlock ub : ubs)
            if (ub.getStatus())
                bid = ub.getBid();
        if (bid == 0) return null;
        return blocksDAO.getBlockByBid(bid);
    }

    /**
     * get all blocks information in database
     * @return List of blocks
     */
    @Override
    public List<Blocks> getAllBlocks() {
        return blocksDAO.getAllBlocks();
    }

    /**
     * get hood by uid
     * @param uid uid
     * @return hood
     */
    @Override
    public Hoods getHoodByUid(int uid) {
        return hoodsDAO.getHoodByUid(uid);
    }

    /**
     * get hood by bid
     * @param bid block id
     * @return hood
     */
    @Override
    public Hoods getHoodByBid(int bid) {
        return hoodsDAO.getHoodByBid(bid);
    }

    /**
     * get hood by hid
     * @param hid hood id
     * @return hood
     */
    @Override
    public Hoods getHoodByHid(int hid) {
        return hoodsDAO.getHoodByHid(hid);
    }

    /**
     * get all hoods in database
     * @return list of hoods
     */
    @Override
    public List<Hoods> getAllHoods() {
        return hoodsDAO.getAllHoods();
    }

    /**
     * get blocks in same hood by uid
     * @param uid user id
     * @return list of blocks
     */
    @Override
    public List<Blocks> getBlocksInSameHoodByUid(int uid) {
        Hoods hood = getHoodByUid(uid);
        return blocksDAO.getBlocksByHid(hood.getHid());
    }

    /**
     * current user block
     * @param uid user id
     * @return user block
     */
    @Override
    public UserBlock getCurrentMember(int uid) {
        List<UserBlock> ubs = userBlockDAO.getUserBlocksByUid(uid);
        for (UserBlock ub : ubs)
            if (ub.getStatus())
                return ub;
        return null;
    }

    /**
     * get users by bid (in user blocks, all active members)
     * @param bid bid
     * @return list of users
     */
    @Override
    public List<Users> getUsersByBid(int bid) {
        List<Users> usersList = new ArrayList<>();
        List<UserBlock> ubs = userBlockDAO.getUserBlocksByBid(bid);
        for (UserBlock ub : ubs) {
            int tempUid = ub.getUid();
            Users user = usersDAO.getUserInfoByUid(tempUid);
            usersList.add(user);
        }
        return usersList;
    }

    @Override
    public List<Users> getSameBuildingByUid(int uid) {
        return null;
    }

    /**
     * get all users info in the same block with uid
     * @param uid user id
     * @return list of users
     */
    @Override
    public List<Users> getUsersInSameBlockByUid(int uid) {
        int bid = 0;
        List<UserBlock> ubs = userBlockDAO.getUserBlocksByUid(uid);
        for (UserBlock ub : ubs)
            if (ub.getStatus())
                bid = ub.getBid();
        if (bid == 0) return null;
        return getUsersByBid(bid);
    }

    /**
     * get all users info in the same hood with uid
     * @param uid user id
     * @return list of users
     */
    @Override
    public List<Users> getUsersInSameHoodByUid(int uid) {
        List<Users> usersList = new ArrayList<>();
        Hoods hood = hoodsDAO.getHoodByUid(uid);
        List<Blocks> blocksList = blocksDAO.getBlocksByHid(hood.getHid());
        for (Blocks block : blocksList) {
            List<Users> tempList = getUsersByBid(block.getBid());
            usersList.addAll(tempList);
        }
        return usersList;
    }

    @Override
    public List<Users> getFriendsByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getFriendsFromBlockByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getFriendsFromHoodByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getFriendsFromOldByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getNeighborsByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getNeighborsFromBlockByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getNeighborsFromHoodByUid(int uid) {
        return null;
    }

    @Override
    public List<Users> getStrangersFromSameBuilding(int uid) {
        return null;
    }

    @Override
    public List<Users> getStrangersFromSameBlock(int uid) {
        return null;
    }

    @Override
    public List<Users> getStrangersFromSameHood(int uid) {
        return null;
    }
}
