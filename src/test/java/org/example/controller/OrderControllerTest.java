package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.forms.FreelancerUpdateForm;
import org.example.controller.forms.OrderSaveForm;
import org.example.controller.forms.OrderUpdateForm;
import org.example.service.OrderService;
import org.example.service.dto.OrderDto;
import org.example.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
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

class OrderControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;
    private OrderService service;

    @BeforeAll
    static void beforeAll() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void beforeEach() {
        service = mock(OrderServiceImpl.class);
        OrderController controller = new OrderController(service);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void findByIdWhenPathVarValidAndDtoPresentedThenStatusOk() throws Exception {
        Long id = 1L;
        when(service.findById(id)).thenReturn(new OrderDto());

        mockMvc.perform(get("/orders/{id}", String.valueOf(id)))
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

        mockMvc.perform(get("/orders"))
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
        var saveFormJson = objectMapper.writeValueAsString(new OrderSaveForm());
        var savedDto = new OrderDto();
        savedDto.setId(1L);
        var responseJson = objectMapper.writeValueAsString(savedDto);

        when(service.save(any(OrderSaveForm.class))).thenReturn(savedDto);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(saveFormJson))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(responseJson)
                );

        verify(service, times(1)).save(any(OrderSaveForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateWhenFormValidThenStatusOk() throws Exception {
        var updateForm = new FreelancerUpdateForm();
        updateForm.setId(1L);
        var updateFormJson = objectMapper.writeValueAsString(updateForm);
        doNothing().when(service).update(any(OrderUpdateForm.class));

        mockMvc.perform(put("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateFormJson))
                .andExpectAll(
                        status().isOk()
                );

        verify(service, times(1)).update(any(OrderUpdateForm.class));
        verifyNoMoreInteractions(service);
    }

    @Test
    void deleteWhenValidPathVarThenStatusOk() throws Exception {
        Long id = 1L;
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/orders/{id}", String.valueOf(id)))
                .andExpect(status().isOk());

        verify(service, times(1)).delete(id);
        verifyNoMoreInteractions(service);
    }
}