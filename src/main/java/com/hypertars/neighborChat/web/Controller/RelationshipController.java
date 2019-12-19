package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.Membership.MembershipService;
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
@RequestMapping("Relationship")
public class RelationshipController extends NBCBaseController {
    @Resource
    private UserAccountService userService;

    @Resource
    private com.hypertars.neighborChat.service.Message.MessageService MessageService;

    @Resource
    private MembershipService blockService;

    @Resource
    private RelationshipService relationshipService;

    @RequestMapping(value = "getAllFriends", produces = "text/script;charset=UTF-8")
    public String getAllFriends(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(relationshipService.getFriendsByUid(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getAllNeighbors", produces = "text/script;charset=UTF-8")
    public String getAllNeighbors(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(relationshipService.getNeighborsByUid(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getAllStrangersFromBlock", produces = "text/script;charset=UTF-8")
    public String getAllStrangersFromBlock(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(relationshipService.getStrangersFromSameBlock(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getAllStrangersFromHood", produces = "text/script;charset=UTF-8")
    public String getAllStrangersFromHood(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(relationshipService.getStrangersFromSameHood(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "applyFriend", produces = "text/script;charset=UTF-8")
    public String applyFriend(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                int recipient = Integer.parseInt(request.getParameter("recipient"));
                String txt = request.getParameter("txt");
                if (relationshipService.checkFriendApplicationExist(user.getUid(), recipient)) {
                    res.setResultObj("friend application already exists");
                    return res;
                } else if (relationshipService.addFriendApplication(user.getUid(), recipient, txt)) {
                    res.setResultObj("success");
                } else res.setResultObj("failure");
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    public String decideFriendApplication(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                int applicant = Integer.parseInt(request.getParameter("applicant"));
                boolean decision = Boolean.parseBoolean(request.getParameter("decision"));
                if (!decision) {
                    relationshipService.rejectFriendApplication(applicant, user.getUid());
                } else if (decision) {
                    relationshipService.acceptFriendApplication(applicant, user.getUid());
                }

                res.setResultObj(null);
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    public String addNeighbor(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String deleteFriend(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();

                res.setResultObj(null);
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    public String deleteNeighbor(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();

                res.setResultObj(null);
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    public String getAllFriendApplicationAsRecipient(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();

                res.setResultObj(null);
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }
}

