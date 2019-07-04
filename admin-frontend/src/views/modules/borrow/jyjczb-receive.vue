<template>
  <el-dialog
    :title="'装备领用'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    width="55%"
    :visible.sync="visible">
  <div class="mod-config">

    <el-form >
    <el-form-item style="margin-bottom: 5px">
      <el-tag type="warning" size="small" >申请装备</el-tag>
    </el-form-item>
    </el-form>

    <vxe-grid
      ref="xTable"
      border
      resizable
      highlight-current-row
      show-overflow
      :loading="loading"
      :edit-config="{key:'zbid', trigger: 'click', mode: 'cell'}"
      :data.sync="tableData">
      <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
      <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbmc" title="装备类别" width="120"></vxe-table-column>
      <vxe-table-column field="zbid" title="装备编号" v-if="false" ></vxe-table-column>
      <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
      <vxe-table-column field="kcsl" title="库存" width="70"></vxe-table-column>
      <vxe-table-column field="sqsl" title="申请数量" width="80" ></vxe-table-column>
      <vxe-table-column field="sjsl" title="实际数量" width="95" :edit-render="{name: 'input'}" ></vxe-table-column>
    </vxe-grid>
  </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
       <el-button @click="visible = false">取消</el-button>
    </span>
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
            pageSize: 10
          },
          tableColumn: [
            { type: 'selection', width: 50 },
            { field: 'zblbmc', title: '类别名称', width: 110},
            // { field: 'zbid', title: '装备编码' },
            { field: 'zbmc', title: '装备名称'},
            { field: 'kcsl', title: '库存', width: 70 },
            { field: 'sqsl', title: '申请数量', width: 80  },
            { field: 'sjsl', title: '实际数量', width: 80  }
          ],
          tableData: [],
          visible: false,
          dataForm: {
            zblbmc: '',
            zbmc: ''
          },
          dataList: [],
          pageIndex: 1,
          pageSize: 10,
          totalPage: 0,
          dataListLoading: false,
          dataListSelections: []
        }
      },
      methods: {
        init (jrsqid) {
          this.visible = true
          this.getDataList(jrsqid);
        },
        // 获取数据列表
        getDataList(jrsqid) {
          this.loading = true
          this.$http({
            url: this.$http.adornUrl('/borrow/jyjczb/zblist'),
            method: 'get',
            params: {
              'jrsqid': jrsqid
            }
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.tableData = data.data
            } else {
              this.tableData = []
            }
            this.loading = false
          })
        },

        getDataAllList () {
          this.dataForm.zblbmc = ''
          this.dataForm.zbmc = ''
          this.getDataList()
        },

        dataFormSubmit () {
          var datas =  this.$refs.xTable.getRecords();
          datas = this.convertDatas(datas);
          this.$http({
            url: this.$http.adornUrl(`/borrow/jyjczb/reveice`),
            method: 'post',
            data: datas
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        },

        convertDatas (datas) {
          if (datas.length > 0) {
            var newDatas = []
            for (var i = 0; i < datas.length; i++) {
              var data = {}
              data['id'] = datas[i].id
              data['jrsqid'] = datas[i].jrsqid
              data['zblbid'] = datas[i].zblbid
              data['zblbmc'] = datas[i].zblbmc
              data['zbid'] = datas[i].zbid
              data['zbmc'] = datas[i].zbmc
              data['sqsl'] = datas[i].sqsl
              data['sjsl'] = datas[i].sjsl
              data['sqbmid'] = datas[i].sqbmid
              data['sqbmmc'] = datas[i].sqbmmc
              newDatas.push(data)
            }
          }
          return newDatas
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
