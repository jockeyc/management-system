package demo.demo.dao;

import demo.demo.model.GradeInfo;
import demo.demo.model.SelectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GradeInfoMapper {
    List<GradeInfo> getGradeByIdSemester(String xh, String xq);
    List<String> getSemester();
}