package com.dailycodebuffer.spring.data.jpa.tutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // Als je die cascade niet doet wordt de Course die in het (nieuwe) CourseMaterial object zit, niet gesavet. Hibernate geeft de exception
    // org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial.course -> com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course; nested exception is java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial.course -> com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false             // Of de Course optioneel is. False betekent dat elke CourseMaterial een Course moet hebben
    )
    // The column in the Course entity/table that we are referencing
    @JoinColumn(
            name = "course_id", // Column name
            referencedColumnName = "courseId" //Field name
    )
    private Course course;
}
