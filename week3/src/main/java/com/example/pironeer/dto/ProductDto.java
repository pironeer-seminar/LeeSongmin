package com.example.pironeer.dto;

public class ProductDto {

    // Request DTO for creating a new product
    public static class CreateRequest {
        private String name;
        private int price;
        private int stockQuantity;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
        }
    }

    // Response DTO for product information
    public static class Response {
        private Long id;
        private String name;
        private int price;
        private int stockQuantity;

        public Response(Long id, String name, int price, int stockQuantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }
    }
}