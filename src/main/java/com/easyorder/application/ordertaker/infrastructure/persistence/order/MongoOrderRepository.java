package com.easyorder.application.ordertaker.infrastructure.persistence.order;

import com.easyorder.application.ordertaker.domain.order.Order;
import com.easyorder.application.ordertaker.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

// Infrastructure Service
@Repository
public class MongoOrderRepository implements OrderRepository {

    private final MongoOrderDAO mongoDao;

    public MongoOrderRepository(MongoOrderDAO mongoDao) {
        this.mongoDao = mongoDao;
    }

    @Override
    public void save(Order order) {
        mongoDao.save(order);
    }

    @Override
    public Order findById(String orderId) {
        return mongoDao.findById(orderId)
                .orElseThrow();
    }
}
