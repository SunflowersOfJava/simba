<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>Echarts</title>
<%@ include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/lib/echarts/echarts.min.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));

	        // 指定图表的配置项和数据
	        var option = {
	        	    title: {
	        	        text: '未来一周气温变化',
	        	        subtext: '纯属虚构'
	        	    },
	        	    tooltip: {
	        	        trigger: 'axis'
	        	    },
	        	    legend: {
	        	        data:['最高气温','最低气温']
	        	    },
	        	    toolbox: {
	        	        show: true,
	        	        feature: {
	        	            dataZoom: {
	        	                yAxisIndex: 'none'
	        	            },
	        	            dataView: {readOnly: false},
	        	            magicType: {type: ['line', 'bar']},
	        	            restore: {},
	        	            saveAsImage: {}
	        	        }
	        	    },
	        	    xAxis:  {
	        	        type: 'category',
	        	        boundaryGap: false,
	        	        data: ['周一','周二','周三','周四','周五','周六','周日']
	        	    },
	        	    yAxis: {
	        	        type: 'value',
	        	        axisLabel: {
	        	            formatter: '{value} °C'
	        	        }
	        	    },
	        	    series: [
	        	        {
	        	            name:'最高气温',
	        	            type:'line',
	        	            data:[11, 11, 15, 13, 12, 13, 10],
	        	            markPoint: {
	        	                data: [
	        	                    {type: 'max', name: '最大值'},
	        	                    {type: 'min', name: '最小值'}
	        	                ]
	        	            },
	        	            markLine: {
	        	                data: [
	        	                    {type: 'average', name: '平均值'}
	        	                ]
	        	            }
	        	        },
	        	        {
	        	            name:'最低气温',
	        	            type:'line',
	        	            data:[1, -2, 2, 5, 3, 2, 0],
	        	            markPoint: {
	        	                data: [
	        	                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
	        	                ]
	        	            },
	        	            markLine: {
	        	                data: [
	        	                    {type: 'average', name: '平均值'},
	        	                    [{
	        	                        symbol: 'none',
	        	                        x: '90%',
	        	                        yAxis: 'max'
	        	                    }, {
	        	                        symbol: 'circle',
	        	                        label: {
	        	                            normal: {
	        	                                position: 'start',
	        	                                formatter: '最大值'
	        	                            }
	        	                        },
	        	                        type: 'max',
	        	                        name: '最高点'
	        	                    }]
	        	                ]
	        	            }
	        	        }
	        	    ]
	        	};

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
		});
	</script>
</body>
</html>
