package com.ruoyi.charity.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyh
 * @date 2024/2/26 11:33
 * @desc IntelliJ IDEA
 */

@Configuration
public class RegisterRabbitConfig {


    /**
     * 接收注册信息的交换就
     * @return
     */
    @Bean
    public DirectExchange registerDirectExchange() {
        return new DirectExchange("direct_register_exchange",true,false);
    }

    /**
     * 绑定注册信息的队列
     * @return
     */
    @Bean
    public Queue registerQueue() {
        return new Queue("register.direct.queue",true);
    }

    @Bean
    Binding bindingRegisterDirect() {
        return BindingBuilder.bind(registerQueue()).to(registerDirectExchange()).with("REGISTER");
    }


}
