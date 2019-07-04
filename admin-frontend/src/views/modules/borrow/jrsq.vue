<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.sqmc" placeholder="借用单名称" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="dataForm.sqbmmc" placeholder="申请部门" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="dataForm.jcbmmc" placeholder="借出部门" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain>查询</el-button>
        <el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
      </el-form-item>
    </el-form>

    <el-form >
      <el-form-item style="margin-bottom: 5px">
        <el-button v-if="isAuth('borrow:jrsq:save')" size="small" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('borrow:jrsq:delete')" size="small"  type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <!--<el-table-column-->
        <!--prop="jrsqid"-->
        <!--header-align="center"-->
        <!--align="center"-->
        <!--width="130"-->
        <!--label="借用单编号">-->
      <!--</el-table-column>-->
      <el-table-column
        prop="sqmc"
        header-align="center"
        align="center"
        label="借用单名称">
      </el-table-column>
      <el-table-column
        prop="sqbmid"
        header-align="center"
        align="center"
        v-if="false"
        label="申请部门编号">
      </el-table-column>
      <el-table-column
        prop="sqbmmc"
        header-align="center"
        align="center"
        label="申请部门">
      </el-table-column>
      <el-table-column
        prop="jcbmid"
        header-align="center"
        align="center"
        v-if="false"
        label="借出部门编号">
      </el-table-column>
      <el-table-column
        prop="jcbmmc"
        header-align="center"
        align="center"
        label="借出部门">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        width="100"
        :formatter="dateFormat"
        label="申请时间">
      </el-table-column>
      <el-table-column
        prop="zt"
        header-align="center"
        align="center"
        width="100"
        label="状态">
        <template slot-scope="scope">
        <el-tag v-if="scope.row.ztm === 1" size="small" type="warning">草稿状态</el-tag>
        <el-tag v-if="scope.row.ztm === 2" size="small" type="primary">已提交</el-tag>
        <el-tag v-if="scope.row.ztm === 3" size="small" type="success">审核通过</el-tag>
        <el-tag v-if="scope.row.ztm === 9" size="small" type="danger">审核不通过</el-tag>
      </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="180"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.ztm === 1 || scope.row.ztm === 9" type="text" size="small" @click="addOrUpdateHandle(scope.row.jrsqid,scope.row.id)">修改</el-button>
          <el-button v-if="scope.row.ztm === 1 || scope.row.ztm === 9" type="text" size="small" @click="deleteHandle(scope.row.jrsqid)">删除</el-button>
          <el-button v-if="scope.row.ztm === 1 || scope.row.ztm === 9 " type="text" size="small" @click="sumbitHandle(scope.row.id)">提交</el-button>
          <el-button v-if="scope.row.ztm === 2" type="text" size="small" @click="withdrawHandle(scope.row.id)">撤回</el-button>
          <el-button type="text" size="small" @click="detailHandle(scope.row.jrsqid,scope.row.id)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <jrsq-detail v-if="jrsqDetailVisible" ref="jrsqDetail"></jrsq-detail>
  </div>
</template>

<script>
  import AddOrUpdate from './jrsq-add-or-update'
  import jrsqDetail from './jrsq-detail'
  let moment = require('moment');
  export default {
    data () {
      return {
        dataForm: {
          sqbm: '',
          sqbmmc:'',
          jcbmmc:''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        order: '',
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        jrsqDetailVisible: false,
        menuListTreeProps: {
          label: 'name',
          children: 'children'
        }
      }
    },
    components: {
      AddOrUpdate,
      jrsqDetail
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/borrow/jrsq/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'order': this.order,
            'sqmc': this.dataForm.sqmc,
            'sqbmmc': this.dataForm.sqbmmc,
            'jcbmmc': this.dataForm.jcbmmc
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },

      getDataAllList () {
        this.dataForm.sqmc = '';
        this.dataForm.sqbmmc = '';
        this.dataForm.jcbmmc = '';
        this.getDataList();
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (jrsqid, id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(jrsqid, id);
        })
      },
      // 删除
      deleteHandle (jrsqid) {
        var isAudit = false;

        for (var i = 0; i < this.dataListSelections.length; i++) {
          if (this.dataListSelections[i].ztm !== 1) {
            isAudit = true;
            break;
          }
        }

        if (!isAudit) {
          var jrsqids = jrsqid ? [jrsqid] : this.dataListSelections.map(item => {
            return item.jrsqid
          })
          this.$confirm(`确定进行${jrsqid ? '删除' : '批量删除'}操作?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http({
              url: this.$http.adornUrl('/borrow/jrsq/delete'),
              method: 'post',
              data: this.$http.adornData(jrsqids, false)
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.getDataList()
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          })
        }else {
          this.$message.error('请选择"草稿状态"的记录')
        }
      },

      // 提交
      sumbitHandle (id) {
        this.$confirm('确定提交？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/borrow/jrsq/submit'),
            method: 'post',
            data: this.$http.adornData(id, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },

      withdrawHandle (id) {
        this.$confirm('确定撤回？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/borrow/jrsq/withdraw'),
            method: 'post',
            data: this.$http.adornData(id, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },

      detailHandle (jrsqid, id) {
        this.jrsqDetailVisible = true;
        this.$nextTick(() => {
          this.$refs.jrsqDetail.init(jrsqid, id)
        })
      },

      dateFormat: function (row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return '';
        }
        return moment(date).format('YYYY-MM-DD');
      }
    }
  }
</script>
