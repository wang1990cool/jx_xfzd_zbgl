<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.ssbmmc" placeholder="原所属部门" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.zbmc" placeholder="装备名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.xssbmmc" placeholder="现所属部门" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('kcgl:zbkcmxb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button  type="danger" @click="printHandle()" :disabled="dataListSelections.length <= 0">批量打印条码</el-button>
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
      <el-table-column
        prop="zbbm"
        header-align="center"
        align="center"
        label="装备编码"
        width="100">
      </el-table-column>
      <el-table-column
        prop="zbmc"
        header-align="center"
        align="center"
        label="装备名称">
      </el-table-column>
<!--      <el-table-column
        prop="ssbmid"
        header-align="center"
        align="center"
        label="所属部门id">
      </el-table-column>-->
      <el-table-column
        prop="ssbmmc"
        header-align="center"
        align="center"
        label="所属部门名称"
        width="120">
      </el-table-column>
<!--      <el-table-column
        prop="ztm"
        header-align="center"
        align="center"
        label="状态码">
        <template slot-scope="scope">
          <el-button type="text" size="small" v-if="scope.row.ztm ==1 " >
            在库
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.ztm ==2 " >
            在车
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.ztm ==3 " >
            在人
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.ztm ==4 " >
            借出
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.ztm ==6 " >
            报废
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.ztm ==7 " >
            微型消防站
          </el-button>
        </template>
      </el-table-column>-->
      <el-table-column
        prop="ztxs"
        header-align="center"
        align="center"
        label="状态"
        width="80">
      </el-table-column>

      <el-table-column
        prop="zt"
        header-align="center"
        align="center"
        label="装备位置">
      </el-table-column>
<!--      <el-table-column
        prop="xssbmid"
        header-align="center"
        align="center"
        label="现所属部门编码">
      </el-table-column>-->
      <el-table-column
        prop="xssbmmc"
        header-align="center"
        align="center"
        label="现所属部门名称"
        width="130">
      </el-table-column>
<!--      <el-table-column
        prop="bfrq"
        header-align="center"
        align="center"
        label="报废日期">
      </el-table-column>
      <el-table-column
        prop="byrq"
        header-align="center"
        align="center"
        label="上次保养日期">
      </el-table-column>
      <el-table-column
        prop="scrq"
        header-align="center"
        align="center"
        label="生产日期">
      </el-table-column>
      <el-table-column
        prop="createUserId"
        header-align="center"
        align="center"
        label="">
      </el-table-column>-->
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="入库时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">查看</el-button>
          <el-button type="text" size="small" @click="printHandle(scope.row.id)">打印条码</el-button>
<!--
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
-->
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
  </div>
</template>

<script>
  import AddOrUpdate from './zbkcmxb-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          ssbmmc: '',
          zbmc: '',
          xssbmmc: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/kcgl/zbkcmxb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'ssbmmc': this.dataForm.ssbmmc,
            'zbmc': this.dataForm.zbmc,
            'xssbmmc': this.dataForm.xssbmmc
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
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
        //打印
      printHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '打印' : '批量打印'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/kcgl/zbkcmxb/print'),
            method: 'post',
            data: this.$http.adornData(ids, false)
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
        /*this.$http({
          url: this.$http.adornUrl('/kcgl/zbkcmxb/print'),
          method: 'post',
          data: this.$http.adornData(id, false)
        }).then(({data}) => {
        })*/
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/kcgl/zbkcmxb/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
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
      }
    }
  }
</script>
