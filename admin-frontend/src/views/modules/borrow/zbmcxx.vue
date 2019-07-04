<template>
  <el-dialog
    :title="'装备信息'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    width="55%"
    :visible.sync="visible">
  <div class="mod-config">

    <el-form :inline="true" :model="dataForm" >
      <el-form-item>
        <el-input v-model="dataForm.zblbmc" placeholder="装备类别" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-input v-model="dataForm.zbmc" placeholder="装备名称" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain>查询</el-button>
        <el-button @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
      </el-form-item>
    </el-form>
    <el-form >
    <el-form-item style="margin-bottom: 5px">
      <el-button size="small" type="primary" @click="addHandle()">选择</el-button>
    </el-form-item>
    </el-form>

    <vxe-grid
      ref="zbxxTable"
      border
      :loading="loading"
      :pager-config="tablePage"
      :columns="tableColumn"
      :data.sync="tableData"
      @current-page-change="handleCurrentChange"
      @page-size-change="handleSizeChange"></vxe-grid>
  </div>
  </el-dialog>
</template>

<script>
    export default {
      data () {
        return {
          dataForm: {
            zblbmc: '',
            zbmc: ''
          },
          loading: false,
          tablePage: {
            total: 0,
            currentPage: 1,
            pageSize: 8
          },
          tableColumn: [
            { type: 'selection', width: 50 },
            { field: 'zblbmc', title: '类别名称', width: 120},
            { field: 'zbid', title: '装备编码' },
            { field: 'zbmc', title: '装备名称' }
          ],
          tableData: [],
          visible: false,
          dataForm: {
            zblbmc: '',
            zbmc: ''
          },
          dataList: [],
          pageIndex: 1,
          pageSize: 8,
          totalPage: 0,
          dataListLoading: false,
          dataListSelections: []
        }
      },
      methods: {
        init () {
          this.visible = true
          this.getDataList();
        },
        // 获取数据列表
        getDataList() {
          // alert(this.totalPage.currentPage)
          this.loading = true
          this.$http({
            url: this.$http.adornUrl('/dic/zdzbmcxxb/list'),
            method: 'get',
            params: this.$http.adornParams({
              'page': this.tablePage.currentPage,
              'limit': this.tablePage.pageSize,
              'zblbmc': this.dataForm.zblbmc,
              'zbmc': this.dataForm.zbmc
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.tableData = data.page.list
              this.tablePage.total = data.page.totalCount
            } else {
              this.tableData = []
              this.tablePage.total = 0
            }
            this.loading = false
          })
        },

        getDataAllList () {
          this.dataForm.zblbmc = '';
          this.dataForm.zbmc = '';
          this.getDataList();
        },

        searchEvent () {
          this.tablePage.currentPage = 1
          this.getDataList()
        },
        handleSizeChange (pageSize) {
          this.tablePage.pageSize = pageSize
          this.searchEvent()
        },
        handleCurrentChange (currentPage) {
          this.tablePage.currentPage = currentPage
          this.getDataList()
        },

        addHandle () {
          this.$emit('insertData', this.$refs.zbxxTable.getSelectRecords())
        }

      }
    }
</script>
<style scoped>

</style>
