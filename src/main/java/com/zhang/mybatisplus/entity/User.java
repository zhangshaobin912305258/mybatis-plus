package com.zhang.mybatisplus.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@EqualsAndHashCode
public class User extends Model<User>{
    private static final long serialVersionUID = -4210671672219766048L;
    // 主键 Mybatis-plus默认以id作为数据库主键,并且id采用雪花算法生成，如果是其他名字需要添加注解@TableId
    // @TableId
    // private Long userId;
    private Long id;
    //姓名  如果数据库字段名和entity名不一致时，可以采用此注解
    //@TableField("name")
    private String name;
    //年龄
    private Integer age;
    //邮箱
    private String email;
    //直属上级
    private Long managerId;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    //修改时间
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    //版本
    @Version
    private Integer version;
    //逻辑删除 0未删除 1已删除
    private Integer deleted;
    //排除非表字段(数据库不存在此字段)
    //方式1 加入transient标识   private transient String remark; 弊端：不参与json序列化
    //方式2 字段加入static修饰 private static String remark; 弊端：类属性
    //方式3 加入@TableField(exist=false) 标识此字段不是数据库的字段
    @TableField(exist = false)
    private String remark;


}
