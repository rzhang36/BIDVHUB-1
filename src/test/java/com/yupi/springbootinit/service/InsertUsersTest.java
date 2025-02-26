package com.yupi.springbootinit.service;

import cn.hutool.core.date.StopWatch;
import com.yupi.springbootinit.mapper.UserMapper;
import com.yupi.springbootinit.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class InsertUsersTest {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    /**
     * 插入用户
     */
    @Test
    public void doInsertUsers(){
        final int INSERT_COUNT = 1000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i = 0; i < INSERT_COUNT; i++){
            User user = new User();
            user.setUserAccount("User" + i);
            user.setUserPassword("12345678");
            user.setUserName("FakeUser" + i);
            user.setUserAvatar("");
            user.setUserRole("");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println("插入" + INSERT_COUNT + "条用户数据耗时：" + stopWatch.getTotalTimeMillis() + "ms");
    }

    //CPU密集型任务，线程数设置为CPU核心数 - 1
    //IO密集型任务，线程数设置为CPU核心数 * 2
    private ExecutorService executorService = new ThreadPoolExecutor(60, 1000, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000));

    @Test
    public void doConcurrentInsertUsers(){
        final int INSERT_COUNT = 100000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        int batchSize = 5000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            List<User> userList = new ArrayList<>();
            while(true){
                j++;
                User user = new User();
                user.setUserAccount("User" + i);
                user.setUserPassword("12345678");
                user.setUserName("FakeUser" + i);
                user.setUserAvatar("");
                user.setUserRole("");
                userList.add(user);
                if(j % batchSize == 0) {
                    break;
                }
            }

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName: " + Thread.currentThread().getName());
                userService.saveBatch(userList, batchSize);
            }, executorService);

            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        stopWatch.stop();
        System.out.println("插入" + INSERT_COUNT + "条用户数据耗时：" + stopWatch.getTotalTimeMillis() + "ms");
    }
}
