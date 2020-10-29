package com.thoughtworks.capability.gtb.entrancequiz.dto;

import com.thoughtworks.capability.gtb.entrancequiz.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto extends BaseDto {

    @Min(1)
    private Integer id;

    // TODO GTB-知识点: + 对参数进行了校验，不错
    @NotEmpty
    private String name;

    public static StudentDto from(StudentEntity studentEntity) {
        return StudentDto.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .build();
    }
}
