<template>
  <el-menu
    router :default-active="$route.path"
    default-active="/Student/Select"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose">
    <el-menu-item-group>
      <template slot="title">常用功能</template>
      <el-menu-item index="/Student/Select">
        <template slot="title">
          <i class="el-icon-circle-plus"></i>
          <span>选课</span>
        </template>
      </el-menu-item>
      <el-menu-item index="/Student/Delete">
        <i class="el-icon-remove"></i>
        <span slot="title">退课</span>
      </el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <template slot="title">数据查询</template>
      <el-menu-item index="/Student/Course">
        <i class="el-icon-info"></i>
        <span slot="title">课表查询</span>
      </el-menu-item>
      <el-menu-item index="/Student/Search">
        <i class="el-icon-info"></i>
        <span slot="title">课程查询</span>
      </el-menu-item>
      <el-menu-item index="/Student/Grade">
        <i class="el-icon-info"></i>
        <span slot="title">我的成绩</span>
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
