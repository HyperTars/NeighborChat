package com.hypertars.neighborChat.service.user;

import com.hypertars.neighborChat.model.User;

public interface UserService {

    /**
     * insert an user info to db
     * @param user user model
     * @return 1, if success;0 or -1, else
     */
    int insert(User user);

    /**
     * update an user info to db
     * @param user user model
     * @return 1, if success;0 or -1, else
     */
    int update(User user);

    /**
     * select by user id
     * @param uid user id
     * @return user
     */
    User selectByUid(int uid);

    /**
     * login in
     * @param user attr: uid, pass needed
     */
    String loginIn(User user);

    /**
     * get user by session value
     * @param session session
     * @return user
     */
    User getUserBySession(String session);

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