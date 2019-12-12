package com.zhang.mybatisplus.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhang.mybatisplus.entity.User;
import com.zhang.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        //Assert.assertEquals(5,users.size());
        System.out.println(users);
    }

    @Test
    public void testInsert() {
        User user = User.builder()
            .name("向后")
            .age(31)
            .email("912305258@qq.com")
            .managerId(1088248166370832385L)
            .createTime(LocalDateTime.now())
            .remark("我是备注信息")
            .build();
        int rows = userMapper.insert(user);
        System.out.println("影响记录数:" + rows);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<Long> ids = Arrays.asList(1087982257332887553L,1088248166370832385L,1088250446457389058L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","王天风");
        map.put("age", 25);
        // where name = '王天风' and age = 25   map中的key对应的是数据库的列名  不是entity的属性名
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper() {
        //名字中包含雨并且年龄小于40
        //name like '%雨%' and age<40
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //QueryWrapper<User> queryWrapper = Wrappers.<User>query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void testWrapper2() {
        //名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
        //name like '%雨%' and age between 20 and 40 and email is not null
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper3() {
        //名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
        //SELECT * FROM user WHERE (name LIKE ? OR age >= ?) ORDER BY age DESC , id ASC 
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or().ge("age", 30).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper4() {
        //创建日期为2019年2月14日并且直属上级为名字为王姓
        //SELECT * FROM user WHERE (date_format(create_time,'%Y-%m-%d') = ? 
        //    AND manager_id IN (select id from user where name like '王%')) 
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
            .inSql("manager_id", "select id from user where name like '王%'");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper5() {
        //名字为王姓并且（年龄小于40或邮箱不为空）
        //name like '王%' and (age<40 or email is not null)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq->wq.lt("age", 40).or().isNotNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper6() {
        //名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
        //name like '王%' or (age<40 and age>20 and email is not null)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(
            wq->wq.lt("age", 40).gt("age", 20).isNotNull("email")
            );
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper7() {
        //（年龄小于40或邮箱不为空）并且名字为王姓
        //(age<40 or email is not null) and name like '王%'
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(qw->qw.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper8() {
        //年龄为30、31、34、35
        //age in (30、31、34、35) 
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30,31,34,35));
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWrapper9() {
        //只返回满足条件的其中一条语句即可
        //limit 1
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30,31,34,35)).last("limit 1");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    //select中字段不全部出现的查询

    @Test
    public void testWrapper10() {
        //名字中包含雨并且年龄小于40(需求1加强版)
        //select id,name from user where name like '%雨%' and age<40
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    //select id,name,age,email from user where name like '%雨%' and age<40

    @Test
    public void testWrapper11() {
        //select id,name,age,email from user where name like '%雨%' and age<40
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").lt("age", 40).select(User.class,info->
            !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id")
            );
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        String name = "王";
        String email = "";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //之前的逻辑
        /*
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.like("email", email);
        }
        */
        //现在的逻辑
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)
            .like(StringUtils.isNotBlank(email), "email",email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByWrapperEntity() {
        User user = User.builder().name("王天风").build();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

}
