package com.adrianodantas.exemplo.mockito.business;

import java.util.ArrayList;
import java.util.List;

import com.adrianodantas.exemplo.mockito.model.Course;
import com.adrianodantas.exemplo.mockito.service.CourseService;

public class CourseBusinessImpl {
	
	private CourseService courseService;

	public CourseBusinessImpl(CourseService courseService) {
		this.courseService = courseService;
	}
	
	public List<Course> filterCourseByName(String name){
		
		List<Course> allCourses = this.courseService.listAllCourses();
		List<Course> filteredCourses = new ArrayList<Course>();
		
		allCourses.stream()
			.filter(s -> s.getName().contains(name))
			.forEach(c -> {
				filteredCourses.add(c);
		}); 
				
		return filteredCourses;
	}
	
	public void deleteNotRelated(String name) {
		List<Course> allCourses = this.courseService.listAllCourses();
		for (Course course : allCourses) {
			if (!course.getName().contains(name)) {
				this.courseService.delete(course);
			}
		}
	}
	
}
 