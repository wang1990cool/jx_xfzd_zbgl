<template>
  <el-dialog
    :title="'装备领用'"
    :close-on-click-modal="false"
    :modal="false"
    :append-to-body="true"
    :modal-append-to-body="true"
    width="60%"
    :visible.sync="visible">
  <div class="mod-config">

    <el-form>
    <el-form-item style="margin-top: -30px;margin-bottom: 2px">
      申请装备信息
    </el-form-item>
    </el-form>

    <vxe-grid
      border
      resizable
      size="mini"
      highlight-current-row
      show-overflow
      :loading="loading"
      :data.sync="tableDataJymx">
      <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
      <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbmc" title="装备类别" width="120"></vxe-table-column>
      <vxe-table-column field="zbid" title="装备编号" v-if="false" ></vxe-table-column>
      <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
      <vxe-table-column field="kcsl" title="库存" width="70"></vxe-table-column>
      <vxe-table-column field="sqsl" title="申请数量" width="80" ></vxe-table-column>
      <vxe-table-column field="sjsl" title="实际数量" width="95"  ></vxe-table-column>

    </vxe-grid>

    <el-form :inline="true"  style="margin-top: 10px;margin-bottom: -20px">
      <el-form-item label="装备扫码:" >
        <el-input v-model="dataForm.zbbm" placeholder="装备编号" clearable size="mini" @blur="addZbByScanHandle()"> </el-input>
      </el-form-item>

      <!--<el-form-item style="margin-bottom: 5px">-->
        <!--&lt;!&ndash;<el-button size="mini" type="primary" @click="addHandle()">添加装备</el-button>&ndash;&gt;-->
        <!--<el-button size="mini" type="danger" @click="removeSelectedsHandle()">删除选中</el-button>-->
      <!--</el-form-item>-->
    </el-form>

    <!--<el-form  >-->
      <!--<el-form-item style="margin-bottom: 5px">-->
        <!--&lt;!&ndash;<el-button size="mini" type="primary" @click="addHandle()">添加装备</el-button>&ndash;&gt;-->
        <!--<el-button size="mini" type="danger" @click="removeSelectedsHandle()">删除选中</el-button>-->
      <!--</el-form-item>-->
    <!--</el-form>-->

    <vxe-table
      ref="zbTable"
      border
      resizable
      size="mini"
      highlight-current-row
      show-overflow
      :loading="loading"
      :edit-config="{key:'zbbm', trigger: 'manual', mode: 'row'}"
      :data.sync="tableDataJcmx">
      <vxe-table-column type="selection" width="60"></vxe-table-column>
      <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
      <vxe-table-column field="jrsqid" title="申请单编号" v-if="false"></vxe-table-column>
      <vxe-table-column field="zblbid" title="装备类别码" v-if="false"></vxe-table-column>
      <!--<vxe-table-column field="zblbmc" title="装备类别"></vxe-table-column>-->
      <vxe-table-column field="zbbm" title="装备编码"></vxe-table-column>
      <vxe-table-column field="zbmc" title="装备名称" ></vxe-table-column>
      <vxe-table-column title="操作" width="70">
        <template  slot-scope="scope">
          <el-button type="text" size="small" @click="deleteHandle(scope.row)">删除</el-button>
        </template>
      </vxe-table-column>
    </vxe-table>

  </div>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
       <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
    export default {
      data () {
        return {
          jrsqid: '',
          dataForm: {
            zblbmc: '',
            zbmc: ''
          },
          loading: false,
          tablePage: {
            total: 0,
            currentPage: 1,
            pageSize: 10
          },
          tableDataJymx: [],
          tableDataJcmx: [],
          visible: false
        }
      },
      methods: {
        init (jrsqid) {
          this.visible = true
          this.getDataListJymx(jrsqid)
          this.getDataListJcmx(jrsqid)
          this.jrsqid = jrsqid
        },

        // 获取数据列表
        getDataListJymx (jrsqid) {
          this.loading = true
          this.$http({
            url: this.$http.adornUrl('/borrow/jyjczb/zblist'),
            method: 'get',
            params: {
              'jrsqid': jrsqid
            }
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.tableDataJymx = data.data
            } else {
              this.tableDataJymx = []
            }
            this.loading = false
          })
        },
        // 获取数据列表
        getDataListJcmx (jrsqid) {
          this.loading = true
          this.$http({
            url: this.$http.adornUrl('/borrow/jczbmx/list'),
            method: 'get',
            params: {
              'jrsqid': jrsqid
            }
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.tableDataJcmx = data.data
            } else {
              this.tableDataJcmx = []
            }
            this.loading = false
          })
        },

        getDataAllList () {
          this.dataForm.zblbmc = ''
          this.dataForm.zbmc = ''
          this.getDataList()
        },

        // 表单提交
        dataFormSubmit () {
          var data = {jrsqid: this.jrsqid, jrsqmx: this.$refs.zbTable.getAllRecords()}
          this.$http({
            url: this.$http.adornUrl(`/borrow/jczbmx/addKcmx`),
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
        },
        // 删除选中数据
        removeSelectedsHandle () {
          this.$refs.zbTable.removeSelecteds()
        },

        addZbByScanHandle () {
          var zbbm = this.dataForm.zbbm;
          if (zbbm.length != 0) {
            this.$http({
              url: this.$http.adornUrl('/borrow/jczbmx/geZbmxByZbbm'),
              method: 'get',
              params: {
                'zbbm': zbbm
              }
            }).then(({data}) => {
              if (data && data.code === 0) {
                var datas = [];
                datas.push(data.data)
                this.insertData(datas)
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        },

        insertData (datas) {
            datas = this.convertDatas(datas)
            var records = this.$refs.zbTable.getRecords();
            // 判断数据是否已经存在
            if (records.length != 0) {
              var insertRecords = this.$refs.zbTable.getInsertRecords();

              for (var i = 0; i < datas.length; i++) {
                var isExists = false;
                for (var j = 0; j < records.length; j++) {
                  if (datas[i].zbbm == records[j].zbbm) {
                    isExists = true;
                    break;
                  }
                }
                if (insertRecords.length > 0) {
                  for (var j = 0; j < insertRecords.length; j++) {
                    if (datas[i].zbbm == insertRecords[j].zbbm) {
                      isExists = true;
                      break;
                    }
                  }
                }

                if (!isExists) {
                  this.$refs.zbTable.insert(datas[i], 0)
                }
              }
            }else {
              this.$refs.zbTable.insert(datas)
            }
        },

        convertDatas (datas) {
          if (datas.length > 0) {
            var newDatas = []
            for (var i = 0; i < datas.length; i++) {
              var data = {}
              data['id'] = ''
              data['jrsqid'] = this.jrsqid
              // data['zblbid'] = datas[i].zblbid;
              // data['zblbmc'] = datas[i].zblbmc;
              data['zbid'] = datas[i].zbid
              data['zbbm'] = datas[i].zbbm
              data['zbmc'] = datas[i].zbmc
              data['zt'] = '借用'
              newDatas.push(data)
            }
          }
          return newDatas
        },

        deleteHandle (row) {
          // this.$refs.xTable.setActiveRow(row)
          // alert(this.$refs.zbTable.getActiveRow())
          this.$refs.zbTable.remove(row)
        }
      }
    }
</script>
<style scoped>

</style>
