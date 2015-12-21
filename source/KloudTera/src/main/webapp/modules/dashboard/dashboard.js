//related code for authentication handling
'use strict';

angular.module('cloudLanes.dashboard')

.controller('dashboardCtrl', dashboardCtrl);

//Inject my dependencies
dashboardCtrl.$inject = [ '$scope', '$http', '$location', '$rootScope','httpService'];

//Now create our controller function with all necessary logic
function dashboardCtrl($scope, $http, $location, $rootScope,httpService)
{
	$scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
	$scope.series = ['Series A'];
	$scope.data = [
		[100, 500, 700, 800, 600, 900]
	];
	$scope.onClick = function (points, evt) {
		console.log(points, evt);
	};

	$scope.percent = 45;
	$scope.msg = "dashboardCtrl";

	$scope.alertsCollection = [];

	$scope.jobsCollection = [];

	$scope.libraryStatusCollection = [];


	$scope.alertsStatus = [{"alertType":"Critical","count":0},
							{"alertType":"Warning","count":0},
							{"alertType":"Information","count":0} ];

	$scope.jobsCollection = [{"jobType":"Jobs Failed","count":5},
		{"jobType":"Jobs In Progress","count":3},
		{"jobType":"Jobs Completed","count":2} ];

	$scope.libraryStatusCollection = [{"libraryStatus":"No. Of Libraries","count":0},
		{"libraryStatus":"No. Of Tapes","count":8},
		{"libraryStatus":"Failed Tapes","count":3},
		{"libraryStatus":"Tapes Uploaded","count":5} ];

	$scope.init = function() {
		//All initialization code goes here
		loadAlerts();
		loadAlertList();
	};

	var loadAlertList = function()
	{
		$scope.alertsCollection = $scope.alertsStatus;
	}

	var loadAlerts = function() {

		httpService.getAlerts()
			.success(function(data, status, headers) {
				angular.forEach(data,function(data)
				{
					if(data.serverity == 'Critical')
					{
						$scope.alertsStatus[0].count++;
					}else if(data.serverity == 'Warning')
					{
						$scope.alertsStatus[1].count++;
					}else if(data.serverity == 'Information')
					{
						$scope.alertsStatus[2].count++;
					}
				});
			})
			.error(function(data, status, headers, config){
				alert("Error while fetching alerts");
			});
	};

	$scope.init();
}