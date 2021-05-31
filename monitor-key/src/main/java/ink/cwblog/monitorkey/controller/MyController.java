package ink.cwblog.monitorkey.controller;

import ink.cwblog.monitorkey.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenw
 * @date 2021/5/24 17:10
 */
@Slf4j
@RestController
public class MyController {

    @Autowired
    private RedisUtils redisUtils;


    @GetMapping("/set")
    public void setKey(@RequestParam("param")String value){
        log.info("写入时间：{}",System.currentTimeMillis());
        redisUtils.set("live:subscribe:expire:123",value,1);
    }
}
