package com.example.demo2.Controller;

import com.example.demo2.Service.RedisService;
import com.example.demo2.Vo.RedisInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value="register", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    public Object register(@RequestBody RedisInfo redisInfo){
        redisService.addKey(redisInfo);
        return redisInfo;
    }

    @RequestMapping(value="get",method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public Object get (@RequestBody RedisInfo redisInfo){
        String value = redisService.getValue(redisInfo.getKey());
        redisInfo.setValue(value);
        return redisInfo;
    }
}
