//related code for authentication handling
'use strict';

angular.module('cloudLanes.auth')

.controller('authCtrl', authCtrl);

// Inject my dependencies
authCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope',
		'$http','$cookieStore' ];

// Now create our controller function with all necessary logic
function authCtrl($scope, httpService, $location, $rootScope, $http,$cookieStore) {
	$scope.user = {
		user_id : "",
		password : ""
	};

	$scope.config = {
		headers : {
			'Content-Type' : 'application/json'
		}
	};

	$scope.submit = function() {

		httpService.login($scope.user).success(
				function(data, status, headers, config) {
					if (data.status == true) {
						$rootScope.loggedInUser = $scope.user;
						$rootScope.loggedInUser.authenticated = true;
						$cookieStore.put('appAuthrnticated', $rootScope.loggedInUser);
						$location.path("home/dashboard");
					} else {
						$scope.$emit('ErrorEvent', {
							errorMsg : 'Invalid Credentials!',
							alertType : "alert-danger"
						});
					}
				}).error(function(data, status, headers, config) {
			$scope.$emit('ErrorEvent', {
				errorMsg : data,
				alertType : "alert-danger"
			});
		});
	};
	
	$scope.init = function() {
		var authenticated = $cookieStore.get('appAuthrnticated');
		if (typeof authenticated != 'undefined') {
			if (authenticated.authenticated === true) {
				$rootScope.loggedInUser = authenticated;
				$location.path("/home/dashboard");				
			} else {
				console.log("Login available with authentication false");
			}
		} else {
			console.log("App not athunticated..");
		}
	};
	
	$scope.init();
}