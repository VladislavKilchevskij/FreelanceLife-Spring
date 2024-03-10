package org.example.service.mapper;

import org.example.config.DatasourceConfig;
import org.example.config.ValidationConfig;
import org.example.config.WebMvcConfig;
import org.example.config.YamlPropertiesConfig;
import org.example.controller.forms.QualificationSaveForm;
import org.example.controller.forms.QualificationUpdateForm;
import org.example.model.FreelancerEntity;
import org.example.model.OrderEntity;
import org.example.model.QualificationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig({WebMvcConfig.class, ValidationConfig.class, DatasourceConfig.class, YamlPropertiesConfig.class})
class QualificationMapperTest {

    @Autowired
    private QualificationMapper mapper;

    @Test
    void toEntityFromUpdateForm() {
        var form = new QualificationUpdateForm();
        form.setId(1L);
        form.setQualificationName("test");

        var entity = mapper.toEntity(form);

        assertAll(
                () -> assertEquals(1L, entity.getId()),
                () -> assertEquals("test", entity.getQualificationName())
        );
    }

    @Test
    void toEntityFromSaveForm() {
        var form = new QualificationSaveForm();
        form.setQualificationName("test");

        var entity = mapper.toEntity(form);

        assertAll(
                () -> assertNull(entity.getId()),
                () -> assertEquals("test", entity.getQualificationName())
        );
    }

    @Test
    void toSimpleDtoFromEntity() {
        var entity = new QualificationEntity();
        entity.setId(1L);
        entity.setQualificationName("test");

        var simpleDto = mapper.toSimpleDto(entity);

        assertAll(
                () -> assertEquals(1L, simpleDto.getId()),
                () -> assertEquals("test", simpleDto.getQualificationName())
        );
    }

    @Test
    void toDtoFromEntity() {
        var entity = new QualificationEntity();
        entity.setId(1L);
        entity.setQualificationName("test");

        var entityRel = new FreelancerEntity();
        entityRel.setId(1L);
        entityRel.setFreelancerName("test23");
        entityRel.setFreelancerSecondName("test24");
        entityRel.setFreelancerEmail("test25");

        var entityRel1 = new FreelancerEntity();
        entityRel1.setId(2L);
        entityRel1.setFreelancerName("test33");
        entityRel1.setFreelancerSecondName("test34");
        entityRel1.setFreelancerEmail("test35");

        List<FreelancerEntity> freelancers = List.of(entityRel, entityRel1);

        var entityRel2 = new OrderEntity();
        var orderPrice = new BigDecimal("100.00");
        var orderTerm = LocalDate.of(2024, 12, 31);
        entityRel2.setId(1L);
        entityRel2.setOrderTitle("test23");
        entityRel2.setOrderDescription("test24");
        entityRel2.setOrderPrice(orderPrice);
        entityRel2.setOrderTerm(orderTerm);

        var entityRel3 = new OrderEntity();
        var orderPrice1 = new BigDecimal("100.00");
        var orderTerm1 = LocalDate.of(2024, 12, 31);
        entityRel3.setId(2L);
        entityRel3.setOrderTitle("test23");
        entityRel3.setOrderDescription("test24");
        entityRel3.setOrderPrice(orderPrice1);
        entityRel3.setOrderTerm(orderTerm1);

        List<OrderEntity> orders = List.of(entityRel2, entityRel3);

        entity.setFreelancers(freelancers);
        entity.setOrders(orders);

        var dto = mapper.toDto(entity);

        assertAll(
                () -> assertEquals(1L, dto.getId()),
                () -> assertEquals("test", dto.getQualificationName()),

                () -> assertEquals(entityRel.getId(), dto.getFreelancers().get(0).getId()),
                () -> assertEquals(entityRel.getFreelancerName(), dto.getFreelancers().get(0).getFreelancerName()),
                () -> assertEquals(entityRel.getFreelancerSecondName(), dto.getFreelancers().get(0).getFreelancerSecondName()),
                () -> assertEquals(entityRel.getFreelancerEmail(), dto.getFreelancers().get(0).getFreelancerEmail()),

                () -> assertEquals(entityRel1.getId(), dto.getFreelancers().get(1).getId()),
                () -> assertEquals(entityRel1.getFreelancerName(), dto.getFreelancers().get(1).getFreelancerName()),
                () -> assertEquals(entityRel1.getFreelancerSecondName(), dto.getFreelancers().get(1).getFreelancerSecondName()),
                () -> assertEquals(entityRel1.getFreelancerEmail(), dto.getFreelancers().get(1).getFreelancerEmail()),

                () -> assertEquals(entityRel2.getId(), dto.getOrders().get(0).getId()),
                () -> assertEquals(entityRel2.getOrderTitle(), dto.getOrders().get(0).getOrderTitle()),
                () -> assertEquals(entityRel2.getOrderDescription(), dto.getOrders().get(0).getOrderDescription()),
                () -> assertEquals(entityRel2.getOrderPrice(), dto.getOrders().get(0).getOrderPrice()),
                () -> assertEquals(entityRel2.getOrderTerm(), dto.getOrders().get(0).getOrderTerm()),

                () -> assertEquals(entityRel3.getId(), dto.getOrders().get(1).getId()),
                () -> assertEquals(entityRel3.getOrderTitle(), dto.getOrders().get(1).getOrderTitle()),
                () -> assertEquals(entityRel3.getOrderDescription(), dto.getOrders().get(1).getOrderDescription()),
                () -> assertEquals(entityRel3.getOrderPrice(), dto.getOrders().get(1).getOrderPrice()),
                () -> assertEquals(entityRel3.getOrderTerm(), dto.getOrders().get(1).getOrderTerm())
        );
    }

    @Test
    void toDtoFromEntityWithNullArgument () {
        assertNull(mapper.toDto(null));
    }

    @Test
    void toSimpleDtoFromEntityWithNullArgument () {
        assertNull(mapper.toSimpleDto(null));
    }

    @Test
    void toEntityFromUpdateFormWithNullArgument () {
        assertNull(mapper.toEntity((QualificationUpdateForm) null));
    }

    @Test
    void toEntityFromSaveFormWithNullArgument () {
        assertNull(mapper.toEntity((QualificationSaveForm) null));
    }
}