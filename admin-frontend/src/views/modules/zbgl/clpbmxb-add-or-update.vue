<template>
  <el-dialog
    :title="!dataForm.id ? '新增车辆配备' : '新增人员配备'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
<!--    <el-form-item label="人员编码" prop="rybh"  hidden="true">
      <el-input v-model="dataForm.rybh" placeholder="装备名称编号"></el-input>
    </el-form-item>-->
<!--  <el-form-item label="人员姓名" prop="ryxm">
    <el-input v-model="dataForm.zbmc" placeholder="人员姓名"></el-input>
  </el-form-item>-->
    <el-form-item label="车牌号" prop="cph">
      <el-select v-model="dataForm.cph" label="栏目" placeholder="请选择">
        <el-option v-for="item in clxxList" :key="item.label" :label="item.label" :value="item.label" >
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
    <el-form-item label="装备编号" prop="zbid" hidden="true">
      <el-input v-model="dataForm.zbid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="装备类别" prop="zbmc">
      <el-select v-model="dataForm.zbmc" label="栏目" placeholder="请选择" @change="selectZbmc">
        <el-option v-for="item in zbmcList" :key="item.label" :label="item.label" :value="item.label" >
        </el-option>
      </el-select>
    </el-form-item>
      <el-form-item label="具体装备" prop="zbbm" >
        <el-select v-model="dataForm.zbbm" label="栏目" placeholder="请选择" >
          <el-option v-for="item in zbbmList" :key="item.value" :label="item.label" :value="item.value" >
          </el-option>
        </el-select>
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
//          rybh: '',
          cph: '',
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
        clxxList: [],
        zblbList: [],
        zbmcList: [],
        zbbmList: [],

        dataRule: {

        }
      }
    },
    methods: {
        init(id){
          this.visible = true
          this.$http({
            url: this.$http.adornUrl('/dic/zdclxxb/selectByssbmmc'),
            method: 'get'
          }).then(({data}) => {
            this.clxxList = data.clxxList
          }).then(() => {
              this.$http({
                url: this.$http.adornUrl('/dic/zdzblbxxb/select'),
                method: 'get'
              }).then(({data}) => {
                this.zblbList = data.zblbList
              }).then(() => {
                this.$refs['dataForm'].resetFields()
              })
          })

        },
/*
      //下拉框选中事件 车辆信息
      selectClxx(vId){//这个vId也就是value值
        let obj = {};
        obj = this.ryxxList.find((item) => {//这里的hymcDate就是上面遍历的数据源
          return item.label === vId;//筛选出匹配数据
        });
        this.dataForm.rybh = obj.value
        this.dataForm.ryxm = obj.label

      },
*/
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
        this.getZbbhList(obj.value)
      },
      // 获取装备编号信息
      getZbbhList (zbid) {
        this.$http({
          url: this.$http.adornUrl('/kcgl/zbkcmxb/select?zbid='+ zbid ),
          method: 'get',
          params: this.$http.adornParams({
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.zbbmList = data.zbbmList
          } else {
            this.zbbmList = []
          }
        })
      },
/*      init (id) {
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
      },*/
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/zbgl/clpbmxb/update`),
              method: 'get',
              params: this.$http.adornParams({
                'zbbm': this.dataForm.zbbm,
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
