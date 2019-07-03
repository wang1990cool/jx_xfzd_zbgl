<template>
  <el-dialog
    width="60%"
      :title="title"
      :close-on-click-modal="false"
      :visible.sync="visible">
      <el-form :model="dataForm" ref="dataForm" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="借用单名称" prop="sqmc">
              <el-input v-model="dataForm.sqmc" placeholder="借用单名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
          <el-form-item label="申请部门" prop="sqbmmc">
            <el-input v-model="dataForm.sqbmmc"  placeholder="申请部门"></el-input>
          </el-form-item>
          </el-col>
        </el-row>

      <el-form-item label="借用单编码" prop="id" v-if="false">
        <el-input v-model="dataForm.id"  placeholder="借用单编码"></el-input>
      </el-form-item>

      <el-form-item label="借用单编码" prop="jrsqid" v-if="false">
        <el-input v-model="dataForm.jrsqid"  placeholder="借用单编码"></el-input>
      </el-form-item>

    <el-form-item label="借用单编码" prop="jrsqid" v-if="false">
      <el-input v-model="dataForm.jrsqid"  placeholder="借用单编码"></el-input>
    </el-form-item>


    <el-form-item label="申请部门编号" prop="sqbmid" v-if="false">
      <el-input v-model="dataForm.sqbmid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="借出部门编号" prop="jcbmid" v-if="false">
      <el-input v-model="dataForm.jcbmid" placeholder="借出部门编号"></el-input>
    </el-form-item>
    <el-form-item label="借出部门名称" prop="jcbmmc" v-if="false">
      <el-input v-model="dataForm.jcbmmc" placeholder="借出部门名称"></el-input>
    </el-form-item>

    <el-form-item label="状态码" prop="ztm" v-if="false">
      <el-input v-model="dataForm.ztm" placeholder="状态码"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="zt" v-if="false">
      <el-input v-model="dataForm.zt" placeholder="状态"></el-input>
    </el-form-item>

    <el-form-item label="备注" prop="bz" v-if="false">
      <el-input v-model="dataForm.bz" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="创建人编号" prop="createUserId" v-if="false">
      <el-input v-model="dataForm.createUserId" placeholder="创建人编号"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="createTime" v-if="false">
      <el-input v-model="dataForm.createTime" placeholder="创建人"></el-input>
    </el-form-item>
    </el-form>

      <vxe-table
        ref="xTable"
        border
        resizable
        highlight-current-row
        show-overflow
        :data="tableData">
        <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
        <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
        <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
        <vxe-table-column field="zblbmc" title="装备类别" width="160"></vxe-table-column>
        <vxe-table-column field="zbid" title="装备编号" v-if="false" ></vxe-table-column>
        <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
        <vxe-table-column field="zbsl" title="装备数量" width="120"></vxe-table-column>
      </vxe-table>
    </div>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="audit('agree')">同意</el-button>
      <el-button type="danger"  @click="audit('disagree')">不同意</el-button>
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>

</template>

<script>
  export default {
    data () {
      return {
        title: '审核',
        depShowType: {
          value: 'id',
          label: 'name',
          children: 'nodes'
        },
        selectDeptId: [],
        // 借用明细表格数据
        tableData: [],

        visible: false,
        dataForm: {
          id: '',
          jrsqid: '',
          sqmc: '',
          sqbmid: '',
          sqbmmc: '',
          jcbmid: '',
          jcbmmc: '',
          ztm: '',
          zt: '',
          ghrq: '',
          bz: '',
          createUserId: '',
          createTime: ''
        }
      }
    },
    components: {
    },
    methods: {
      init (jrsqid, id) {
        this.visible = true
        this.getData(jrsqid, id);
      },

      getData (jrsqid, id) {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/borrow/jrsqmx/list'),
          method: 'get',
          params: {
            'jrsqid': jrsqid
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            // this.$refs.xTable.insert(data.page)
            this.tableData = data.page
          } else {
            this.tableData = []
          }
          this.dataListLoading = false
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            this.selectDeptId = []
            if (id) {
              this.$http({
                url: this.$http.adornUrl(`/borrow/jrsq/info/${id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                    this.dataForm.id = data.jrsqb.id
                    this.dataForm.jrsqid = data.jrsqb.jrsqid
                    this.dataForm.sqmc = data.jrsqb.sqmc
                    this.dataForm.sqbmid = data.jrsqb.sqbmid
                    this.dataForm.sqbmmc = data.jrsqb.sqbmmc
                    this.dataForm.jcbmid = data.jrsqb.jcbmid
                    this.dataForm.jcbmmc = data.jrsqb.jcbmmc
                    this.dataForm.ztm = data.jrsqb.ztm
                    this.dataForm.zt = data.jrsqb.zt
                    this.dataForm.ghrq = data.jrsqb.ghrq
                    this.dataForm.bz = data.jrsqb.bz
                    this.dataForm.createUserId = data.jrsqb.createUserId
                    this.dataForm.createTime = data.jrsqb.createTime

                }
              })
            }
          })
        })
      },

      audit (cz) {
        this.$http({
          url: this.$http.adornUrl('/borrow/shJrsq/audit'),
          method: 'post',
          data: this.$http.adornData({id: this.dataForm.id, cz: cz}, false)
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
      }
    }
  }
</script>
