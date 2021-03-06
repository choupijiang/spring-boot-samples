package io.ostenant.springboot.sample;

import io.ostenant.springboot.sample.entity.Course;
import io.ostenant.springboot.sample.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private CourseService courseService;

    @Test
    public void testSaveCourse() throws Exception {
        courseService.saveAll(Arrays.asList(
                Course.builder().id(1)
                        .name("English")
                        .lessonPeriod(90.0)
                        .score(90.0)
                        .build(),
                Course.builder().id(2)
                        .name("Math")
                        .lessonPeriod(90.0)
                        .score(95.0)
                        .build()));

    }

    @Test
    public void testUpdateCourse() throws Exception {
        courseService.update(Course.builder()
                .id(0).score(95.0)
                .build());
    }

    @Test
    public void testFindAllCourses() throws Exception {
        List<Course> courses = courseService.findAll();
        assertThat(courses.size(), equalTo(2));
        assertThat(courses.get(0), equalTo(Course.builder().id(1)
                .name("English")
                .lessonPeriod(90.0)
                .score(90.0)
                .build()));

        assertThat(courses.get(1), equalTo(Course.builder().id(2)
                .name("Math")
                .lessonPeriod(90.0)
                .score(95.0)
                .build()));
    }

    @Test
    public void testFindCourseById() throws Exception {
        Course course = courseService.findById(1);
        assertThat(course, equalTo(Course.builder().id(1)
                .name("English")
                .lessonPeriod(90.0)
                .score(95.0)
                .build()));
    }

    @Test
    public void testDeleteCourseById() throws Exception {
        courseService.deleteById(1);
    }

    @Test
    public void testDeleteAll() {
        courseService.deleteAll();
    }

}
