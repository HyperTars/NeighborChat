package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.service.Membership.MembershipService;
import com.hypertars.neighborChat.service.Message.MessageService;
import com.hypertars.neighborChat.service.Relationship.RelationshipService;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import com.hypertars.neighborChat.web.NBCBaseController;
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
    private MembershipService blockService;

    @Resource
    private RelationshipService relationshipService;

    public String getAllUserBlocksForCurrentUser(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);
    }

    public String blocksAvailable(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);
    }

    public String applyBlock(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);
    }

    public String decideBlockApplication(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);
    }

    public String exitCurrentBlock(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);
    }
}
