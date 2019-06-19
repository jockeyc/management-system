package demo.demo.controller;

import demo.demo.dao.CourseInfoMapper;
import demo.demo.dao.GradeInfoMapper;
import demo.demo.dao.SelectionMapper;
import demo.demo.model.*;
import demo.demo.respone.RespCode;
import demo.demo.respone.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@RestController
public class CourseInfoController {
    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @GetMapping("/SelectCourseInfo")
    @ResponseBody
    public RespEntity selectCourseInfo(@RequestParam("semester")String semester){
        System.out.println("semester: "+semester);
        List<CourseInfo> courseInfos=courseInfoMapper.getByXq(semester);
        if(!courseInfos.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), courseInfos);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @Autowired
    private GradeInfoMapper gradeInfoMapper;
    @GetMapping("/SearchGrade")
    @ResponseBody
    public RespEntity SearchGrade (String semester,String xh){
        System.out.println("成绩查询"+semester+" "+xh);
        List<GradeInfo> gradeInfos= gradeInfoMapper.getGradeByIdSemester(xh,semester);
        if(!gradeInfos.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), gradeInfos);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @GetMapping("/GetSemester")
    @ResponseBody
    public RespEntity GetSemester (){
        List<String> semesters= gradeInfoMapper.getSemester();
        if(!semesters.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), semesters);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }



    @GetMapping("/DeleteCourseInfo")
    @ResponseBody
    public RespEntity deleteCourseInfo(@RequestParam("semester")String semester,String xh){
        System.out.println("semester: "+semester+" xh:"+xh);
        List<CourseInfo> courseInfos=courseInfoMapper.getByXhXq(semester,xh);
        if(!courseInfos.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), courseInfos);
        }else{
            return new RespEntity(new RespCode(0,"检索失败或为空"));
        }
    }

    @GetMapping("/SearchCourseInfo")
    @ResponseBody
    public RespEntity selectCourseInfoById(String semester,String id,String name){
        System.out.println("semester: "+semester+" 课号："+id+"课名："+name);
        List<CourseInfo> courseInfos;
        if(id!=""&&name!=""){
            courseInfos=courseInfoMapper.getByKhName(semester,id,name);
        }else if(id!=""){
            courseInfos=courseInfoMapper.getByKh(semester,id);
        }else if(name!=""){
            courseInfos=courseInfoMapper.getByName(semester,name);
        }else{
            courseInfos=courseInfoMapper.getByXq(semester);
        }
        if(!courseInfos.isEmpty()){
            return new RespEntity(new RespCode(1,"检索成功"), courseInfos);
        }else{
            return new RespEntity(new RespCode(0,"检索为空"));
        }
    }

    @Autowired
    private SelectionMapper selectionMapper;

    @PostMapping("/SelectCourse")
    @ResponseBody
    public RespEntity selectCourse(@RequestBody List<SelectInfo> selectInfos){
        List <SelectInfo> failure = new ArrayList<>();
        List <SelectInfo> success = new ArrayList<>();
        int count1=0,count2 =0;
        for (SelectInfo selectInfo : selectInfos) {
            if(selectionMapper.hasSame(selectInfo)){
                failure.add(selectInfo);
                count1++;
            }else {
                selectionMapper.insert(selectInfo);
                success.add(selectInfo);
                count2++;
            }
        }
        if(count1!=0 && count2!=0){
            return new RespEntity((new RespCode(2,"有部分课程已选择，不可重复选择")), new failsucess(failure,success));
        }else if(count2==0 ){
            return new RespEntity((new RespCode(0,"全部课程已选择，不可重复选择")));
        }
        return new RespEntity(new RespCode(1,"选课成功"));
    }

    @PostMapping("/DeleteCourse")
    @ResponseBody
    public RespEntity deleteCourse(@RequestBody List<SelectInfo> selectInfos){
        for (SelectInfo selectInfo : selectInfos) {
            selectionMapper.delete(selectInfo);
        }
        return new RespEntity(new RespCode(1,"选课成功"));
    }
}