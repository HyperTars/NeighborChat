package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    /**
     * insert an user info into db
     * @param user user model
     * @return 1 or 0
     */
    int insert(User user);

    /**
     * update an user info into db
     * @param user user model
     * @return 1 or 0
     */
    int update(User user);

    /**
     * select by user id
     * @param uid user id
     * @return user
     */
    User selectByUid(int uid);
}
