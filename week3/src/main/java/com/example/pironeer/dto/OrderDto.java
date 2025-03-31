package com.example.pironeer.dto;

import com.example.pironeer.service.OrderRequestItem;

import java.util.List;

public class OrderDto {

    // Request DTO for creating a new order
    public static class CreateRequest {
        private Long userId;
        private List<OrderRequestItem> items;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public List<OrderRequestItem> getItems() {
            return items;
        }

        public void setItems(List<OrderRequestItem> items) {
            this.items = items;
        }
    }

    // Response DTO for an order item
    public static class OrderItemResponse {
        private Long productId;
        private String productName;
        private int price;
        private int amount;
        private int itemTotal;

        public OrderItemResponse(Long productId, String productName, int price, int amount) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.amount = amount;
            this.itemTotal = price * amount;
        }

        public Long getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getPrice() {
            return price;
        }

        public int getAmount() {
            return amount;
        }

        public int getItemTotal() {
            return itemTotal;
        }
    }

    // Response DTO for order information
    public static class Response {
        private Long id;
        private Long userId;
        private String username;
        private String status;
        private List<OrderItemResponse> items;
        private int totalAmount;

        public Response(Long id, Long userId, String username, String status, List<OrderItemResponse> items) {
            this.id = id;
            this.userId = userId;
            this.username = username;
            this.status = status;
            this.items = items;
            this.totalAmount = items.stream().mapToInt(OrderItemResponse::getItemTotal).sum();
        }

        public Long getId() {
            return id;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getStatus() {
            return status;
        }

        public List<OrderItemResponse> getItems() {
            return items;
        }

        public int getTotalAmount() {
            return totalAmount;
        }
    }
}