<template>
  <div class="login-box">
    <el-image class="shu" :src="url"></el-image>
    <el-row type="flex" justify="center">
      <el-col :span="6" class="margin:0">
        <el-input v-model="id" placeholder="请输入帐号">
          <template slot="prepend">帐号</template>
        </el-input>
      </el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="6">
        <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
          <template slot="prepend">密码</template>
        </el-input>
      </el-col>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="6">
        <el-button id="login" v-on:click="check" style="width:100%" type="primary">登录</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import SHU from '../assets/SHU.png'
  import Hello from './Hello'

  export default {
    name: "Login.vue",
    data() {
      return {
        id: '',
        password: '',
        url: SHU
      }
    },
    methods: {
      check: function (event) {
        //获取值
        var id = this.id;
        var password = this.password;
        if (id === '' || password === '') {
          this.$message({
            message: '账号或密码为空！',
            type: 'error'
          })
        } else {
          this.axios.post('http://localhost:8080/login', {'id': this.id, 'pwd': this.password}).then(res => {
            console.log(res.data);
            if (res.data.code === 1) {
              this.$store.dispatch("setInfoFun", res.data.data);
              this.$router.push({name: 'Select', params: {id: id}});
            } else if (res.data.code === 2) {
              this.$store.dispatch("setInfoFun", res.data.data);
              this.$router.push({name: 'InputGrade', params: {id: id}});
            } else if (res.data.code === 3) {
              this.$store.dispatch("setInfoFun", {id:id});
              this.$router.push({name: 'AddCourse', params: {id: id}});
            } else {
              this.$message({
                message: res.data.msg,
                type: 'error'
              })
            }
          });
        }
      }
    }
  }
</script>

<style>
  .el-row {
    margin-bottom: 20px;
  }

  .login-box {
    text-align: center;
    margin-top: 100px;
  }

  .shu {
    margin-bottom: 50px;
  }
</style>
