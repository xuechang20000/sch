<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>echarts示例</title>
</head>


<body>
	<div id="main" style="height:400px"></div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/echarts/echarts.js"></script>
	<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '<%=request.getContextPath()%>/resources/echarts/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' //使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
                    tooltip: {show: true},
                    legend: {data:['销量']},
                    xAxis : [{type : 'category',data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]}],
                    yAxis : [{type : 'value'}],
                    series : [
                        {"name":"销量","type":"bar","data":[5, 20, 40, 10, 10, 20]}
                    ]
                };
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</body>
</html>
	