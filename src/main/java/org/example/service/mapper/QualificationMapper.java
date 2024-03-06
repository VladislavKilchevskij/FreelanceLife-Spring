package org.example.service.mapper;

import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.model.QualificationEntity;
import org.example.service.dto.QualificationDto;
import org.example.service.dto.QualificationSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface QualificationMapper {

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "freelancers", ignore = true)
    })
    QualificationEntity toEntity(QualificationSaveForm form);

    @Mappings(value = {
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "freelancers", ignore = true)
    })
    QualificationEntity toEntity(QualificationUpdateForm form);

    QualificationDto toDto(QualificationEntity entity);

    QualificationSimpleDto toSimpleDto(QualificationEntity entity);

}