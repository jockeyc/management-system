<template>
  <el-container style="position:absolute; left:0; top:0; width:100%;height:100%;">
    <el-header height="80px">
      <h2 class="font" style="float: left;margin-top: 10px">教学事务管理系统</h2>
      <span class="font" style="float: right;margin-top: 35px">
        姓名：{{xm}}&nbsp;
        {{identity}}{{xh}}&nbsp;&nbsp;
        <el-button type="primary" size="mini" @click="exit">退出</el-button>
      </span>

    </el-header>
    <el-container >
      <el-aside width="150px">
        <my-menu style="height: 100%"></my-menu>
      </el-aside>
      <el-main >
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
  import MyMenu from './MyMenu'
  import SelectCourseTable from  './SelectCourseTable'
  import DeleteCourseTable from  './DeleteCourseTable'
  import CourseTable from './CourseTable'
  import CourseSearch from './CourseSearch'
  import GradeTable from './GradeTable'
  import {mapState,mapActions,mapGetters} from 'vuex'
  export default {
    name: 'Main',
    data() {
      return {
        identity:""
      }
    },
    mounted(){
      this.init();
    },
    computed:{
      ...mapState({
        xm:state=>state.Info.xm,
        xh:state=>state.Info.xh,
      })
    },
    components: {
      MyMenu,SelectCourseTable,DeleteCourseTable,CourseTable,
      CourseSearch,GradeTable
    },
    methods:{
      init(){
        console.log(sessionStorage.getItem("info"));
        this.$store.dispatch("setInfoFun",JSON.parse(sessionStorage.getItem("info")))
        this.identity=this.$store.state.Info.xh[0]==="1"?"学号：":"工号：";
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
