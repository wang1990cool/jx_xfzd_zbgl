<template>
  <div class="mod-config">
    <el-form >
      <el-form-item style="margin-bottom: 5px">
        <el-button size="small" type="primary" @click="addHandle()">添加</el-button>
        <el-button size="small" type="danger" @click="deleteHandle()">删除选中</el-button>
      </el-form-item>
    </el-form>

    <vxe-table
      ref="xTable"
      border
      resizable
      highlight-current-row
      show-overflow
      :data.sync="tableData"
      :edit-config="{key:'id', trigger: 'click', mode: 'cell'}"
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
</template>

<script>
  import AddOrUpdate from './jrsqmx-add-or-update'
  export default {
    data () {
      return {
        tableData: []
      }
    },
    created () {

      this.tableData = this.getDataList();
    },

    activated () {

      this.tableData = this.getDataList();
    },
    methods: {
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/borrow/jrsqmx/list'),
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.tableData = data.page
            // this.totalPage = data.page.totalCount
          } else {
            this.tableData = []
            // this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },

      editActivedEvent ({ row, column }, event) {
        console.log(`打开 ${column.title} 列编辑`)
      },
      editClosedEvent ({ row, column }, event) {
        console.log(`关闭 ${column.title} 列编辑`)
      },

      // 添加装备
      addHandle () {
        var datas = [{
          zblbid: 'dj',
          zblbmc: '单警装备',
          zbid:'1',
          zbmc: '手电筒',
          zbsl:'12'
        }, {
          zblbid: 'dj',
          zblbmc: '单警装备1',
          zbid:'2',
          zbmc: '警棍',
          zbsl:'12'
        }]

        var records = this.$refs.xTable.getInsertRecords();
        // 判断数据是否已经存在
        if (records.length != 0) {
          for (var i = 0; i < datas.length; i++) {
            for (var j = 0; j < records.length; j++) {
              if (datas[i].zbid == records[j].zbid) {
                break;
              }
            }
            if (j == records.length) {
              this.$refs.xTable.insert(datas[i])
            }
          }
        }else {
          this.$refs.xTable.insert(datas)
        }
      },

      // 删除选中数据
      deleteHandle () {
        this.$refs.xTable.removeSelecteds()
      },

      // 获得表单数据
      getDataHandle () {
        return this.$refs.xTable.getInsertRecords()
      }

    }
  }
</script>
