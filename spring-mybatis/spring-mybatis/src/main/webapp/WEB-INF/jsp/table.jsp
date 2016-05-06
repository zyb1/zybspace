<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app">
<head>
<title>angulajs table</title>

<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/angular.jsp" />
<jsp:include page="../common/angular-ui-grid.jsp" />

<script src="${ctx}/module/scripts/user/usertables.js"></script>

</head>
<body>
	<div ng-controller="MainCtrl">

		<div ui-i18n="{{lang}}">
			<div>
				<input type="button" id="search1" value="重新获取" ng-click="refreshData()" />
			</div>
			<div ui-grid="gridOptions" style="width: 100%; height: 100%;"
				ui-grid-resize-columns ui-grid-selection ui-grid-pagination></div>

		</div>

	</div>
</body>
</html>