package com.hypertars.neighborChat.service.membership;

import com.hypertars.neighborChat.model.BlockApplication;
import com.hypertars.neighborChat.model.UserBlock;

import java.util.List;

public interface membershipService {

    /** apply for block */
    List<UserBlock> getUserBlocksByUid (int uid);
    boolean checkBlockApplicationExist (BlockApplication ba);
    boolean addBlockApplication(int applicant, int bid, String txt);

    /** execute application */
    boolean acceptBlockApplication (int applicant, int uid);
    boolean rejectBlockApplication (int applicant, int uid);
    boolean deleteBlockApplication (int applicant, int bid);

    /** check application */
    List<BlockApplication> getBlockApplicationByApplicant (int applicant);
    boolean checkBlockApplicationAcceptance (BlockApplication ba);
    boolean checkBlockApplicationFailure (BlockApplication ba);

    /** block member */
    boolean joinBlock (int uid, int bid);
    boolean quitBlock (int uid);

    /** notification */
    List<BlockApplication> notifyPassedBlockApplicationFromApplicant (int applicant);
    List<BlockApplication> notifyFailedBlockApplicationFromApplicant (int applicant);
    List<BlockApplication> notifyNewBlockApplicationToRecipient (int recipient);
    List<UserBlock> notifyNewBlockMember (int uid);



}
