package com.easyorder.application.kitchenoperator;

import com.easyorder.application.ordertaker.domain.order.OrderPlaced;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Profile("inmemory")
public class SpringOrderPlacedListener {

    @EventListener(OrderPlaced.class)
    public void listenOrderPlaced(OrderPlaced orderPlaced) {
        System.out.println(orderPlaced);
    }
}
