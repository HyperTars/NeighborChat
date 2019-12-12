package com.hypertars.neighborChat.service.block;

/**
 * @author Visionary
 * @since 2019/12/11 9:25 PM
 */
public interface BlockService {

    void userJoinInBlock(int uid, int bid);

    void agreeApplicant(int applicant);

    void rejectApplicant(int applicant);

}
