<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.zbmc" placeholder="装备名称" clearable></el-input>
      </el-form-item>
      <!--<el-form-item>-->
        <!--<el-input v-model="dataForm.xssbmmc" placeholder="现所属部门" clearable></el-input>-->
      <!--</el-form-item>-->
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-form >
      <el-form-item style="margin-bottom: 5px">
        <el-button size="small" type="primary" @click="addHandle()">新增报废器材</el-button>
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
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">详情</el-button>
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
    <zbmcxx-grid v-if="zbmcxxGridVisible" ref="zbmcxxGrid" @refreshDataList="getDataList"></zbmcxx-grid>
  </div>
</template>

<script>
  import zbmcxxGrid from './zbmcxx'

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
        zbmcxxGridVisible: false,
        addOrUpdateVisible: false
      }
    },
    components: {
      zbmcxxGrid,
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
          url: this.$http.adornUrl('/device/scrap/list'),
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
      addHandle () {
        this.zbmcxxGridVisible = true
        this.$nextTick(() => {
          this.$refs.zbmcxxGrid.init()
        })
      },
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
    }
  }
</script>
