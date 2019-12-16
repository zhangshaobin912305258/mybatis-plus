package com.zhang.mybatisplus.test;

import com.zhang.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OptTest {

    @Test
    public void testUpdate() {
        int version = 1;
        User user = User.builder()
                .id(1206548772545425410L)
                .age(40)
                .deleted(0)
                .name("张三")
                .email("zhangsan@qq.coms")
                .managerId(1088248166370832385L)
                .updateTime(LocalDateTime.of(2019,12,15,10,10,10))
                .version(version)
                .build();
        boolean result = user.updateById();
        System.out.println("修改结果:"+result);
    }

}
