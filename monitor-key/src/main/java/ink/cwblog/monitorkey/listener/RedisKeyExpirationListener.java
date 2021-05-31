package ink.cwblog.monitorkey.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author chenw
 * @date 2021/5/24 18:03
 */
@Slf4j
@Component
public class RedisKeyExpirationListener implements MessageListener {

    /**
     * 针对redis数据失效事件，进行数据处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        log.info("数据：{}", JSON.toJSONString(message));
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("过期：{}", System.currentTimeMillis());
        log.info("结果:{},{}", key, channel);
        if (key.startsWith("live:subscribe:expire:")) {
            String[] split = key.split("live:subscribe:expire:");

            log.info("哈哈哈哈:{}", split[1]);
        }
    }
}
