<template>
  <el-dialog
    :title="!dataForm.id ? '详情' : '装备详情'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="装备编码" prop="zbbm" >
      <el-input v-model="dataForm.zbbm" placeholder="装备编码"></el-input>
    </el-form-item>
    <el-form-item label="装备名称编号" prop="zbid"  hidden="true">
      <el-input v-model="dataForm.zbid" placeholder="装备名称编号"></el-input>
    </el-form-item>
    <el-form-item label="装备名称" prop="zbmc">
      <el-input v-model="dataForm.zbmc" placeholder="装备名称"></el-input>
    </el-form-item>
    <el-form-item label="所属部门id" prop="ssbmid"  hidden="true">
      <el-input v-model="dataForm.ssbmid" placeholder="所属部门id"></el-input>
    </el-form-item>
    <el-form-item label="原属部门" prop="ssbmmc">
      <el-input v-model="dataForm.ssbmmc" placeholder="所属部门名称"></el-input>
    </el-form-item>
    <el-form-item label="状态码" prop="ztm"  hidden="true">
      <el-input v-model="dataForm.ztm" placeholder="状态码"></el-input>
    </el-form-item>
      <el-form-item label="状态" prop="ztxs"  hidden="true">
        <el-input v-model="dataForm.ztxs" placeholder="状态"></el-input>
      </el-form-item>
    <el-form-item label="状态位置" prop="zt"  hidden="true">
      <el-input v-model="dataForm.zt" placeholder="状态位置"></el-input>
    </el-form-item>
    <el-form-item label="现所属部门编码" prop="xssbmid" hidden="true">
      <el-input v-model="dataForm.xssbmid" placeholder="现所属部门编码"></el-input>
    </el-form-item>
    <el-form-item label="现属部门" prop="xssbmmc">
      <el-input v-model="dataForm.xssbmmc" placeholder="现所属部门名称"></el-input>
    </el-form-item>
    <el-form-item label="报废日期" prop="bfrq">
      <el-input v-model="dataForm.bfrq" placeholder="报废日期"></el-input>
    </el-form-item>
    <el-form-item label="上次保养日期" prop="byrq">
      <el-input v-model="dataForm.byrq" placeholder="上次保养日期"></el-input>
    </el-form-item>
    <el-form-item label="生产日期" prop="scrq">
      <el-input v-model="dataForm.scrq" placeholder="生产日期"></el-input>
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
<!--
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
-->
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
          zbbm: '',
          zbid: '',
          zbmc: '',
          ssbmid: '',
          ssbmmc: '',
          ztm: '',
          zt: '',
          xssbmid: '',
          xssbmmc: '',
          bfrq: '',
          byrq: '',
          scrq: '',
          createUserId: '',
          createTime: ''
        },
        dataRule: {
          /*zbbm: [
            { required: true, message: '装备编码不能为空', trigger: 'blur' }
          ],
          zbid: [
            { required: true, message: '装备名称编号不能为空', trigger: 'blur' }
          ],
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
          ],
          ssbmid: [
            { required: true, message: '所属部门id不能为空', trigger: 'blur' }
          ],
          ssbmmc: [
            { required: true, message: '所属部门名称不能为空', trigger: 'blur' }
          ],
          ztm: [
            { required: true, message: '状态吗不能为空', trigger: 'blur' }
          ],
          zt: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          xssbmid: [
            { required: true, message: '现所属部门编码不能为空', trigger: 'blur' }
          ],
          xssbmmc: [
            { required: true, message: '现所属部门名称不能为空', trigger: 'blur' }
          ],
          bfrq: [
            { required: true, message: '报废日期不能为空', trigger: 'blur' }
          ],
          byrq: [
            { required: true, message: '上次保养日期不能为空', trigger: 'blur' }
          ],
          scrq: [
            { required: true, message: '生产日期不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]*/
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
              url: this.$http.adornUrl(`/kcgl/zbkcmxb/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.zbbm = data.zbkcmxb.zbbm
                this.dataForm.zbid = data.zbkcmxb.zbid
                this.dataForm.zbmc = data.zbkcmxb.zbmc
                this.dataForm.ssbmid = data.zbkcmxb.ssbmid
                this.dataForm.ssbmmc = data.zbkcmxb.ssbmmc
                this.dataForm.ztm = data.zbkcmxb.ztm
                this.dataForm.zt = data.zbkcmxb.zt
                this.dataForm.xssbmid = data.zbkcmxb.xssbmid
                this.dataForm.xssbmmc = data.zbkcmxb.xssbmmc
                this.dataForm.bfrq = data.zbkcmxb.bfrq
                this.dataForm.byrq = data.zbkcmxb.byrq
                this.dataForm.scrq = data.zbkcmxb.scrq
                this.dataForm.createUserId = data.zbkcmxb.createUserId
                this.dataForm.createTime = data.zbkcmxb.createTime
              }
            })
          }
        })
      }
      // 表单提交
/*      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/kcgl/zbkcmxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'zbbm': this.dataForm.zbbm,
                'zbid': this.dataForm.zbid,
                'zbmc': this.dataForm.zbmc,
                'ssbmid': this.dataForm.ssbmid,
                'ssbmmc': this.dataForm.ssbmmc,
                'ztm': this.dataForm.ztm,
                'zt': this.dataForm.zt,
                'xssbmid': this.dataForm.xssbmid,
                'xssbmmc': this.dataForm.xssbmmc,
                'bfrq': this.dataForm.bfrq,
                'byrq': this.dataForm.byrq,
                'scrq': this.dataForm.scrq,
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
      }*/
    }
  }
</script>
