package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest
{
@Autowired private CourseRepository courseRepository;

//@Test
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

    courseRepository.save(course);
}

@Test
public void printCourses() {
    List<Course> courses = courseRepository.findAll();
    
    System.out.println("courses = " + courses);
}
@Test
public void findAllPagination(){
    Pageable firstPagewithThreeRecords = PageRequest.of(0, 3);
    Pageable secondPageWithTwoRecords = PageRequest.of(1,2);
    
    Page<Course> page = courseRepository.findAll(firstPagewithThreeRecords);
    
    List<Course> courses = page.getContent();
    long totalElements = page.getTotalElements();
    long totalPages = page.getTotalPages();

    System.out.println("totalPages = " + totalPages);
    System.out.println("totalElements = " + totalElements);
    System.out.println("courses = " + courses);
}

@Test
public void findAllSorting() {
    Pageable sortByTitle =
            PageRequest.of(
                    0,
                    2,
                    Sort.by("title")
                    );
    Pageable sortByCreditDesc =
            PageRequest.of(
                    0,
                    2,
                    Sort.by("credit").descending()
            );

    Pageable sortByTitleAndCreditDesc =
            PageRequest.of(
                    0,
                    2,
                    Sort.by("title")
                    .descending()
                    .and(Sort.by("credit"))
            );
    
    List<Course> courses
            = courseRepository.findAll(sortByTitle).getContent();

    System.out.println("courses = " + courses);
}
@Test
public void printfindByTitleContaining() {
    Pageable firstPageTenRecords =
            PageRequest.of(0,10);

    List<Course> courses =
            courseRepository.findByTitleContaining(
                    "D",
                    firstPageTenRecords).getContent();

    System.out.println("courses = " + courses);
/**
 * Course(courseId=5, title=DBA, credit=5, courseMaterial=null, teacher=Teacher(teacherId=1, firstName=Qutub, lastName=Khan)), 
 * Course(courseId=9, title=DBA, credit=5, courseMaterial=null, teacher=null), 
 * Course(courseId=11, title=DBA, credit=5, courseMaterial=null, teacher=null), 
 * Course(courseId=13, title=DBA, credit=5, courseMaterial=null, teacher=null)]

 */
}


}
