<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.zblbmc" placeholder="类别名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.zbmc" placeholder="装备名称" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('dic:zdzbmcxxb:save')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button v-if="isAuth('dic:zdzbmcxxb:delete')" type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
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
<!--      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="">
      </el-table-column>-->
<!--      <el-table-column
        prop="zblbid"
        header-align="center"
        align="center"
        label="类别编码">
      </el-table-column>-->
      <el-table-column
        prop="zblbmc"
        header-align="center"
        align="center"
        label="类别名称">
      </el-table-column>
<!--      <el-table-column
        prop="zbid"
        header-align="center"
        align="center"
        label="装备编码">
      </el-table-column>-->
      <el-table-column
        prop="zbmc"
        header-align="center"
        align="center"
        label="装备名称">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        width="80"
        label="装备照片">
        <template slot-scope="scope">
          <el-button   type="text" size="small" @click="zbzpHandle(scope.row.id)">装备图片 </el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="bfzq"
        header-align="center"
        align="center"
        label="报废周期 -1：不保养，单位为年">
      </el-table-column>
      <el-table-column
        prop="byzq"
        header-align="center"
        align="center"
        label="保养周期 -1：不保养，单位为月">
      </el-table-column>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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
    <zbzp v-if="zbzpVisible" ref="zbzp" @refreshDataList="getDataList"></zbzp>
  </div>
</template>

<script>
  import AddOrUpdate from './zdzbmcxxb-add-or-update'
  import Zbzp from './zdzbmcxxb-zszp.vue'
  export default {
    data () {
      return {
        dataForm: {
          zblbmc: '',
          zbmc: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        zbzpVisible: false
      }
    },
    components: {
      AddOrUpdate,
      Zbzp
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/dic/zdzbmcxxb/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'zblbmc': this.dataForm.zblbmc,
            'zbmc': this.dataForm.zbmc
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
      //查看照片
      zbzpHandle (id) {
        this.zbzpVisible = true
        this.$nextTick(() => {
          this.$refs.zbzp.init(id)
        })
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
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
            url: this.$http.adornUrl('/dic/zdzbmcxxb/delete'),
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
