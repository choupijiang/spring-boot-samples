package io.ostenant.springboot.sample.entity;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "name", "lessonPeriod", "score"})
@ToString
public class Course implements Serializable {

    private Integer id;

    private String name;

    private Double lessonPeriod;

    private Double score;
}