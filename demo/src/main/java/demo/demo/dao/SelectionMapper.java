package demo.demo.dao;

import demo.demo.model.SelectInfo;
import demo.demo.model.Selection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SelectionMapper {
    int  insert(SelectInfo record);
    void delete(SelectInfo record);
    boolean hasSame(SelectInfo record);
}