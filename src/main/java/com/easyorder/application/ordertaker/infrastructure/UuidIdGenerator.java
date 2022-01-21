package com.easyorder.application.ordertaker.infrastructure;

import com.easyorder.application.ordertaker.domain.IdGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Infrastructure service
@Service
public class UuidIdGenerator implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
