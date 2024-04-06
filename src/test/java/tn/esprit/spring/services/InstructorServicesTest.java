package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest

public class InstructorServicesTest {
    @Autowired
    private IInstructorServices instructorServices;
    @Autowired
    private ICourseServices courseServices;
    @Test
    public void testAddInstructor() {
        // Create add

        Course course = new Course();
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



}
