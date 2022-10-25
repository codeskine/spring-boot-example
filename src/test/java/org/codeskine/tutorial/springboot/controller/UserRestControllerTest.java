package org.codeskine.tutorial.springboot.controller;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codeskine.tutorial.springboot.configuration.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Import(ApplicationTestConfiguration.class)
class UserRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void findAll() throws Exception {
    this.mockMvc.perform(get("/users"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements", is(2)));
  }

  @Test
  void findByEmail() throws Exception {
    this.mockMvc.perform(get("/users/s.veloccia@innen.it"))
        .andDo(print())
        .andExpect(status().isOk());
  }

}