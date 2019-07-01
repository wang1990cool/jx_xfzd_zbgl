<template>
  <el-dialog
    :title="!dataForm.id ? '新增入库' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">

      <el-form-item label="类别编码" prop="zblbbh" hidden="true">
        <el-input v-model="dataForm.zblbbh" placeholder="类别编码"></el-input>
      </el-form-item>
      <el-form-item label="类别名称" prop="zblbmc">
        <el-select v-model="dataForm.zblbmc" label="栏目" placeholder="请选择" @change="selectZblb">
          <el-option v-for="item in zblbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="装备编号" prop="zbid" hidden="true">
        <el-input v-model="dataForm.zbid" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="装备名称" prop="zbmc">
        <el-select v-model="dataForm.zbmc" label="栏目" placeholder="请选择" @change="selectZbmc">
          <el-option v-for="item in zbmcList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
    <el-form-item label="所属部门编号" prop="ssbmid" hidden="true">
      <el-input v-model="dataForm.ssbmid" placeholder="所属部门编号"></el-input>
    </el-form-item>
    <el-form-item label="所属部门" prop="ssbmmc">
      <el-select v-model="dataForm.ssbmmc" label="栏目" placeholder="请选择" @change="selectBmmc">
        <el-option v-for="item in bmmcList" :key="item.label" :label="item.label" :value="item.label" >
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="生产日期" prop="scrq">
      <el-date-picker
        v-model="dataForm.scrq"
        type="date"
        placeholder="选择日期"
        value-format="yyyyMMdd">
      </el-date-picker>
    </el-form-item>
    <el-form-item label="入库数量" prop="zbsl">
      <el-input v-model="dataForm.zbsl" placeholder="入库数量"></el-input>
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
          zblbbh: '',
          zblbmc: '',
          zbid: '',
          zbmc: '',
          ssbmid: '',
          ssbmmc: '',
          scrq: '',
          zbsl: ''
        },
        zblbList: [],
        zbmcList: [],
        bmmcList: [],
        dataRule: {
          zbid: [
            { required: true, message: '装备编号不能为空', trigger: 'blur' }
          ],
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
          ],
          ssbmid: [
            { required: true, message: '所属部门编号不能为空', trigger: 'blur' }
          ],
          ssbmmc: [
            { required: true, message: '所属部门名称不能为空', trigger: 'blur' }
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
        this.$http({
          url: this.$http.adornUrl('/dic/zdzblbxxb/select'),
          method: 'get'
        }).then(({data}) => {
          this.zblbList = data.zblbList
        }).then(() => {
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
                    url: this.$http.adornUrl(`/kcgl/zbkcxxb/info/${this.dataForm.id}`),
                    method: 'get',
                    params: this.$http.adornParams()
                  }).then(({data}) => {
                    if (data && data.code === 0) {
                      this.dataForm.zbid = data.zbkcxxb.zbid
                      this.dataForm.zbmc = data.zbkcxxb.zbmc
                      this.dataForm.ssbmid = data.zbkcxxb.ssbmid
                      this.dataForm.ssbmmc = data.zbkcxxb.ssbmmc
                      this.dataForm.zbsl = data.zbkcxxb.zbsl
                    }
                  })
                }
              })
            })
        })
      },
      //下拉框选中事件 装备类别
      selectZblb(vId){//这个vId也就是value值
        let obj = {};
        obj = this.zblbList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });

        this.dataForm.zblbbm = obj.value
        this.dataForm.zblbmc = obj.label
        var zblbid = obj.value;
        this.getZbmcList(zblbid)
      },
      // 获取装备名称信息
      getZbmcList (zblbid) {
        this.$http({
          url: this.$http.adornUrl('/dic/zdzbmcxxb/select?zblbid='+ zblbid ),
          method: 'get',
          params: this.$http.adornParams({
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.zbmcList = data.zbmcList
          } else {
            this.zbmcList = []
          }
        })
      },

      //下拉框选中事件  装备名称
      selectZbmc(vId){//这个vId也就是value值
        let obj = {};
        obj = this.zbmcList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });

        this.dataForm.zbid= obj.value
        this.dataForm.zbmc = obj.label
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
              url: this.$http.adornUrl(`/kcgl/zbkcxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'zbid': this.dataForm.zbid,
                'zbmc': this.dataForm.zbmc,
                'ssbmid': this.dataForm.ssbmid,
                'ssbmmc': this.dataForm.ssbmmc,
                'scrq': this.dataForm.scrq,
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
