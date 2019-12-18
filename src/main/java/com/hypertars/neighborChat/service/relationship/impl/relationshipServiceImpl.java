package com.hypertars.neighborChat.service.relationship.impl;

import com.hypertars.neighborChat.dao.*;
import com.hypertars.neighborChat.model.FriendApplication;
import com.hypertars.neighborChat.model.Friends;
import com.hypertars.neighborChat.model.Neighbors;
import com.hypertars.neighborChat.service.relationship.relationshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class relationshipServiceImpl implements relationshipService {

    @Resource
    private BlockApplicationDAO blockApplicationDAO;

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private FriendApplicationDAO friendApplicationDAO;

    @Resource
    private FriendsDAO friendsDAO;

    @Resource
    private HoodsDAO hoodsDAO;

    @Resource
    private MailBoxDAO mailBoxDAO;

    @Resource
    private MessageDAO messageDAO;

    @Resource
    private NeighborsDAO neighborsDAO;

    @Resource
    private RecipientDAO recipientDAO;

    @Resource
    private ReplyDAO replyDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @Resource
    private UsersDAO usersDAO;

    /**
     * check whether friendship already exists
     * @param uidA uidA
     * @param uidB uidB
     * @return true or false
     */
    @Override
    public boolean checkFriendship(int uidA, int uidB) {
        Friends friends = new Friends();
        friends.setUidA(uidA);
        friends.setUidB(uidB);
        return friendsDAO.checkFriends(friends) > 0;
    }

    /**
     * check whether friend application exists
     * @param applicant user id
     * @param recipient user id
     * @return true or false
     */
    @Override
    public boolean checkFriendApplicationExist(int applicant, int recipient) {
        FriendApplication fa = new FriendApplication();
        fa.setApplicant(applicant);
        fa.setRecipient(recipient);
        return friendApplicationDAO.checkFriendApplication(fa) > 0;
    }

    /**
     * add friend application, check exists before adding, if exists, update timestamp
     * @param applicant user id
     * @param recipient user id
     * @param txt text
     * @return true or false
     */
    @Override
    public boolean addFriendApplication(int applicant, int recipient, String txt) {
        FriendApplication fa = new FriendApplication();
        fa.setApplicant(applicant);
        fa.setRecipient(recipient);
        fa.setTxt(txt);
        if (checkFriendApplicationExist(applicant, recipient)) {
            friendApplicationDAO.updateFriendApplication(fa);
            return false;
        }
        friendApplicationDAO.addFriendApplication(fa);
        return true;
    }

    /**
     * accept friend application
     * @param applicant applicant uid
     * @param recipient recipient uid
     * @return true or false
     */
    @Override
    public boolean acceptFriendApplication(int applicant, int recipient) {
        FriendApplication fa = new FriendApplication();
        fa.setApplicant(applicant);
        fa.setRecipient(recipient);
        friendApplicationDAO.acceptFriendApplication(fa);
        return true;
    }

    /**
     * reject friend application
     * @param applicant applicant uid
     * @param recipient recipient uid
     * @return true or false
     */
    @Override
    public boolean rejectFriendApplication(int applicant, int recipient) {
        FriendApplication fa = new FriendApplication();
        fa.setApplicant(applicant);
        fa.setRecipient(recipient);
        friendApplicationDAO.rejectFriendApplication(fa);
        return true;
    }

    /**
     * delete friend application
     * @param applicant uid
     * @param recipient uid
     * @return true or false
     */
    @Override
    public boolean deleteFriendApplication(int applicant, int recipient) {
        FriendApplication fa = new FriendApplication();
        fa.setApplicant(applicant);
        fa.setRecipient(recipient);
        if (friendApplicationDAO.checkFriendApplication(fa) == 0)
            return true;
        friendApplicationDAO.deleteFriendApplication(fa);
        return true;
    }

    /**
     * get friend applications by applicant
     * @param applicant uid
     * @return list of friend applications
     */
    @Override
    public List<FriendApplication> getFriendApplicationByApplicant(int applicant) {
        return friendApplicationDAO.getFriendApplicationByApplicant(applicant);
    }

    /**
     * get friend applications by recipient
     * @param recipient uid
     * @return list of applications
     */
    @Override
    public List<FriendApplication> getFriendApplicationByRecipient(int recipient) {
        return friendApplicationDAO.getFriendApplicationByRecipient(recipient);
    }

    /**
     * delete friendship
     * @param uidA uid
     * @param uidB uid
     * @return true or false
     */
    @Override
    public boolean deleteFriend(int uidA, int uidB) {
        if (!checkFriendship(uidA, uidB)) return true;
        Friends friends = new Friends();
        friends.setUidA(uidA);
        friends.setUidB(uidB);
        friendsDAO.deleteFriend(friends);
        return true;
    }

    /**
     * add friendship
     * @param uidA uidA
     * @param uidB uidB
     * @return true or false
     */
    @Override
    public boolean addFriends(int uidA, int uidB) {
        if (checkFriendship(uidA, uidB)) return true;
        Friends friends = new Friends();
        int uidMin = Math.min(uidA, uidB);
        int uidMax = Math.max(uidA, uidB);
        friends.setUidA(uidMin);
        friends.setUidB(uidMax);
        friendsDAO.addFriend(friends);
        return true;
    }

    /**
     * check whether already neighbor
     * @param uidA uid
     * @param uidB uid
     * @return true or false
     */
    @Override
    public boolean checkNeighborExist(int uidA, int uidB) {
        Neighbors neighbors = new Neighbors();
        neighbors.setUidA(uidA);
        neighbors.setUidB(uidB);
        return neighborsDAO.checkNeighbor(neighbors) > 0;
    }

    /**
     * add new neighbors, check before adding
     * @param uidA uid
     * @param uidB uid
     * @return true or false
     */
    @Override
    public boolean addNeighbor(int uidA, int uidB) {
        if (checkFriendship(uidA, uidB)) {
            return true;
        }
        Neighbors neighbors = new Neighbors();
        neighbors.setUidA(uidA);
        neighbors.setUidB(uidB);
        neighborsDAO.addNeighbor(neighbors);
        return true;
    }

    /**
     * delete neighbor
     * @param uidA uid
     * @param uidB uid
     * @return true or false
     */
    @Override
    public boolean deleteNeighbor(int uidA, int uidB) {
        if (!checkFriendship(uidA, uidB)) {
            return true;
        }
        Neighbors neighbors = new Neighbors();
        neighbors.setUidA(uidA);
        neighbors.setUidB(uidB);
        neighborsDAO.deleteNeighbor(neighbors);
        return true;
    }

    /**
     * delete all neighbors belong to uid
     * @param uid user id
     * @return true or false
     */
    @Override
    public boolean deleteAllNeighbors(int uid) {
        neighborsDAO.deleteAllNeighbors(uid);
        return true;
    }

    /**
     * notify new friend application to recipient since lastLog
     * @param recipient uid
     * @return list of friend applications
     */
    @Override
    public List<FriendApplication> notifyNewFriendApplicationToRecipient(int recipient) {
        friendApplicationDAO.notifyNewFriendApplication(recipient);
        return null;
    }

    /**
     * notify new friend application made to applicant since lastLog
     * @param applicant uid
     * @return list of friend applications
     */
    @Override
    public List<FriendApplication> notifyNewFriendApplicationMadeToApplicant(int applicant) {
        return friendApplicationDAO.notifyFriendApplicationMade(applicant);
    }

    /**
     * notify new friend application accepted to applicant since lastLog
     * @param applicant uid
     * @return list of friend applications
     */
    @Override
    public List<FriendApplication> notifyPassedFriendApplicationFromApplicant(int applicant) {
        List<FriendApplication> passed = new ArrayList<>();
        List<FriendApplication> newMade = notifyNewFriendApplicationMadeToApplicant(applicant);
        for (FriendApplication fa: newMade) {
            if (fa.getDecision() == 1)
                passed.add(fa);
        }
        return passed;
    }

    /**
     * notify new friend application rejected to applicant since last log
     * @param applicant uid
     * @return list of friend applications
     */
    @Override
    public List<FriendApplication> notifyFailedFriendApplicationFromApplicant(int applicant) {
        List<FriendApplication> rejected = new ArrayList<>();
        List<FriendApplication> newMade = notifyNewFriendApplicationMadeToApplicant(applicant);
        for (FriendApplication fa: newMade) {
            if (fa.getDecision() == 0)
                rejected.add(fa);
        }
        return rejected;
    }
}
