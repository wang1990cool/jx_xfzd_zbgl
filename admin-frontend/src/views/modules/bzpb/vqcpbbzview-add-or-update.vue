<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="zbmc">
      <el-input v-model="dataForm.zbmc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="zbslmin">
      <el-input v-model="dataForm.zbslmin" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="zbslmax">
      <el-input v-model="dataForm.zbslmax" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="bfzbsl">
      <el-input v-model="dataForm.bfzbsl" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="bmmc">
      <el-input v-model="dataForm.bmmc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="zbsl">
      <el-input v-model="dataForm.zbsl" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="id">
      <el-input v-model="dataForm.id" placeholder=""></el-input>
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
          zblbmc: 0,
          zbmc: '',
          zbslmin: '',
          zbslmax: '',
          bfzbsl: '',
          bmmc: '',
          zbsl: '',
          id: ''
        },
        dataRule: {
          zbmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zbslmin: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zbslmax: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          bfzbsl: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          bmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zbsl: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          id: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.zblbmc = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.zblbmc) {
            this.$http({
              url: this.$http.adornUrl(`/bzpb/vqcpbbzview/info/${this.dataForm.zblbmc}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.zbmc = data.vqcpbbzview.zbmc
                this.dataForm.zbslmin = data.vqcpbbzview.zbslmin
                this.dataForm.zbslmax = data.vqcpbbzview.zbslmax
                this.dataForm.bfzbsl = data.vqcpbbzview.bfzbsl
                this.dataForm.bmmc = data.vqcpbbzview.bmmc
                this.dataForm.zbsl = data.vqcpbbzview.zbsl
                this.dataForm.id = data.vqcpbbzview.id
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
              url: this.$http.adornUrl(`/bzpb/vqcpbbzview/${!this.dataForm.zblbmc ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'zblbmc': this.dataForm.zblbmc || undefined,
                'zbmc': this.dataForm.zbmc,
                'zbslmin': this.dataForm.zbslmin,
                'zbslmax': this.dataForm.zbslmax,
                'bfzbsl': this.dataForm.bfzbsl,
                'bmmc': this.dataForm.bmmc,
                'zbsl': this.dataForm.zbsl,
                'id': this.dataForm.id
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
