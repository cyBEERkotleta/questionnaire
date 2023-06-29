package com.app.questionnaire.controller;

import com.app.questionnaire.model.dto.TopicDTO;
import com.app.questionnaire.model.dto.UserDTO;
import com.app.questionnaire.model.mappers.TopicMapper;
import com.app.questionnaire.model.mappers.UserMapper;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.service.ITopicService;
import com.app.questionnaire.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * контроллер для обработки
 * поступающих запросов на сайт
 *
 * @author Катя Левкович
 * @version 1.1, 25.06.2023
 */
@RestController
@RequiredArgsConstructor
public class MyController {
    private final IUserService userService;
    private final ITopicService topicService;

    @GetMapping("/users")
    public List<UserDTO> processUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    @GetMapping("/users/{id}")
    public UserDTO processUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return UserMapper.INSTANCE.toDTO(user);
    }

    @GetMapping("/topics/{id}")
    public TopicDTO processTopic(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id);
        return TopicMapper.INSTANCE.toDTO(topic);
    }
}