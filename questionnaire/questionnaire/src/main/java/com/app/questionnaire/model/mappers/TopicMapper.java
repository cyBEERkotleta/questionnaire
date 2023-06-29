package com.app.questionnaire.model.mappers;

import com.app.questionnaire.model.dto.TopicDTO;
import com.app.questionnaire.model.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * преобразователь Topic-сущности в Topic-DTO
 *
 * @author Катя Левкович
 * @version 1.0, 27.06.2023
 */
@Mapper
public interface TopicMapper {
    public TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    public TopicDTO toDTO(Topic topic);
}