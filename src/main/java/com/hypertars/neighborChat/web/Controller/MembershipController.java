package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.model.BlockApplication;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.Membership.MembershipService;
import com.hypertars.neighborChat.service.Message.MessageService;
import com.hypertars.neighborChat.service.Relationship.RelationshipService;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCLogicCallBack;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("Membership")
public class MembershipController extends NBCBaseController {

    @Resource
    private UserAccountService userService;

    @Resource
    private MessageService MessageService;

    @Resource
    private MembershipService membershipService;

    @Resource
    private RelationshipService relationshipService;

    @RequestMapping(value = "getAllUserBlocksForCurrentUser", produces = "text/script;charset=UTF-8")
    public String getAllUserBlocksForCurrentUser(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(membershipService.getUserBlocksByUid(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "blocksAvailable", produces = "text/script;charset=UTF-8")
    public String blocksAvailable(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                res.setResultObj(membershipService.getAllBlocks());
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "applyBlock", produces = "text/script;charset=UTF-8")
    public String applyBlock(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                int bid = Integer.parseInt(request.getParameter("bid"));
                String txt = request.getParameter("txt");
                BlockApplication ba = new BlockApplication();
                ba.setBid(bid);
                ba.setApplicant(user.getUid());
                if (membershipService.checkBlockApplicationExist(ba)) {
                    res.setResultObj("Application Already Exists");
                } else {
                    membershipService.addBlockApplication(user.getUid(), bid, txt);
                }
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "decideBlockApplication", produces = "text/script;charset=UTF-8")
    public String decideBlockApplication(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                int applicant = Integer.parseInt(request.getParameter("applicant"));
                short decision = Short.parseShort(request.getParameter("decision"));
                BlockApplication ba = new BlockApplication();
                ba.setApplicant(applicant);
                ba.setBid(membershipService.getCurrentMember(user.getUid()).getBid());
                if (!membershipService.checkBlockApplicationExist(ba)) {
                    res.setResultObj("block application not exists");
                } else if (decision == 1) {
                    membershipService.acceptBlockApplication(applicant, user.getUid());
                    res.setResultObj("accept succeeded");
                } else if (decision == 0) {
                    membershipService.rejectBlockApplication(applicant, user.getUid());
                    res.setResultObj("reject succeeded");
                } else {
                    res.setResultObj("failure");
                }
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "exitCurrentBlock", produces = "text/script;charset=UTF-8")
    public String exitCurrentBlock(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                if (membershipService.quitBlock(user.getUid())) {
                    res.setResultObj("success");
                } else {
                    res.setResultObj("failure");
                }
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }
}
