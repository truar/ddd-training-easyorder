package com.easyorder.application.ordertaker.infrastructure.persistence.order;

import com.easyorder.application.ordertaker.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderDAO extends MongoRepository<Order, String> {
}
