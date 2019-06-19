package demo.demo.dao;

import demo.demo.model.College;

public interface CollegeMapper {
    int insert(College record);

    int insertSelective(College record);
}