package com.ruoyi.charity.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RaiseAuditRabbitConfig {

    /**
     * 绑定注册信息的交换机
     * @return
     */
    @Bean
    public DirectExchange raiseAuditDirectExchange() {
        return new DirectExchange("direct_raise_audit_exchange");
    }

    /**
     * 绑定注册信息的队列
     * @return
     */
    @Bean
    public Queue raiseAuditQueue() {
        return new Queue("audit.direct.queue",true);
    }

    @Bean
    Binding bindingRaiseAuditDirect() {
        return BindingBuilder.bind(raiseAuditQueue()).to(raiseAuditDirectExchange()).with("RAISE_AUDIT_ROUTING_KEY");
    }

}
