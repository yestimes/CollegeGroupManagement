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

    showData();
});

function showData(){
  console.log("call echarts");
  var myChart = echarts.init(document.getElementById('pie-box'));

  // 指定图表的配置项和数据
  option = {
    title : {
        text: '南丁格尔玫瑰图',
        subtext: '纯属虚构',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x : 'center',
        y : 'bottom',
        data:['国学社','绿色协会','远风社']
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
                {value:10, name:'国学社'},
                {value:12, name:'绿色协会'},
                {value:15, name:'远风社'},
            ]
        }
    ]
};


  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
}
