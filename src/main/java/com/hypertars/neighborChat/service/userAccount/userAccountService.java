package com.hypertars.neighborChat.service.userAccount;

import com.hypertars.neighborChat.model.Users;

public interface userAccountService {

    /** register */
    boolean checkUName (String UName);
    boolean userReg (String uname, String passwd, String email, String fName, String lName);

    /** login */
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

    /** forget password */
    Users userAuth (String uname, String email);
    boolean resetPassword (int uid, String passwd);

    /** update user info */
    boolean updateUserInfo (String uname, String passwd, String email, String fName, String lName, String addr1,
                            String addr2, String intro, String photo, short nRange, boolean notify);

    /** load info */
    Users getUserByUid (int uid);

    void updateLastLog (int uid);
}