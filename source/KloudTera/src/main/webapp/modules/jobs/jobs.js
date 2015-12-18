'use strict';

angular.module('cloudLanes.jobs')

    .controller('jobsCtrl', jobsCtrl);

//Inject my dependencies
jobsCtrl.$inject = [ '$scope', '$http', '$location', '$rootScope'];

//Now create our controller function with all necessary logic
function jobsCtrl($scope, $http, $location, $rootScope)
{

    $scope.msg = "jobsCtrl";

    $scope.init = function() {
        //All initialization code goes here
    };
    $scope.init();
}
