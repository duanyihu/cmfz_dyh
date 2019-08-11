<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%--引入eacharts文件--%>
    <script src="${path}/js/jquery.min.js"></script>
    <script src="${path}/js/echarts.js"></script>
    <script src="${path}/js/china.js"></script>
   <script type="text/javascript">
      /* $(function(){

           $.get("",function (data) {
               // 基于准备好的dom，初始化echarts实例
               var myChart = echarts.init(document.getElementById('main'));
               var series=[];
               for(var i=0;i<data.length;i++){
                   series.push(
                       {
                           name: data[i].title,
                           type: 'map',
                           mapType: 'china',
                           roam: false,
                           label: {
                               normal: {
                                   show: false
                               },
                               emphasis: {
                                   show: true
                               }
                           },
                           data:data[i].cities
                       }
                   )
                   ]
               }

               // 指定图表的配置项和数据
               option = {
                   title : {
                       text: '用户分布图',
                       subtext: '纯属虚构',
                       left: 'center'
                   },
                   tooltip : {
                       trigger: 'item'
                   },
                   legend: {
                       orient: 'vertical',
                       left: 'left',
                       data:['小姐姐','小哥哥']
                   },
                   visualMap: {
                       min: 0,
                       max: 2500,
                       left: 'left',
                       top: 'bottom',
                       text:['高','低'],           // 文本，默认为数值文本
                       calculable : true
                   },
                   toolbox: {
                       show: true,
                       orient : 'vertical',
                       left: 'right',
                       top: 'center',
                       feature : {
                           mark : {show: true},
                           dataView : {show: true, readOnly: false},
                           restore : {show: true},
                           saveAsImage : {show: true}
                       }
                   },
                   series : series
               };
               // 使用刚指定的配置项和数据显示图表。
               myChart.setOption(option);
           });
           },"json")*/
      $(function(){

          // 基于准备好的dom，初始化echarts实例
          var myChart = echarts.init(document.getElementById('main'));

          $.get("${path}/us/queryAllMap",function(data){

              var series=[];

              for(var i=0;i<data.length;i++){
                  var d =data[i];

                  series.push({
                      name: d.title,
                      type: 'map',
                      mapType: 'china',
                      label: {
                          normal: {
                              show: false
                          },
                          emphasis: {
                              show: true
                          }
                      },
                      data:d.cities
                  })
              }

              // 指定图表的配置项和数据
              option = {
                  title : {
                      text: '用户分布图',
                      subtext: '纯属虚构',
                      left: 'center'
                  },
                  tooltip : {
                      trigger: 'item'
                  },
                  legend: {
                      orient: 'vertical',
                      left: 'left',
                      data:['小姐姐','小哥哥']
                  },
                  visualMap: {
                      min: 0,
                      max: 2500,
                      left: 'left',
                      top: 'bottom',
                      text:['高','低'],           // 文本，默认为数值文本
                      calculable : true
                  },
                  toolbox: {
                      show: true,
                      orient : 'vertical',
                      left: 'right',
                      top: 'center',
                      feature : {
                          mark : {show: true},
                          dataView : {show: true, readOnly: false},
                          restore : {show: true},
                          saveAsImage : {show: true}
                      }
                  },
                  series : series
              };

              // 使用刚指定的配置项和数据显示图表。
              myChart.setOption(option);

          },"json");
      });

   </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
    