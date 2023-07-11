package com.app.questionnaire.controller;

import com.app.questionnaire.exception.TopicException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.TopicDTO;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.mappers.TopicMapper;
import com.app.questionnaire.model.service.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с темами форм
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TopicController {
    private final ITopicService topicService;

    @GetMapping("/topics")
    public List<TopicDTO> getTopics() {
        List<Topic> topics = topicService.findAll();
        return TopicMapper.INSTANCE.toDTOs(topics);
    }

    @GetMapping("/topics/{id}")
    public TopicDTO getTopic(@PathVariable Long id) {
        Topic topic = topicService.getTopicById(id);
        return TopicMapper.INSTANCE.toDTO(topic);
    }

    @PostMapping("/delete_topic")
    public RequestResult deleteTopic(@RequestParam Long id) {
        topicService.deleteTopicById(id);

        return new RequestResult(true, "Тема успешно удалена");
    }

    @PostMapping("/save_topic")
    public RequestResult saveTopic(@RequestParam TopicDTO topic) {
        topicService.saveTopic(TopicMapper.INSTANCE.fromDTO(topic));

        return new RequestResult(true, "Тема успешно сохранена");
    }

    @ExceptionHandler(UserException.class)
    public RequestResult handleException(TopicException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}