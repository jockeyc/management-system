<template>
  <el-menu
    router :default-active="$route.path"
    default-active="/Teacher/InputGrade"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose">
    <el-menu-item-group>
      <template slot="title">常用功能</template>
      <el-menu-item index="/Teacher/InputGrade">
          <i class="el-icon-circle-plus"></i>
          <span slot="title">成绩登记</span>
      </el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <template slot="title">数据查询</template>
      <el-menu-item index="/Teacher/StudentsInfo">
        <i class="el-icon-info"></i>
        <span slot="title">学生信息</span>
      </el-menu-item>
      <el-menu-item index="/Teacher/GradeSummary">
        <i class="el-icon-info"></i>
        <span slot="title">成绩总览</span>
      </el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<script>
  export default {
    name: "Menu",
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>

</style>
