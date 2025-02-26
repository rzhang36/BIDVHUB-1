package com.yupi.springbootinit.onceTask;
import java.util.Date;

import com.yupi.springbootinit.mapper.UserMapper;
import com.yupi.springbootinit.model.entity.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InsertUsers {

//    @Resource
//    private UserMapper userMapper;
//
//    /**
//     * 插入用户
//     */
//    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨执行
//    public void doInsertUsers(){
//        final int INSERT_COUNT = 1000;
//        for(int i = 0; i < INSERT_COUNT; i++){
//            User user = new User();
//            user.setUserAccount("User" + i);
//            user.setUserPassword("12345678");
//            user.setUserName("FakeUser" + i);
//            user.setUserAvatar("");
//            user.setUserRole("");
//            userMapper.insert(user);
//        }
//    }
}
