<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">

    <el-form-item label="类别编号" prop="lbid" hidden="true">
      <el-input v-model="dataForm.lbid" placeholder="类别编号"></el-input>
    </el-form-item>

    <el-form-item label="类别名称" prop="lbmc">
      <el-input v-model="dataForm.lbmc" placeholder="类别名称"></el-input>
    </el-form-item>

    <el-form-item label="" prop="createUserId" hidden="true">
      <el-input v-model="dataForm.createUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime" hidden="true">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>

    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          lbid: '',
          lbmc: '',
          createUserId: '',
          createTime: ''
        },
        dataRule: {
/*          lbid: [
            { required: true, message: '类别编号不能为空', trigger: 'blur' }
          ],*/
          lbmc: [
            { required: true, message: '类别名称不能为空', trigger: 'blur' }
/*          ],
          createUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }*/
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdzblbxxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.lbid = data.zdzblbxxb.lbid
                this.dataForm.lbmc = data.zdzblbxxb.lbmc
                this.dataForm.createUserId = data.zdzblbxxb.createUserId
                this.dataForm.createTime = data.zdzblbxxb.createTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdzblbxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'lbid': this.dataForm.lbid,
                'lbmc': this.dataForm.lbmc,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime
              })
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
        })
      }
    }
  }
</script>
