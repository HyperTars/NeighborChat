package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
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
@RequestMapping("message")
public class MessageController extends NBCBaseController {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private MessageService messageService;

    @Resource
    private MembershipService membershipService;

    @Resource
    private RelationshipService relationshipService;

    @RequestMapping(value = "getAllMessageThreads", produces = "text/script;charset=UTF-8")
    public String getAllMessageThreads(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(messageService.getMessageByRecipient(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getMsgidUnread", produces = "text/script;charset=UTF-8")
    public String getMsgidUnread(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                res.setResultObj(messageService.getMsgidUnread(user.getUid()));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getReplyByThread", produces = "text/script;charset=UTF-8")
    public String getReplyByThread(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                int msgid = Integer.parseInt(request.getParameter("msgid"));
                res.setResultObj(messageService.getRecipientByMsgid(msgid));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "addMessage", produces = "text/script;charset=UTF-8")
    public String addMessage(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                Users user = loginUsers.get();
                // input recipient = 0 if not particular
                int recipient = Integer.parseInt(request.getParameter("recipient"));
                int rRange = Integer.parseInt(request.getParameter("rRange"));
                String title = request.getParameter("title");
                String sub = request.getParameter("sub");
                String txt = request.getParameter("txt");
                String coord = request.getParameter("coord");
                if (!messageService.addNewMsg(recipient, user.getUid(), rRange, title, sub, txt, coord)) {
                    res.setResultObj("insert new message error");
                } else res.setResultObj("success");
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "addReply", produces = "text/script;charset=UTF-8")
    public String addReply(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                int msgid = Integer.parseInt(request.getParameter("msgid"));
                Users user = loginUsers.get();
                String txt = request.getParameter("txt");
                String coord = request.getParameter("coord");
                if(!messageService.addReply(msgid, user.getUid(), txt, coord)) {
                    res.setResultObj("insert new reply error");
                } else res.setResultObj("success");
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }
}
