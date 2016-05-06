<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>table</title>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/angular.jsp" />
<link href="${ctx}/common/css/bootstrap.css?v=<%=System.currentTimeMillis()%>" rel="stylesheet" />


<style>
.page-list .pagination {
	float: left;
}

.page-list .pagination span {
	cursor: pointer;
}

.page-list .pagination .separate span {
	cursor: default;
	border-top: none;
	border-bottom: none;
}

.page-list .pagination .separate span:hover {
	background: none;
}

.page-list .page-total {
	float: left;
	margin: 25px 20px;
}

.page-list .page-total input, .page-list .page-total select {
	height: 26px;
	border: 1px solid #ddd;
}

.page-list .page-total input {
	width: 40px;
	padding-left: 3px;
}

.page-list .page-total select {
	width: 50px;
}
</style>
</head>

<body>
	<div ng-app="DemoApp" ng-controller="DemoController">
		<table class="table table-striped">
			<thead>
				<tr>
					<td>ID</td>
					<td>username</td>
					<td>password</td>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="user in persons">
					<td>{{user.int_id}}</td>
					<td>{{user.username}}</td>
					<td>{{user.password}}</td>
				</tr>
			</tbody>
		</table>
		<tm-pagination conf="paginationConf"></tm-pagination>
	</div>
	<script type="text/javascript">
    var app = angular.module('DemoApp', ['tm.pagination']);

    app.controller('DemoController', ['$scope', 'BusinessService', function ($scope, BusinessService) {

        var GetAllEmployee = function () {

            var postData = {
                pageIndex: $scope.paginationConf.currentPage,
                pageSize: $scope.paginationConf.itemsPerPage
            }

            BusinessService.list(postData.pageIndex,postData.pageSize).success(function (response) {
                $scope.paginationConf.totalItems = response.total;
                $scope.persons = response.list;
            });
        }

        //配置分页基本参数
        $scope.paginationConf = {
            currentPage: 1,
            itemsPerPage: 5
        };

        /***************************************************************
		        当页码和页面记录数发生变化时监控后台查询
		        如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。 
        ***************************************************************/
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAllEmployee);
    }]);


    //业务类
    app.factory('BusinessService', ['$http', function ($http) {
        var list = function (pageIndex,pageSize) {
            return $http.get('${pageContext.request.contextPath}/user/getUserListByPage',{params:{"pageIndex":pageIndex,"pageSize":pageSize}});
        }

        return {
            list: function (pageIndex,pageSize) {
                return list(pageIndex,pageSize);
            }
        }
    }]);
</script>
</body>
</html>
