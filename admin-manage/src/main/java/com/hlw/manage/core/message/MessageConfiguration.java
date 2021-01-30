package com.hlw.manage.core.message;

import com.hlw.common.constants.CommonConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author mqz
 * @description
 * @since 2020/5/11
 */
@Configuration
public class MessageConfiguration {

    /**
     * 创建连接工厂
     * @param connectionFactory
     * @param adapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter adapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(adapter, new PatternTopic(CommonConstants.KEY_FOR_SAVE_LOG));
        return container;
    }
    /**
     * @param message
     * @return
     */
    @Bean
    public MessageListenerAdapter adapter(MessageConsumer message){
        // onMessage 如果RedisMessage 中 没有实现接口，这个参数必须跟RedisMessage中的读取信息的方法名称一样
        return new MessageListenerAdapter(message, "onMessage");
    }

}
