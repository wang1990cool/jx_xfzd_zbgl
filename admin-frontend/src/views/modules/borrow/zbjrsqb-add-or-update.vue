<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="sqmc">
      <el-input v-model="dataForm.sqmc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="sqbmid">
      <el-input v-model="dataForm.sqbmid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="sqbmmc">
      <el-input v-model="dataForm.sqbmmc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="jcbmid">
      <el-input v-model="dataForm.jcbmid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="jcbmmc">
      <el-input v-model="dataForm.jcbmmc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ztm">
      <el-input v-model="dataForm.ztm" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="zt">
      <el-input v-model="dataForm.zt" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ghrq">
      <el-input v-model="dataForm.ghrq" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="bz">
      <el-input v-model="dataForm.bz" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
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
        },
        dataRule: {
          sqmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sqbmid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sqbmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          jcbmid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          jcbmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ztm: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zt: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ghrq: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          bz: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/borrow/zbjrsqb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.sqmc = data.zbjrsqb.sqmc
                this.dataForm.sqbmid = data.zbjrsqb.sqbmid
                this.dataForm.sqbmmc = data.zbjrsqb.sqbmmc
                this.dataForm.jcbmid = data.zbjrsqb.jcbmid
                this.dataForm.jcbmmc = data.zbjrsqb.jcbmmc
                this.dataForm.ztm = data.zbjrsqb.ztm
                this.dataForm.zt = data.zbjrsqb.zt
                this.dataForm.ghrq = data.zbjrsqb.ghrq
                this.dataForm.bz = data.zbjrsqb.bz
                this.dataForm.createUserId = data.zbjrsqb.createUserId
                this.dataForm.createTime = data.zbjrsqb.createTime
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
              url: this.$http.adornUrl(`/borrow/zbjrsqb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'sqmc': this.dataForm.sqmc,
                'sqbmid': this.dataForm.sqbmid,
                'sqbmmc': this.dataForm.sqbmmc,
                'jcbmid': this.dataForm.jcbmid,
                'jcbmmc': this.dataForm.jcbmmc,
                'ztm': this.dataForm.ztm,
                'zt': this.dataForm.zt,
                'ghrq': this.dataForm.ghrq,
                'bz': this.dataForm.bz,
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
