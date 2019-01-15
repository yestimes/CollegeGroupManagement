$(document).ready(function(){
    $.getJSON("/getIndexInitInfo",function(res){
        console.log(res);
        //初始化页面信息
        //根据权限，初始化左边菜单
        if(res.status == 200){
          if(res.permission != 3){
            alert("不允许非授权用户访问");
               window.location = "/login.html"
          }
          else {
            //sa user
            $("#user-name-box").text(res.name);
          }
        }
        else{
          alert("未登陆");
          window.location = "/login.html"
        }
    });

    console.log("start");
    showData();
    console.log("end");
});



function showData(){
    console.log("call echarts");
    var maleChart = echarts.init(document.getElementById('male-pie-box'));

    // 指定图表的配置项和
    maleOption = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'right',
            data:['男', '女']
        },
        series: [
            {
                name:'性别比例',
                type:'pie',
                radius: ['70%', '50%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[

                ]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    maleChart.setOption(maleOption);

    var countChart = echarts.init(document.getElementById('pie-box'));


    countOption = {
      title : {
          text: '成员分布',
          x:'center'
      },
      tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
          x : 'center',
          y : 'bottom',
          data:[]
      },
      toolbox: {
          show : true,
          feature : {
              mark : {show: true},
              dataView : {show: true, readOnly: false},
              magicType : {
                  show: true,
                  type: ['pie', 'funnel']
              },
              restore : {show: true},
              saveAsImage : {show: true}
          }
      },
      calculable : true,
      series : [
            {
              name:'面积模式',
              type:'pie',
              radius : [30, 110],
              center : ['50%', '50%'],
              roseType : 'area',
              data:[

              ]
          }
      ]
  };

  countChart.setOption(countOption);


    $.getJSON("/StatisInfo", function(res){

      console.log(res);
        maleChart.setOption({
          series:[{
            data:[
              {value: res.maleNum, name:'男'},
              {value: res.formaleNum, name:'女'}
            ]
          }]
        });

        countData = []
        for( i in res.orgaNames){
          countData.push({value: res.orgaMemCount[i], name: res.orgaNames[i]});
        }

        countChart.setOption({
          legend: {
            data: res.orgaNames
          },
          series :[{
            data: countData
          }]
        });

    });
}
