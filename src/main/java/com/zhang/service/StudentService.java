package com.zhang.service;

import com.zhang.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: King
 * @Create: 2019-12-07 16:57
 * @Desc:
 **/
public interface StudentService {
    void insert(Student entity);

    void update(Student entity);

    void delete(Long id);

    List<Student> findAll();

    Student getById(Long id);

    List<Student> fuzzyQuery(@Param(value = "fuzzy")String fuzzy,@Param(value = "pageNum") Integer pageNum,@Param(value = "pageSize") Integer pageSize,@Param(value = "classId")Long classId);

    Integer findCount(@Param(value = "fuzzy") String fuzzy,@Param(value = "classId") Long classId);

    List<Student> findClassId(@Param(value = "classId") Long id,@Param("studentId") Long studentId );

}
