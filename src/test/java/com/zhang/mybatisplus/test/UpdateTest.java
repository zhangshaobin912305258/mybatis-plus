package com.zhang.mybatisplus.test;

import com.zhang.mybatisplus.entity.User;
import com.zhang.mybatisplus.mapper.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateById() {
        User user = User.builder()
            .id(1088248166370832385L)
            .age(26)
            .email("912305258@qq.com")
            .build();
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数:"+ rows);
    }

}