package com.ruoyi.charity.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DonationMaterialRabbitConfig {

    /**
     * 绑定注册信息的交换机
     * @return
     */
    @Bean
    public DirectExchange DonationMaterialDirectExchange() {
        return new DirectExchange("direct_donation_material_exchange");
    }

    /**
     * 绑定注册信息的队列
     * @return
     */
    @Bean
    public Queue DonationMaterialQueue() {
        return new Queue("material.direct.queue",true);
    }

    @Bean
    Binding bindingDonationMaterialDirect() {
        return BindingBuilder.bind(DonationMaterialQueue()).to(DonationMaterialDirectExchange()).with("ACTIVITY_DONATION_MATERIAL_KEY");
    }

}
