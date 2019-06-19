package demo.demo.dao;

import demo.demo.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {
    Teacher getById(String id);
}