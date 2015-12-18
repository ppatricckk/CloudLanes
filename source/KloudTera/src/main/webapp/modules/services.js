app.factory('httpService', [ '$http', function($http) {

	var getRequestNoParms = function(url) {
		return $http({
			method : 'GET',
			url : url,
			cache : false
		});
	};

	var getRequest = function(url, params) {
		return $http({
			method : 'GET',
			url : url,
			data : params,
			cache : false
		});
	};

	var postRequest = function(url, params) {
		return $http({
			method : 'POST',
			url : url,
			data : params,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		});
	};
	
	var deleteRequest = function(url, params) {
		return $http({
			method : 'DELETE',
			url : url,
			data : params,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
		});
	};

	return {
		loginWithoutParams : function() {
			return getRequestNoParms();
		},
		login : function(user) {
			return postRequest("rest/loginService/authenticate", user);
		},

		//tape-administration calls

		createVTL : function(data) {
			return postRequest("rest/vtlservice/add",data);
		},

		createTape : function(data) {
			return postRequest("rest/tapeservice/add",data);
		},

		createVault : function(data) {
			return postRequest("rest/vaultservices/add", data);
		},


			getVTLBrands : function() {
			return getRequestNoParms("rest/vtlservice/vtlBrands");
		},

		getVTLModels : function(data) {
			return getRequestNoParms("rest/vtlservice/vtlModels?brandId=" + data);
		},

		getVTLDriveVendors : function(data) {
			return getRequestNoParms("rest/vtlservice/driveVendors?brandId=" + data);
		},

		getVTLDriveModels : function(data) {
			return getRequestNoParms("rest/vtlservice/driveModels?brandId=" + data);
		},

		getVTLMediaTypes : function(data) {
			return getRequestNoParms("rest/vtlservice/mediaTypes?brandId=" + data);
		},

		getTapeVTLMediaTypes : function(data) {
			return getRequestNoParms("rest/tapeservice/mediaTypes?libraryName=" + data);
		},


		getVTLList : function() {
			return getRequestNoParms("rest/vtlservice/vtlList");
		},

		getTapeList : function(data) {
			return getRequestNoParms("rest/tapeservice/tapeList?libraryName=" + data);
		},

		// alert calls
		getAlerts : function() {
			return getRequestNoParms("rest/alertservices/list");
		},
		closeAlert : function(data) {
			return postRequest("rest/alertservices/update",data);
		},
		getAlertRecommendation : function(code) {
			return getRequestNoParms("rest/alertservices/recommendation?code=" + code);
		},
		// vault calls
		getVaultList : function() {
			return getRequestNoParms("rest//vaultservices/list");
		},
		getPolicyList : function() {
			return getRequestNoParms("rest/policyservices/list");
		},
		deletePolicy : function(data) {
			return deleteRequest("rest/policyservices/delete",data);
		},
		addPolicy : function(data) {
			return postRequest("rest/policyservices/add",data);
		},
		updatePolicy : function(data) {
			return postRequest("rest/policyservices/update",data);
		}
	};

} ]);