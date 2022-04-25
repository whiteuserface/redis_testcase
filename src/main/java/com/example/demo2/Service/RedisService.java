package com.example.demo2.Service;


import com.example.demo2.Vo.RedisInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void addKey(RedisInfo redisInfo){
        try{
            //키 세팅
            redisTemplate.opsForValue().set(redisInfo.getKey(), redisInfo.getValue());

        } catch(Exception e){
            //에러발생
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
    }
    public String getValue(String key){
        String value = "";
        try{
            if(redisTemplate.hasKey(key)){
                value = redisTemplate.opsForValue().get(key);
            }
        }  catch (Exception e){
            log.error("### Redis Get Value Error !!! ::: {}", e.getMessage());
        }
        return value;
    }


}
