package com.annotation.demo.model;

import lombok.Data;

@Data
public class SucceedEvent {
    private String username;

    public SucceedEvent(String username){
        this.username = username;
    }
}
