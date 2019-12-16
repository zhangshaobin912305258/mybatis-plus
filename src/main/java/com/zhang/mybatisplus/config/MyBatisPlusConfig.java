package com.zhang.mybatisplus.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyBatisPlusConfig {

    //public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    /**
     * 配置分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //多租户配置
        /*ArrayList<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }

            @Override
            public Expression getTenantId(boolean where) {
                return new LongValue(1088248166370832385L);
            }

            @Override
            public boolean doTableFilter(String tableName) {
                if ("role".equals(tableName)) {
                    //角色表不增加租户信息
                    return true;
                }
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        //SQL过滤器
        paginationInterceptor.setSqlParserFilter(metaObject -> {
            MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
            if ("com.zhang.mybatis.mapper.UserMapper.selectById".equals(mappedStatement.getId())) {
                return true;
            }
            return false;
        });*/

        //动态表名配置
       /* ArrayList<ISqlParser> sqlParserList = new ArrayList<>();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("user", (metaObject, sql, tableName) -> myTableName.get());
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParserList);*/
        return paginationInterceptor;
    }

    /**
     * 乐观锁插件
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    @Profile({"dev", "test"})
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        PerformanceMonitorInterceptor performanceMonitorInterceptor = new PerformanceMonitorInterceptor();
        return performanceMonitorInterceptor;
    }

    /*
    配置逻辑删除 3.1之后版本不需要配置
    public ISqlInjector sqlInjector () {
        //return new LogicSql
    }*/
}
