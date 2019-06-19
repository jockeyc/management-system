<template>
  <el-container style="position:absolute; left:0; top:0; width:100%;height:100%;">
    <el-header height="80px">
      <h2 class="font" style="float: left;margin-top: 10px">教学事务管理系统</h2>
      <span class="font" style="float: right;margin-top: 35px">
        姓名：{{xm}}&nbsp;
        工号：{{xh}}
        <el-button type="primary" size="mini" @click="exit">退出</el-button>
      </span>
    </el-header>
    <el-container >
      <el-aside width="150px">
        <teacher-menu style="height: 100%"></teacher-menu>
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
      TeacherMenu,InputGrade,StudentsInfo,GradeSummary
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
