package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDAO {

    /**
     * check whether uname is already exists
     *
     * @param uname user name
     * @return Integer (count uname)
     */
    int checkUName(@Param("uname") String uname);

    /**
     * register user
     *
     * @param user user model
     * @return 1 or 0
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
    Users getUserInfoByUid(@Param("uid") Integer uid);

    /**
     * update an user info into db
     *
     * @param user user model
     * @return 1 or 0
     */
    boolean userUpdate(Users user);

    /**
     * get users from same building
     * @param uid user id
     * @return List<Users> users models
     */
    List<Users> getSameBulding(@Param("uid") Integer uid);

    /**
     * update lastLog
     * @return 1 or 0
     */
    boolean updateLastLog();
}


