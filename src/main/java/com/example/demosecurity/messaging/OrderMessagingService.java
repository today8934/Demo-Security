package com.example.demosecurity.messaging;

import com.example.demosecurity.Order;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
