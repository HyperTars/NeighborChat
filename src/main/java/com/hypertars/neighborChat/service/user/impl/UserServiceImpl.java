package com.hypertars.neighborChat.service.user.impl;

import com.hypertars.neighborChat.dao.BlocksDAO;
import com.hypertars.neighborChat.dao.UserBlockDAO;
import com.hypertars.neighborChat.dao.UsersDAO;
import com.hypertars.neighborChat.enums.NBCResultCodeEnum;
import com.hypertars.neighborChat.exception.NBCException;
import com.hypertars.neighborChat.model.Blocks;
import com.hypertars.neighborChat.model.Hoods;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.user.UserService;
import com.hypertars.neighborChat.utils.AssertUtils;
import com.hypertars.neighborChat.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    /** user dao */
    @Resource
    private UsersDAO usersDAO;

    /** block dao */
    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    /** users session map */
    private ConcurrentHashMap<String, Users> userMap = new ConcurrentHashMap<>();
    private int maxLoginUsers = 100;

    @Override
    public String loginIn(Users user) {

        // 1. check pass
        AssertUtils.assertNotNull(user);
        Users userSelected = usersDAO.selectByUName(user.getUname());
        AssertUtils.assertNotNull(userSelected);
        String session = getRandomString(30);

        // 2. insert into map
        if (userMap.size() >= maxLoginUsers) {
            String[] keySet = userMap.keySet().toArray(new String[0]);
            String expireKey = keySet[0];
            userMap.remove(expireKey);
        }
        userMap.put(session, userSelected);

        return session;
    }

    /**
     * get random string
     * @param length len
     * @return random string
     */
    private static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
                default: break;
            }
        }
        return sb.toString();
    }


    @Override
    public Users getUserBySession(String session) {
        return userMap.get(session);
    }

    @Override
    public Users selectByUid(int uid) {
        return usersDAO.selectByUid(uid);
//        return null;
    }

    @Override
    public List<Users> getUsersByBid(int bid) {
        List<UserBlock> userBlocks = userBlockDAO.getUserBlocksByBid(bid);
        List<Users> users = new ArrayList<>();
        for (UserBlock userBlock: userBlocks) {
            users.add(usersDAO.selectByUid(userBlock.getUid()));
        }
        return users;
    }

    @Override
    public UserBlock getUserBlockByUid(int uid) {
        return userBlockDAO.getUserBlocksByUid(uid);
    }

    @Override
    public Map<String, List<Users>> getUsersByHid(int hid) {
        List<Blocks> blocks = userBlockDAO.getBlocksByHid(hid);
        Map<String, List<Users>> usersByBlock = new HashMap<>();
        for (Blocks block: blocks) {
            usersByBlock.put(block.getBname(), getUsersByBid(block.getBid()));
        }
        return usersByBlock;
    }

    @Override
    public void insert(Users user) {
        checkUserInfo(user);
        usersDAO.insert(user);
    }

    /**
     * check whther user info is valid
     * @param user user model
     */
    private void checkUserInfo(Users user) {
        if (user == null || !(StringUtils.isNotEmpty(user.getUname()) &&
                StringUtils.isNotEmpty(user.getPasswd()) &&
                StringUtils.isNotEmpty(user.getFName()) &&
                StringUtils.isNotEmpty(user.getLName()) )) {
            throw new NBCException("invalid user info", NBCResultCodeEnum.INVALID_DATA);
        }
    }

    @Override
    public void update(Users user) {
        if (user == null) {
            return ;
        }
        usersDAO.update(user);
    }

}