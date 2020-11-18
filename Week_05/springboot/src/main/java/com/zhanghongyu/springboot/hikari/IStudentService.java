package com.zhanghongyu.springboot.jdbc;

import java.util.List;

public interface IStudentService {
    public void create(String name, Integer age);
    public List<Student> listStudents();
    public void delete(Integer id);
    public void update(Integer id, Integer age);
}
