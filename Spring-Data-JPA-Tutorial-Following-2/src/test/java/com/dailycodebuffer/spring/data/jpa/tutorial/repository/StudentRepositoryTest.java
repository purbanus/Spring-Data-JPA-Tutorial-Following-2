package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;

@SpringBootTest
// @DataJpaTest Dit doet een rollback na elke test. Tevens gebruikt het een in-memory database
class StudentRepositoryTest
{
@Autowired
private StudentRepository studentRepository;

@Test
public void saveStudent()
{
	Guardian guardian = Guardian.builder()
	    .email( "marjo@gmail.com" )
	    .name( "Marjo" )
	    .mobile( "1234567890" )
	    .build();
	Student student = Student.builder()
	    .emailId( "marjo@gmail.com" )
	    .firstName( "Marjo" )
	    .lastName( "van Erp" )
	    .guardian( guardian )
	    .build();
	studentRepository.save( student );

}
@Test
public void saveStudentWithGuardian()
{
	Guardian guardian = Guardian.builder()
	    .email( "marjo@gmail.com" )
	    .name( "Marjo" )
	    .mobile( "1234567890" )
	    .build();
	Student student = Student.builder()
	    .emailId( "purbanus@gmail.com" )
	    .firstName( "Peter" )
	    .lastName( "Urbanus" )
	    .guardian( guardian )
	    .build();
	studentRepository.save( student );

}
@Test
public void updateFirstNames()
{
	Student student = studentRepository.findById( 10L )
	    .orElseThrow();
	student.setFirstName( "Karel" );
	student.setLastName( "Loopgraaf" );
	studentRepository.save( student );
	student = studentRepository.findById( 11L )
	    .orElseThrow();
	student.setFirstName( "Karel" );
	studentRepository.save( student );
}
@Test
public void printAll()
{
	List<Student> students = studentRepository.findAll();
	System.err.println( "students = " + students );
}
@Test
public void printStudentsByFirstName()
{
	List<Student> students = studentRepository.findByFirstName( "Karel" );
	System.err.println( "students by first name = " + students );
}
@Test
public void printStudentsByFirstNameContaining()
{
	List<Student> students = studentRepository.findByFirstNameContaining( "et" );
	System.err.println( "students by first name containing = " + students );
}
@Test
public void printStudentsByGuardianName()
{
	List<Student> students = studentRepository.findByGuardianName( "Marjo" );
	System.err.println( "students by guardian name = " + students );
}
@Test
public void printStudentsByFirstAndLastName()
{
	Student student = studentRepository.findByFirstNameAndLastName( "Karel", "Loopgraaf" );
	System.err.println( "students by first and last name = " + student );
}
@Test
public void printStudentByEmailAddress()
{
	Student student = studentRepository.getStudentByEmailAddress( "marjo@gmail.com" );
	System.err.println( "students by email address = " + student );
}
@Test
public void printgetStudentFirstNameByEmailAddress()
{
	String firstName = studentRepository.getStudentFirstNameByEmailAddress( "marjo@gmail.com" );
	System.err.println( "firstName = " + firstName );
}

@Test
public void printgetStudentByEmailAddressNative()
{
	Student student = studentRepository.getStudentByEmailAddressNative( "marjo@gmail.com" );
	System.err.println( "student = " + student );
}
@Test
public void printgetStudentByEmailAddressNativeNamedParam()
{
	Student student = studentRepository.getStudentByEmailAddressNativeNamedParam( "marjo@gmail.com" );
	System.err.println( "student = " + student );
}

@Test
public void updateStudentNameByEmailIdTest()
{
	studentRepository.updateStudentNameByEmailId( "Lieve Marjo", "marjo@gmail.com" );
}

}
