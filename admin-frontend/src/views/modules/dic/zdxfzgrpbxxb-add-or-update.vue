<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="xfzlbid" hidden="true">
      <el-input v-model="dataForm.xfzlbid" placeholder=""></el-input>
    </el-form-item>
      <el-form-item label="类别名称" prop="xfzlbmc">
        <el-select v-model="dataForm.xfzlbmc" label="栏目" placeholder="请选择" @change="selectXfzlb">
          <el-option v-for="item in xfzlbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类别编码" prop="zblbbh" hidden="true">
        <el-input v-model="dataForm.zblbbh" placeholder="类别编码"></el-input>
      </el-form-item>
      <el-form-item label="类别名称" prop="zblbmc">
        <el-select v-model="dataForm.zblbmc" label="栏目" placeholder="请选择" @change="selectZblb">
          <el-option v-for="item in zblbList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="装备编号" prop="zbmcbh" hidden="true">
        <el-input v-model="dataForm.zbmcbh" placeholder=""></el-input>
      </el-form-item>
      <el-form-item label="装备名称" prop="zbmc">
        <el-select v-model="dataForm.zbmc" label="栏目" placeholder="请选择" @change="selectZbmc">
          <el-option v-for="item in zbmcList" :key="item.label" :label="item.label" :value="item.label" >
          </el-option>
        </el-select>
      </el-form-item>
    <el-form-item label="标准数量" prop="bzpbsl">
      <el-input-number v-model="dataForm.bzpbsl"  :min="0"  label="标准配备数量"></el-input-number>
<!--      <el-input v-model="dataForm.bzpbsl" placeholder="标准配备数量"></el-input>-->
    </el-form-item>
    <el-form-item label="备份比" prop="bzbfb">
      <el-input v-model="dataForm.bzbfb" placeholder="标准备份比"></el-input>
    </el-form-item>
    <el-form-item label="" prop="bfzbsl" hidden="true">
      <el-input v-model="dataForm.bfzbsl" placeholder=""></el-input>
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
          zblbbh: '',
          zblbmc: '',
          zbmcbh: '',
          zbmc: '',
          bzpbsl: '',
          bzbfb: '',
          bfzbsl: ''
        },
        xfzlbList: [],
        zblbList: [],
        zbmcList: [],
        dataRule: {
          xfzlbid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          xfzlbmc: [
            { required: true, message: '消防站类别不能为空', trigger: 'blur' }
          ],
          zblbbh: [
            { required: true, message: '装备类别编号不能为空', trigger: 'blur' }
          ],
          zblbmc: [
            { required: true, message: '装备类别名称不能为空', trigger: 'blur' }
          ],
          zbmcbh: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          zbmc: [
            { required: true, message: '装备名称不能为空', trigger: 'blur' }
          ],
          bzpbsl: [
            { required: true, message: '标准配备数量不能为空', trigger: 'blur' }
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
                  url: this.$http.adornUrl(`/dic/zdxfzgrpbxxb/info/${this.dataForm.id}`),
                  method: 'get',
                  params: this.$http.adornParams()
                }).then(({data}) => {
                  if (data && data.code === 0) {
                    this.dataForm.xfzlbid = data.zdxfzgrpbxxb.xfzlbid
                    this.dataForm.xfzlbmc = data.zdxfzgrpbxxb.xfzlbmc
                    this.dataForm.zblbbh = data.zdxfzgrpbxxb.zblbbh
                    this.dataForm.zblbmc = data.zdxfzgrpbxxb.zblbmc
                    this.dataForm.zbmcbh = data.zdxfzgrpbxxb.zbmcbh
                    this.dataForm.zbmc = data.zdxfzgrpbxxb.zbmc
                    this.dataForm.bzpbsl = data.zdxfzgrpbxxb.bzpbsl
                    this.dataForm.bzbfb = data.zdxfzgrpbxxb.bzbfb
                    this.dataForm.bfzbsl = data.zdxfzgrpbxxb.bfzbsl
                  }
                })
              }
            })
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

      //下拉框选中事件 装备类别
      selectZblb(vId){//这个vId也就是value值
        let obj = {};
        obj = this.zblbList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });

        this.dataForm.zblbbh = obj.value
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
        this.dataForm.zbmcbh = obj.value
        this.dataForm.zbmc = obj.label

      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/dic/zdxfzgrpbxxb/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'xfzlbid': this.dataForm.xfzlbid,
                'xfzlbmc': this.dataForm.xfzlbmc,
                'zblbbh': this.dataForm.zblbbh,
                'zblbmc': this.dataForm.zblbmc,
                'zbmcbh': this.dataForm.zbmcbh,
                'zbmc': this.dataForm.zbmc,
                'bzpbsl': this.dataForm.bzpbsl,
                'bzbfb': this.dataForm.bzbfb,
                'bfzbsl': this.dataForm.bfzbsl
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
