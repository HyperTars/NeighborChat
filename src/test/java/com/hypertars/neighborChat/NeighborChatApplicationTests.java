package com.hypertars.neighborChat;

import com.hypertars.neighborChat.dao.*;
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
    private BlockApplicationDAO blockApplicationDAO;

    @Resource
    private BlocksDAO blocksDAO;

    @Resource
    private FriendApplicationDAO friendApplicationDAO;

    @Resource
    private FriendsDAO friendsDAO;

    @Resource
    private HoodsDAO hoodsDAO;

    @Resource
    private MailBoxDAO mailBoxDAO;

    @Resource
    private MessageDAO messageDAO;

    @Resource
    private NeighborsDAO neighborsDAO;

    @Resource
    private RecipientDAO recipientDAO;

    @Resource
    private UserBlockDAO userBlockDAO;

    @Resource
    private ReplyDAO replyDAO;

    @Resource
    private UsersDAO usersDAO;

    @Resource
    private UserAccountService userService;

    @Test
    void contextLoads() {
        System.out.println(blocksDAO.getBlockByBid(1));
    }

}
