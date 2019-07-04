<template>
  <el-dialog
    :title="'装备详情'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    width="55"
    :visible.sync="visible">
  <div class="mod-config">

    <el-form :inline="true"  style="margin-top: -30px;margin-bottom: -20px">
      <el-form-item label="扫码归还:" >
        <el-input v-model="dataForm.zbbm" placeholder="装备编号" clearable size="mini" @blur="returnScanHandle()"> </el-input>
      </el-form-item>
    </el-form>

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
      <vxe-table-column field="zbbm" title="装备编码" width="100"></vxe-table-column>
      <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
      <vxe-table-column field="zt" title="状态" width="90">
        <template slot-scope="scope">
        <el-tag v-if="scope.row.zt === '借用'" size="small" type="success">借用</el-tag>
        <el-tag v-if="scope.row.zt === '已归还'" size="small" type="primary">已归还</el-tag>
        </template>
      </vxe-table-column>
      <vxe-table-column title="操作" width="70">
        <template  slot-scope="scope">
          <el-button v-if="scope.row.zt === '借用'" type="text" size="small" @click="returnHandle(scope.row)">归还</el-button>
        </template>
      </vxe-table-column>
    </vxe-grid>

  </div>

  </el-dialog>
</template>

<script>
    export default {
      data () {
        return {
          jrsqid: '',
          dataForm: {
            zbbm: ''
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
        },
        returnHandle (row) {
          this.returnZb(row.jrsqid,row.zbbm)
        },

        returnScanHandle () {
          var zbbm = this.dataForm.zbbm;
          this.returnZb(this.jrsqid, zbbm)
        },

        returnZb (jrsqid, zbbm) {
          this.$http({
            url: this.$http.adornUrl(`/borrow/ghJrsq/return`),
            method: 'post',
            data: {
              jrsqid : jrsqid,
              zbbm : zbbm
            }
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataListJcmx(jrsqid)
                  this.$emit('refreshDataList')
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      }
    }
</script>
<style scoped>

</style>
