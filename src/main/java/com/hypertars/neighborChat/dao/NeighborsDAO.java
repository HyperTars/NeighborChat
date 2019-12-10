package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.Friends;
import com.hypertars.neighborChat.model.Neighbors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NeighborsDAO {

    /**
     * get all neighbors of the user
     * @param uid user id
     * @return List<Neighbors> neighbors models
     */
    List<Neighbors> getNeighbors(@Param("uid") int uid);

    /**
     * get all neighbors of the user from same block
     * @param uid user id
     * @return List<Neighbors> neighbors models
     */
    List<Neighbors> getNeighborsFromBlock(@Param("uid") int uid);

    /**
     * get all neighbors of the user from same hood
     * @param uid user id
     * @return List<Neighbors> neighbors models
     */
    List<Neighbors> getNeighborsFromHood(@Param("uid") int uid);

    /**
     * check whether uid A and uid B are already neighbors
     * @param uidA uid A
     * @param uidB uid B
     * @return count
     */
    int checkNeighbors(@Param("uidA") int uidA, @Param("uidB") int uidB);

    /**
     * add new neighbors
     * @param uidA current user uid
     * @param uidB neighbor uid
     * @return 1 or 0
     */
    boolean addNeighbor(@Param("uidA") int uidA, @Param("uidB") int uidB);

    /**
     * delete neighbor
     * @param uidA current user uid
     * @param uidB neighbor uid
     * @return 1 or 0
     */
    boolean deleteNeighbor(@Param("uidA") int uidA, @Param("uidB") int uidB);
}
