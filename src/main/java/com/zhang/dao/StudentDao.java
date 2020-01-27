package com.zhang.dao;

import com.zhang.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-07 16:51
 * @Desc:
 **/
public interface StudentDao {

    void insert(Student entity);

    void update(Student entity);

    void delete(Long id);

    List<Student> findAll();

    Student getById(Long id);

    List<Student> fuzzyQuery(Map map);

    Integer findCount(@Param("fuzzy") String fuzzy,@Param("classId")Long classId);

    List<Student > exitContactBy(@Param(value = "studentName") String studentName);

    List<Student> findClassId(@Param(value = "classId") Long id,@Param("studentId") Long studentId );

}
