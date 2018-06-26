package io.ostenant.springboot.sample.service;

import io.ostenant.springboot.sample.config.ReadOnlyDataSource;
import io.ostenant.springboot.sample.entity.Course;
import io.ostenant.springboot.sample.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public int deleteById(Integer id) {
        return courseMapper.deleteById(id);
    }

    public void saveAll(List<Course> courses) {
        courses.forEach(courseMapper::save);
    }

    @Transactional(readOnly = true)
    @ReadOnlyDataSource
    public List<Course> findAll() {
        return courseMapper.findAll();
    }

    @Transactional(readOnly = true)
    @ReadOnlyDataSource
    public Course findById(Integer id) {
        return courseMapper.findById(id);
    }

    public int update(Course course) {
        return courseMapper.update(course);
    }

    public void deleteAll() {
        courseMapper.deleteAll();
    }

}
