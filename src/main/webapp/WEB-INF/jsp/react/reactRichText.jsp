<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>React 富文本演示</title>
<%@ include file="../common/header.jsp"%>
<%@ include file="../common/react.jsp"%>
<%@ include file="../common/draft.jsp"%>
</head>
<body>
	<div id="example" style="width:800px;height:600px"></div>
	<script src="<%=request.getContextPath()%>/js/reactSource/demoRichEditor.js" type="text/babel"></script>
	<script type="text/babel">
	

	</script>
</body>
</html>