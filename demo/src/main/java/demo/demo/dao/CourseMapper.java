package demo.demo.dao;

import demo.demo.model.Course;

public interface CourseMapper {
    int insert(Course record);

    int insertSelective(Course record);
}