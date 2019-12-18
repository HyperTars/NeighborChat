package com.hypertars.neighborChat.service.relationship;

import com.hypertars.neighborChat.model.FriendApplication;

import java.util.List;

public interface relationshipService {

    /** friend application*/
    boolean checkFriendApplicationExist (int applicant, int recipient);
    boolean addFriendApplication(int applicant, int recipient, String txt);
    boolean acceptFriendApplication (int applicant, int recipient);
    boolean rejectFriendApplication (int applicant, int recipient);
    boolean deleteFriendApplication (int applicant, int recipient);

    List<FriendApplication> getFriendApplicationByApplicant (int applicant);
    List<FriendApplication> getFriendApplicationByRecipient (int recipient);

    /** friend */
    boolean checkFriendship (int uidA, int uidB);
    boolean deleteFriend (int uidA, int uidB);
    boolean addFriends (int uidA, int uidB);

    /** neighbor */
    boolean checkNeighborExist (int uidA, int uidB);
    boolean addNeighbor (int uidA, int uidB);
    boolean deleteNeighbor (int uidA, int uidB);
    boolean deleteAllNeighbors (int uid);

    /** notification */
    List<FriendApplication> notifyNewFriendApplicationToRecipient (int recipient);
    List<FriendApplication> notifyNewFriendApplicationMadeToApplicant (int applicant);
    List<FriendApplication> notifyPassedFriendApplicationFromApplicant (int applicant);
    List<FriendApplication> notifyFailedFriendApplicationFromApplicant (int applicant);
}
