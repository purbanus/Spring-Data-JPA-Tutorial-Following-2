package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest
{
@Autowired private CourseRepository codeRepository;

@Test
public void saveCourseWithTeacher() {
    Teacher teacher = Teacher.builder()
            .firstName("Priyanka")
            .lastName("Singh")
            .build();

    Course course = Course
            .builder()
            .title("Python")
            .credit(6)
            .teacher(teacher)
            .build();

    codeRepository.save(course);
}

@Test
public void printCourses() {
    List<Course> courses = codeRepository.findAll();
    
    System.out.println("courses = " + courses);
}

}
