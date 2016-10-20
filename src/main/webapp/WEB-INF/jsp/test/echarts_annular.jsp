<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<title>环形图</title>
<%@ include file="../common/header.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/lib/echarts/echarts.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/echartsUtil/pie/annularUtil.js"></script>
</head>
<body style="padding: 0px; margin: 0px">
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 800px; height: 800px;"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			AnnularUtil.init("main", contextPath + "/te/pie.do", null);
		});
	</script>
</body>
</html>
