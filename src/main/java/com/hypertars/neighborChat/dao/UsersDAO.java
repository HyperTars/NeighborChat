package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDAO {
    /**
     * insert an user info into db
     * @param user user model
     * @return 1 or 0
     */
    int insert(Users user);

    /**
     * update an user info into db
     * @param user user model
     * @return 1 or 0
     */
    int update(Users user);

    /**
     * select by user id
     * @param uid user id
     * @return user
     */
    Users selectByUid(int uid);
}
