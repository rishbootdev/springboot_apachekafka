package com.rishbootdev.userservice.service;

import com.rishbootdev.userservice.dto.CreateUserRequestDto;
import com.rishbootdev.userservice.entity.User;
import com.rishbootdev.userservice.event.UserCreatedEvent;
import com.rishbootdev.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void createUser(CreateUserRequestDto createUserRequestDto) {

        User user = modelMapper.map(createUserRequestDto, User.class);
        User savedUser = userRepository.save(user);

        UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser, UserCreatedEvent.class);
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent.getId().toString(), userCreatedEvent);

    }

}
