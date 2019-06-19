<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="课程号">
        <el-input v-model="id" placeholder="课程号"></el-input>
      </el-form-item>
      <el-form-item label="课程名">
        <el-input v-model="name" placeholder="课程名"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="search" type="primary">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      ref="multipleTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      tooltip-effect="dark"
      style="width: 100%;justify-content:space-between"
      display="flex"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        prop="kh"
        label="课号">
      </el-table-column>
      <el-table-column
        prop="km"
        label="课名">
      </el-table-column>
      <el-table-column
        prop="xf"
        label="学分">
      </el-table-column>
      <el-table-column
        prop="xm"
        label="教师名">
      </el-table-column>
      <el-table-column
        prop="xs"
        label="学时">
      </el-table-column>
      <el-table-column
        prop="sksj"
        label="上课时间">
      </el-table-column>
      <el-table-column
        prop="xymc"
        label="学院">
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination style="text-align: center"
                     layout="prev, pager, next"
                     :total="total"
                     :page-size="pageSize"
                     @current-change="currentChange">
      </el-pagination>
    </div>
    <div style="margin-top: 20px">
      <el-button @click="selectCourse">选课</el-button>
      <el-button @click="toggleSelection()">取消选择</el-button>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        total: 1000,
        pageSize: 8,
        currentPage: 1,
        id: '',
        name: '',
        tableData: [],
        multipleSelection: []
      }
    },
    computed: {
      selectInfo: () => {
        let temp2 = [];
        for (i of this.multipleSelection) {
          let temp = [];
          temp.push(this.$store.state.Info.xh);
          temp.push(i.kh);
          temp.push(i.gh);
          temp.push(this.$store.state.semester);
          temp2.push(temp);
          console.log(temp2);
        }
        return temp2;
      }
    },
    methods: {
      toggleSelection(rows) {
        console.log(this.multipleSelection);
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      selectCourse() {
        let temp2 = [];
        let info = function (xh, kh, gh, semester) {
          this.xh = xh;
          this.kh = kh;
          this.gh = gh;
          this.semester = semester;
        };
        for (const value of this.multipleSelection) {
          let temp;
          temp = new info(this.$store.state.Info.xh, value.kh, value.gh, this.$store.state.semester);
          temp2.push(temp);
        }
        console.log(temp2);
        this.axios.post('http://localhost:8080/SelectCourse', temp2)
          .then(res => {
            console.log(res.data.code);
            if (res.data.code === 0) {
              this.$message({
                message: res.data.msg,
                type: 'error'
              });
            } else if (res.data.code === 1) {
              this.$message({
                message: res.data.msg,
                type: 'success'
              });
            } else {
              let fail='',success='';
              for(let value of res.data.data.failure){
                fail+=value.kh+" ";
              }
              for(let value of res.data.data.success){
                success+=value.kh+" ";
              }
              this.$message({
                message: '选课失败:'+fail+'\n'+'选课成功:'+success,
                type: 'warning'
              })
            }
          });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      search() {
        this.axios.get('http://localhost:8080/SearchCourseInfo', {
          params: {
            'semester': this.$store.state.semester,
            'id': this.id,
            'name': this.name
          }
        }).then(res => {
          console.log(res.data.data);
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        })
      },
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.multipleSelection = [];
      }
    },
    mounted() {
      this.axios.get('http://localhost:8080/SelectCourseInfo', {params: {'semester': this.$store.state.semester}}).then(res => {
        console.log(res.data.data);
        this.tableData = res.data.data;
        this.total = res.data.data.length;
      })
    }
  }
</script>
