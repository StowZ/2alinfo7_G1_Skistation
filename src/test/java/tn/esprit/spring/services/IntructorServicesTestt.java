package tn.esprit.spring.services;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class IntructorServicesTestt {
    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @Mock
    private IInstructorRepository instructorRepository;
    @Mock
    private ICourseServices courseServices;
    @Mock
    private ICourseRepository courseRepository;
    @Order(1)
    @Test
    void testAddInstructor() {
        // Create an instructor object
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        Set<Course> courses = new HashSet<>();
        // Add courses to instructor if needed
        // instructor.setCourses(courses);

        // Mock the behavior of instructorRepository.save() to return the instructor object
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Call the method under test
        Instructor result = instructorServices.addInstructor(instructor);

        // Verify that instructorRepository.save() was called exactly once with any Instructor object
        verify(instructorRepository, times(1)).save(any(Instructor.class));

        // Assert that the returned instructor matches the expected instructor
        assertEquals(instructor.getFirstName(), result.getFirstName());
        assertEquals(instructor.getLastName(), result.getLastName());
        assertEquals(instructor.getDateOfHire(), result.getDateOfHire());
        // Assert other attributes if needed
    }
    @Order(2)
    @Test
    void testUpdateInstructor() {
        // Create an instructor object
        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L); // Set the ID of an existing instructor
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        // Mock the behavior of instructorRepository.save() to return the instructor object
        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        // Call the method under test
        Instructor result = instructorServices.updateInstructor(instructor);

        // Verify that instructorRepository.save() was called exactly once with any Instructor object
        verify(instructorRepository, times(1)).save(any(Instructor.class));

        // Assert that the returned instructor matches the expected instructor
        assertEquals(instructor.getFirstName(), result.getFirstName());
        assertEquals(instructor.getLastName(), result.getLastName());
        assertEquals(instructor.getDateOfHire(), result.getDateOfHire());
    }
    @Order(3)
    @Test
    void testRetrieveAllInstructors() {
        // Create a list of instructors for mock response
        List<Instructor> mockInstructors = new ArrayList<>();
        mockInstructors.add(new Instructor(1L, "John", "Doe", LocalDate.now(), null)); // Example instructor
        // Add more instructors as needed for your test scenario

        // Mock the behavior of instructorRepository.findAll() to return the mock instructors list
        when(instructorRepository.findAll()).thenReturn(mockInstructors);

        // Call the method under test
        List<Instructor> result = instructorServices.retrieveAllInstructors();

        // Verify that instructorRepository.findAll() was called exactly once
        verify(instructorRepository, times(1)).findAll();

        // Assert that the returned list of instructors matches the mock instructors list
        assertEquals(mockInstructors.size(), result.size());
        for (int i = 0; i < mockInstructors.size(); i++) {
            assertEquals(mockInstructors.get(i).getNumInstructor(), result.get(i).getNumInstructor());
            assertEquals(mockInstructors.get(i).getFirstName(), result.get(i).getFirstName());
            assertEquals(mockInstructors.get(i).getLastName(), result.get(i).getLastName());
            assertEquals(mockInstructors.get(i).getDateOfHire(), result.get(i).getDateOfHire());
            // Assert other attributes if needed
        }

    }
    @Order(4)
    @Test
    void testAddInstructorAndAssignToCourse() {
        // Create a mock course
        Course course = new Course();
        course.setNumCourse(200L);
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SNOWBOARD);
        course.setPrice(50.0f);
        course.setTimeSlot(60);
        courseRepository.save(course);
        // Mock the behavior of courseRepository.findById() to return the mock course
        when(courseRepository.findById(200L)).thenReturn(Optional.of(course));

        // Create a mock instructor
        Instructor instructor = new Instructor();
        instructor.setNumInstructor(1L); // Set the ID of an existing instructor
        instructor.setFirstName("John");
        instructor.setLastName("Doe");

        // Call the method under test
        Instructor result = instructorServices.addInstructorAndAssignToCourse(instructor, 200L);

        // Verify that courseRepository.findById() was called exactly once with the correct parameter
        verify(courseRepository, times(1)).findById(200L);

        // Verify that instructorRepository.save() was called exactly once
        verify(instructorRepository, times(1)).save(instructor);

    }
    @Order(5)
    @Test
    public void testDeleteInstructor() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Call the deleteInstructor() method with a sample instructor ID
        instructorServices.deleteInstructor(1L);

        // Verify that the deleteById() method of the instructorRepository was called with the correct ID
        verify(instructorRepository).deleteById(1L);
    }

}
