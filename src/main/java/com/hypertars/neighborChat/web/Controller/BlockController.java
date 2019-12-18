package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.dao.BlockApplicationDAO;
import com.hypertars.neighborChat.dao.BlocksDAO;
import com.hypertars.neighborChat.dao.UserBlockDAO;
import com.hypertars.neighborChat.model.BlockApplication;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.block.BlockService;
import com.hypertars.neighborChat.service.user.UserService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCLogicCallBack;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import sun.management.snmp.jvminstr.JvmOSImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Visionary
 * @since 2019/12/10 9:00 PM
 */
@RestController
@RequestMapping("blocks")
public class BlockController extends NBCBaseController {

    @Resource
    private UserService userService;

    @Resource
    private BlockService blockService;

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private BlockApplicationDAO blockApplicationDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @GetMapping("getUsersNearby")
    public String getUsersNearby(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                Users user = loginUsers.get();
                UserBlock userBlock = userService.getUserBlockByUid(user.getUid());
                Map<String, List<Users>> usersList = userService.getUsersByHid(blocksDAO.selectByBid(userBlock.getBid()).getHid());
                NBCResult<Object> result = new NBCResult<>();
                result.setResultObj(usersList);
                return result;
            }
        });
        return JSON.toJSONString(res);
    }

    @GetMapping("joinInBlock")
    public String joinInBlock(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                String bid = request.getParameter("bid");
                blockService.userJoinInBlock(loginUsers.get().getUid(), Integer.parseInt(bid));
                return new NBCResult<Object>();
            }
        });
        return JSON.toJSONString(res);
    }

    @GetMapping("allApplicants")
    public String allApplicants(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                int bid = userBlockDAO.getUserBlocksByUid(loginUsers.get().getUid()).getBid();
                List<BlockApplication> blockApplications = blockApplicationDAO.selectByBidStatus(bid, true);
                NBCResult<Object> result = new NBCResult<>();
                result.setResultObj(blockApplications);
                return result;
            }
        });
        return JSON.toJSONString(res);
    }

    @GetMapping("agreeApplicant")
    public String agreeApplicant(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                int applicant = Integer.parseInt(request.getParameter("applicant"));
                blockService.agreeApplicant(applicant);
                return new NBCResult<Object>();
            }
        });
        return JSON.toJSONString(res);
    }

    @GetMapping("rejectApplicant")
    public String rejectApplicant(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                int applicant = Integer.parseInt(request.getParameter("applicant"));
                blockService.rejectApplicant(applicant);
                return new NBCResult<Object>();
            }
        });
        return JSON.toJSONString(res);
    }

}
