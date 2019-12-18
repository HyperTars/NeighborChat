package com.hypertars.neighborChat.service.userAccount;

import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;

import java.util.List;
import java.util.Map;

public interface userAccountService {

    /**
     * insert an user info to db
     * @param user user model
     * @return 1, if success;0 or -1, else
     */
    void insert(Users user);

    /**
     * update an user info to db
     * @param user user model
     * @return 1, if success;0 or -1, else
     */
    void update(Users user);

    /**
     * select by user id
     * @param uid user id
     * @return user
     */
    Users selectByUid(int uid);

    /**
     * login in
     * @param user attr: uid, pass needed
     */
    String loginIn(Users user);

    /**
     * get user by session value
     * @param session session
     * @return user
     */
    Users getUserBySession(String session);

    List<Users> getUsersByBid(int bid);

    UserBlock getUserBlockByUid(int uid);

    Map<String, List<Users>> getUsersByHid(int hid);

    /** register */
    int checkUName (int uid);
    int userReg (Users user);

    /** forget password */
    int checkUserInfo (String uname, String email);
    int resetPassword (int uid, String passwd);

    /** update user info */
    int updateUserInfo (Users user);
}