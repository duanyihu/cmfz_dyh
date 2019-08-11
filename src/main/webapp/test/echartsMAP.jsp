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
       $(function(){

           // 基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.getElementById('main'));

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
               series : [
                   {
                       name: '小姐姐',
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
                       data:[
                           {name: '北京',value: Math.round(Math.random()*1000)},
                           {name: '天津',value: Math.round(Math.random()*1000)},
                           {name: '上海',value: Math.round(Math.random()*1000)},
                           {name: '重庆',value: Math.round(Math.random()*1000)},
                           {name: '河北',value: Math.round(Math.random()*1000)},
                           {name: '河南',value: Math.round(Math.random()*1000)},
                           {name: '云南',value: Math.round(Math.random()*1000)},
                           {name: '辽宁',value: Math.round(Math.random()*1000)},
                           {name: '黑龙江',value: Math.round(Math.random()*1000)},
                           {name: '湖南',value: Math.round(Math.random()*1000)},
                           {name: '安徽',value: Math.round(Math.random()*1000)},
                           {name: '山东',value: Math.round(Math.random()*1000)},
                           {name: '新疆',value: Math.round(Math.random()*1000)},
                           {name: '江苏',value: Math.round(Math.random()*1000)},
                           {name: '浙江',value: Math.round(Math.random()*1000)},
                           {name: '江西',value: Math.round(Math.random()*1000)},
                           {name: '湖北',value: Math.round(Math.random()*1000)},
                           {name: '广西',value: Math.round(Math.random()*1000)},
                           {name: '甘肃',value: Math.round(Math.random()*1000)},
                           {name: '山西',value: Math.round(Math.random()*1000)},
                           {name: '内蒙古',value: Math.round(Math.random()*1000)},
                           {name: '陕西',value: Math.round(Math.random()*1000)},
                           {name: '吉林',value: Math.round(Math.random()*1000)},
                           {name: '福建',value: Math.round(Math.random()*1000)},
                           {name: '贵州',value: Math.round(Math.random()*1000)},
                           {name: '广东',value: Math.round(Math.random()*1000)},
                           {name: '青海',value: Math.round(Math.random()*1000)},
                           {name: '西藏',value: Math.round(Math.random()*1000)},
                           {name: '四川',value: Math.round(Math.random()*1000)},
                           {name: '宁夏',value: Math.round(Math.random()*1000)},
                           {name: '海南',value: Math.round(Math.random()*1000)},
                           {name: '台湾',value: Math.round(Math.random()*1000)},
                           {name: '香港',value: Math.round(Math.random()*1000)},
                           {name: '澳门',value: Math.round(Math.random()*1000)}
                       ]
                   },
                   {
                       name: '小哥哥',
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
                       data:[
                           {name: '北京',value: Math.round(Math.random()*1000)},
                           {name: '天津',value: Math.round(Math.random()*1000)},
                           {name: '上海',value: Math.round(Math.random()*1000)},
                           {name: '重庆',value: Math.round(Math.random()*1000)},
                           {name: '河北',value: Math.round(Math.random()*1000)},
                           {name: '安徽',value: Math.round(Math.random()*1000)},
                           {name: '新疆',value: Math.round(Math.random()*1000)},
                           {name: '浙江',value: Math.round(Math.random()*1000)},
                           {name: '江西',value: Math.round(Math.random()*1000)},
                           {name: '山西',value: Math.round(Math.random()*1000)},
                           {name: '内蒙古',value: Math.round(Math.random()*1000)},
                           {name: '吉林',value: Math.round(Math.random()*1000)},
                           {name: '福建',value: Math.round(Math.random()*1000)},
                           {name: '广东',value: Math.round(Math.random()*1000)},
                           {name: '西藏',value: Math.round(Math.random()*1000)},
                           {name: '四川',value: Math.round(Math.random()*1000)},
                           {name: '宁夏',value: Math.round(Math.random()*1000)},
                           {name: '香港',value: Math.round(Math.random()*1000)},
                           {name: '澳门',value: Math.round(Math.random()*1000)}
                       ]
                   }
               ]
           };

           // 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);

       });
   </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
    