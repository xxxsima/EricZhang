package com.zhang.service.impl;

import com.zhang.Exception.StudentException;
import com.zhang.dao.StudentDao;
import com.zhang.model.Student;
import com.zhang.other.message.StudentMessage;
import com.zhang.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: King
 * @Create: 2019-12-07 17:01
 * @Desc:
 **/
@Service("studentService")
public class StudentServiceImpl  implements StudentService {
    @Resource
    private StudentDao studentDao;
    @Override
    public void insert(Student entity) {
        if((entity.getId()==null && exitcontactBy(entity.getStudentName()))) {
            throw  new StudentException(StudentMessage.NAME_VERIFY);
        }
        studentDao.insert(entity);
    }
    @Override
    public void update(Student entity) {
        if (entity.getId()!=null && entity.getId()!= ' ') {
            studentDao.update(entity);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            studentDao.delete(id);
        }
    }
    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student getById(Long id) {
        return studentDao.getById(id);
    }

    @Override
    public List<Student> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize,Long classId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("classId",classId);
        return studentDao.fuzzyQuery(map);
    }

    @Override
    public Integer findCount(String fuzzy,Long classId) {
        return studentDao.findCount(fuzzy,classId);
    }

    @Override
    public List<Student> findClassId(Long id, Long studentId) {
        return studentDao.findClassId(id,studentId);
    }

    private boolean exitcontactBy(String studentName) {
        return studentDao.exitContactBy(studentName).size()>0;
    }
}
