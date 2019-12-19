package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.Message.MessageService;
import com.hypertars.neighborChat.service.Membership.MembershipService;
import com.hypertars.neighborChat.service.Relationship.RelationshipService;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCLogicCallBack;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("UserAccount")
public class UserAccountController extends NBCBaseController {
    @Resource
    private UserAccountService userAccountService;

    @Resource
    private MessageService MessageService;

    @Resource
    private MembershipService blockService;

    @Resource
    private RelationshipService relationshipService;

    @RequestMapping(value = "loginIn", produces = "text/script;charset=UTF-8")

    public String loginIn(HttpServletRequest request, HttpServletResponse response, String callback) {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        Users user = new Users();
        user.setUname(uname);
        user.setPasswd(pass);
        String session = userAccountService.loginIn(user);
        Cookie cookie = new Cookie("userSession", session);
        cookie.setPath("/");
        response.addCookie(cookie);

        user = userAccountService.getUserBySession(session);
        return callback + "(" + JSON.toJSONString(user) + ")";
    }

    @RequestMapping(value = "getUserInfo", produces = "text/script;charset=UTF-8")
    public String getUserInfo(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();

        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                res.setResultObj(loginUsers.get());
                return res;
            }
        });
//        return JSON.toJSONString(result);
        return callback + "(" + JSON.toJSONString(result) + ")";
    }
}
