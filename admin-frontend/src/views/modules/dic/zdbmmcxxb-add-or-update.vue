<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="上机部门" prop="pid" hidden="true">
      <el-input v-model="dataForm.pid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="部门名称" prop="bmmc">
      <el-input v-model="dataForm.bmmc" placeholder=""></el-input>
    </el-form-item>
<!--    <el-form-item label="消防站类别" prop="xfzlbid" hidden="true">
      <el-input v-model="dataForm.xfzlbid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="消防站类别" prop="xfzlbmc">
      <el-input v-model="dataForm.xfzlbmc" placeholder=""></el-input>
    </el-form-item>-->
      <el-form-item label="" prop="xfzlbid" hidden="true">
        <el-input v-model="dataForm.xfzlbid" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="类别名称" prop="xfzlbmc">
        <el-select v-model="dataForm.xfzlbmc" label="栏目" placeholder="请选择" @change="selectXfzlb">
          <el-option v-for="item in xfzlbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
    <el-form-item label="备注" prop="bz">
      <el-input v-model="dataForm.bz" placeholder=""></el-input>
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
          pid: '',
          bmmc: '',
          xfzlbid: '',
          xfzlbmc: '',
          bz: ''
        },
        xfzlbList: [],
        dataRule: {
/*          pid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],*/
          bmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          xfzlbid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          xfzlbmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
/*          ],
          bz: [
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
          url: this.$http.adornUrl('/dic/zdxfzlbxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.xfzlbList = data.xfzlbList
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/dic/zdbmmcxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.pid = data.zdbmmcxxb.pid
                  this.dataForm.bmmc = data.zdbmmcxxb.bmmc
                  this.dataForm.xfzlbid = data.zdbmmcxxb.xfzlbid
                  this.dataForm.xfzlbmc = data.zdbmmcxxb.xfzlbmc
                  this.dataForm.bz = data.zdbmmcxxb.bz
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
              url: this.$http.adornUrl(`/dic/zdbmmcxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'pid': this.dataForm.pid,
                'bmmc': this.dataForm.bmmc,
                'xfzlbid': this.dataForm.xfzlbid,
                'xfzlbmc': this.dataForm.xfzlbmc,
                'bz': this.dataForm.bz
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
