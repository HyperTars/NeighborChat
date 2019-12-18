package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.BlockApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlockApplicationDAO {

    int insert(BlockApplication blockApplication);

    void update(BlockApplication blockApplication);

    List<BlockApplication> selectByUidStatus(@Param("applicant") int uid, @Param("status") boolean status);

    List<BlockApplication> selectByBidStatus(@Param("bid") int bid, @Param("status") boolean status);



    /**
     * get all applications belong to the Applicant
     * @param applicant applicant uid
     * @return List<BlockApplication> models
     */
    List<BlockApplication> getBlockApplicationByApplicant(@Param("applicant") int applicant);

    /**
     * add new block application
     * @param blockApplication blockApplication model
     */
    void addBlockApplication(BlockApplication blockApplication);

    /** Block member accepts application
     */
    void acceptBlockApplication(BlockApplication blockApplication);

    /** Block member rejects application
     */
    void rejectBlockApplication(BlockApplication blockApplication);

    /**
     * Notify recipient if new application in his/her block
     * @param uid recipient uid
     * @return List<BlockApplication> BlockApplications models
     */
    List<BlockApplication> notifyNewBlockApplication (@Param("uid") int uid);

    /**
     * If notified block application decision, delete it
     * @param blockApplication blockApplication model
     */
    void deleteBlockApplication(BlockApplication blockApplication);

    int checkBlockApplicationExist(BlockApplication blockApplication);

    BlockApplication getExactBlockApplication(BlockApplication blockApplication);
}
