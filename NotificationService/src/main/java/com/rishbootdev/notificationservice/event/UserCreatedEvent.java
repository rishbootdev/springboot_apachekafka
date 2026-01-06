package com.rishbootdev.notificationservice.event;


import lombok.Data;

@Data
public class UserCreatedEvent {

    private Long id;
    private String fullName;
    private String email;
}
