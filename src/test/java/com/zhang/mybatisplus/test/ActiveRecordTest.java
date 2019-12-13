package com.zhang.mybatisplus.test;

import java.time.LocalDateTime;

import com.zhang.mybatisplus.entity.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveRecordTest {

    @Test
    public void insert() {
        User user = User.builder()
            .name("向东")
            .age(25)
            .email("xiangdong@qq.com")
            .build();
        boolean result = user.insert();
        System.out.println("插入结果:"+result);
    }

    @Test
    public void update() {
        User user = User.builder()
            .id(1205525271717920770L)
            .name("向东")
            .age(25)
            .email("xiangdong@qq.com")
            .createTime(LocalDateTime.now())
            .build();
        boolean result = user.updateById();
        System.out.println("更新结果:"+result);
    }
}