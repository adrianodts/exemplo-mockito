package com.adrianodantas.exemplo.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.adrianodantas.exemplo.mockito.model.Course;
import com.adrianodantas.exemplo.mockito.service.CourseService;

public class CourseBusinessImplTest {
	
	private List<Course> courses;
	
	@SuppressWarnings("serial")
	@Before
	public void setup() {		
		courses = new ArrayList<Course>(){{
			add(new Course(1, 
					"C# Design patterns", 
					"This course will help those students who are having knowledge of C# and visual studio and willing to explore different design patterns for development as well as for interviews", 
					3));
			
			add(new Course(2, 
					"Angular 5", 
					"This course starts from scratch, you neither need to know Angular 1 nor Angular 2! (Angular 5 simply is the latest version of Angular 2)", 
					27));
			
			add(new Course(3, 
					"Java Master Class", 
					"Join just under 200,000 students just like you who’re having massive success with their Java 8 and Java 9 programs using this exact course (and learning to code the right way)", 
					72));
			
			add(new Course(3, 
					"Java Spring MVC", 
					"How to program with spring MVC to the right way", 
					8));
		}};
	}
	
	@Test
	public void filterCourseByNameTest_usingMockito() {
		//given
		int filterCount = 1; 
		
		//when
		CourseService courseServiceMock = mock(CourseService.class);		
		
		when(courseServiceMock.listAllCourses()).thenReturn(courses);		
		
		CourseBusinessImpl courseBusinessImpl = new CourseBusinessImpl(courseServiceMock);
		
		List<Course> courses = courseBusinessImpl.filterCourseByName("Spring");
		
		//then
		assertEquals(courses.size(), filterCount);
	}

	@Test
	public void filterCourseByNameTest_usingStub() {
				
		//given
		int filterCount = 2; 
		
		//when
		CourseServiceImplStub courseServiceImplStub = new CourseServiceImplStub();
		CourseBusinessImpl courseBusinessImpl = new CourseBusinessImpl(courseServiceImplStub);
		
		List<Course> courses = courseBusinessImpl.filterCourseByName("Java");
		
		//then
		assertEquals(courses.size(), filterCount);
	}
	
	
	
	@Test
	public void deleteCourse() {

		CourseService courseServiceMock = mock(CourseService.class);	

		when(courseServiceMock.listAllCourses()).thenReturn(courses);

		CourseBusinessImpl courseBusinessImpl = new CourseBusinessImpl(courseServiceMock);

		courseBusinessImpl.deleteNotRelated("Java");

		//verify(courseServiceMock).delete("Angular");

		verify(courseServiceMock, Mockito.never()).delete("Java");

		verify(courseServiceMock, Mockito.atLeast(0)).delete("Java");

		//verify(courseServiceMock, Mockito.times(1)).delete("Angular");
		// atLeastOnce, atLeast

	}
	
	public class CourseServiceImplStub implements CourseService{
		public List<Course> listAllCourses() {
			return courses;
		}	
		
		public void delete(Course course) {
			courses.remove(course);
		}
		
		public void delete(String name) {
			for (Course course : courses) {
				if (!course.getName().contains(name))
					courses.remove(course);
			}
		}
	}
	
}
