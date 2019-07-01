<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="申请单ID" prop="jrsqid">
      <el-input v-model="dataForm.jrsqid" placeholder="申请单ID"></el-input>
    </el-form-item>
    <el-form-item label="" prop="zblbid">
      <el-input v-model="dataForm.zblbid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="装备类别" prop="zblbmc">
      <el-input v-model="dataForm.zblbmc" placeholder="装备类别"></el-input>
    </el-form-item>
    <el-form-item label="" prop="zbid">
      <el-input v-model="dataForm.zbid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="装备名称" prop="zbmc">
      <el-input v-model="dataForm.zbmc" placeholder="装备名称"></el-input>
    </el-form-item>
    <el-form-item label="装备数量" prop="zbsl">
      <el-input v-model="dataForm.zbsl" placeholder="装备数量"></el-input>
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
          jrsqid: '',
          zblbid: '',
          zblbmc: '',
          zbid: '',
          zbmc: '',
          zbsl: ''
        },
        dataRule: {
          jrsqid: [
            { required: true, message: '申请单ID不能为空', trigger: 'blur' }
          ],
          zblbid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zblbmc: [
            { required: true, message: '装备类别不能为空', trigger: 'blur' }
          ],
          zbid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
          ],
          zbsl: [
            { required: true, message: '装备数量不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/borrow/zbjrsqmxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.jrsqid = data.zbjrsqmxb.jrsqid
                this.dataForm.zblbid = data.zbjrsqmxb.zblbid
                this.dataForm.zblbmc = data.zbjrsqmxb.zblbmc
                this.dataForm.zbid = data.zbjrsqmxb.zbid
                this.dataForm.zbmc = data.zbjrsqmxb.zbmc
                this.dataForm.zbsl = data.zbjrsqmxb.zbsl
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
              url: this.$http.adornUrl(`/borrow/zbjrsqmxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'jrsqid': this.dataForm.jrsqid,
                'zblbid': this.dataForm.zblbid,
                'zblbmc': this.dataForm.zblbmc,
                'zbid': this.dataForm.zbid,
                'zbmc': this.dataForm.zbmc,
                'zbsl': this.dataForm.zbsl
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
