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
    </el-form>
    <el-table
      ref="multipleTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      tooltip-effect="dark"
      style="width: 100%;justify-content:space-between"
      display="flex">
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
      <el-table-column
        fixed="right"
        label="修改"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination style="text-align: center"
                   layout="prev, pager, next"
                   :total="total"
                   :page-size="pageSize"
                   @current-change="currentChange">
    </el-pagination>
    <el-dialog
      title="输入成绩"
      :visible.sync="dialogVisible"
      width="40%">
      <el-form style="margin-right: 60px" :model="form">
        <el-form-item  label="平时成绩" :label-width="formLabelWidth">
          <el-input v-on:input="check" v-model="form.pscj"></el-input>
        </el-form-item>
        <el-form-item label="考试成绩" :label-width="formLabelWidth">
          <el-input   v-on:input="check" v-model="form.kscj"></el-input>
        </el-form-item>
        <el-form-item label="总评成绩" :label-width="formLabelWidth">
          <el-input v-on:input="check" v-model="form.zpcj"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateGrade">确 定</el-button>
      </div>
    </el-dialog>
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
      getCourseGrade() {
        this.axios.get('http://localhost:8080/GetCourseGrade', {
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
      handleClick(row) {
        this.dialogVisible = true;
        console.log(row);
        if(row.pscj) this.form.pscj=row.pscj;
        if(row.kscj) this.form.kscj=row.kscj;
        if(row.zpcj) this.form.zpcj=row.zpcj;
        this.form.xh=row.xh;
      },
      check(){
        if(this.form.pscj>100) this.form.pscj=100;
        if(this.form.pscj<0) this.form.pscj=0;
        if(this.form.kscj>100) this.form.kscj=100;
        if(this.form.kscj<0) this.form.kscj=0;
        if(this.form.zpcj>100) this.form.zpcj=100;
        if(this.form.zpcj<0) this.form.zpcj=0;
      },
      updateGrade(){
        let temp2={
          xh:this.form.xh.replace(/\s*/g, ""),
          xq:this.semester[this.value - 1].label.replace(/\s*/g, ""),
          kh:this.course[this.courseName - 1].label.replace(/\s*/g, ""),
          gh:this.$store.state.Info.gh.replace(/\s*/g, ""),
          pscj:this.form.pscj,
          kscj:this.form.kscj,
          zpcj:this.form.zpcj
        };
        this.axios.post('http://localhost:8080/UpdateGrade', temp2)
          .then(res => {
            console.log(res.data);
            this.getCourseGrade();
            this.dialogVisible=false;
          });
      }

    }
    ,
    mounted() {
      this.getSemester();
    }
  }
</script>
