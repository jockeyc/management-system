<template>
  <div>
    <el-table
      ref="multipleTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      tooltip-effect="dark"
      style="width: 100%;justify-content:space-between"
      display="flex"
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
  </div>
</template>

<script>
  export default {
    data() {
      return {
        total:1000,
        pageSize:8,
        currentPage:1,
        tableData: [],
        multipleSelection: []
      }
    },
    computed: {
      selectInfo: () => {
        let temp2;
        for (i of this.multipleSelection) {
          let temp;
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
      deleteCourse() {
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
        this.axios.post('http://localhost:8080/DeleteCourse', temp2)
          .then(res => {
            console.log(res.data);
            this.refresh();
          });
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      refresh(){
        this.axios.get('http://localhost:8080/DeleteCourseInfo', {
          params: {
            'semester': this.$store.state.semester,
            'xh': this.$store.state.Info.xh
          }
        }).then(res => {
          console.log(res.data.data)
          this.tableData = res.data.data;
          this.total=res.data.data.length;
        })
      },
      currentChange(currentPage){
        this.currentPage = currentPage;
        this.multipleSelection=[];
      }
    },
    mounted() {
      this.refresh();
    }
  }
</script>
