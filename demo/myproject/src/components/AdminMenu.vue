<template>
  <el-menu
    router :default-active="$route.path"
    default-active="/Admin/AddCourse"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose">
    <el-menu-item-group>
      <template slot="title">常用功能</template>
      <el-menu-item index="/Admin/AddCourse">
          <i class="el-icon-circle-plus"></i>
          <span slot="title">开设课程</span>
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
