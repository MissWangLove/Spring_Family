package com.wangzhi.springdruid;


import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ConnectionProxy;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author : wz157
 * @date : 2020-05-18 14:10
 * @description : 数据库连接Filter
 * @path : com.wangzhi.springdruid.ConnectionLogFilter
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-18 14:10
 */
@Slf4j
public class ConnectionLogFilter extends FilterEventAdapter {

    @Override
    public void connection_connectBefore(FilterChain chain, Properties info) {
        log.info("BEFORE CONNECTION!!!");
    }

    @Override
    public void connection_connectAfter(ConnectionProxy connection) {
        log.info("AFTER CONNECTION!!!");
    }
}
