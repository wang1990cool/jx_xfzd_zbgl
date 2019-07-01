<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="所属部门编号" prop="ssbmid" hidden="true">
      <el-input v-model="dataForm.ssbmid" placeholder="所属部门编号"></el-input>
    </el-form-item>
<!--    <el-form-item label="所属部门" prop="ssbmmc">
      <el-input v-model="dataForm.ssbmmc" placeholder="所属部门名称"></el-input>
    </el-form-item>-->
    <el-form-item label="所属部门" prop="ssbmmc">
      <el-select v-model="dataForm.ssbmmc" label="栏目" placeholder="请选择" @change="selectBmmc">
        <el-option v-for="item in bmmcList" :key="item.label" :label="item.label" :value="item.label" >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="上次保养日期" prop="byrq" hidden="true">
      <el-input v-model="dataForm.byrq" placeholder="上次保养日期"></el-input>
    </el-form-item>
    <el-form-item label="车牌号" prop="cph">
      <el-input v-model="dataForm.cph" placeholder="车牌号"></el-input>
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
          ssbmid: '',
          ssbmmc: '',
          byrq: '',
          cph: ''
        },
        bmmcList: [],
        dataRule: {
          ssbmid: [
            { required: true, message: '所属部门编号不能为空', trigger: 'blur' }
          ],
          ssbmmc: [
            { required: true, message: '所属部门名称不能为空', trigger: 'blur' }
          ],

          cph: [
            { required: true, message: '车牌号不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$http({
          url: this.$http.adornUrl('/dic/zdbmmcxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.bmmcList = data.bmmcList
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/dic/zdclxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.ssbmid = data.zdclxxb.ssbmid
                  this.dataForm.ssbmmc = data.zdclxxb.ssbmmc
                  this.dataForm.byrq = data.zdclxxb.byrq
                  this.dataForm.cph = data.zdclxxb.cph
                }
              })
            }
          })
        })
      },

      //下拉框选中事件  部门名称
      selectBmmc(vId){//这个vId也就是value值
        let obj = {};
        obj = this.bmmcList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });

        this.dataForm.ssbmid = obj.value
        this.dataForm.ssbmmc = obj.label

      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdclxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'ssbmid': this.dataForm.ssbmid,
                'ssbmmc': this.dataForm.ssbmmc,
                'byrq': this.dataForm.byrq,
                'cph': this.dataForm.cph
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
