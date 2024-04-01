package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServicesImplTest {

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseService;

    @Test
    public void testAddCourse() {
        // Create a course
        Course course = new Course();
        course.setLevel(1);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);
        // courseRepository.save()
        when(courseRepository.save(course)).thenReturn(course);

        Course addedCourse = courseService.addCourse(course);

        verify(courseRepository, times(1)).save(course);
        // Assertions
        assertEquals(course, addedCourse);
    }
}
