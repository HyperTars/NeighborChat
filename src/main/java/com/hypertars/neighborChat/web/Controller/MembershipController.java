package com.hypertars.neighborChat.web.Controller;

import com.hypertars.neighborChat.service.Membership.MembershipService;
import com.hypertars.neighborChat.service.Message.MessageService;
import com.hypertars.neighborChat.service.Relationship.RelationshipService;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import com.hypertars.neighborChat.web.NBCBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
