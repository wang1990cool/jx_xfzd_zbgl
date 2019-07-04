<template>
  <el-dialog
    :title="'装备详情'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    width="60%"
    :visible.sync="visible">
  <div class="mod-config">

    <vxe-grid
      ref="zbTable"
      border
      resizable
      size="mini"
      highlight-current-row
      show-overflow
      :loading="loading"
      :edit-config="{key:'zbbm', trigger: 'click', mode: 'cell'}"
      :data.sync="tableDataJcmx">
      <vxe-table-column type="selection" width="60"></vxe-table-column>
      <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
      <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
      <!--<vxe-table-column field="zblbmc" title="装备类别"></vxe-table-column>-->
      <vxe-table-column field="zbbm" title="装备编码"></vxe-table-column>
      <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
      <vxe-table-column field="zt" title="状态" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.zt === '借用'" size="small" type="success">借用</el-tag>
          <el-tag v-if="scope.row.zt === '已归还'" size="small" type="primary">已归还</el-tag>
        </template>
      </vxe-table-column>
    </vxe-grid>

  </div>
    <span slot="footer" class="dialog-footer">
       <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
    export default {
      data () {
        return {
          jrsqid: '',
          dataForm: {
            zblbmc: '',
            zbmc: ''
          },
          loading: false,
          tableDataJcmx: [],
          visible: false
        }
      },
      methods: {
        init (jrsqid) {
          this.visible = true
          this.getDataListJcmx(jrsqid)
          this.jrsqid = jrsqid
        },
        // 获取数据列表
        getDataListJcmx (jrsqid) {
          this.loading = true
          this.$http({
            url: this.$http.adornUrl('/borrow/jczbmx/returnList'),
            method: 'get',
            params: {
              'jrsqid': jrsqid
            }
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.tableDataJcmx = data.data
            } else {
              this.tableDataJcmx = []
            }
            this.loading = false
          })
        }
      }
    }
</script>
<style scoped>

</style>
