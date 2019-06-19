package demo.demo.dao;

import demo.demo.model.Open;

public interface OpenMapper {
    int insert(Open record);

    int insertSelective(Open record);
}