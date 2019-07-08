/**
 * 各种画echarts图表的方法都封装在这里
 * 注意：这里echarts没有采用按需引入的方式，只是为了方便学习
 */

import echarts from 'echarts'

import {
  BaiduMap
} from './map.js';

const install = function(Vue) {
  Object.defineProperties(Vue.prototype, {
    $chart: {
      get () {
        return {
          mapCharts: function (id) {
            BaiduMap.init().then(BMap => {
              var map = new BMap.Map(id);  // 创建Map实例
              var point = new BMap.Point(118.527055,31.669013);
              // map.centerAndZoom("马鞍山", 20);

              map.centerAndZoom(point, 18);

              var marker = new BMap.Marker(point);  // 创建标注
              map.addOverlay(marker);
              marker.setLabel("雨山大队");

              // //添加地图类型控件
              map.addControl(new BMap.MapTypeControl({
                mapTypes:[
                  BMAP_NORMAL_MAP,
                  BMAP_HYBRID_MAP
                ]}));

              map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            })
          },

          peiCharts: function (id) {
            this.chart = echarts.init(document.getElementById(id));
            this.chart.clear();

            const optionData = {
              title : {
                text: '装备类型比重',
                x:'center'
              },
              tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
              },
              // legend: {
              //   orient: 'vertical',
              //   left: 'left',
              //   data: ['在车装备', '库房装备', '警用装备']
              // },
              series : [
                {
                  name: '装备数量',
                  type: 'pie',
                  radius : '55%',
                  center: ['50%', '55%'],
                  data:[
                    {value:335, name:'在车装备'},
                    {value:810, name:'库房装备'},
                    {value:234, name:'警用装备'},
                  ],
                  itemStyle: {
                    emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                  }
                }
              ]
            };
            this.chart.setOption(optionData);
          }
        }
      }
    }
  })
}

export default {
  install,
  components: {
    BaiduMap
  }
}
