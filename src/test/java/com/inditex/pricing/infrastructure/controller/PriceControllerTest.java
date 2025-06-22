package com.inditex.pricing.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for PriceController using H2 in-memory database.
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1_priceRequestAt2020_06_14_10_00() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2_priceRequestAt2020_06_14_16_00() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void test3_priceRequestAt2020_06_14_21_00() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test4_priceRequestAt2020_06_15_10_00() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void test5_priceRequestAt2020_06_16_21_00() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
