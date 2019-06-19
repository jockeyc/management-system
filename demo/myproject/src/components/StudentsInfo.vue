<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="学期">
        <el-select v-model="value" @change="getCourse" placeholder="请选择">
          <el-option
            v-for="item in semester"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程">
        <el-select v-model="courseName" @change="getStudentsInfo" placeholder="请选择">
          <el-option
            v-for="item in course"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-table
      ref="multipleTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      tooltip-effect="dark"
      style="width: 100%;justify-content:space-between"
      display="flex">
      <el-table-column
        prop="xh"
        label="学号">
      </el-table-column>
      <el-table-column
        prop="xm"
        label="姓名">
      </el-table-column>
      <el-table-column
        prop="xb"
        label="性别">
      </el-table-column>
      <el-table-column
        prop="xy"
        label="学院">
      </el-table-column>
      <el-table-column
        prop="sjhm"
        label="手机号码">
      </el-table-column>
    </el-table>
    <el-pagination style="text-align: center"
                   layout="prev, pager, next"
                   :total="total"
                   :page-size="pageSize"
                   @current-change="currentChange">
    </el-pagination>
  </div>


</template>

<script>
  export default {
    data() {
      return {
        dialogVisible: false,
        total: 0,
        pageSize: 8,
        currentPage: 1,
        tableData: [],
        multipleSelection: [],
        semester: [],
        course: [],
        value: '',
        courseName: '',
        formLabelWidth: '120px',
        form:{
          pscj:undefined,
          kscj:undefined,
          zpcj:undefined,
          xh:undefined
      }
      }
    },
    computed: {},
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
      getSummaries(param) {
        const {columns, data} = param;
        const average = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            average[index] = '平均分';
            return;
          } else if (index === 2) {
            average[index] = '';
            return;
          }
          const values = data.map(item => Number(item[column.property]));
          if (!values.every(value => isNaN(value))) {
            average[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            average[index] /= values.length;
          } else {
            average[index] = '';
          }
        });
        return average;
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      refresh() {
        this.axios.get('http://localhost:8080/SearchGrade', {
          params: {
            'semester': this.$store.state.semester,
            'xh': this.$store.state.Info.xh
          }
        }).then(res => {
          console.log(res.data.data);
          this.tableData = res.data.data;
          this.total=res.data.data.length;
        })
      },
      getCourse() {
        let a = [];
        this.axios.get('http://localhost:8080/GetCourse', {
          params: {
            'semester': this.semester[this.value - 1].label.replace(/\s*/g, ""),
            'gh': this.$store.state.Info.gh
          }
        }).then(res => {
          if (res.data.code === 1) {
            res.data.data.forEach((item, i) => {
              let temp = {value: i + 1, label: item};
              a.push(temp);
            });
            this.course = a;
          } else {
            this.course = [];
          }
          this.courseName = '';
          this.tableData=[];
        })
      },
      getSemester() {
        let smes = [];
        this.axios.get('http://localhost:8080/GetSemester').then(res => {
          res.data.data.forEach((item, i) => {
            let temp = {value: i + 1, label: item};
            smes.push(temp);
          });
          this.semester = smes;
          console.log(this.semester);
        });
        this.value = '';
      },
      getStudentsInfo() {
        this.axios.get('http://localhost:8080/GetStudentsInfo', {
          params: {
            'semester': this.semester[this.value - 1].label.replace(/\s*/g, ""),
            'gh': this.$store.state.Info.gh,
            'km': this.course[this.courseName - 1].label.replace(/\s*/g, ""),
          }
        }).then(res => {
          this.tableData = res.data.data;
          this.total = res.data.data.length;
        })
      },
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.multipleSelection = [];
      },


    }
    ,
    mounted() {
      this.getSemester();
    }
  }
</script>
