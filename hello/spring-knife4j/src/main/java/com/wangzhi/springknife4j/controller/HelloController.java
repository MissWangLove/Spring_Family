package com.wangzhi.springknife4j.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : wz157
 * @date : 2020-05-24 19:23
 * @description : Test Swagger-UI
 * @path : com.wangzhi.springknife4j.controller.HelloController
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-24 19:23
 */
@Api(value = "Hello-Controller",tags = "Hello")
@RestController
public class HelloController {

    @ApiOperation(value = "get-test", notes = "测试get用例")
    @GetMapping("/get/hello")
    public String testHello() {
        return "Hello, Get";
    }

    @ApiOperation(value = "get-test", notes = "测试get用例,携带参数")
    @GetMapping("/get/hello/param")
    @ApiImplicitParam(value = "name",name = "name",dataType = "string",paramType = "query",defaultValue = "wangzhi")
    public String testHelloParam(String name) {
        return "Hello, " + name;
    }

    @ApiOperation(value = "post-test", notes = "测试post用例")
    @PostMapping("/post/hello")
    public String testPostHello() {
        return "Hello, Post";
    }

}
