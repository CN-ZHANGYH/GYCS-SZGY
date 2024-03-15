package com.ruoyi.charity.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
// bindings其实就是用来确定队列和交换机绑定关系
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "material.direct.queue",autoDelete = "false"),
        exchange = @Exchange(value = "direct_donation_material_exchange", type = ExchangeTypes.DIRECT),
        key = {"ACTIVITY_DONATION_MATERIAL_KEY"}
))
public class DonationMaterialDirectListener {

    @RabbitHandler
    public void process(String message) {

    }
}
