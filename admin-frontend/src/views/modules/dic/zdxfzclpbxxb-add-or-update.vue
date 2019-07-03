<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
<!--    <el-form-item label="消防站类别编码" prop="xfzlbid">
      <el-input v-model="dataForm.xfzlbid" placeholder="消防站类别编码"></el-input>
    </el-form-item>-->
<!--    <el-form-item label="消防站类别" prop="xfzlbmc">
      <el-input v-model="dataForm.xfzlbmc" placeholder="消防站类别"></el-input>
    </el-form-item>-->
      <el-form-item label="类别名称" prop="xfzlbmc">
        <el-select v-model="dataForm.xfzlbmc" label="栏目" placeholder="请选择" @change="selectXfzlb">
          <el-option v-for="item in xfzlbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
    <el-form-item label="最大配备" prop="zbslmax">
<!--      <el-input v-model="dataForm.zbslmax" placeholder="最大配备"></el-input>-->
      <el-input-number v-model="dataForm.zbslmax"  :min="0"  label="最大配备"></el-input-number>
    </el-form-item>
    <el-form-item label="最小配备" prop="zbslmin">
<!--      <el-input v-model="dataForm.zbslmin" placeholder="最小配备"></el-input>-->
      <el-input-number v-model="dataForm.zbslmin"  :min="0"  label="最小配备"></el-input-number>
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
          xfzlbid: '',
          xfzlbmc: '',
          zbslmax: '',
          zbslmin: ''
        },
        xfzlbList: [],
        dataRule: {
          xfzlbid: [
            { required: true, message: '消防站类别编码不能为空', trigger: 'blur' }
          ],
          xfzlbmc: [
            { required: true, message: '消防站类别不能为空', trigger: 'blur' }
          ],
          zbslmax: [
            { required: true, message: '最大配备不能为空', trigger: 'blur' }
          ],
          zbslmin: [
            { required: true, message: '最小配备不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$http({
          url: this.$http.adornUrl('/dic/zdxfzlbxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.xfzlbList = data.xfzlbList
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/dic/zdxfzclpbxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.xfzlbid = data.zdxfzclpbxxb.xfzlbid
                  this.dataForm.xfzlbmc = data.zdxfzclpbxxb.xfzlbmc
                  this.dataForm.zbslmax = data.zdxfzclpbxxb.zbslmax
                  this.dataForm.zbslmin = data.zdxfzclpbxxb.zbslmin
                }
              })
            }
          })
        })
      },

      //下拉框选中事件 消防站类别
      selectXfzlb(vId){//这个vId也就是value值
        let obj = {};
        obj = this.xfzlbList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });

        this.dataForm.xfzlbid = obj.value
        this.dataForm.xfzlbmc = obj.label

      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdxfzclpbxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'xfzlbid': this.dataForm.xfzlbid,
                'xfzlbmc': this.dataForm.xfzlbmc,
                'zbslmax': this.dataForm.zbslmax,
                'zbslmin': this.dataForm.zbslmin
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
