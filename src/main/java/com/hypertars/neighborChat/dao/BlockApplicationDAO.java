package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.BlockApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sun.jvm.hotspot.opto.Block;

import java.util.Date;
import java.util.List;

@Mapper
public interface BlockApplicationDAO {

    /**
     * get all applications belong to the Applicant
     * @param applicant applicant uid
     * @return List<BlockApplication> models
     */
    List<BlockApplication> getBlockApplicationByApplicant(@Param("applicant") int applicant);

    /**
     * get all applications belong to the Recipient
     * @param uid recipient uid
     * @return List<BlockApplication> models
     */
    List<BlockApplication> getBlockApplicationByRecipient(@Param("uid") int uid);

    /**
     * add new block application
     * @param blockApplication blockApplication model
     * @return 1 or 0
     */
    boolean addBlockApplication(BlockApplication blockApplication);

    /** Block member accepts application
     * @return 1 or 0
     */
    boolean acceptBlockApplication(@Param("applicant") int applicant, @Param("bid") int bid);

    /** Block member rejects application
     * @return 1 or 0
     */
    boolean rejectBlockApplication(@Param("applicant") int applicant, @Param("bid") int bid);

    /**
     * Notify recipient if new application in his/her block
     * @param uid recipient uid
     * @return List<BlockApplication> BlockApplications models
     */
    List<BlockApplication> notifyNewBlockApplication (@Param("uid") int uid);

    /**
     * Notify applicant if block applications pass
     * @param applicant uid
     * @return List<BlockApplication> BlockApplications models
     */
    List<BlockApplication> notifyPassBlockApplication (@Param("applicant") int applicant);

    /**
     * Notify applicant if block applications fail
     * @param applicant uid
     * @return List<BlockApplication> BlockApplications models
     */
    List<BlockApplication> notifyFailBlockApplication (@Param("applicant") int applicant);

    /**
     * If notified block application decision, delete it
     * @param blockApplication blockApplication model
     * @return 1 or 0
     */
    boolean deleteBlockApplication(BlockApplication blockApplication);
}
