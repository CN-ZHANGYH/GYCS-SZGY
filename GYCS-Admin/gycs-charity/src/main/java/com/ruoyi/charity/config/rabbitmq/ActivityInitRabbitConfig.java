package com.ruoyi.charity.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyh
 * @date 2024/3/15 8:57
 * @desc IntelliJ IDEA
 */

@Configuration
public class ActivityInitRabbitConfig {
    /**
     * 绑定注册信息的交换机
     * @return
     */
    @Bean
    public DirectExchange ActivityInitDirectExchange() {
        return new DirectExchange("direct_activity_init_exchange");
    }

    /**
     * 绑定注册信息的队列
     * @return
     */
    @Bean
    public Queue ActivityInitQueue() {
        return new Queue("activity.direct.queue",true);
    }

    @Bean
    Binding bindingActivityInitDirect() {
        return BindingBuilder.bind(ActivityInitQueue()).to(ActivityInitDirectExchange()).with("ACTIVITY_INIT_KEY");
    }


}
