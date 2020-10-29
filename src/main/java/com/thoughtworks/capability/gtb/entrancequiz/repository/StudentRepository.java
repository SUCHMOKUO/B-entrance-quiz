package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    // TODO GTB-工程实践: - 推荐使用Map保存学员数据
    private final Set<StudentEntity> studentEntities = new HashSet<StudentEntity>(36) {{
        add(new StudentEntity(1, "成吉思汗"));
        add(new StudentEntity(2, "鲁班七号"));
        add(new StudentEntity(3, "太乙真人"));
        add(new StudentEntity(4, "钟无艳"));
        add(new StudentEntity(5, "花木兰"));
        add(new StudentEntity(6, "雅典娜"));
        add(new StudentEntity(7, "芈月"));
        add(new StudentEntity(8, "白起"));
        add(new StudentEntity(9, "刘禅"));
        add(new StudentEntity(10, "庄周"));
        add(new StudentEntity(11, "马超"));
        add(new StudentEntity(12, "刘备"));
        add(new StudentEntity(13, "哪吒"));
        add(new StudentEntity(14, "大乔"));
        add(new StudentEntity(15, "蔡文姬"));
    }};

    public List<StudentEntity> findAll() {
        return new ArrayList<>(studentEntities)
                .stream()
                .sorted(Comparator.comparingInt(StudentEntity::getId))
                .collect(Collectors.toList());
    }

    public StudentEntity save(StudentEntity studentEntity) {
        boolean entityHasNoId = Objects.isNull(studentEntity.getId());

        if (entityHasNoId) {
            studentEntities.add(studentEntity);
            // TODO GTB-工程实践: - 计算id的方式不够健壮，可以使用字段保存最大id
            studentEntity.setId(studentEntities.size());
        } else {
            studentEntities.remove(studentEntity);
            studentEntities.add(studentEntity);
        }

        return studentEntity;
    }

    public Optional<StudentEntity> findByName(String name) {
        return studentEntities
                .stream()
                .filter(studentEntity -> studentEntity.getName().equals(name))
                .findAny();
    }
}
