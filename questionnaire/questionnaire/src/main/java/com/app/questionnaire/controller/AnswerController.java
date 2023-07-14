package com.app.questionnaire.controller;

import com.app.questionnaire.exception.AccessDeniedException;
import com.app.questionnaire.exception.AnswerException;
import com.app.questionnaire.additional.RequestResult;
import com.app.questionnaire.model.dto.AnswerDTO;
import com.app.questionnaire.model.entity.Answer;
import com.app.questionnaire.model.entity.AnsweredForm;
import com.app.questionnaire.model.entity.Field;
import com.app.questionnaire.model.entity.User;
import com.app.questionnaire.model.mappers.AnswerMapper;
import com.app.questionnaire.model.service.IAnswerService;
import com.app.questionnaire.model.service.IAnsweredFormService;
import com.app.questionnaire.model.service.IFieldService;
import com.app.questionnaire.model.service.IUserService;
import com.app.questionnaire.security.AccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с ответами на поля
 *
 * @author Катя Левкович
 * @version 1.1, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AnswerController {
    private final IAnswerService answerService;
    private final IAnsweredFormService answeredFormService;
    private final IFieldService fieldService;
    private final IUserService userService;
    private final AccessHandler accessHandler;

    @PostMapping("/answers/{id}")
    public AnswerDTO getAnswer(@PathVariable Long id, @RequestBody String token) throws AccessDeniedException {
        Answer answer = answerService.getAnswerById(id);
        User user = answer.getAnsweredForm().getForm().getUser();
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @PostMapping("/answers/answered_form_{id}")
    public List<AnswerDTO> getAnswersByAnsweredFormId(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        AnsweredForm answeredForm = answeredFormService.getAnsweredFormById(id);
        User user = answeredForm.getForm().getUser();
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        List<Answer> answers = answerService.getAnswersByAnsweredFormId(id);
        return AnswerMapper.INSTANCE.toDTOs(answers);
    }

    @PostMapping("/answers/field_{id}")
    public List<AnswerDTO> getAnswersByFieldId(@PathVariable Long id, @RequestBody String token)
            throws AccessDeniedException {
        Field field = fieldService.getFieldById(id);
        User user = field.getForm().getUser();
        accessHandler.checkUsersAreOneEntityOrThrown(token, user);

        List<Answer> answers = answerService.getAnswersByFieldId(id);
        return AnswerMapper.INSTANCE.toDTOs(answers);
    }

    @PostMapping("/save_answer")
    public RequestResult saveAnswer(@RequestBody AnswerDTO answer) {
        answerService.saveAnswer(AnswerMapper.INSTANCE.fromDTO(answer));

        return new RequestResult(true, "Ответ успешно сохранён");
    }

    @ExceptionHandler(AnswerException.class)
    public RequestResult handleException(AnswerException exception) {
        return new RequestResult(false, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public RequestResult handleException(AccessDeniedException exception) {
        return new RequestResult(false, "Недостаточно прав для этого действия");
    }
}