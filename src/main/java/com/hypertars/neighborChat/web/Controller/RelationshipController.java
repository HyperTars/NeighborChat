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

    public String getAllFriends(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String getAllNeighbors(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String getAllStrangersNearby(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String applyFriend(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String decideFriendApplication(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String addNeighbor(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String deleteFriend(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }

    public String deleteNeighbor(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();
        return JSON.toJSONString(result);

    }
}

