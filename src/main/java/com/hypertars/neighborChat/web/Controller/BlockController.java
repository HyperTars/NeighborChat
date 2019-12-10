package com.hypertars.neighborChat.web.Controller;

import com.alibaba.fastjson.JSON;
import com.hypertars.neighborChat.dao.BlocksDAO;
import com.hypertars.neighborChat.model.UserBlock;
import com.hypertars.neighborChat.model.Users;
import com.hypertars.neighborChat.service.user.UserService;
import com.hypertars.neighborChat.web.NBCBaseController;
import com.hypertars.neighborChat.web.NBCLogicCallBack;
import com.hypertars.neighborChat.web.NBCResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Visionary
 * @since 2019/12/10 9:00 PM
 */
@RestController
@RequestMapping("blocks")
public class BlockController extends NBCBaseController {

    @Resource
    private UserService userService;

    @Resource
    private BlocksDAO blocksDAO;

    @GetMapping("getUsersNearby")
    public String getUsersNearby(HttpServletRequest request) {
        NBCResult<Object> res = protectController(request, null, new NBCLogicCallBack() {
            @Override
            public NBCResult<Object> execute() throws Exception {
                Users user = loginUsers.get();
                UserBlock userBlock = userService.getUserBlockByUid(user.getUid());
                Map<String, List<Users>> usersList = userService.getUsersByHid(blocksDAO.selectByBid(userBlock.getBid()).getHid());
                NBCResult<Object> result = new NBCResult<>();
                result.setResultObj(usersList);
                return result;
            }
        });
        return JSON.toJSONString(res);
    }

}
