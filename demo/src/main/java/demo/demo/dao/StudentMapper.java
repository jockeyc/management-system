package demo.demo.dao;

import demo.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface StudentMapper {
    List<Student> getAll();
    Student getById(String id);
}
