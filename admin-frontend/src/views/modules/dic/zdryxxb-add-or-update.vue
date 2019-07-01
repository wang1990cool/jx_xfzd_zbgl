<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="人员编号" prop="rybh">
      <el-input v-model="dataForm.rybh" placeholder="人员编号"></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="ryxm">
      <el-input v-model="dataForm.ryxm" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item label="所属部门" prop="ssbmid" hidden="true">
      <el-input v-model="dataForm.ssbmid" placeholder="所属部门"></el-input>
    </el-form-item>
      <el-form-item label="所属部门" prop="ssbmmc">
        <el-select v-model="dataForm.ssbmmc" label="栏目" placeholder="请选择" @change="selectBmmc">
          <el-option v-for="item in bmmcList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>

<!--    <el-form-item label="" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>-->
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
          rybh: '',
          ryxm: '',
          ssbmid: '',
          ssbmmc: '',
          createUserId: '',
          createTime: ''
        },
        bmmcList: [],
        dataRule: {
          rybh: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ryxm: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ssbmid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ssbmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
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
                url: this.$http.adornUrl(`/dic/zdryxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.rybh = data.zdryxxb.rybh
                  this.dataForm.ryxm = data.zdryxxb.ryxm
                  this.dataForm.ssbmid = data.zdryxxb.ssbmid
                  this.dataForm.ssbmmc = data.zdryxxb.ssbmmc
/*                  this.dataForm.createUserId = data.zdryxxb.createUserId
                  this.dataForm.createTime = data.zdryxxb.createTime*/
                }
              })
            }
          })
        })
      },
      // 获取装备名称信息
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
              url: this.$http.adornUrl(`/dic/zdryxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'rybh': this.dataForm.rybh,
                'ryxm': this.dataForm.ryxm,
                'ssbmid': this.dataForm.ssbmid,
                'ssbmmc': this.dataForm.ssbmmc,
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
