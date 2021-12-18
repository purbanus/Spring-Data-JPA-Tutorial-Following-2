package com.dailycodebuffer.spring.data.jpa.tutorial.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Dit is om een StackOverflowException te voorkomen: Teacher.toString() gebruikt Course.toString die weer  Teacher.toString gebruikt, etc etc ad nauseam
@ToString(exclude = "courses")
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;

    // Zie opmerking bij Course.teacher
    // @@NOG Maar wat als ik hier een list met courses wil hebben?
//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "teacher_id",
//            referencedColumnName = "teacherId"
//    )
//    private List<Course> courses;
    
  // Probeersel
	@OneToMany(
		mappedBy = "teacher",
		cascade = CascadeType.ALL, // Zodat de courses ook gesavet worden
		fetch = FetchType.LAZY 
	)
    private List<Course> courses;
}
