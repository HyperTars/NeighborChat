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
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("user")
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
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "userReg", produces = "text/script;charset=UTF-8")
    public String userReg(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                String uname = request.getParameter("uname");
                String passwd = request.getParameter("passwd");
                passwd = getSHA256(passwd);
                String email = request.getParameter("email");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                NBCResult<Object> res = new NBCResult<>();
                if (userAccountService.userReg(uname, passwd, email, fName, lName))
                    res.setResultObj("success");
                else res.setResultObj("failure");
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "updateInfo", produces = "text/script;charset=UTF-8")
    public String updateUserInfo(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                int uid = Integer.parseInt(request.getParameter("uid"));
                String uname = request.getParameter("uname");
                String passwd = request.getParameter("passwd");
                String email = request.getParameter("email");
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String addr1 = request.getParameter("addr1");
                String addr2 = request.getParameter("addr2");
                String intro = request.getParameter("intro");
                String photo = request.getParameter("photo");
                short nRange = Short.parseShort(request.getParameter("nRange"));
                boolean notify = Boolean.parseBoolean(request.getParameter("notify"));
                if (userAccountService.updateUserInfo(uid, uname, passwd, email, fName, lName, addr1, addr2, intro, photo, nRange, notify))
                    res.setResultObj("success");
                else res.setResultObj("failure");
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    @RequestMapping(value = "getUserByUid", produces = "text/script;charset=UTF-8")
    public String getUserByUid(HttpServletRequest request, String callback) {
        NBCResult<Object> result = new NBCResult<>();
        result = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                NBCResult<Object> res = new NBCResult<>();
                int uid = Integer.parseInt(request.getParameter("uid"));
                res.setResultObj(userAccountService.getUserByUid(uid));
                return res;
            }
        });
        return callback + "(" + JSON.toJSONString(result) + ")";
    }

    private static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encodeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
}
