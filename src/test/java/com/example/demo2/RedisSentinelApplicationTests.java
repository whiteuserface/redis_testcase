package com.example.demo2;

import com.example.demo2.Service.RedisService;
import com.example.demo2.Vo.RedisInfo;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RedisSentinelApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    void test(){
        while(true){
            try{
                //1초마다 호출
                Thread.sleep(3000);
                //Redis Info setting

                RedisInfo redisInfo = new RedisInfo();
                redisInfo.setKey("keys");
                redisInfo.setValue("values");

                redisService.addKey(redisInfo);

                HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/get")
                        .header("Content-Type", "application/json")
                        .body(redisInfo)
                        .asJson();
                log.info("### 테스트 결과 => status : {} | response : {}", response.getStatus() , response.getBody().getObject());
            } catch(Exception e){
                log.error("### 테스트 에러 발생 => {}", e.getMessage());
            }
        }
    }
}
