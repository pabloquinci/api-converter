package com.possumus.apiconverter;

import com.possumus.apiconverter.config.ConversionConfig;
import com.possumus.apiconverter.controller.ConversionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ConversionIntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
    @Test
    public void runTests() throws Exception {
        this.mockMvc.perform(get("http://localhost:8080/api/conversions/decimalARomano/3560")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(get("http://localhost:8080/api/conversions/romanoADecimal/MMMMMCMXCVIII")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}
