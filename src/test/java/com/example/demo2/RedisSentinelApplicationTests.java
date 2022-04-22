package com.example.demo2;

import com.example.demo2.Service.RedisService;
import com.example.demo2.Vo.RedisInfo;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class RedisSentinelApplicationTests {
    @Test
    void test(){
        while(true){
            try{
                //1초마다 호출
                Thread.sleep(1000);
                //Redis Info setting
                RedisInfo redisInfo = new RedisInfo();
                redisInfo.setKey("keys");
                redisInfo.setValue("values");

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
    @Test
    void test2(){
        while(true){
            try{
                //1초마다 호출
                Thread.sleep(1000);
                //Redis Info setting
                String key = "k";
                RedisService redisService =  new RedisService();
                String value = redisService.getValue(key);
                RedisInfo redisInfo = new RedisInfo();
                redisInfo.setKey(key);
                redisInfo.setValue(value);

                HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/getvalue")
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
