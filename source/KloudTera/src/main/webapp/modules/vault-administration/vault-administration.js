'use strict';

angular.module('cloudLanes.vault')

    .controller('vaultCtrl', vaultCtrl)
    .controller('createVaultCtrl', createVaultCtrl)
    .controller('listVaultCtrl', listVaultCtrl)
    .controller('createPolicyCtrl', createPolicyCtrl)
    .controller('listPolicyCtrl', listPolicyCtrl);
 

//Inject my dependencies
vaultCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
createVaultCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
listVaultCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
createPolicyCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
listPolicyCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];


function vaultCtrl($scope, httpService, $location, $rootScope, $http)
{

	$scope.init = function() {
		$location.path("home/vault-administration/list-vault");
    };
    $scope.init();

}

//create vault
function createVaultCtrl($scope, httpService, $location, $rootScope, $http)
{
	$scope.vault = {
		accountName:"",
		type:"",
		accessKey:"",
		secretKey:"",
		container:""
	};

	$scope.vaultTypes =[
		{vaultName:"Amazon AWS S3"},
		{vaultName:"Microsoft Azure"}
	]

	var clearVaultData = function(){
		$scope.vault.accountName = "";
		$scope.vault.type = "";
		$scope.vault.accessKey = "";
		$scope.vault.secretKey = "";
		$scope.vault.container = "";
	};

	
	$scope.createVault = function(isValid) {
		if(isValid){
			httpService.createVault($scope.vault).success(function(data, status, headers) {
				$location.path("home/vault-administration/list-vault");
			}).error(function(data, status, headers, config) {
				alert(data);
			});
		}

	};

	$scope.init = function() {
		clearVaultData();
		$scope.vault.type = $scope.vaultTypes[0].vaultName;
	};
    $scope.init();

}

//list vault
function listVaultCtrl($scope, httpService, $location, $rootScope, $http)
{
    $scope.vaultCollection = [];
    
    $scope.loadVaultList = function() {

		httpService.getVaultList().success(function(data, status, headers) {
			$scope.vaultCollection = data;
		}).error(function(data, status, headers, config) {
			alert("Error while fetching vaults");
		});
	};
    
	$scope.init = function() {
        $scope.loadVaultList();    	
    };
    $scope.init();

}

//create policy
function createPolicyCtrl($scope, httpService, $location, $rootScope, $http)
{

	$scope.policy = {
			name:"",
			type:"",
			criteria:"",
			value:""
			
	};
		
	$scope.createPolicy = function(isValid) {
		if(isValid){
			httpService.addPolicy($scope.policy).success(function(data, status, headers) {
				$scope.clearPlocily();
				$location.path("home/vault-administration/list-policy");
			}).error(function(data, status, headers, config) {
				alert(data);
			});
		}
	};
	
	
	$scope.clearPlocily = function(){
	  $scope.policy.name = "";
	  $scope.policy.type = "";
	  $scope.policy.criteria = "";
	  $scope.policy.value = "";
	};
	
	$scope.init = function() {
			$scope.clearPlocily();
    };
    $scope.init();

}

//list policy
function listPolicyCtrl($scope, httpService, $location, $rootScope, $http)
{

	 $scope.policyCollection = [];
	 $scope.policy = {};
	 $scope.updateValues = {
				name:"",
				type:"",
				criteria:"",
				value:""
				
		};
	    
	    $scope.loadPolicylist = function() {
	    	$('#pListLoader').show();
			httpService.getPolicyList()
				.success(function(data, status, headers) {
					$scope.policyCollection = data;
					$('#pListLoader').hide();
				})
				.error(function(data, status, headers, config){
					alert("Error while fetching policy");
					$('#pListLoader').hide();
				});
		};
	    
		
		$scope.deletePolicy = function(row) {
			
			httpService.deletePolicy(row)
			.success(function(data, status, headers) {
				$scope.loadPolicylist ();
			})
			.error(function(data, status, headers, config){
				alert("Error while deleting policy");
			});
	    };
	    
	    $scope.updatePolicy = function(row) {
	    	   
	      $scope.updateValues.name=row.name;
	      $scope.updateValues.type=row.type;
	      $scope.updateValues.criteria=row.criteria;
	      $scope.updateValues.value=row.value;
	      $scope.updateValues.id=row.id;
	    	$("#updateModal").modal();
	    };
	    
	    $scope.update = function(isValid) {
	    	if(isValid){
	    		httpService.updatePolicy($scope.updateValues)
				.success(function(data, status, headers) {
					$scope.loadPolicylist();
					 $('#updateModal').modal('hide');
				})
				.error(function(data, status, headers, config){
					 $('#updateModal').modal('hide');
					 alert("Error while updating policy");
				});
	    	}
	    };
	    
	    
		$scope.init = function() {
	        $scope.loadPolicylist();
	        $('#pListLoader').hide();
	    };
	    $scope.init();

}