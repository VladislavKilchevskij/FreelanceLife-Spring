package org.example.service.mapper;

import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.model.FreelancerEntity;
import org.example.service.dto.FreelancerDto;
import org.example.service.dto.FreelancerSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FreelancerMapper {

    @Mapping(target = "id", ignore = true)
    FreelancerEntity toEntity(FreelancerSaveForm dto);
    FreelancerEntity toEntity(FreelancerUpdateForm dto);

    FreelancerDto toDto(FreelancerEntity entity);

    FreelancerSimpleDto toSimpleDto(FreelancerEntity entity);


}
