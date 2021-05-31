package ink.cwblog.monitorkey.config;

import ink.cwblog.monitorkey.listener.RedisKeyExpirationListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author chenw
 * @date 2021/5/24 17:57
 */
@Configuration
public class RedisListenerConfig {


    @Value("${spring.redis.database}")
    private Integer redisDb;
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        String topic = new StringBuilder("__keyevent@").append(redisDb.toString()).append("__:expired").toString();
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new RedisKeyExpirationListener(), new PatternTopic(topic));
        return container;
    }
}
