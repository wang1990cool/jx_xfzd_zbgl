<template>
<div>
  <el-row :gutter="20" class="kpiRow">
    <el-col :span="8">
      <div class="grid-content bg-purple-one">
        <div style="float:left;height:100px;width:23%;text-align: right">
          <img src='./kc.png' style='width:55px; height:45px; margin-top:15px; margin-left: 60px'>
        </div>
        <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
          <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">20010</h2>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="grid-content bg-purple-two">
        <div style="float:left;height:100px;width:23%;text-align: right">
          <img src='./car.png' style='width:55px; height:45px; margin-top:15px; margin-left: 60px'>
        </div>
        <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
          <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">1001</h2>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="grid-content bg-purple-three">
        <div style="float:left;height:100px;width:23%;text-align: right">
          <img src='./people.png' style='width:55px; height:45px; margin-top:15px;margin-left: 60px'>
        </div>
        <div style="float:right;margin-top: 15px;padding-left: 80px;width:75%;text-align: left">
          <h2 style="color: #0e507c; height:8px; line-height:10px;margin-bottom: 0px;">5000</h2>
        </div>
      </div>
    </el-col>
  </el-row>


  <el-row class="chartsRow">
    <el-col :span="16">
      <el-row>
        <el-col>
          <div class="mapDiv">
            <img class="img" src="./map.jpg"/>
          </div>
          <!--<div id="mapCharts" style="height: 565px; margin-top: -30px;margin-right: 10px">-->
          <!--</div>-->
        </el-col >
      </el-row>
    </el-col>

    <el-col :span=8>
      <el-row>
        <el-col :span=24 >
          <div class="imgDiv">
            <img class="img" src="./xfzd.jpeg"/>
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
      <el-row style="margin-top: -60px;">
        <el-col :span="4">
          <h2 style="margin-top: -5px;">按标准装配完成度:</h2>
        </el-col >
        <el-col :span="20">
          <el-progress :text-inside="true" :stroke-width="18" :percentage="90"></el-progress>
        </el-col>
      </el-row>
    </el-col>
  </el-row>


  <el-row style="margin-top: -70px;">
    <el-col :span="24">
      <el-form :inline="true" >
        <el-form-item label="装备名称:" style="margin-top: -30px;margin-bottom: -20px;" >
        <el-input placeholder="装备编号" clearable size="primary" style="width: 100%"> </el-input>
      </el-form-item>
        <el-form-item style="margin-top: -30px;margin-bottom: -20px;" >
          <el-button @click="getDataList()" icon="el-icon-zoom-in" type="primary" plain>查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>

  <el-row style="margin-top: -30px;">
    <el-col :span=24>
      <div  class="commonDiv">
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
          <vxe-table-column field="zbid" title="标准数量" ></vxe-table-column>
          <vxe-table-column field="zbmc" title="备用数量" ></vxe-table-column>
          <vxe-table-column field="zbsl" title="标准备用数量"></vxe-table-column>
          <vxe-table-column field="zbsl" title="有效期"></vxe-table-column>
        </vxe-table>
      </div>
    </el-col>
  </el-row>


</div>
</template>

<script>
  export default {
    name: "dashboardCharts",
    data () {
      return {
        kpi: {
          zk: '',
          zc: '',
          zr: ''
        }
      }
    },
    mounted () {
      this.$chart.peiCharts('pieCharts');
      this.$chart.mapCharts('mapCharts');
    },
    methods: {
      initKpi() {
        this.$http({
          url: this.$http.adornUrl(`/borrow/zbjrsqmxb/info/${this.dataForm.id}`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataForm.jrsqid = data.zbjrsqmxb.jrsqid
            this.dataForm.zblbid = data.zbjrsqmxb.zblbid
            this.dataForm.zblbmc = data.zbjrsqmxb.zblbmc
            this.dataForm.zbid = data.zbjrsqmxb.zbid
            this.dataForm.zbmc = data.zbjrsqmxb.zbmc
            this.dataForm.zbsl = data.zbjrsqmxb.zbsl
          }
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

