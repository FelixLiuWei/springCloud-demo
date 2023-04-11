package com.annotation.demo.model;

import lombok.Data;

@Data
public class RegisterEvent {
    private String username;

    public RegisterEvent(String username) {
        this.username = username;
    }
}
