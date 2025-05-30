package com.inditex.pricing.infrastructure.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the PriceController REST endpoint.
 * Validates different scenarios to ensure correct pricing logic.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String baseUrl = "/api/prices";

    @Test
    @DisplayName("Test 1: 2020-06-14 10:00 - Should return priceList 1 with price 35.50")
    void test1() throws Exception {
        mockMvc.perform(get(baseUrl)
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Test 2: 2020-06-14 16:00 - Should return priceList 2 with price 25.45 (higher priority)")
    void test2() throws Exception {
        mockMvc.perform(get(baseUrl)
                        .param("date", "2020-06-14T16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Test 3: 2020-06-14 21:00 - Should return priceList 1 with price 35.50")
    void test3() throws Exception {
        mockMvc.perform(get(baseUrl)
                        .param("date", "2020-06-14T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Test 4: 2020-06-15 10:00 - Should return priceList 3 with price 30.50")
    void test4() throws Exception {
        mockMvc.perform(get(baseUrl)
                        .param("date", "2020-06-15T10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Test 5: 2020-06-16 21:00 - Should return priceList 4 with price 38.95")
    void test5() throws Exception {
        mockMvc.perform(get(baseUrl)
                        .param("date", "2020-06-16T21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
