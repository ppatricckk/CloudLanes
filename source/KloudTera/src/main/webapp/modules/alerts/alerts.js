//related code for alert module handling
'use strict';

(function() {
	angular.module('cloudLanes.alerts')

	.controller('alertCtrl', alertCtrl);

	// Inject my dependencies
	alertCtrl.$inject = [ '$scope', 'httpService', '$location', '$window' ];

	// Now create our controller function with all necessary logic
	function alertCtrl($scope, httpService, $location, $window) {
		$scope.alertCollection = [];
		$scope.row = {};
		$scope.details = {};
		$scope.recommendation = "";
		$scope.itemsByPage = 10;

		$scope.w = angular.element($window);
		$scope.w.bind('resize', function() {
			$("#divTableContainer").height($("#divAlertRow").height() - 15);
		});

		// Function to load alert from Back end
		$scope.loadAlerts = function() {

			httpService.getAlerts().success(function(data, status, headers) {
				$scope.alertCollection = data;
			}).error(function(data, status, headers, config) {
				alert("Error while fetching alerts");
			});
		};

		$scope.showConfirmation = function(row) {
			$scope.row = angular.copy(row);
			$("#closeAlert").modal();
		};

		// Function to show alert details in modal
		$scope.showDetails = function(row) {
			$scope.details = row;
			$scope.recommendation = row.recommendation.recommendation;
			$scope.code = row.recommendation.code;
			$("#alertModal").modal();
		};

		// Function to update alert
		$scope.closeAlert = function() {
			$scope.row.status = "Close";
			httpService.closeAlert($scope.row).success(
					function(data, status, headers) {
						$scope.loadAlerts();
					}).error(function(data, status, headers, config) {
				alert("Error while updating alerts");
			});
		};

		// init fuction to load controller
		$scope.init = function() {
			$scope.loadAlerts();
		};
		$scope.init();
	}
})();