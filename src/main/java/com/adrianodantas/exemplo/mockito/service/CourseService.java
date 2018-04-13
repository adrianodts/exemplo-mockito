package com.adrianodantas.exemplo.mockito.service;

import java.util.List;

import com.adrianodantas.exemplo.mockito.model.Course;

public interface CourseService {

	public List<Course> listAllCourses();

	public void delete(Course course);
	
	public void delete(String name);
}
