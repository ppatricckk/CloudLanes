//related code for authentication handling
'use strict';

angular.module('cloudLanes.dashboard')

.controller('dashboardCtrl', dashboardCtrl);

//Inject my dependencies
dashboardCtrl.$inject = [ '$scope', '$http', '$location', '$rootScope','httpService'];

//Now create our controller function with all necessary logic
function dashboardCtrl($scope, $http, $location, $rootScope,httpService)
{
	$scope.attrs = {
		"caption": "Sales - 2012 v 2013",
		"numberprefix": "$",
		"plotgradientcolor": "",
		"bgcolor": "FFFFFF",
		"showalternatehgridcolor": "0",
		"divlinecolor": "CCCCCC",
		"showvalues": "0",
		"showcanvasborder": "0",
		"canvasborderalpha": "0",
		"canvasbordercolor": "CCCCCC",
		"canvasborderthickness": "1",
		"yaxismaxvalue": "30000",
		"captionpadding": "30",
		"linethickness": "3",
		"yaxisvaluespadding": "15",
		"legendshadow": "0",
		"legendborderalpha": "0",
		"palettecolors": "#f8bd19,#008ee4,#33bdda,#e44a00,#6baa01,#583e78",
		"showborder": "0"
	};

	$scope.categories = [
		{
			"category": [
				{
					"label": "Jan"
				},
				{
					"label": "Feb"
				},
				{
					"label": "Mar"
				},
				{
					"label": "Apr"
				},
				{
					"label": "May"
				},
				{
					"label": "Jun"
				},
				{
					"label": "Jul"
				},
				{
					"label": "Aug"
				},
				{
					"label": "Sep"
				},
				{
					"label": "Oct"
				},
				{
					"label": "Nov"
				},
				{
					"label": "Dec"
				}
			]
		}
	];

	$scope.dataset = [
		{
			"seriesname": "2013",
			"data": [
				{
					"value": "22400"
				},
				{
					"value": "24800"
				},
				{
					"value": "21800"
				},
				{
					"value": "21800"
				},
				{
					"value": "24600"
				},
				{
					"value": "27600"
				},
				{
					"value": "26800"
				},
				{
					"value": "27700"
				},
				{
					"value": "23700"
				},
				{
					"value": "25900"
				},
				{
					"value": "26800"
				},
				{
					"value": "24800"
				}
			]
		},
		{
			"seriesname": "2012",
			"data": [
				{
					"value": "10000"
				},
				{
					"value": "11500"
				},
				{
					"value": "12500"
				},
				{
					"value": "15000"
				},
				{
					"value": "16000"
				},
				{
					"value": "17600"
				},
				{
					"value": "18800"
				},
				{
					"value": "19700"
				},
				{
					"value": "21700"
				},
				{
					"value": "21900"
				},
				{
					"value": "22900"
				},
				{
					"value": "20800"
				}
			]
		}
	];


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