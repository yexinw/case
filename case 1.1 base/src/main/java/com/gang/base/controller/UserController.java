package com.gang.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.gang.base.common.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserControlelr
 * @Description TODO
 * @Date 2019/12/17 14:57
 * @Created by zengzg
 */
@RestController
@RequestMapping(("/user"))
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @PostMapping(value = "create", produces = "application/json", consumes = "application/json")
    public User create(@RequestBody User user) {
        logger.info("------> this is in create add  :{}<-------", JSONObject.toJSONString(user));
        return user;
    }

    @GetMapping("pathtest/{key}")
    public String pathTest(@PathVariable("key") String key) {
        logger.info("------> this is key :{} <-------", key);
        return key;
    }

    @PostMapping("posttest")
    public String postTest(@RequestParam("key") String key) {
        logger.info("------> this isi key :{} <-------", key);
        return key;
    }
}
