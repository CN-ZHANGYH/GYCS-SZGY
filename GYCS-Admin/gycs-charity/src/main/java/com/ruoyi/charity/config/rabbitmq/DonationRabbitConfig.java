package com.ruoyi.charity.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyh
 * @date 2024/3/4 10:01
 * @desc IntelliJ IDEA
 */

@Configuration
public class DonationRabbitConfig {

    /**
     * 绑定注册信息的交换机
     * @return
     */
    @Bean
    public DirectExchange donationDirectExchange() {
        return new DirectExchange("direct_donation_fund_exchange");
    }

    /**
     * 绑定注册信息的队列
     * @return
     */
    @Bean
    public Queue donationQueue() {
        return new Queue("donation.direct.queue",true);
    }

    @Bean
    Binding bindingDonationFundDirect() {
        return BindingBuilder.bind(donationQueue()).to(donationDirectExchange()).with("DONATION_FUND_KEY");
    }


}
