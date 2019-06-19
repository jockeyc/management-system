import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Hello from "../components/Hello";
import StudentMain from "../components/StudentMain";
import SelectCourseTable from "../components/SelectCourseTable"
import DeleteCourseTable from "../components/DeleteCourseTable"
import CourseTable from "../components/CourseTable"
import CourseSearch from "../components/CourseSearch"
import GradeTable from "../components/GradeTable"
import TeacherMain from "../components/TeacherMain";
import InputGrade from "../components/InputGrade";
import StudentsInfo from "../components/StudentsInfo";
import GradeSummary from "../components/GradeSummary";
import AdminMain from "../components/AdminMain";
import AdminAddCourse from "../components/AdminAddCourse";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/', // 和router-link to相呼应，导航到/newcontact
      name: 'Login',
      component: Login
    },
    {
      path: '/hello',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/Student',
      name: 'Student',
      component: StudentMain,
      children: [
        {
          // 当 /user/:id/profile 匹配成功，
          // UserProfile 会被渲染在 User 的 <router-view> 中
          path: '/Student/Select',
          name: 'Select',
          component: SelectCourseTable
        },
        {
          path: '/Student/Delete',
          name: 'Delete',
          component: DeleteCourseTable
        },
        {
          path: '/Student/Course',
          name: 'Course',
          component: CourseTable
        },
        {
          path: '/Student/Search',
          name: 'Search',
          component: CourseSearch
        },
        {
          path: '/Student/Grade',
          name: 'Grade',
          component: GradeTable
        }
      ]
    },
    {
      path: '/Teacher',
      name: 'Teacher',
      component: TeacherMain,
      children: [{
        path:'/Teacher/InputGrade',
        name:'InputGrade',
        component:InputGrade
      },{
        path:'/Teacher/StudentsInfo',
        name:'/Teacher/StudentsInfo',
        component:StudentsInfo
      },{
        path:'/Teacher/GradeSummary',
        name:'/Teacher/GradeSummary',
        component:GradeSummary
      }]
    },
    {
        path:'/Admin',
        name:'Admin',
        component:AdminMain,
        children:[{
          path:'/Admin/AddCourse',
          name:'AddCourse',
          component:AdminAddCourse
        }]
    }
    ]
})
