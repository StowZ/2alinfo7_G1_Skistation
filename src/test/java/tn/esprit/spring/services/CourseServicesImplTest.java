package tn.esprit.spring.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CourseServicesImplTest {

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllCourses() {
        // Given
        List<Course> courses = Arrays.asList(
                new Course(1L, 1, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 100.0f, 60, null),
                new Course(2L, 2, TypeCourse.COLLECTIVE_ADULT, Support.SNOWBOARD, 150.0f, 90, null)
        );
        when(courseRepository.findAll()).thenReturn(courses);

        // When
        List<Course> retrievedCourses = courseServices.retrieveAllCourses();

        // Then
        assertEquals(2, retrievedCourses.size());
        assertEquals(1, retrievedCourses.get(0).getLevel());
        assertEquals(TypeCourse.COLLECTIVE_CHILDREN, retrievedCourses.get(0).getTypeCourse());
        // Similarly, check other attributes
    }

    @Test
    void addCourse() {
        // Given
        Course courseToAdd = new Course(1L, 1, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 100.0f, 60, null);
        when(courseRepository.save(any(Course.class))).thenReturn(courseToAdd);

        // When
        Course addedCourse = courseServices.addCourse(courseToAdd);

        // Then
        assertEquals(courseToAdd, addedCourse);
        // You can also verify if save method of repository was called with correct arguments
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void updateCourse() {
        // Given
        Course courseToUpdate = new Course(1L, 1, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 100.0f, 60, null);
        when(courseRepository.save(any(Course.class))).thenReturn(courseToUpdate);

        // When
        Course updatedCourse = courseServices.updateCourse(courseToUpdate);

        // Then
        assertEquals(courseToUpdate, updatedCourse);
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    @Test
    void retrieveCourse() {
        // Given
        Long courseId = 1L;
        Course course = new Course(courseId, 1, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 100.0f, 60, null);
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // When
        Course retrievedCourse = courseServices.retrieveCourse(courseId);

        // Then
        assertNotNull(retrievedCourse);
        assertEquals(courseId, retrievedCourse.getNumCourse());
        assertEquals(TypeCourse.COLLECTIVE_CHILDREN, retrievedCourse.getTypeCourse());
        // Similarly, check other attributes
    }
}
