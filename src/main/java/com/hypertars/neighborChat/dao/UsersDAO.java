package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersDAO {
    /**
     * insert an user info into db
     * @param user user model
     * @return 1 or 0
     */
    void insert(Users user);

    /**
     * update an user info into db
     * @param user user model
     * @return 1 or 0
     */
    void update(Users user);

    /**
     * select by user id
     * @param uid user id
     * @return user
     */
    Users selectByUid(@Param("uid") Integer uid);

    Users selectByUName(@Param("uname") String uname);
}
