package com.app.questionnaire.controller;

import com.app.questionnaire.additional.tokenable.TokenWithTopic;
import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.TopicException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.model.dto.TopicDTO;
import com.app.questionnaire.model.entity.Topic;
import com.app.questionnaire.model.mappers.TopicMapper;
import com.app.questionnaire.model.service.ITopicService;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с темами форм
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TopicController {
    private final ITopicService topicService;
    private final IUserService userService;
    private final AccessHandler accessHandler;

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
    public RequestResult deleteTopic(@RequestBody TokenWithTopic tokenWithTopic) throws AccessDeniedException {
        accessHandler.checkTokenIsFromAdminAccountOrThrown(tokenWithTopic.getToken());

        topicService.deleteTopicById(tokenWithTopic.getTopic().getId());

        return new RequestResult(true, "Тема успешно удалена");
    }

    @PostMapping("/save_topic")
    public RequestResult saveTopic(@RequestBody TokenWithTopic tokenWithTopic) throws AccessDeniedException {
        accessHandler.checkTokenIsFromAdminAccountOrThrown(tokenWithTopic.getToken());

        topicService.saveTopic(TopicMapper.INSTANCE.fromDTO(tokenWithTopic.getTopic()));

        return new RequestResult(true, "Тема успешно сохранена");
    }

    @ExceptionHandler(TopicException.class)
    public RequestResult handleException(TopicException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}