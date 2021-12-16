package com.dailycodebuffer.spring.data.jpa.tutorial.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

@Id
@SequenceGenerator(
        name = "course_sequence",
        sequenceName = "course_sequence",
        allocationSize = 1
)
@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "course_sequence"
)
private Long courseId;

private String title;
private Integer credit;

// MappedBy is het veld in CourseMaterial dat de Course bevat
// We hebben CourseMaterial gedefinieerd als de Owner van de relatie, dus moet je mappedBy gebruiken om de property van de Owner aan te geven
// Eigenaardig: er komt in de database GEEN veld bij voor course_material!
@OneToOne(
        mappedBy = "course"
        // Dit doet hier niets, itt in CourseMaterial
//        fetch = FetchType.LAZY
)
private CourseMaterial courseMaterial;

// Advies van JPA: Ga altijd voor ManyToOne relaties, niet voor OneToMany
@ManyToOne(
        cascade = CascadeType.ALL
)
@JoinColumn(
        name = "teacher_id",
        referencedColumnName = "teacherId"
)
    private Teacher teacher;

//@ManyToMany(
//        cascade = CascadeType.ALL
//)
//@JoinTable(
//        name = "studen_course_map",
//        joinColumns = @JoinColumn(
//                name = "course_id",
//                referencedColumnName = "courseId"
//        ),
//        inverseJoinColumns = @JoinColumn(
//                name = "student_id",
//                referencedColumnName = "studentId"
//        )
//)
//private List<Student> students;
//
//public void addStudents(Student student){
//    if(students == null) students = new ArrayList<>();
//    students.add(student);
//}
}
