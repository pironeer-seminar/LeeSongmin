package com.example.pironeer.service;

import com.example.pironeer.domain.Order;
import com.example.pironeer.domain.Product;
import com.example.pironeer.domain.User;
import com.example.pironeer.dto.OrderDto;
import com.example.pironeer.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Transactional
    public Long createOrder(Long userId, OrderRequestItem item) {
        // Find the user and product
        User user = userService.findUserEntityById(userId);
        Product product = productService.findProductEntityById(item.productId());

        // Validate stock (will throw an exception if stock is insufficient)
        productService.decreaseStock(item.productId(), item.amount());

        // Create order
        Order order = new Order(user, product, "ORDERED", item.amount());
        Order savedOrder = orderRepository.save(order);
        return savedOrder.id;
    }

    @Transactional
    public OrderDto.Response createOrderDto(OrderDto.CreateRequest request) {
        // Find the user
        User user = userService.findUserEntityById(request.getUserId());

        // Create and save orders for each product
        List<Order> savedOrders = new ArrayList<>();

        for (OrderRequestItem item : request.getItems()) {
            Product product = productService.findProductEntityById(item.productId());

            // Validate stock (will throw an exception if stock is insufficient)
            productService.decreaseStock(item.productId(), item.amount());

            // Create order
            Order order = new Order(user, product, "ORDERED", item.amount());
            savedOrders.add(orderRepository.save(order));
        }

        // Create the response
        List<OrderDto.OrderItemResponse> orderItems = savedOrders.stream()
                .map(order -> new OrderDto.OrderItemResponse(
                        order.product.id,
                        order.product.name,
                        order.product.price,
                        order.amount))
                .collect(Collectors.toList());

        return new OrderDto.Response(
                savedOrders.get(0).id,
                user.id,
                user.username,
                "ORDERED",
                orderItems);
    }

    @Transactional(readOnly = true)
    public OrderDto.Response getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));

        // Create a single item response for this order
        OrderDto.OrderItemResponse itemResponse = new OrderDto.OrderItemResponse(
                order.product.id,
                order.product.name,
                order.product.price,
                order.amount);

        List<OrderDto.OrderItemResponse> items = List.of(itemResponse);

        return new OrderDto.Response(
                order.id,
                order.user.id,
                order.user.username,
                order.status,
                items);
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByUserId(Long userId) {
        User user = userService.findUserEntityById(userId);
        return orderRepository.findByUser(user);
    }

    @Transactional(readOnly = true)
    public List<OrderDto.Response> getOrderDtosByUserId(Long userId) {
        User user = userService.findUserEntityById(userId);
        List<Order> orders = orderRepository.findByUser(user);

        // Group orders by ID (assuming same-time orders have same IDs or processing time)
        return orders.stream().map(order -> {
            OrderDto.OrderItemResponse itemResponse = new OrderDto.OrderItemResponse(
                    order.product.id,
                    order.product.name,
                    order.product.price,
                    order.amount);

            return new OrderDto.Response(
                    order.id,
                    order.user.id,
                    order.user.username,
                    order.status,
                    List.of(itemResponse));
        }).collect(Collectors.toList());
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        // Check if order is already canceled
        if ("CANCELED".equals(order.status)) {
            throw new IllegalStateException("Order is already canceled");
        }

        // Restore the stock
        productService.increaseStock(order.product.id, order.amount);

        // Update order status
        order.status = "CANCELED";
        orderRepository.save(order);
    }

    @Transactional
    public OrderDto.Response cancelOrderDto(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        // Check if order is already canceled
        if ("CANCELED".equals(order.status)) {
            throw new IllegalStateException("Order is already canceled");
        }

        // Restore the stock
        productService.increaseStock(order.product.id, order.amount);

        // Update order status
        order.status = "CANCELED";
        Order savedOrder = orderRepository.save(order);

        // Create response
        OrderDto.OrderItemResponse itemResponse = new OrderDto.OrderItemResponse(
                savedOrder.product.id,
                savedOrder.product.name,
                savedOrder.product.price,
                savedOrder.amount);

        return new OrderDto.Response(
                savedOrder.id,
                savedOrder.user.id,
                savedOrder.user.username,
                savedOrder.status,
                List.of(itemResponse));
    }
}