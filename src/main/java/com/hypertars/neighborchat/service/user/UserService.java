package com.hypertars.neighborChat.service.user;

import com.hypertars.neighborChat.model.Users;

public interface UserService {

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

    /**
     * join in a block
     * @param bid blockid
     * @param uid user id
     */
    void applyJoinBlock(int bid, int uid);

    /**
     * approve or reject an application
     * @param applicant app id
     * @param uid user id
     */
    void decideApplication(int applicant, int uid);

}