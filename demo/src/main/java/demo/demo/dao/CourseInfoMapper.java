package demo.demo.dao;

import demo.demo.model.CourseInfo;
import demo.demo.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseInfoMapper {
    List<CourseInfo> getByXq(String xq);
    List<CourseInfo> getByKh(String xq,String kh);
    List<CourseInfo> getByName(String xq,String name);
    List<CourseInfo> getByKhName(String xq,String id,String name);
    List<CourseInfo> getByXhXq(String xq, String xh);
}
