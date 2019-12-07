package com.hypertars.neighborchat;

import com.hypertars.neighborchat.dao.BlocksDAO;
import com.hypertars.neighborchat.dao.UsersDAO;
import com.hypertars.neighborchat.model.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.hypertars.neighborchat.dao")
class NeighborChatApplicationTests {

    @Resource
    private UsersDAO usersDAO;

    @Resource
    private BlocksDAO blocksDAO;

    @Test
    void contextLoads() {

        System.out.println(usersDAO.selectByUid(1));

//        Users users = new Users();
//        users.setUid(1);
//        users.setUname("hxyyy");
//        users.setPasswd("123");
//        users.setFName("a");
//        users.setLName("dd");
//        usersDAO.insert(users);

//        blocksDAO.selectByBid(1);


    }

}
