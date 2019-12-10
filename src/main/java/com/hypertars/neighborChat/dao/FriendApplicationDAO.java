package com.hypertars.neighborChat.dao;

import com.hypertars.neighborChat.model.BlockApplication;
import com.hypertars.neighborChat.model.FriendApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendApplicationDAO {

    /**
     * get all friend applications belong to the applicant
     * @param applicant uid applicant
     * @return List<FriendApplication> models
     */
    List<FriendApplication> getFriendApplicationByApplicant(@Param("applicant") int applicant);

    /**
     * get all friend applications belong to the recipient
     * @param recipient uid
     * @return List<FriendApplication> models
     */
    List<FriendApplication> getFriendApplicationByRecipient(@Param("recipient") int recipient);

    /**
     * update timestamp of the friend application (if exists)
     * @return 1 or 0
     */
    boolean updateFriendApplication(@Param("applicant") int applicant, @Param("recipient") int recipient);

    /**
     * add new friend application (if not exists)
     * @param friendApplication friend application
     * @return 1 or 0
     */
    boolean addFriendApplication(FriendApplication friendApplication);

    /**
     * accept friend application (mark decision as True)
     * @param applicant applicant uid
     * @param recipient recipient uid
     * @return 1 or 0
     */
    boolean acceptFriendApplication(@Param("applicant") int applicant, @Param("recipient") int recipient);

    /**
     * reject friend application (mark decision as False)
     * @param applicant applicant uid
     * @param recipient recipient uid
     * @return 1 or 0
     */
    boolean rejectFriendApplication(@Param("applicant") int applicant, @Param("recipient") int recipient);

    /**
     * Notify applicant if friend applications made
     * @param applicant uid
     * @return List<FriendApplication> friendApplications models
     */
    List<FriendApplication> notifyFriendApplicationMade (@Param("applicant") int applicant);

    /**
     * notify recipient if new friend application
     * @param recipient recipient uid
     * @return List<FriendApplication> friendapplication model
     */
    List<FriendApplication> notifyNewFriendApplication(@Param("recipient") int recipient);

    /**
     * If notified friend application decision, delete it
     * @param friendApplication friendApplication model
     * @return 1 or 0
     */
    boolean deleteFriendApplication(FriendApplication friendApplication);
}
