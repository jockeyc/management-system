package demo.demo.controller;

import demo.demo.dao.CourseInfoMapper;
import demo.demo.dao.GradeForTeacherMapper;
import demo.demo.dao.GradeInfoMapper;
import demo.demo.dao.SelectionMapper;
import demo.demo.model.*;
import demo.demo.respone.RespCode;
import demo.demo.respone.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GradeInfoController {
    @Autowired
    private GradeForTeacherMapper gradeForTeacherMapper;

    @GetMapping("/GetCourse")
    @ResponseBody
    public RespEntity GetCourse(@RequestParam("semester")String semester,String gh){
        List<String> course=gradeForTeacherMapper.getCourse(semester,gh);
        if(!course.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), course);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @GetMapping("/GetCourseGrade")
    @ResponseBody
    public RespEntity GetCourseGrade(@RequestParam("semester")String semester,String gh,String km){
        List<GradeForTeacher> gradeForTeachers=gradeForTeacherMapper.getCourseGrade(semester,gh,km);
        if(!gradeForTeachers.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), gradeForTeachers);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @GetMapping("/GetStudentsInfo")
    @ResponseBody
    public RespEntity GetStudentsInfo(@RequestParam("semester")String semester,String gh,String km){
        List<StudentsInfo> studentsInfo=gradeForTeacherMapper.getStudentsInfo(semester,gh,km);
        if(!studentsInfo.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), studentsInfo);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @PostMapping("/UpdateGrade")
    @ResponseBody
    public RespEntity UpdateGrade(@RequestBody Selection selection){
        System.out.println(selection.getXh()+selection.getKh()+selection.getZpcj());
        gradeForTeacherMapper.update(selection);
        return new RespEntity(new RespCode(1,"修改成功"));
    }
}