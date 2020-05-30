package com.wangzhi.mybatisdemo.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;

@Data
@Builder
public class TCoffee {
    /**
    * 主键id
    */
    private Long id;

    /**
    * 咖啡名字
    */
    private String name;

    /**
    * 咖啡价格
    */
    private Money price;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;
}