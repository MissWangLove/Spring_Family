package com.wangzhi.simplejdbcdemo;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author : wz157
 * @date : 2020-05-19 11:18
 * @description : Foo实体对应数据层
 * @path : com.wangzhi.simplejdbcdemo.FooDO
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-19 11:18
 */
@Data
@Builder
@ToString
public class FooDO {
    private Integer id;
    private String name;
}
