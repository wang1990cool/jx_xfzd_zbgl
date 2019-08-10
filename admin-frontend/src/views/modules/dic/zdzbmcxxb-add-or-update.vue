<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="类别编码" prop="zblbmc" hidden="true">
      <el-input v-model="dataForm.zblbmc" placeholder="类别编码"></el-input>
    </el-form-item>
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
      <el-form-item label="装备照片" prop="zbzp">
        <el-table-column v-if="false"
                         prop="zppath"
                         header-align="center"
                         align="center"
                         width="80"
                         label="装备照片">
        </el-table-column>
        <el-upload
          class="avatar-uploader"
          :action= "uploadUrl"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="装备简介" prop="zbjs">
         <Ueditor   ref="ue" :value="ueditor.value" :config="ueditor.config" id="hhh" ></Ueditor>
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
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<script>
  import Ueditor from '@/components/UE.vue';
  export default {
    components: {
      Ueditor
    },
    data () {
      return {
        uploadUrl: this.$http.adornUrl(`/upload?token=${this.$cookie.get('token')}`),
        visible: false,
        dataForm: {
          id: 0,
          zblbid: '',
          zblbmc: '',
          zbid: '',
          zbmc: '',
          bfzq: '',
          byzq: '',
          zbjs: '',
          bz: '',
          createUserId: '',
          createTime: '',
          zbzp: '',
          zppath:''
        },
        zblbList: [],
        imageUrl: '',
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
        },
        ueditor: {
          value: '',
          config: {
          }
        }
      }
    },
    methods: {
      editorReady(instance) {
        instance.setContent(this.dataForm.zbjs);
        instance.addListener('contentChange', () => {
          this.dataForm.content = instance.getContent();
        });
      },

      closeHandle () {
        this.$refs['dataForm'].resetFields()
        this.ueditor.value = this.dataForm.content
      },
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
                  this.imageUrl = 'data:image/png;base64,' + data.zdzbmcxxb.zbzp
                  this.dataForm.zppath = data.zdzbmcxxb.zppath
                  this.ueditor.value = data.zdzbmcxxb.zbjs
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
        this.dataForm.zblbid = obj.value
        this.dataForm.zblbmc = obj.label

      },
      handleBeforeUpload(file){
        if(!(file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg')) {
          this.$notify.warning({
            title: '警告',
            message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
          })
        }
        let size = file.size / 1024 / 1024 / 2
        if (size > 2) {
          this.$notify.warning({
            title: '警告',
            message: '图片大小必须小于2M'
          })
        }
      },
      // 文件超出个数限制时的钩子
      handleExceed (files, fileList) {
        this.$message({
          showClose: true,
          message: '这是一条消息提示'
        })
      },
      // 文件列表移除文件时的钩子
      handleRemove (file, fileList) {
      },
      // 点击文件列表中已上传的文件时的钩子
      handlePictureCardPreview (file) {

        this.imageUrl = file.url

      },
      uploadFile ( ) {
        this.$refs.upload.submit()
      },

      handleAvatarSuccess (res, file) {
        this.imageUrl = URL.createObjectURL(file.raw)
        console.log(file.raw)
        this.dataForm.zppath = res.filename
        console.log(res.filename)
      },

      beforeAvatarUpload (file) {
        const isJPG = file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isJPG) {
          this.$message.error('上传图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!')
        }
        return isJPG && isLt2M
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
                'zbjs': this.$refs.ue.getUEContent(),
                'bz': this.dataForm.bz,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime,
                'zppath': this.dataForm.zppath
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
