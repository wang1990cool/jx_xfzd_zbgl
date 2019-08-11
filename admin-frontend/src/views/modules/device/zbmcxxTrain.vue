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

    <el-form :inline="true" :model="dataForm" style="margin-bottom: -10px;margin-top: -20px">
      <!--<el-form-item>-->
        <!--<el-input size="small"  v-model="dataForm.zblbmc" placeholder="装备类别" clearable></el-input>-->
      <!--</el-form-item>-->

      <el-form-item>
        <el-input size="small"  v-model="dataForm.zbmc" placeholder="装备名称" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button size="small"  @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain>查询</el-button>
        <el-button size="small"  @click="getDataAllList()" icon="el-icon-zoom-out" type="primary" plain >取消</el-button>
        <el-button size="small" type="primary" @click="addHandle()">设为训练器材</el-button>
      </el-form-item>
    </el-form>
    <el-form >

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
            // { field: 'zblbmc', title: '类别名称', width: 120},
            { field: 'zbbm', title: '装备编码' },
            { field: 'zbmc', title: '装备名称' },
            { field: 'ssbmmc', title: '所属部门名称'},
            { field: 'ztxs', title: '状态'}
          ],
          tableData: [],
          visible: false,
          zbmcxxGridVisible:false,
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
            url: this.$http.adornUrl('/kcgl/zbkcmxb/list'),
            method: 'get',
            params: this.$http.adornParams({
              'page': this.tablePage.currentPage,
              'limit': this.tablePage.pageSize,
              'zblbmc': this.dataForm.zblbmc,
              'zbmc': this.dataForm.zbmc,
              'ztxs': '在库'
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
          var selectRecords = this.$refs.zbxxTable.getSelectRecords()

          var ids = selectRecords.map(item => {
            return item.id
          })

          this.$confirm(`确定设为训练器材?`, '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$http({
              url: this.$http.adornUrl('/device/train/train'),
              method: 'post',
              data: this.$http.adornData(ids, false)
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$emit('refreshDataList')
                    this.handleCurrentChange(1)
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }).catch(() => {})
        }

      }
    }
</script>
<style scoped>

</style>
