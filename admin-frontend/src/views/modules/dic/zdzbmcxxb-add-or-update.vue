<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类别编码" prop="zblbmc" hidden="true">
      <el-input v-model="dataForm.zblbmc" placeholder="类别编码"></el-input>
    </el-form-item>
<!--    <el-form-item label="类别名称" prop="zblbmc">
      <el-input v-model="dataForm.zblbmc" placeholder="类别名称"></el-input>
    </el-form-item>-->
      <el-form-item label="类别名称" prop="zblbid">
        <el-select v-model="dataForm.zblbid" label="栏目" placeholder="请选择" @change="selectGet">
          <el-option v-for="item in zblbList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
      </el-form-item>
    <el-form-item label="装备编码" prop="zbid" hidden="true">
      <el-input v-model="dataForm.zbid" placeholder="装备编码"></el-input>
    </el-form-item>
    <el-form-item label="装备名称" prop="zbmc">
      <el-input v-model="dataForm.zbmc" placeholder="装备名称"></el-input>
    </el-form-item>
    <el-form-item label="报废周期" prop="bfzq">
      <el-input v-model="dataForm.bfzq" placeholder="报废周期 -1：不保养，单位为年"></el-input>
    </el-form-item>
    <el-form-item label="保养周期" prop="byzq">
      <el-input v-model="dataForm.byzq" placeholder="保养周期 -1：不保养，单位为月"></el-input>
    </el-form-item>
<!--    <el-form-item label="" prop="bz">
      <el-input v-model="dataForm.bz" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createUserId">
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
          zblbid: '',
          zblbmc: '',
          zbid: '',
          zbmc: '',
          bfzq: -1,
          byzq: -1,
          bz: '',
          createUserId: '',
          createTime: ''
        },
        zblbList: [],
        dataRule: {
          zblbid: [
            { required: true, message: '类别编码不能为空', trigger: 'blur' }
          ],
          zblbmc: [
            { required: true, message: '类别名称不能为空', trigger: 'blur' }
          ],
/*          zbid: [
            { required: true, message: '装备编码不能为空', trigger: 'blur' }
          ],*/
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
/*          ],
          bfzq: [
            { required: true, message: '报废周期 -1：不保养，单位为年不能为空', trigger: 'blur' }
          ],
          byzq: [
            { required: true, message: '保养周期 -1：不保养，单位为月不能为空', trigger: 'blur' }
          ],
          bz: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
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
          url: this.$http.adornUrl('/dic/zdzblbxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.zblbList = data.zblbList
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/dic/zdzbmcxxb/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.zblbid = data.zdzbmcxxb.zblbid
                  this.dataForm.zblbmc = data.zdzbmcxxb.zblbmc
                  this.dataForm.zbid = data.zdzbmcxxb.zbid
                  this.dataForm.zbmc = data.zdzbmcxxb.zbmc
                  this.dataForm.bfzq = data.zdzbmcxxb.bfzq
                  this.dataForm.byzq = data.zdzbmcxxb.byzq
                  this.dataForm.bz = data.zdzbmcxxb.bz
                  this.dataForm.createUserId = data.zdzbmcxxb.createUserId
                  this.dataForm.createTime = data.zdzbmcxxb.createTime
                }
              })
            }
          })
        })
      },

      //下拉框选中事件
      selectGet(vId){//这个vId也就是value值
        let obj = {};
        obj = this.zblbList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.value === vId;//筛选出匹配数据
        });

//        this.dataForm.zblbid = obj.value
        this.dataForm.zblbmc = obj.label

      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdzbmcxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'zblbid': this.dataForm.zblbid,
                'zblbmc': this.dataForm.zblbmc,
                'zbid': this.dataForm.zbid,
                'zbmc': this.dataForm.zbmc,
                'bfzq': this.dataForm.bfzq,
                'byzq': this.dataForm.byzq,
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
