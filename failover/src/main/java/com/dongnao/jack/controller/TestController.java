package com.dongnao.jack.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    JedisCluster jedisCluster;

    @RequestMapping("/invoke")
    public
    @ResponseBody
    String test() {
        logger.debug("test==================");
        for (int i = 0; i < 100; i++) {
            jedisCluster.set("key" + i, "value" + i);
        }

        return "OK";
    }

}
