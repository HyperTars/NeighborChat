package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.user.UserService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCLogicCallBack;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController extends NBCBaseController {

    @Resource
    private UserService userService;

    @GetMapping("loginIn")
//    @RequestMapping()
//    @PostMapping
    /* @RequestMapping */
    public String loginIn(HttpServletRequest request, HttpServletResponse response) {
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        Users user = new Users();
        user.setUname(uname);
        user.setPasswd(pass);
        String session = userService.loginIn(user);
        Cookie cookie = new Cookie("userSession", session);
//        cookie.setDomain("/");
        cookie.setPath("/");
        response.addCookie(cookie);

        user = userService.getUserBySession(session);
        return JSON.toJSONString(user);
    }

    @GetMapping("getUserInfo")
    public String getUserInfo(HttpServletRequest request) {
        NBCResult<Object> result = new NBCResult<>();

        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                res.setResultObj(loginUsers.get());
                return res;
            }
        });

        return JSON.toJSONString(result);
    }

}