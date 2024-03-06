package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.WebMvcConfig;
import org.example.controller.forms.FreelancerSaveForm;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.service.FreelancerService;
import org.example.service.dto.FreelancerDto;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringJUnitWebConfig(WebMvcConfig.class)
@ExtendWith(MockitoExtension.class)
class FreelancerControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @Mock
    private FreelancerService service;

    @InjectMocks
    private FreelancerController controller;

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
        when(service.findById(id)).thenReturn(new FreelancerDto());

        mockMvc.perform(get("/freelancers/{id}", String.valueOf(id)))
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

        mockMvc.perform(get("/freelancers"))
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
        var saveFormJson = objectMapper.writeValueAsString(new FreelancerSaveForm());
        var savedDto = new FreelancerDto();
        savedDto.setId(1L);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(FreelancerSaveForm.class))).thenReturn(savedDto);

        mockMvc.perform(post("/freelancers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(FreelancerSaveForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new FreelancerUpdateForm();
        updateForm.setId(1L);
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        doNothing().when(service).update(any(FreelancerUpdateForm.class));

        mockMvc.perform(put("/freelancers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk()
                );

        verify(service, times(1)).update(any(FreelancerUpdateForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        Long id = 1L;
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/freelancers/{id}", String.valueOf(id)))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}