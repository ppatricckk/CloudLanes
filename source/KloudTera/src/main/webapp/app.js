//declare all the modules so they will be available for future use
angular.module('cloudLanes.dashboard', []);
angular.module('cloudLanes.auth', []);
angular.module('cloudLanes.vtl', []);
angular.module('cloudLanes.tapeAdministration', []);
angular.module('cloudLanes.alerts', []);
angular.module('cloudLanes.jobs', []);
angular.module('cloudLanes.settings', []);
angular.module('cloudLanes.vault', []);

//declare Main App and include all the dependency
var app =  angular.module('cloudLanes', [
                                         'ui.router',
                                         'ngCookies',
                                         'cloudLanes.tapeAdministration',
                                         'cloudLanes.dashboard',
                                         'cloudLanes.auth',
	                                     'smart-table',
	                                     'cloudLanes.alerts',
 										 'cloudLanes.jobs',
										 'cloudLanes.settings',
	                                     'cloudLanes.vault',
										 'ng-fusioncharts'
                                       ]);


app.constant("alertType", {
    danger: "alert-danger",
    success: "alert-success",
    warning: "alert-warning",
    info:"alert-info"
});

app.run(function($rootScope, $location) {
    $rootScope.$on( "ErrorEvent", function(event, data) {


        if($('#alertdiv').length == 0)
        {
            $('#alert_placeholder').append('<div id="alertdiv" class="alert ' +  data.alertType + '"> <span>'
                +data.errorMsg+'</span></div>')

            setTimeout(function() { // this will automatically close the alert and remove this if the users doesnt close it in 5 secs
                $("#alertdiv").remove();

            }, 3000);
        }

    });
});

//from main app we are going to set only ui-routing, rest all functionality will be handled by it's respective module
app.config(function($stateProvider,$urlRouterProvider) {
	 $urlRouterProvider
    .otherwise('/auth');
	
	//state which will load login screen
	$stateProvider
	 .state('auth',{
		url:'/auth',
	 	templateUrl: "modules/auth/auth.html",
	 	controller:"authCtrl"
	 })
	 //state for home view
	 .state('home',{
		url:'/home',
	 	templateUrl: "modules/home.html",
	 })
	 
	  //state dashboard 
	 .state('home.dashboard',{
		url:'/dashboard',
	 	templateUrl: "modules/dashboard/dashboard.html",
	 	controller:"dashboardCtrl"
	 })
	 .state('home.alerts',{
		url:'/alerts',
	 	templateUrl: "modules/alerts/alerts.html",
	 	controller:"alertCtrl"
	 })

	//jobs
	.state('home.jobs',{
		url:'/jobs',
		templateUrl: "modules/jobs/jobs.html",
		controller:"jobsCtrl"
	})

	//settings
	.state('home.settings',{
		url:'/settings',
		templateUrl: "modules/settings/settings.html",
		controller:"settingsCtrl"
	})

	  //state tape-administration
	 .state('home.tapeAdministration',{
		url:'/tape-administration',
	 	templateUrl: "modules/tape-administration/tape-administration.html",
	 	controller:"tapeAdministrationCtrl"
	 })
	
	 .state('home.tapeAdministration.createTapeLibrary',{
		url:'/create-tape-library',
	 	templateUrl: "modules/tape-administration/create-tape-library.html",
	 	controller:"createTapeLibraryCtrl"
	 })
	 .state('home.tapeAdministration.listTapeLibrary',{
		url:'/list-tape-library',
	 	templateUrl: "modules/tape-administration/list-tape-library.html",
	 	controller:"listTapeLibraryCtrl"
	 })
	 .state('home.tapeAdministration.createTape',{
		url:'/create-tape',
	 	templateUrl: "modules/tape-administration/create-tape.html",
	 	controller:"createTapeCtrl"
	 })
	 .state('home.tapeAdministration.listTape',{
		url:'/list-tapes',
	 	templateUrl: "modules/tape-administration/list-tapes.html",
	 	controller:"listTapesCtrl"
	 })
	 
	 //vault states
	 .state('home.vaultAdministration',{
		url:'/vault-administration',
	 	templateUrl: "modules/vault-administration/vault-administration.html",
	 	controller:"vaultCtrl"
	 	
	 })
	 
	  .state('home.vaultAdministration.createVault',{
		url:'/create-vault',
	 	templateUrl: "modules/vault-administration/create-vault.html",
	 	controller:"createVaultCtrl"
	 	
	 })
	 
	 .state('home.vaultAdministration.createPolicy',{
		url:'/create-policy',
	 	templateUrl: "modules/vault-administration/create-policy.html",
	 	controller:"createPolicyCtrl"
	 	
	 })
	 
	 .state('home.vaultAdministration.listVault',{
		url:'/list-vault',
	 	templateUrl: "modules/vault-administration/vault-list.html",
	 	controller:"listVaultCtrl"
	 	
	 })
	 
	  .state('home.vaultAdministration.listPolicy',{
		url:'/list-policy',
	 	templateUrl: "modules/vault-administration/policy-list.html",
	 	controller:"listPolicyCtrl"
	 })
	 

	 ;
	
});



app.directive('autoActive', ['$location', function ($location) {
    return {
        restrict: 'A',
        scope: false,
        link: function (scope, element) {
            function setActive() {
                var path = $location.path();
                if (path) {
                    angular.forEach(element.find('li'), function (li) {
                        var anchor = li.querySelector('a');
                        var paths = anchor.href.split("#");

                        if(paths.length > 1){
                            if(paths[1] == path){
                                angular.element(li).addClass('active');
                            }else{
                                angular.element(li).removeClass('active');
                            }
                        }else{
                            angular.element(li).removeClass('active');
                        }

                    });
                }
            }
            setActive();
            scope.$on('$locationChangeSuccess', setActive);
        }
    };
}]);



//Home controller
app.controller("homeCtrl", function($scope,$stateParams) {
	//TODO add implementaion
});