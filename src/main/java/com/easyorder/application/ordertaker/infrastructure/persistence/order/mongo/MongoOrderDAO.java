package com.easyorder.application.ordertaker.infrastructure.persistence.order.mongo;

import com.easyorder.application.ordertaker.domain.order.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Profile("mongo")
public interface MongoOrderDAO extends MongoRepository<Order, String> {
}
