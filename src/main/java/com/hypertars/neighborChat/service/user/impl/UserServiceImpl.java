package com.hypertars.neighborChat.service.user.impl;

import com.hypertars.neighborChat.dao.BlockDAO;
import com.hypertars.neighborChat.dao.UserDAO;
import com.hypertars.neighborChat.enums.NBCResultCodeEnum;
import com.hypertars.neighborChat.exception.NBCException;
import com.hypertars.neighborChat.model.User;
import com.hypertars.neighborChat.service.user.UserService;
import com.hypertars.neighborChat.utils.AssertUtils;
import com.hypertars.neighborChat.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    /** user dao */
    @Resource
    private UserDAO userDAO;

    /** block dao */
    @Resource
    private BlockDAO blockDAO;

    /** users session map */
    private ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
    private int maxLoginUsers = 100;

    @Override
    public String loginIn(User user) {

        // 1. check pass
        AssertUtils.assertNotNull(user);
        User userSelected = selectByUid(user.getUid());
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
    public User getUserBySession(String session) {
        return userMap.get(session);
    }

    @Override
    public User selectByUid(int uid) {
        return userDAO.selectByUid(uid);
    }

    @Override
    public int insert(User user) {
        checkUserInfo(user);
        return userDAO.insert(user);
    }

    /**
     * check whther user info is valid
     * @param user user model
     */
    private void checkUserInfo(User user) {
        if (user == null || !(StringUtils.isNotEmpty(user.getUname()) &&
                StringUtils.isNotEmpty(user.getPasswd()) &&
                StringUtils.isNotEmpty(user.getFName()) &&
                StringUtils.isNotEmpty(user.getLName()) )) {
            throw new NBCException("invalid user info", NBCResultCodeEnum.INVALID_DATA);
        }
    }

    @Override
    public int update(User user) {
        if (user == null) {
            return -1;
        }
        return userDAO.update(user);
    }

    @Override
    public void applyJoinBlock(int bid, int uid) {
        // todo

    }

    @Override
    public void decideApplication(int applicant, int uid) {
        // todo
    }
}