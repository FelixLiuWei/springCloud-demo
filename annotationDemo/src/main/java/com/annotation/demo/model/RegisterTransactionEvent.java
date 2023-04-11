package com.annotation.demo.model;

import lombok.Data;

@Data
public class RegisterTransactionEvent {
    private String username;

    public RegisterTransactionEvent(String username) {
        this.username = username;
    }
}
