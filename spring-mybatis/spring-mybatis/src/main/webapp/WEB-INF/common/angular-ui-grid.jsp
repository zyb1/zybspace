<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script
	src="<%=basePath%>/common/scripts/angularjs/ui-grid/3.1.1/ui-grid.min.js"></script>

<link
	href="<%=basePath%>/common/style/angularjs/ui-grid/3.1.1/ui-grid.min.css"
	rel="stylesheet" type="text/css">