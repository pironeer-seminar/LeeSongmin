package com.example.pironeer.controller;

import com.example.pironeer.dto.OrderDto;
import com.example.pironeer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto.Response> createOrder(@RequestBody OrderDto.CreateRequest request) {
        OrderDto.Response response = orderService.createOrderDto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto.Response> getOrderById(@PathVariable Long id) {
        OrderDto.Response response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto.Response>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderDto.Response> orders = orderService.getOrderDtosByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<OrderDto.Response> cancelOrder(@PathVariable Long id) {
        OrderDto.Response response = orderService.cancelOrderDto(id);
        return ResponseEntity.ok(response);
    }
}