package demo.demo.controller;


import demo.demo.dao.LoginUserMapper;
import demo.demo.dao.StudentMapper;
import demo.demo.dao.TeacherMapper;
import demo.demo.model.LoginUser;
import demo.demo.model.Student;
import demo.demo.model.Teacher;
import demo.demo.respone.RespCode;
import demo.demo.respone.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.AssertFalse;
import java.util.List;


@RestController
public class LoginController {
    @Autowired
    private LoginUserMapper loginUserMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;


    @PostMapping("/login")
    @ResponseBody
    public RespEntity login(@RequestBody LoginUser loginUser) {
        String id = loginUser.getId();
        String password = loginUser.getPwd();
        System.out.println("id：" + id + " pwd: " + password);
        if (loginUserMapper.isPwdTrue(loginUser)) {
            if (id.charAt(0) == '1') {
                Student student = studentMapper.getById(id);
                return new RespEntity(new RespCode(1, "学生登录成功"), student);
            } else if (id.charAt(0) == '0') {
                Teacher teacher = teacherMapper.getById(id);
                return new RespEntity(new RespCode(2, "老师登录成功"), teacher);
            } else if (id.equals("admin")){
                return new RespEntity(new RespCode(3, "管理员登录成功"));
            }
        } else {
            return new RespEntity(new RespCode(0, "账号不存在或密码错误"));
        }
        return new RespEntity(new RespCode(0,""));
    }
}