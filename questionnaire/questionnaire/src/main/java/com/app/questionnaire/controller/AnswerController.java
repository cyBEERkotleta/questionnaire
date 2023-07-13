package com.app.questionnaire.controller;

import com.app.questionnaire.exception.AnswerException;
import com.app.questionnaire.exception.UserException;
import com.app.questionnaire.model.RequestResult;
import com.app.questionnaire.model.dto.AnswerDTO;
import com.app.questionnaire.model.entity.Answer;
import com.app.questionnaire.model.mappers.AnswerMapper;
import com.app.questionnaire.model.service.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * контроллер для обработки
 * запросов, связанных с ответами на поля
 *
 * @author Катя Левкович
 * @version 1.0, 06.07.2023
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AnswerController {
    private final IAnswerService answerService;

    @GetMapping("/answers/{id}")
    public AnswerDTO getAnswer(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        return AnswerMapper.INSTANCE.toDTO(answer);
    }

    @GetMapping("/answers/answered_form_{id}")
    public List<AnswerDTO> getAnswersByAnsweredFormId(@PathVariable Long id) {
        List<Answer> answers = answerService.getAnswersByAnsweredFormId(id);
        return AnswerMapper.INSTANCE.toDTOs(answers);
    }

    @GetMapping("/answers/field_{id}")
    public List<AnswerDTO> getAnswersByFieldId(@PathVariable Long id) {
        List<Answer> answers = answerService.getAnswersByFieldId(id);
        return AnswerMapper.INSTANCE.toDTOs(answers);
    }

    @PostMapping("/save_answer")
    public RequestResult saveAnswer(@RequestBody AnswerDTO answer) {
        answerService.saveAnswer(AnswerMapper.INSTANCE.fromDTO(answer));

        return new RequestResult(true, "Ответ успешно сохранён");
    }

    @ExceptionHandler(UserException.class)
    public RequestResult handleException(AnswerException exception) {
        return new RequestResult(false, exception.getMessage());
    }
}