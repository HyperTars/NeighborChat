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
}
