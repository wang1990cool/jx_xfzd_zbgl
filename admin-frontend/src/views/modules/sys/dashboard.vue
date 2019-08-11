<template>
  <div>
    <el-row :gutter="20" class="kpiRow">
      <el-col :span="8">
        <div class="grid-content bg-purple-one">
          <div style="float:left;height:100px;width:23%;text-align: right">
            <img src='~@/assets/img/kc.png' style='width:55px; height:45px; margin-top:15px; margin-left: 60px'>
          </div>
          <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
            <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">{{kpi.zk}}</h2>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-two">
          <div style="float:left;height:100px;width:23%;text-align: right">
            <img src='~@/assets/img/car.png' style='width:55px; height:45px; margin-top:15px; margin-left: 60px'>
          </div>
          <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
            <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">{{kpi.zc}}</h2>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple-three">
          <div style="float:left;height:100px;width:23%;text-align: right">
            <img src='~@/assets/img/people.png' style='width:55px; height:45px; margin-top:15px;margin-left: 60px'>
          </div>
          <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
            <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">{{kpi.zr}}</h2>
          </div>
        </div>
      </el-col>
    </el-row>


    <el-row class="chartsRow">
      <el-col :span="16">
        <el-row>
          <el-col>
            <!--<div class="mapDiv">-->
            <!--<img class="img" src="./map.jpg"/>-->
            <!--</div>-->
            <div id="mapCharts" style="height: 520px; margin-top: -30px;margin-right: 10px">
            </div>
          </el-col >
        </el-row>
      </el-col>

      <el-col :span=8>
        <el-row>
          <el-col :span=24 >
            <div class="imgDiv">
              <img class="img" src="~@/assets/img/xfzd.jpeg"/>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col >
            <div id="pieCharts" class="peiDiv"></div>
          </el-col>
        </el-row>
      </el-col>

    </el-row>

    <el-row>
      <el-col :span="24">
        <el-row style="margin-top: -90px;">
          <el-col :span="4">
            <h2 style="margin-top: -5px;">按标准装配完成度:</h2>
          </el-col >
          <el-col :span="20">
            <el-progress color="#01A9DB" :stroke-width="15" :percentage="completeness"></el-progress>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row style="margin-top: -70px;">
      <el-col :span="4">
        <h2 style="margin-top: -20px;">装备名称:</h2>
      </el-col>
      <el-col :span="20">
        <el-form>
          <el-form-item  style="margin-top: -30px;margin-bottom: -20px;" >
            <el-input v-model="search.zbmc" placeholder="装备名称" clearable size="primary" style="width: 100%"> </el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <el-row style="height: 10px">
      <el-col :span="4">
        <h2 style="margin-top: -20px;">异常状态检索:</h2>
      </el-col>
      <el-col :span="20" style="margin-top: -18px;">
        <template>
          <el-checkbox-group v-model="search.checkList" @change="checkChange()">
            <el-checkbox key="1" label="1">装配标准不足</el-checkbox>
            <!--<el-checkbox key="2" label="2">备用标准不足</el-checkbox>-->
            <el-checkbox key="3" label="3">装备使用超时</el-checkbox>
          </el-checkbox-group>
        </template>
      </el-col>
    </el-row>

    <el-row  style="margin-top: -80px;">
      <el-col :span="24">
        <div style="text-align:center;">
          <el-button @click="getDataList()"  icon="el-icon-search" type="danger">查询</el-button>
        </div>
      </el-col>
    </el-row>

    <el-row style="margin-top: -20px;">
      <el-col :span=24>
        <div>
          <vxe-table
            ref="xTable"
            border
            resizable
            highlight-current-row
            show-overflow
            :data="tableData">
            <vxe-table-column type="index" title="序号" width="60"></vxe-table-column>
            <vxe-table-column field="jrsqid" title="大队名称"></vxe-table-column>
            <vxe-table-column field="zblbid" title="装备名称" ></vxe-table-column>
            <vxe-table-column field="zblbmc" title="装备数量"></vxe-table-column>
            <vxe-table-column field="zbid" title="最小标准配备" ></vxe-table-column>
            <vxe-table-column field="zbmc" title="备份标准配备" ></vxe-table-column>
            <vxe-table-column field="zbmc" title="当前库存" ></vxe-table-column>
            <!--<vxe-table-column field="zbsl" title="有效期"></vxe-table-column>-->
          </vxe-table>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  export default {
    data () {
      return {
        kpi: {
          zk: '',
          zc: '',
          zr: ''
        },
        tableData: [],
        search: {
          zbmc: '',
          checkList: []
        },
        completeness: 90
      }
    },
    mounted () {
      this.initPeiCharts()
      this.$chart.mapCharts('mapCharts')
      this.initKpi()
    },
    methods: {
      initKpi () {
        this.$http({
          url: this.$http.adornUrl('/sys/dashboard/kcSize'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.kpi.zk = 12358 //data.kc[0]
            this.kpi.zc = 4868
            this.kpi.zr = 1000
          }
        })
      },

      initPeiCharts () {
        this.$http({
          url: this.$http.adornUrl('/sys/dashboard/kcSize'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$chart.peiCharts('pieCharts', data.kc)
          }
        })
      },

      getDataList () {
        this.loading = true
        this.$http({
          url: this.$http.adornUrl('/sys/dashboard/zblist'),
          method: 'get',
          params: {
            'zbmc': this.search.zbmc,
            'yc': this.search.checkList.toString()
          }
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.tableData = data.data
          } else {
            this.tableData = []
          }
          this.loading = false
        })
      }

    }
  }
</script>

<style>
  .peiDiv {
    height: 280px;
    margin-left: 50px;
    margin-top: -50px;
  }
  .imgDiv{
    height: 280px;
  }
  .img{
    width:95%;
    height:100%;
    margin-left: 20px;
    margin-top: -25px;
  }
  .mapDiv{
    height: 550px;
  }
  .el-row {
    margin-bottom: 40px;
  &:last-child {
     margin-bottom: 0;
   }
  }

  .kpiRow{
    margin-bottom: 25px;
  &:last-child {
     margin-bottom: 0;
   }
  }

  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #409EFF;
  }
  .bg-purple-one {
    background: #67C23A;
  }
  .bg-purple-two {
    background: #E6A23C;
  }
  .bg-purple-three {
    background: #F56C6C;
  }
  .bg-purple-light {
    background: #409EFF;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 75px;
  }

  .row-bg {
    padding: 10px 0;
    background-color: #409EFF;
  }

</style>

