package com.thoughtworks.capability.gtb.entrancequiz.controller;

import com.thoughtworks.capability.gtb.entrancequiz.dto.StudentDto;
import com.thoughtworks.capability.gtb.entrancequiz.entity.StudentEntity;
import com.thoughtworks.capability.gtb.entrancequiz.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void should_get_students_info() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(15)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[14].id", is(15)));
    }

    @Test
    void should_add_student() throws Exception {
        StudentDto studentDto = StudentDto.builder()
                .name("name")
                .build();

        String json = studentDto.toJson();

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(16)));

        Optional<StudentEntity> studentEntity = studentRepository.findByName("name");

        assertTrue(studentEntity.isPresent());
        assertEquals(studentEntity.get().getId().intValue(), 16);
    }

    @Test
    void should_not_add_student_when_empty_name() throws Exception {
        StudentDto studentDto = StudentDto.builder()
                .name("")
                .build();

        String json = studentDto.toJson();

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

        assertEquals(studentRepository.findAll().size(), 15);
    }
}
