<template>
  <el-container style="position:absolute; left:0; top:0; width:100%;height:100%;">
    <el-header height="80px">
      <h2 class="font" style="float: left;margin-top: 10px">教学事务管理系统</h2>
      <span class="font" style="float: right;margin-top: 35px">
        管理员：admin&nbsp;&nbsp;
         <el-button type="primary" size="mini" @click="exit">退出</el-button>
      </span>
    </el-header>
    <el-container >
      <el-aside width="150px">
        <admin-menu style="height: 100%"></admin-menu>
      </el-aside>
      <el-main >
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
  import TeacherMenu from './TeacherMenu'
  import InputGrade from  './InputGrade'
  import {mapState,mapActions,mapGetters} from 'vuex'
  import StudentsInfo from "./StudentsInfo";
  import GradeSummary from "./GradeSummary";
  import AdminAddCourse from "./AdminAddCourse";
  import AdminMenu from "./AdminMenu";
  export default {
    name: 'Main',
    data() {
      return {
      }
    },
    mounted(){
      this.init();
    },
    computed:{
      ...mapState({
        xm:state=>state.Info.xm,
        xh:state=>state.Info.gh,
      })
    },
    components: {
      AdminAddCourse,AdminMenu
    },
    methods:{
      init(){
        console.log(sessionStorage.getItem("info"));
        this.$store.dispatch("setInfoFun",JSON.parse(sessionStorage.getItem("info")))
      },
      exit(){
        this.$router.push({name: 'Login'});
      }
    }
  }

</script>

<style>
  .font{
    font-family:"Microsoft YaHei",serif;

  }
  .el-header {
    line-height: 60px;
  }



</style>
