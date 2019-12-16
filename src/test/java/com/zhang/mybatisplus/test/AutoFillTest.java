package com.zhang.mybatisplus.test;

import com.zhang.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AutoFillTest {

    @Test
    public void testInsert() {
        User user = User.builder()
                .age(33)
                .deleted(0)
                .name("张三")
                .email("zhangsan@qq.coms")
                .managerId(1088248166370832385L)
                .build();
        boolean insert = user.insert();
        System.out.println("插入结果:"+insert);
    }

    @Test
    public void testUpdate() {
        User user = User.builder()
                .id(1206548772545425410L)
                .age(37)
                .deleted(0)
                .name("张三")
                .email("zhangsan@qq.coms")
                .managerId(1088248166370832385L)
                .updateTime(LocalDateTime.of(2019,12,15,10,10,10))
                .build();
        boolean result = user.updateById();
        System.out.println("修改结果:"+result);
    }
}

