package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.CourseMaterial;

@SpringBootTest
class CourseMaterialRepositoryTest
{
@Autowired private CourseMaterialRepository repository;

@Test
public void SaveCourseMaterial() {
    Course course =
            Course.builder()
            .title(".net")
            .credit(6)
            .build();

    CourseMaterial courseMaterial =
            CourseMaterial.builder()
            .url("www.dailycodebuffertje.com")
            .course(course)
            .build();

    repository.save(courseMaterial);
}

//Omdat ze ID's hebben ziet hij het als bestaande rijen en savet ze alleen als er wat gewijzigd is
@Test
public void SaveCourseMaterialWithIds() {
    Course course =
            Course.builder()
            .courseId( 4L )
            .title(".netty")
            .credit(6)
            .build();

    CourseMaterial courseMaterial =
            CourseMaterial.builder()
            .courseMaterialId( 5L )
            .url("www.dailycodebuffer.com")
            .course(course)
            .build();

    repository.save(courseMaterial);
}

@Test
public void printAllCourseMaterials() {
    List<CourseMaterial> courseMaterials = repository.findAll();
    
    // Dit helpt niet
//    for ( CourseMaterial courseMaterial : courseMaterials )
//	{
//		System.out.println( courseMaterial.getCourse() );
//	}

    System.out.println("courseMaterials = " + courseMaterials);
}

}
