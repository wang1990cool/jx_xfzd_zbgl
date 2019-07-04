<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类别编码" prop="zblbid">
      <el-input v-model="dataForm.zblbid" placeholder="类别编码"></el-input>
    </el-form-item>
    <el-form-item label="类别名称" prop="zblbmc">
      <el-input v-model="dataForm.zblbmc" placeholder="类别名称"></el-input>
    </el-form-item>
    <el-form-item label="装备编码" prop="zbbm">
      <el-input v-model="dataForm.zbbm" placeholder="装备编码"></el-input>
    </el-form-item>
    <el-form-item label="装备名称" prop="zbmc">
      <el-input v-model="dataForm.zbmc" placeholder="装备名称"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="zt">
      <el-input v-model="dataForm.zt" placeholder="状态"></el-input>
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
          zblbid: '',
          zblbmc: '',
          zbbm: '',
          zbmc: '',
          zt: ''
        },
        dataRule: {
          zblbid: [
            { required: true, message: '类别编码不能为空', trigger: 'blur' }
          ],
          zblbmc: [
            { required: true, message: '类别名称不能为空', trigger: 'blur' }
          ],
          zbbm: [
            { required: true, message: '装备编码不能为空', trigger: 'blur' }
          ],
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
          ],
          zt: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/borrow/jyjczbmxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.zblbid = data.jyjczbmxb.zblbid
                this.dataForm.zblbmc = data.jyjczbmxb.zblbmc
                this.dataForm.zbbm = data.jyjczbmxb.zbbm
                this.dataForm.zbmc = data.jyjczbmxb.zbmc
                this.dataForm.zt = data.jyjczbmxb.zt
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
              url: this.$http.adornUrl(`/borrow/jyjczbmxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'zblbid': this.dataForm.zblbid,
                'zblbmc': this.dataForm.zblbmc,
                'zbbm': this.dataForm.zbbm,
                'zbmc': this.dataForm.zbmc,
                'zt': this.dataForm.zt
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
