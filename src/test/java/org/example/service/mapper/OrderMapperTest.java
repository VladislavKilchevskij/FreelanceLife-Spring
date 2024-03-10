package org.example.service.mapper;

import org.example.config.DatasourceConfig;
import org.example.config.ValidationConfig;
import org.example.config.WebMvcConfig;
import org.example.config.YamlPropertiesConfig;
import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.model.OrderEntity;
import org.example.model.QualificationEntity;
import org.example.service.dto.QualificationSimpleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({WebMvcConfig.class, ValidationConfig.class, DatasourceConfig.class, YamlPropertiesConfig.class})
class OrderMapperTest {

    @Autowired
    private OrderMapper mapper;

    @Test
    void toEntityFromSaveFormWhenNullAndNotNull() {
        var form = new OrderSaveForm();
        var orderPrice = new BigDecimal("100.00");
        var orderTerm = LocalDate.of(2024, 12, 31);
        form.setOrderTitle("test");
        form.setOrderDescription("test1");
        form.setOrderPrice(orderPrice);
        form.setOrderTerm(orderTerm);
        var simpleDto = new QualificationSimpleDto();
        simpleDto.setId(1L);
        simpleDto.setQualificationName("test22");
        form.setQualification(simpleDto);

        var entity = mapper.toEntity(form);
        var nullEntity = mapper.toEntity((OrderSaveForm) null);

        assertAll(
                () -> assertNull(nullEntity),
                () -> assertEquals("test", entity.getOrderTitle()),
                () -> assertEquals("test1", entity.getOrderDescription()),
                () -> assertEquals(orderPrice, entity.getOrderPrice()),
                () -> assertEquals(orderTerm, entity.getOrderTerm()),
                () -> assertAll(
                        () -> assertEquals(simpleDto.getId(), entity.getQualification().getId()),
                        () -> assertEquals(simpleDto.getQualificationName(), entity.getQualification().getQualificationName())
                )
        );
    }

    @Test
    void toSimpleDtoFromEntity() {
        var entity = new OrderEntity();
        var orderPrice = new BigDecimal("100.00");
        var orderTerm = LocalDate.of(2024, 12, 31);
        entity.setId(1L);
        entity.setOrderTitle("test");
        entity.setOrderDescription("test1");
        entity.setOrderPrice(orderPrice);
        entity.setOrderTerm(orderTerm);

        var dro = mapper.toSimpleDto(entity);
        var nullDto = mapper.toSimpleDto(null);

        assertAll(
                () -> assertNull(nullDto),
                () -> assertEquals(1L, dro.getId()),
                () -> assertEquals("test", dro.getOrderTitle()),
                () -> assertEquals("test1", dro.getOrderDescription()),
                () -> assertEquals(orderPrice, dro.getOrderPrice()),
                () -> assertEquals(orderTerm, dro.getOrderTerm())
        );
    }

    @Test
    void toEntityFromUpdateFormWhenNullAndNotNull() {
        var form = new OrderUpdateForm ();
        var orderPrice = new BigDecimal("100.00");
        var orderTerm = LocalDate.of(2024, 12, 31);
        form.setId(1L);
        form.setOrderTitle("test");
        form.setOrderDescription("test1");
        form.setOrderPrice(orderPrice);
        form.setOrderTerm(orderTerm);
        var simpleDto = new QualificationSimpleDto();
        simpleDto.setId(1L);
        simpleDto.setQualificationName("test22");
        form.setQualification(simpleDto);

        var entity = mapper.toEntity(form);
        var nullEntity = mapper.toEntity((OrderUpdateForm) null);

        assertAll(
                () -> assertNull(nullEntity),
                () -> assertEquals(1L, entity.getId()),
                () -> assertEquals("test", entity.getOrderTitle()),
                () -> assertEquals("test1", entity.getOrderDescription()),
                () -> assertEquals(orderPrice, entity.getOrderPrice()),
                () -> assertEquals(orderTerm, entity.getOrderTerm()),
                () -> assertAll(
                        () -> assertEquals(simpleDto.getId(), entity.getQualification().getId()),
                        () -> assertEquals(simpleDto.getQualificationName(), entity.getQualification().getQualificationName())
                )
        );
    }

    @Test
    void toDtoFromEntity() {
        var entity = new OrderEntity();
        var orderPrice = new BigDecimal("100.00");
        var orderTerm = LocalDate.of(2024, 12, 31);
        entity.setId(1L);
        entity.setOrderTitle("test");
        entity.setOrderDescription("test1");
        entity.setOrderPrice(orderPrice);
        entity.setOrderTerm(orderTerm);
        var qualif1 = new QualificationEntity();
        qualif1.setId(1L);
        qualif1.setQualificationName("test22");
        entity.setQualification(qualif1);

        var dto = mapper.toDto(entity);
        var nullDto = mapper.toDto(null);

        assertAll(
                () -> assertNull(nullDto),
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("test", dto.getOrderTitle()),
                () -> assertEquals("test1", dto.getOrderDescription()),
                () -> assertEquals(orderPrice, dto.getOrderPrice()),
                () -> assertEquals(orderTerm, dto.getOrderTerm()),
                () -> assertAll(
                        () -> assertEquals(qualif1.getId(), dto.getQualification().getId()),
                        () -> assertEquals(qualif1.getQualificationName(), dto.getQualification().getQualificationName())
                )
        );
    }
}