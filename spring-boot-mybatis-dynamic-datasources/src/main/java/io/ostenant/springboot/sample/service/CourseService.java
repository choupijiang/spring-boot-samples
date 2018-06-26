package io.ostenant.springboot.sample.service;

import io.ostenant.springboot.sample.entity.Course;

import java.util.List;

public interface CourseService {

    int deleteById(Integer id);

    void saveAll(List<Course> courses);

    List<Course> findAll();

    Course findById(Integer id);

    int update(Course course);

    void deleteAll();

}
