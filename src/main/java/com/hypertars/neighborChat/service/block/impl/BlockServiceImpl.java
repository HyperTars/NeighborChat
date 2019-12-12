package com.hypertars.neighborChat.service.block.impl;

import com.hypertars.neighborChat.dao.BlockApplicationDAO;
import com.hypertars.neighborChat.dao.BlocksDAO;
import com.hypertars.neighborChat.dao.UserBlockDAO;
import com.hypertars.neighborChat.enums.NBCResultCodeEnum;
import com.hypertars.neighborChat.exception.NBCException;
import com.hypertars.neighborChat.model.BlockApplication;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.service.block.BlockService;
import com.hypertars.neighborChat.utils.AssertUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Visionary
 * @since 2019/12/11 9:25 PM
 */
@Service
public class BlockServiceImpl implements BlockService {

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @Resource
    private BlockApplicationDAO blockApplicationDAO;

    @Override
    public void userJoinInBlock(int uid, int bid) {
        UserBlock userBlock = userBlockDAO.getUserBlocksByUid(uid);
        AssertUtils.assertNotNull(userBlock);
        userBlock.setStatus(false);
        userBlockDAO.update(userBlock);

        List<BlockApplication> blockApplications = blockApplicationDAO.selectByUidStatus(uid, true);
        if (blockApplications.size() > 1) {
            throw new NBCException("user can only apply one block", NBCResultCodeEnum.INVALID_DATA);
        }

        BlockApplication blockApplication = new BlockApplication();
        blockApplication.setApplicant(uid);
        blockApplication.setBid(bid);
        blockApplicationDAO.insert(blockApplication);
    }

    @Override
    public void agreeApplicant(int applicant) {
        BlockApplication blockApplication = blockApplicationDAO.selectByUidStatus(applicant, true).get(0);
        AssertUtils.assertNotNull(blockApplication);

        blockApplication.setAccepts(blockApplication.getAccepts() + 1);
        blockApplication.setDecisions(blockApplication.getDecisions() + 1);

        int temp = applicantCallBack(blockApplication.getAccepts(), blockApplication.getDecisions(), userBlockDAO.getUserBlocksByBid(blockApplication.getBid()).size());
        if (temp == 1) {
            blockApplication.setStatus(false);
            blockApplicationDAO.update(blockApplication);
            UserBlock userBlock = new UserBlock();
            userBlock.setBid(blockApplication.getBid());
            userBlock.setUid(blockApplication.getApplicant());
            userBlockDAO.insert(userBlock);
        } else if (temp == -1) {
            blockApplication.setStatus(false);
            blockApplicationDAO.update(blockApplication);
        }
        // todo 通知结果
    }

    @Override
    public void rejectApplicant(int applicant) {
        BlockApplication blockApplication = blockApplicationDAO.selectByUidStatus(applicant, true).get(0);
        AssertUtils.assertNotNull(blockApplication);

        blockApplication.setDecisions(blockApplication.getDecisions() + 1);

        int temp = applicantCallBack(blockApplication.getAccepts(), blockApplication.getDecisions(), userBlockDAO.getUserBlocksByBid(blockApplication.getBid()).size());
        if (temp == -1) {
            blockApplication.setStatus(false);
            blockApplicationDAO.update(blockApplication);
        }
        // todo 通知结果
    }

    private int applicantCallBack(int accepts, int decisions, int sum) {
        int line = sum < 3 ? sum : 3;
        if (accepts >= line) {
            return 1;
        } else if (sum - decisions < line - accepts) {
            return -1;
        }
        return 0;
    }
}
