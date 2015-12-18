//related code for authentication handling
'use strict';

angular.module('cloudLanes.tapeAdministration')

    .controller('tapeAdministrationCtrl', tapeAdministrationController)
    .controller('createTapeLibraryCtrl', createTapeLibraryCtrl)
    .controller('listTapeLibraryCtrl', listTapeLibraryCtrl)
    .controller('createTapeCtrl', createTapeCtrl)
    .controller('listTapesCtrl', listTapesCtrl);


//Inject my dependencies
tapeAdministrationController.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
createTapeLibraryCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
listTapeLibraryCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
createTapeCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];
listTapesCtrl.$inject = [ '$scope', 'httpService', '$location', '$rootScope','$http' ];

function tapeAdministrationController(httpService, $scope,  $location, $rootScope)
{

    $scope.msg = "";

    $scope.init = function() {
        //All initialization code goes here
        $location.path("home/tape-administration/list-tape-library");
    };
    $scope.init();

}
function createTapeLibraryCtrl($scope, httpService, $location, $rootScope, $http)
{

    $scope.msg = "createTapeLibraryCtrl";

    $scope.brands =
    [
    ];

    $scope.models =
        [
        ];

    $scope.driveVendors =
        [
        ];

    $scope.driveModels =
        [
        ];

    $scope.mediaTypes =
        [
        ];

    $scope.vaultsList =
        [
        ];

    $scope.vtl =
    {
        libraryName:'',
        brandId:'',
        libraryModelId:'',
        noOfSlots:'',
        noOfEmptySlots:'',
        noOfMaps:'',
        noOfPickers:'',
        noOfDrives:'',
        driveVendorId:'',
        mediaTypeId:'',
        compressionEnabled:'',
        mediaCapacityMB:'',
        vaultId:''
    };

    $scope.clearVTLData = function(){
        $scope.vtl.libraryName = "";
        $scope.vtl.brandId = "";
        $scope.vtl.libraryModelId = "";
        $scope.vtl.noOfSlots = "";
        $scope.vtl.noOfEmptySlots = "";
        $scope.vtl.noOfPickers = "";
        $scope.vtl.noOfMaps = "";
        $scope.vtl.noOfDrives ="";
        $scope.vtl.driveVendorId = "";
        $scope.vtl.compressionEnabled = "";
        $scope.vtl.compressionFactor = 1;
        $scope.vtl.mediaTypeId = "";
        $scope.vtl.mediaCapacityMB = "";
        $scope.vtl.vaultId = "";
    };

    var vtlBrandData = {};

    var getVTLBrands = function(httpService)
    {
        httpService.getVTLBrands().success(function(data,status,headers){

                $scope.brands = data;
                $scope.vtl.brandId = data[0].brandId;
                $scope.brandSelected();
            }

        )
    }
    var getVTLModels = function(httpService)
    {
        httpService.getVTLModels($scope.vtl.brandId).success(function(data,status,headers){

                $scope.models = data;
                $scope.vtl.libraryModelId = data[0].modelId;
            }

        )
    }
    var getVTLMediaTypes = function(httpService)
    {
        httpService.getVTLMediaTypes( $scope.vtl.brandId).success(function(data,status,headers){

                $scope.mediaTypes = data;
                $scope.vtl.mediaTypeId = data[0].typeId;
            }

        )
    }

    var getVTLDriveModels = function(httpService)
    {
        httpService.getVTLDriveModels( $scope.vtl.brandId).success(function(data,status,headers){

                $scope.driveModels = data;
                $scope.vtl.driveModelId = data[0].modelId;
            }

        )
    }

    var getVTLDriveVendors = function(httpService)
    {
        httpService.getVTLDriveVendors( $scope.vtl.brandId).success(function(data,status,headers){

                $scope.driveVendors = data;
                $scope.vtl.driveVendorId = data[0].vendorId;
            }

        )
    }

    var getVaultsList = function(httpService)
    {
        httpService.getVaultList().success(function(data,status,headers){

                $scope.vaultsList = data;
            }

        )
    }

    $scope.init = function()
    {
        $scope.clearVTLData();
        getVTLBrands(httpService);
    };

    $scope.init();

    $scope.createVTL = function(isValid)
    {
        if(isValid){

            httpService.createVTL($scope.vtl).success(function(data, status, headers) {
                $scope.clearVTLData();
                $location.path("home/tape-administration/list-tape-library");
            }).error(function(data, status, headers, config) {
                alert(data);
            });
        }

    }

    $scope.brandSelected = function()
    {
        vtlBrandData = {'brand': $scope.vtl.brand }
        getVTLModels(httpService);
         getVTLDriveModels(httpService);
         getVTLDriveVendors(httpService)
         getVTLMediaTypes(httpService);
         getVaultsList(httpService);
    }


}
function listTapeLibraryCtrl(  $scope, httpService, $location, $rootScope,$http  )
{

    $scope.msg = "listTapeLibraryCtrl";

    $scope.rowCollection = [];

    $scope.VTLConfigs = [{ip: ''}];

    var getVTLList = function()
    {
        httpService.getVTLList().success(function(data, status, headers) {
            $scope.rowCollection = data;
        }).error(function(data, status, headers, config) {
            alert(data);
        });
    }

    $scope.init = function() {
        //All initialization code goes here
        getVTLList();
    };

    $scope.export = function(row)
    {
        $scope.VTLConfigs = [{ip: ''}];
        $("#publishModal").modal();
    }

    $scope.addConfig = function()
    {
        $scope.VTLConfigs.push({'ip':''});
    }

    $scope.removeConfig = function()
    {
        var lastItem = $scope.VTLConfigs.length-1;
        $scope.VTLConfigs.splice(lastItem);
    }

    $scope.init();

}
function createTapeCtrl( $scope, httpService, $location, $rootScope,$http )
{

    $scope.msg = "createTapeCtrl";

    $scope.tape = {
        libraryName: '' ,
        mediaTypeId:'',
        mediaCapacityMB:500,
        noOfTapes:''
    };

    $scope.brands = [];
    $scope.mediaTypes = [];

    var clearTapeData = function()
    {
        $scope.tape.libraryName = '';
        $scope.tape.mediaTypeId = '';
        $scope.tape.mediaCapacityMB = 500;
        $scope.tape.noOfTapes = '';
    }

    var getVTLList = function()
    {
        httpService.getVTLList().success(function(data, status, headers) {
            $scope.brands = data;
            $scope.tape.libraryName = data[0].libraryName;
            $scope.brandSelected();
        }).error(function(data, status, headers, config) {
            alert(data);
        });
    }

    var getVTLMediaTypes = function()
    {
        httpService.getTapeVTLMediaTypes( $scope.tape.libraryName).success(function(data,status,headers){

                $scope.mediaTypes = data;
                $scope.tape.mediaTypeId = data[0].typeId;
            }

        )
    }

    $scope.brandSelected = function(isValid)
    {
        getVTLMediaTypes();
    }

    $scope.init = function() {
        //All initialization code goes here
        getVTLList();
    };

    $scope.init();

    $scope.createTape = function()
    {
        httpService.createTape($scope.tape).success(function(data, status, headers) {
            clearTapeData();
            $location.path("home/tape-administration/list-tapes");
        }).error(function(data, status, headers, config) {
            alert(data);
        });
    }


}
function listTapesCtrl( $scope, httpService, $location, $rootScope,$http )
{


    $scope.msg = "listTapesCtrl";

    $scope.removeAllTapes = false;

    $scope.tape = {
        libraryName: '' ,
    };

    $scope.brands = [];

    $scope.rowCollection = [];

    var deleteTapes = [];

    var removeByAttributes = function(arr, attr, value){//TODO Move to Factory
        var index =hasAttributes(arr, attr, value)
        arr.splice(index,1);
        return arr;
    }

    var hasAttributes = function(arr, attr, value)
    {
        var returnIndex = -1;
        var i = arr.length;
        while(i--){
            if( arr[i]
                && arr[i].hasOwnProperty(attr)
                && (arguments.length > 2 && arr[i][attr] === value ) ){

                returnIndex = i;
                break;

            }
        }

        return returnIndex;
    }

    $scope.deleteAllTapes = function()
    {
        toggleDeleteTape();

        if($scope.removeAllTapes)
        {
            angular.copy($scope.rowCollection,deleteTapes);
        }else
        {
            deleteTapes =  [];
        }

    }

    var toggleDeleteTape = function()
    {
        angular.forEach( $scope.rowCollection , function(row, key)
        {
            row.toDelete = $scope.removeAllTapes;
        });
    }



    $scope.deleteTape  = function(index)
    {
        $scope.removeAllTapes = false;

        var row = $scope.rowCollection[index];

            if( hasAttributes(deleteTapes,'barcode',row.barcode) != -1)
            {
                deleteTapes = removeByAttributes(deleteTapes,'barcode', row.barcode);
            }else
            {
                deleteTapes.push(row);
            }
    }

    var getTapeList = function()
    {
        httpService.getTapeList($scope.tape.libraryName).success(function(data, status, headers) {
            $scope.rowCollection = data;
        }).error(function(data, status, headers, config) {
            alert(data);
        });
    }


    var getVTLList = function()
    {
        httpService.getVTLList().success(function(data, status, headers) {
            $scope.brands = data;
            $scope.tape.libraryName =  $scope.brands[0].libraryName;
            $scope.brandSelected();
        }).error(function(data, status, headers, config) {
            alert(data);
        });
    }

    $scope.brandSelected = function()
    {
        getTapeList();
    }

    $scope.init = function() {
        getVTLList();
        //All initialization code goes here
    };

    $scope.init();

    }





