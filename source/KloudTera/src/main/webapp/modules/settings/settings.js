'use strict';

angular.module('cloudLanes.settings')

    .controller('settingsCtrl', settingsCtrl);

//settingsCtrl my dependencies
settingsCtrl.$inject = [ '$scope', '$http', '$location', '$rootScope'];

//Now create our controller function with all necessary logic
function settingsCtrl($scope, $http, $location, $rootScope)
{

    $scope.msg = "settingsCtrl";

    $scope.init = function() {
        //All initialization code goes here
    };
    $scope.init();
}
