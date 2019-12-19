package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.service.Membership.MembershipService;
import com.hypertars.neighborChat.service.Relationship.RelationshipService;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("Message")
public class MessageController extends NBCBaseController {
    @Resource
    private UserAccountService userService;

    @Resource
    private com.hypertars.neighborChat.service.Message.MessageService MessageService;

    @Resource
    private MembershipService blockService;

    @Resource
    private RelationshipService relationshipService;

    public String getAllMessageThreads(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String getMessageAndReplyByThread(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String addMessage(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String addReply(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }
}