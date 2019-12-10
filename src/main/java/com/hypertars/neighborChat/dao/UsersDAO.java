package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDAO {

    /**
     * check whether uname is already exists
     *
     * @param uname user name
     * @return Integer (count uname)
     */
    boolean checkUName(@Param("uname") String uname);

    /**
     * register user
     *
     * @param user user model
     * @return void
     */
    boolean userReg(Users user);

    /**
     * user auth with uname or email
     *
     * @param user user model
     * @return uid & password
     */
    Users userAuth(Users user);

    /**
     * restore password with uname and email
     *
     * @param user user model
     * @return 1 or 0
     */
    boolean restorePasswd(Users user);

    /**
     * select by user id
     *
     * @param uid user id
     * @return user
     */
    Users selectByUid(@Param("uid") Integer uid);

    /**
     * update an user info into db
     *
     * @param user user model
     * @return 1 or 0
     */
    boolean userUpdate(Users user);
}


