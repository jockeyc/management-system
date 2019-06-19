package demo.demo.controller;

import demo.demo.dao.StudentMapper;
import demo.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class studentController {

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<Student> getAll() {
        return studentMapper.getAll();
    }
}
