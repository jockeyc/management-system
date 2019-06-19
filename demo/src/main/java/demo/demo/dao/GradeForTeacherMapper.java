package demo.demo.dao;

import demo.demo.model.GradeForTeacher;
import demo.demo.model.GradeInfo;
import demo.demo.model.Selection;
import demo.demo.model.StudentsInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeForTeacherMapper {
    List<String> getCourse(String xq, String gh);
    List<GradeForTeacher> getCourseGrade(String xq,String gh,String km);
    void update(Selection selection);
    List<StudentsInfo> getStudentsInfo(String xq,String gh,String km);
}