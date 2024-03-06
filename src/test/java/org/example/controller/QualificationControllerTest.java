package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.WebMvcConfig;
import org.example.controller.forms.*;
import org.example.service.QualificationService;
import org.example.service.dto.QualificationDto;
import org.example.service.dto.QualificationSimpleDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@SpringJUnitWebConfig(WebMvcConfig.class)
@ExtendWith(MockitoExtension.class)
class QualificationControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    @Mock
    private QualificationService service;
    @InjectMocks
    private QualificationController controller;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        Long id = 1L;
        when(service.findById(id)).thenReturn(new QualificationDto());

        mockMvc.perform(get("/qualifications/{id}", String.valueOf(id)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findById(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    void findAllWhenDtosPresentedThenStatusOk() throws Exception {
        when(service.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/qualifications"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(notNullValue())
                );

        verify(service, times(1)).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void saveWhenFormValidAndNoSuchFreelancerInBDThenStatusOk() throws Exception {
        var saveFormJson = objectMapper.writeValueAsString(new QualificationSaveForm());
        var savedDto = new QualificationSimpleDto();
        savedDto.setId(1L);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(QualificationSaveForm.class))).thenReturn(savedDto);

        mockMvc.perform(post("/qualifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(QualificationSaveForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new FreelancerUpdateForm();
        updateForm.setId(1L);
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        doNothing().when(service).update(any(QualificationUpdateForm.class));

        mockMvc.perform(put("/qualifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk()
                );

        verify(service, times(1)).update(any(QualificationUpdateForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        Long id = 1L;
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/qualifications/{id}", String.valueOf(id)))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}