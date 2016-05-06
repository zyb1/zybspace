var app = angular.module('app', [ 'ngTouch', 'ui.grid',
		'ui.grid.resizeColumns', 'ui.grid.pagination', 'ui.grid.selection' ]);

var scope;
app.controller('MainCtrl', [
		'$scope',
		'i18nService',
		'$http', 
		'$interval',
		'uiGridConstants',
		function($scope, i18nService, $http) {
			$scope.langs = i18nService.getAllLangs();
			$scope.lang = 'zh-cn';
			$scope.gridOptions = {
				enableRowSelection : true,
				enableRowHeaderSelection : false,
				multiSelect : false,
				modifierKeysToMultiSelect : false,
				noUnselect:true,
				onRegisterApi : function( gridApi ) {
				    $scope.gridApi = gridApi;
				 },
				paginationPageSizes : [ 25, 50, 75 ],
				paginationPageSize : 25,
				enableSorting : true,
				columnDefs : [ {
					field : 'int_id',
					width : '20%',
					enableColumnMenu : false
				}, {
					field : 'username',
					enableColumnMenu : false,
					cellTemplate:'<div style="color:red;" ng-click="grid.appScope.show({{row.entity.int_id}})">{{row.entity.username}}</div>',
					width : '40%'
				}, {
					field : 'dept_name',
					width : '40%',
					enableColumnMenu : false
				} ]
			};
			
			$scope.show = function(obj){
				console.log(obj);
			};
			
			// 手动刷新按钮
			$scope.refreshData = function() {
				refreshData($scope, $http);
			};

			$scope.toggleRowSelection = function() {
			    $scope.gridApi.selection.clearSelectedRows();
			    $scope.gridOptions.enableRowSelection = !$scope.gridOptions.enableRowSelection;
			    $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.OPTIONS);
			  };
			  
			$http.get(contextpath + '/user/getUserListAndDept').success(
					function(data) {
						// 需进行容错判断
						$scope.gridOptions.data = data;
					});
		} ]);

function refreshData($scope, $http) {

	scope = $scope;// 在全局保存了变量，方便本地的js对数据进行操作

	$http.get(contextpath + '/user/getUserListAndDept').success(function(data) {
		$scope.gridOptions.data = data;
	});
}
