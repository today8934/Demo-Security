package com.example.demosecurity.messaging;

import com.example.demosecurity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitOrderReceiver {

    private final RabbitTemplate rabbit;
    private final MessageConverter converter;

    public Order receiveOrder() {
        return rabbit.receiveAndConvert("tacocloud.order.queue",
                new ParameterizedTypeReference<Order>() {});
    }
}
