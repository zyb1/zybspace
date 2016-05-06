<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script
	src="<%=basePath%>/common/scripts/angularjs/1.3.16/angular.min.js"></script>
<script
	src="<%=basePath%>/common/scripts/angularjs/1.3.16/angular-touch.min.js"></script>
<script
	src="<%=basePath%>/common/scripts/angularjs/1.3.16/angular-animate.min.js"></script>
<script src="<%=basePath%>/common/js/tm.pagination.js"></script>