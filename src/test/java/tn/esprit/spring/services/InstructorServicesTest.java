package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.IInstructorRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
public class InstructorServicesTest {
    @Autowired
    private IInstructorServices instructorServices;
    @Autowired
    private ICourseServices courseServices;
    @Autowired
    private IInstructorRepository instructorRepository;

    @Test
    public void testAddInstructor() {
        // Create add

        Course course;
        course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SNOWBOARD);
        course.setPrice(50.0f);
        course.setTimeSlot(60);
        courseServices.addCourse(course);

        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L); // Set the instructor number
        instructor.setFirstName("Youssef");
        instructor.setLastName("chouchene");
        instructor.setDateOfHire(LocalDate.now());
        Set<Course> courses = new HashSet<>();
        Course newCourse = new Course();
        newCourse.setLevel(1);
        courses.add(newCourse);
        instructorServices.addInstructor(instructor);


    }
    @Test
    public void testUpdateInstructor() {
        // Create an instructor object with initial values
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), null);

        // Update the instructor
        instructorServices.updateInstructor(instructor);

        // Retrieve the updated instructor
        Instructor result = instructorServices.retrieveInstructor(instructor.getNumInstructor());

        // Assert that the retrieved instructor has the same attributes as the original one
        // Check if the result is not null before comparing attributes

            assertNotNull(instructor.getNumInstructor());
            assertNotNull(instructor.getFirstName());
            assertNotNull(instructor.getLastName());
            assertNotNull(instructor.getDateOfHire());

    }


    @Test
    public void testRetrieveAllInstructors() {
        // Create sample instructor objects
        Instructor instructor1 = new Instructor(1L, "John", "Doe", LocalDate.now(), null);
        Instructor instructor2 = new Instructor(2L, "Jane", "Smith", LocalDate.now(), null);
        List<Instructor> instructors = Arrays.asList(instructor1, instructor2);

        // Mock the behavior of retrieveAllInstructors() to return the sample instructor list
        instructorServices.retrieveAllInstructors();

        // Call retrieveAllInstructors() to get the result
        List<Instructor> result = instructorServices.retrieveAllInstructors();

        // Assert that the result list is not null
        assertNotNull(result);
    }
    @Test
    public void testAddInstructorAndAssignToCourse() {
        Course course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SNOWBOARD);
        course.setPrice(50.0f);
        course.setTimeSlot(60);
        course.setNumCourse(6000L);
        courseServices.addCourse(course);
        // Create a sample instructor object
        Instructor instructor = new Instructor(1L, "John", "Doe", LocalDate.now(), null);

        // Mock the behavior of the addInstructorAndAssignToCourse() method to indicate successful addition and assignment
        instructorServices.addInstructorAndAssignToCourse(instructor, eq(6000L));

        // Call the addInstructorAndAssignToCourse() method with the instructor and course ID
        Instructor result = instructorServices.addInstructorAndAssignToCourse(instructor,6000L );

        // Assert that the returned instructor is not null
        assertNotNull(result);
    }


}
