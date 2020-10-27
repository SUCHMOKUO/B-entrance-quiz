package com.thoughtworks.capability.gtb.entrancequiz.entity;

import com.thoughtworks.capability.gtb.entrancequiz.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentEntity {

    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    public static StudentEntity from(StudentDto studentDto) {
        return StudentEntity.builder()
                .id(studentDto.getId())
                .name(studentDto.getName())
                .build();
    }
}
