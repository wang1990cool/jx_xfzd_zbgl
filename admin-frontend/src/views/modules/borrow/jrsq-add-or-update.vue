<template>
  <el-dialog
    width="60%"
      :title="!dataForm.id ? '新增' : '修改'"
      :close-on-click-modal="false"
      :visible.sync="visible">
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="借用单名称" prop="sqmc">
              <el-input v-model="dataForm.sqmc" placeholder="借用单名称"></el-input>
            </el-form-item>
          </el-col>

           <el-col :span="12">
            <el-form-item label="借出部门" prop="selectDeptId">
              <el-cascader
                :props="depShowType"
                ref="cascaderAddr"
                change-on-select
                @change="handleChange"
                :options="options"
                :show-all-levels="false"
                v-model="selectDeptId"
              >
              </el-cascader>
              <!--<el-input v-model="dataForm.jcbmmc" placeholder="借出部门"></el-input>-->
            </el-form-item>
           </el-col>

        </el-row>

        <el-form-item label="借用单编码" prop="id" v-if="false">
          <el-input v-model="dataForm.id"  placeholder="借用单编码"></el-input>
        </el-form-item>

        <el-form-item label="借用单编码" prop="jrsqid" v-if="false">
          <el-input v-model="dataForm.jrsqid"  placeholder="借用单编码"></el-input>
        </el-form-item>

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
    <el-form-item label="借出部门名称" prop="jcbmmc" v-if="false">
      <el-input v-model="dataForm.jcbmmc" placeholder="借出部门名称"></el-input>
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

    <div class="mod-config">
      <el-form >
        <el-form-item style="margin-bottom: 5px">
          <el-button size="small" type="primary" @click="addHandle()">添加装备</el-button>
          <el-button size="small" type="danger" @click="removeSelectedsHandle()">删除选中</el-button>
        </el-form-item>
      </el-form>

      <vxe-table
        ref="xTable"
        border
        resizable
        highlight-current-row
        show-overflow
        :data="tableData"
        :edit-rules="validRules"
        :edit-config="{key:'zbid', trigger: 'click', mode: 'cell'}"
        @edit-actived="editActivedEvent"
        @edit-closed="editClosedEvent">
        <vxe-table-column type="selection" width="60"></vxe-table-column>
        <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
        <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
        <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
        <vxe-table-column field="zblbmc" title="装备类别" width="160"></vxe-table-column>
        <vxe-table-column field="zbid" title="装备编号" v-if="false" ></vxe-table-column>
        <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
        <vxe-table-column field="zbsl" title="装备数量" :edit-render="{name: 'input'}" width="120"></vxe-table-column>
      </vxe-table>
    </div>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    <zbmcxx-grid v-if="zbmcxxGridVisible" ref="zbmcxxGrid" @insertData="insertData"></zbmcxx-grid>
  </el-dialog>

</template>

<script>
  import zbmcxxGrid from './zbmcxx'

  let numRule = (rule, value, callback) => {
    let regExp = /^\d+(\.\d+)?$/;
    if (regExp.test(value) === false) {
      callback(new Error('请输入数字'));
    } else {
      callback();
    }
  };

  export default {
    data () {
      return {
        depShowType: {
          value: 'id',
          label: 'name',
          children: 'nodes'
        },
        selectDeptId: [],
        // 借用明细表格数据
        tableData: [],
        validRules: {
          zbsl: [
            { required: true, validator: numRule }
          ]
        },

        // 装备名称
        zbmcxxGridVisible:false,

        //部门信息
        options: [{
          id: '1',
          name: '雨山大队',
          nodes: [{
            id: '2',
            name: '雨山一中队'
          }, {
            id: '3',
            name: '雨山二中队'
          }]
        }, {
          id: '4',
          name: '向山大队',
          nodes: [{
            id: '5',
            name: '向山一中队'
          }, {
            id: '6',
            name: '向山二中队'
          }]
        }],
        visible: false,
        dataForm: {
          id: '',
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
      zbmcxxGrid
    },
    methods: {
      init (jrsqid, id) {
        this.visible = true
        jrsqid = jrsqid || 0;
        this.getData(jrsqid, id);
      },

      getData (jrsqid, id) {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/borrow/jrsqmx/list'),
          method: 'get',
          params: {
            'jrsqid': jrsqid
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.clearAllData()
            // this.$refs.xTable.insert(data.page)
            this.tableData = data.page
          } else {
            this.tableData = []
          }
          this.dataListLoading = false
        }).then(() => {
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            this.selectDeptId = []
            if (id) {
              this.$http({
                url: this.$http.adornUrl(`/borrow/jrsq/info/${id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                    this.dataForm.id = data.jrsqb.id
                    this.dataForm.jrsqid = data.jrsqb.jrsqid
                    this.dataForm.sqmc = data.jrsqb.sqmc
                    this.dataForm.sqbmid = data.jrsqb.sqbmid
                    this.dataForm.sqbmmc = data.jrsqb.sqbmmc
                    this.dataForm.jcbmid = data.jrsqb.jcbmid
                    this.dataForm.jcbmmc = data.jrsqb.jcbmmc
                    this.dataForm.ztm = data.jrsqb.ztm
                    this.dataForm.zt = data.jrsqb.zt
                    this.dataForm.ghrq = data.jrsqb.ghrq
                    this.dataForm.bz = data.jrsqb.bz
                    this.dataForm.createUserId = data.jrsqb.createUserId
                    this.dataForm.createTime = data.jrsqb.createTime
                    this.selectDeptId = this.changeDetSelect(data.jrsqb.jcbmid, this.options)
                }
              })
            }
          })
        })
      },

      changeDetSelect(key, treeData){
        let arr = []; // 在递归时操作的数组
        let returnArr = []; // 存放结果的数组
        let depth = 0; // 定义全局层级
      // 定义递归函数
        function childrenEach(childrenData, depthN) {
          for (var j = 0; j < childrenData.length; j++) {
            depth = depthN; // 将执行的层级赋值 到 全局层级
            arr[depthN] = (childrenData[j].id);
            if (childrenData[j].id == key) {
              returnArr = arr.slice(0, depthN + 1); //将目前匹配的数组，截断并保存到结果数组，
              break
            } else {
              if (childrenData[j].nodes) {
                depth ++;
                childrenEach(childrenData[j].nodes, depth);
              }
            }
          }
          return returnArr;
        }
        return childrenEach(treeData, depth);
      },

      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$refs.xTable.validate(valid => {
              if (valid) {
                var data = {jrsq: this.dataForm, jrsqmx: this.$refs.xTable.getAllRecords()}
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
              } else {
                this.$XMsg.message({ status: 'error', message: '校验不通过！' })
              }
            })
          }
          // this.clearAllData();
        })
      },

      handleChange: function(item) {
        var labels = this.$refs['cascaderAddr'].currentLabels    //注意2： 获取label值
        if (labels.length >1) {
          this.dataForm.jcbmid = item[1]
          this.dataForm.jcbmmc = labels[1]
        }else{
          this.dataForm.jcbmid = item[0]
          this.dataForm.jcbmmc = labels[0]
        }
      },

      // 添加装备
      addHandle () {
        this.zbmcxxGridVisible = true
        this.$nextTick(() => {
          this.$refs.zbmcxxGrid.init();
        })
      },

      insertData (datas) {
        if (datas.length > 0) {
          datas = this.convertDatas(datas)
          var records = this.$refs.xTable.getRecords();
          // 判断数据是否已经存在
          if (records.length != 0) {
            var insertRecords = this.$refs.xTable.getInsertRecords();

            for (var i = 0; i < datas.length; i++) {
              var isExists = false;
              for (var j = 0; j < records.length; j++) {
                if (datas[i].zbid == records[j].zbid) {
                  isExists = true;
                  break;
                }
              }
              if (insertRecords.length > 0) {
                for (var j = 0; j < insertRecords.length; j++) {
                  if (datas[i].zbid == insertRecords[j].zbid) {
                    isExists = true;
                    break;
                  }
                }
              }

              if (!isExists) {
                this.$refs.xTable.insert(datas[i],0)
              }
            }
          }else {
            this.$refs.xTable.insert(datas)
          }
        } else {
          this.$message.success('请选择装备')
        }
      },

      convertDatas (datas) {
        if (datas.length > 0) {
          var newDatas = []
          for (var i = 0; i < datas.length; i++) {
            var data = {}
            data['id'] = ''
            data['jrsqid'] = this.dataForm.jrsqid;
            data['zblbid'] = datas[i].zblbid;
            data['zblbmc'] = datas[i].zblbmc;
            data['zbid'] = datas[i].zbid;
            data['zbmc'] = datas[i].zbmc;
            newDatas.push(data)
          }
        }
        return newDatas
      },

      clearAllData() {
        this.$refs.xTable.clearAll()
      },

      // 删除选中数据
      removeSelectedsHandle () {
       this.$refs.xTable.removeSelecteds()
      },

      // 获得表单数据
      getDataHandle () {
        // alert(this.$refs.xTable.getRecords())
        return this.$refs.xTable.getInsertRecords();
      },

      getRecords () {
        return this.$refs.xTable.getRecords();
      },

      editActivedEvent ({ row, column }, event) {
        console.log(`打开 ${column.title} 列编辑`)
      },
      editClosedEvent ({ row, column }, event) {
        console.log(`关闭 ${column.title} 列编辑`)
      }
    }
  }
</script>
