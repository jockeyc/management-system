<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="学期">
        <el-select v-model="value" @change="getGrade" placeholder="请选择">
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
      :data="tableData"
      tooltip-effect="dark"
      style="width: 100%;justify-content:space-between"
      display="flex"
      :summary-method="getSummaries"
      show-summary
      @selection-change="handleSelectionChange">
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
        prop="pscj"
        label="平时成绩">
      </el-table-column>
      <el-table-column
        prop="kscj"
        label="考试成绩">
      </el-table-column>
      <el-table-column
        prop="zpcj"
        label="总评成绩">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        multipleSelection: [],
        semester: [],
        value: ''
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
        })
      },
      getGrade() {
        this.axios.get('http://localhost:8080/SearchGrade', {
          params: {
            'semester': this.semester[this.value-1].label.replace(/\s*/g,""),
            'xh': this.$store.state.Info.xh
          }
        }).then(res => {
          console.log(res.data.data);
          this.tableData = res.data.data;
        })
      },
      getSemester() {
        let smes=[];
        this.axios.get('http://localhost:8080/GetSemester').then(res => {
          res.data.data.forEach((item,i)=>{
            let temp={value:i+1,label:item};
            smes.push(temp);
          });
          this.semester=smes;
          console.log(this.semester);
        })
        this.value=this.$store.state.semester;
      }

    }
    ,
    mounted() {
      this.getSemester();
      this.refresh();
    }
  }
</script>
