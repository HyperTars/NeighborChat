package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersDAO {

    Users selectByUid(@Param("uid") int uid);

    void insert(Users users);

    void update(Users users);

    Users selectByUName(@Param("uname") String uname);


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
    boolean updateInfo(Users user);

    /**
     * update lastLog
     * @return 1 or 0
     */
    boolean updateLastLog();

    /**
     * Select users profiles from current user's friends
     * @param uid current user id
     * @return List<Users> Users models
     */
    List<Users> userFromFriends(@Param("uid") Integer uid);

    /**
     * Select users profiles from current user's neighbors
     * @param uid current user id
     * @return List<Users> Users models
     */
    List<Users> userFromNeighbors(@Param("uid") Integer uid);

    /**
     * Select users profiles from current user's same building
     * @param uid current user id
     * @return List<Users> Users models
     */
    List<Users> userFromSameBuilding(@Param("uid") Integer uid);

    /**
     * Select users profiles from current user's same block
     * @param uid current user id
     * @return List<Users> Users models
     */
    List<Users> userFromSameBlock(@Param("uid") Integer uid);

    /**
     * Select users profiles from current user's same hood
     * @param uid current user id
     * @return List<Users> Users models
     */
    List<Users> userFromSameHood(@Param("uid") Integer uid);

}


