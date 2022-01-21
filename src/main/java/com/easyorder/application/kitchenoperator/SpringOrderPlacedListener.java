package com.easyorder.application.kitchenoperator;

import com.easyorder.application.ordertaker.domain.order.OrderPlaced;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class SpringOrderPlacedListener {

    @EventListener(OrderPlaced.class)
    public void listenOrderPlaced(OrderPlaced orderPlaced) {
        System.out.println(orderPlaced);
    }
}
