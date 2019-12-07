package com.hypertars.neighborChat;

import com.hypertars.neighborChat.dao.UsersDAO;
import com.hypertars.neighborChat.model.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.hypertars.neighborChat.dao")
class NeighborChatApplicationTests {

    @Resource
    private UsersDAO usersDAO;

    @Test
    void contextLoads() {
    }

}
