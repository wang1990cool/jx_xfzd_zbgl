<template>
  <el-dialog
    width="65%"
      :title="!dataForm.id ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="借用单名称" prop="sqmc">
              <el-input v-model="dataForm.sqmc" placeholder="借用单名称"></el-input>
            </el-form-item>
          </el-col>

           <el-col :span="8">
            <el-form-item label="借出部门" prop="jcbmmc">
              <el-input v-model="dataForm.jcbmmc" placeholder="借出部门"></el-input>
            </el-form-item>
           </el-col>

           <el-col :span="8">
             <el-form-item label="归还日期" prop="ghrq">
               <el-input v-model="dataForm.ghrq" placeholder="归还日期"></el-input>
             </el-form-item>
           </el-col>
        </el-row>

    <el-form-item label="借用单编码" prop="jrsqid" v-if="false">
      <el-input v-model="dataForm.jrsqid"  placeholder="借用单编码"></el-input>
    </el-form-item>

    <el-form-item label="申请部门" prop="sqbmmc" v-if="false">
      <el-input v-model="dataForm.sqbmmc"  placeholder="申请部门"></el-input>
    </el-form-item>
    <el-form-item label="申请部门编号" prop="sqbmid" v-if="false">
      <el-input v-model="dataForm.sqbmid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="借出部门编号" prop="jcbmid" v-if="false">
      <el-input v-model="dataForm.jcbmid" placeholder="借出部门编号"></el-input>
    </el-form-item>

    <el-form-item label="状态码" prop="ztm" v-if="false">
      <el-input v-model="dataForm.ztm" placeholder="状态码"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="zt" v-if="false">
      <el-input v-model="dataForm.zt" placeholder="状态"></el-input>
    </el-form-item>

    <el-form-item label="备注" prop="bz" v-if="false">
      <el-input v-model="dataForm.bz" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="创建人编号" prop="createUserId" v-if="false">
      <el-input v-model="dataForm.createUserId" placeholder="创建人编号"></el-input>
    </el-form-item>
    <el-form-item label="创建人" prop="createTime" v-if="false">
      <el-input v-model="dataForm.createTime" placeholder="创建人"></el-input>
    </el-form-item>
    </el-form>

    <jrmx-grid ref="jrmxGrid"></jrmx-grid>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import jrmxGrid from './jrsqmx'
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          jrsqid: '',
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
          sqbmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          jcbmmc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    components: {
      jrmxGrid
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
            var data = {jrsq: this.dataForm, jrsqmx:this.$refs.jrmxGrid.getDataHandle()}
            // alert(JSON.stringify(data))
            this.$http({
              url: this.$http.adornUrl(`/borrow/jrsq/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: JSON.stringify(data)
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
