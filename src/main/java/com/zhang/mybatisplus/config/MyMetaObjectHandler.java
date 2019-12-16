package com.zhang.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean has = metaObject.hasSetter("createTime");
        if (has) {
            strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
        }
        //setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean has = metaObject.hasGetter("updateTime");
        if (has) {
            Object updateTime = getFieldValByName("updateTime", metaObject);
            if (updateTime == null) {
                strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
            }
        }
        //setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
