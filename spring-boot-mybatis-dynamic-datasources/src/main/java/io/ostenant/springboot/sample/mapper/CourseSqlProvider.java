package io.ostenant.springboot.sample.mapper;

import io.ostenant.springboot.sample.entity.Course;
import org.apache.ibatis.jdbc.SQL;

public class CourseSqlProvider {

    public String update(Course course) {
        return new SQL() {
            {
                UPDATE("course");

                if (course.getName() != null) {
                    SET("name = #{name,jdbcType=VARCHAR}");
                }

                if (course.getLessonPeriod() != null) {
                    SET("lesson_period = #{lessonPeriod,jdbcType=DOUBLE}");
                }

                if (course.getScore() != null) {
                    SET("score = #{score,jdbcType=DOUBLE}");
                }

                WHERE("id = #{id,jdbcType=INTEGER}");
            }
        }.toString();
    }

}
