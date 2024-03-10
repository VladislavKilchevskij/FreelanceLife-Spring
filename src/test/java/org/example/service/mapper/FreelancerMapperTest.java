package org.example.service.mapper;

import org.example.config.DatasourceConfig;
import org.example.config.ValidationConfig;
import org.example.config.WebMvcConfig;
import org.example.config.YamlPropertiesConfig;
import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.model.FreelancerEntity;
import org.example.model.QualificationEntity;
import org.example.service.dto.QualificationSimpleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({WebMvcConfig.class, ValidationConfig.class, DatasourceConfig.class, YamlPropertiesConfig.class})
class FreelancerMapperTest {

    @Autowired
    private FreelancerMapper mapper;

    @Test
    void toEntityFromSaveFormWhenNullAndNotNull() {
        var form = new FreelancerSaveForm();
        form.setFreelancerName("test");
        form.setFreelancerSecondName("test1");
        form.setFreelancerEmail("test2");
        var simpleDto = new QualificationSimpleDto();
        simpleDto.setId(1L);
        simpleDto.setQualificationName("test22");
        var simpleDto1 = new QualificationSimpleDto();
        simpleDto1.setId(2L);
        simpleDto1.setQualificationName("test23");
        List<QualificationSimpleDto> list = List.of(simpleDto, simpleDto1);
        form.setQualifications(list);

        var entity = mapper.toEntity(form);
        var nullEntity = mapper.toEntity((FreelancerSaveForm) null);

        assertAll(
                () -> assertNull(nullEntity),
                () -> assertEquals("test", entity.getFreelancerName()),
                () -> assertEquals("test1", entity.getFreelancerSecondName()),
                () -> assertEquals("test2", entity.getFreelancerEmail()),
                () -> assertAll(
                        () -> assertEquals(simpleDto.getId(), entity.getQualifications().get(0).getId()),
                        () -> assertEquals(simpleDto.getQualificationName(), entity.getQualifications().get(0).getQualificationName()),
                        () -> assertEquals(simpleDto1.getId(), entity.getQualifications().get(1).getId()),
                        () -> assertEquals(simpleDto1.getQualificationName(), entity.getQualifications().get(1).getQualificationName())
                )
        );
    }

    @Test
    void toEntityFromUpdateFormWhenNullAndNotNull() {
        var form = new FreelancerUpdateForm();
        form.setId(1L);
        form.setFreelancerName("test");
        form.setFreelancerSecondName("test1");
        form.setFreelancerEmail("test2");
        var simpleDto = new QualificationSimpleDto();
        simpleDto.setId(1L);
        simpleDto.setQualificationName("test22");
        var simpleDto1 = new QualificationSimpleDto();
        simpleDto1.setId(2L);
        simpleDto1.setQualificationName("test23");
        List<QualificationSimpleDto> list = List.of(simpleDto, simpleDto1);
        form.setQualifications(list);

        var entity = mapper.toEntity(form);
        var nullEntity = mapper.toEntity((FreelancerUpdateForm) null);

        assertAll(
                () -> assertNull(nullEntity),
                () -> assertEquals(1L, entity.getId()),
                () -> assertEquals("test", entity.getFreelancerName()),
                () -> assertEquals("test1", entity.getFreelancerSecondName()),
                () -> assertEquals("test2", entity.getFreelancerEmail()),
                () -> assertAll(
                        () -> assertEquals(simpleDto.getId(), entity.getQualifications().get(0).getId()),
                        () -> assertEquals(simpleDto.getQualificationName(), entity.getQualifications().get(0).getQualificationName()),
                        () -> assertEquals(simpleDto1.getId(), entity.getQualifications().get(1).getId()),
                        () -> assertEquals(simpleDto1.getQualificationName(), entity.getQualifications().get(1).getQualificationName())
                )
        );
    }

    @Test
    void toDtoFromEntity() {
        var entity = new FreelancerEntity();
        entity.setId(1L);
        entity.setFreelancerName("test");
        entity.setFreelancerSecondName("test1");
        entity.setFreelancerEmail("test2");
        var qualif1 = new QualificationEntity();
        qualif1.setId(1L);
        qualif1.setQualificationName("test22");
        var qualif2 = new QualificationEntity();
        qualif2.setId(2L);
        qualif2.setQualificationName("test23");
        List<QualificationEntity> list = List.of(qualif1, qualif2);
        entity.setQualifications(list);

        var dto = mapper.toDto(entity);
        var nullDto = mapper.toDto(null);

        assertAll(
                () -> assertNull(nullDto),
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("test", dto.getFreelancerName()),
                () -> assertEquals("test1", dto.getFreelancerSecondName()),
                () -> assertEquals("test2", dto.getFreelancerEmail()),
                () -> assertAll(
                        () -> assertEquals(qualif1.getId(), dto.getQualifications().get(0).getId()),
                        () -> assertEquals(qualif1.getQualificationName(), dto.getQualifications().get(0).getQualificationName()),
                        () -> assertEquals(qualif2.getId(), dto.getQualifications().get(1).getId()),
                        () -> assertEquals(qualif2.getQualificationName(), dto.getQualifications().get(1).getQualificationName())
                )
        );
    }

    @Test
    void toSimpleDtoFromEntity() {
        var entity = new FreelancerEntity();
        entity.setId(1L);
        entity.setFreelancerName("test");
        entity.setFreelancerSecondName("test1");
        entity.setFreelancerEmail("test2");

        var simpleDto = mapper.toSimpleDto(entity);
        var nullDto = mapper.toSimpleDto(null);

        assertAll(
                () -> assertNull(nullDto),
                () -> assertEquals(1L, simpleDto.getId()),
                () -> assertEquals("test", simpleDto.getFreelancerName()),
                () -> assertEquals("test1", simpleDto.getFreelancerSecondName()),
                () -> assertEquals("test2", simpleDto.getFreelancerEmail())
        );
    }
}