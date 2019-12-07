package com.hypertars.neighborchat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborchat.model.Users;
import com.hypertars.neighborchat.service.user.UserService;
import com.hypertars.neighborchat.web.NBCBaseController;
import com.hypertars.neighborchat.web.NBCLogicCallBack;
import com.hypertars.neighborchat.web.NBCResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String loginIn(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("uid");
        int uid = Integer.parseInt(id);
        String pass = request.getParameter("pass");

        Users user = new Users();
        user.setUid(uid);
        user.setPasswd(pass);
        String session = userService.loginIn(user);
        Cookie cookie = new Cookie("userSession", session);
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
