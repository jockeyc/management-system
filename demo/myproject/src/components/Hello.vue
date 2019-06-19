<template>
  <div>Hello {{id}}
  </div>
</template>

<script>
  export default {
    name: "Hello",
    props: [],
    data(){
      return{
        id:'hello',
      }
    },
    created() {
      this.id=this.$route.params.id;
    }
  }
</script>

<style scoped>

</style>
