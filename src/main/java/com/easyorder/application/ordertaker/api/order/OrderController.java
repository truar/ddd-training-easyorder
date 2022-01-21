package com.easyorder.application.ordertaker.api.order;

import com.easyorder.application.ordertaker.application.OrderApplicationService;
import com.easyorder.application.ordertaker.domain.order.Order;
import com.easyorder.application.ordertaker.domain.order.OrderLine;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody String restaurantId) {
        orderApplicationService.createOrder(restaurantId);
    }

//    @PostMapping("/{orderId}/orderLines")
    @PostMapping("/{orderId}:addOrderLine")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrderLine(@PathVariable String orderId, @RequestBody String orderLineItemId) {
        orderApplicationService.addItemToOrder(orderId, orderLineItemId);
    }

    @PostMapping("/{orderId}:placeOrder")
    public void placeOrder(@PathVariable String orderId) {
        orderApplicationService.placeOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderDTO readOrder(@PathVariable String orderId) {
        Order order = orderApplicationService.getOrder(orderId);
        return toOrderDTO(order);
    }

    // Mapper
    private OrderDTO toOrderDTO(Order order) {
        List<OrderDTO.OrderLineDTO> orderLinesDTO = toOrderLinesDTO(order.getOrderLines());
        OrderDTO orderDTO = new OrderDTO(order.getTotalPrice(), order.getTotalQuantity(), orderLinesDTO);
        return orderDTO;
    }

    private List<OrderDTO.OrderLineDTO> toOrderLinesDTO(List<OrderLine> orderLines) {
        return orderLines.stream()
                .map(this::toOrderLineDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO.OrderLineDTO toOrderLineDTO(OrderLine orderLine) {
        return new OrderDTO.OrderLineDTO(orderLine.getId(), orderLine.getName(), orderLine.getPrice(),
                orderLine.getComment(), orderLine.getQuantity(), orderLine.getTotalPrice());
    }


}
