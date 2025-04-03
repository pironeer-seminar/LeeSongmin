package com.example.pironeer.dto;

public class UserDto {

    // Request DTO for creating a new user
    public static class CreateRequest {
        private String username;
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    // Response DTO for user information
    public static class Response {
        private Long id;
        private String username;
        private String email;

        public Response(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }
}