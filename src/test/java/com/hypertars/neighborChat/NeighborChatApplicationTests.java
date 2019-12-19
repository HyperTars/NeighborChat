package com.hypertars.neighborChat;

import com.hypertars.neighborChat.dao.BlocksDAO;
import com.hypertars.neighborChat.dao.UserBlockDAO;
import com.hypertars.neighborChat.dao.UsersDAO;
import com.hypertars.neighborChat.service.UserAccount.UserAccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.hypertars.neighborChat.dao")
class NeighborChatApplicationTests {

    @Resource
    private UsersDAO usersDAO;

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @Resource
    private UserAccountService userService;

    @Test
    void contextLoads() {
        System.out.println(blocksDAO.getBlockByBid(1));
    }

}
