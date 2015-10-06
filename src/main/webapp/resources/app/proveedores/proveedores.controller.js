/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ProveedoresController", [
        "$scope",
        "$http",
        ProveedoresController
    ]);

function ProveedoresController($scope, $http) {
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'descripcion', property: 'descripcion', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/resources/proveedores.json',
        globalSearch: true,
        title: 'Lista de proveedores',
        detailViewTitle: 'Vista detalle de venta'
    };


    $scope.proveedor = {
        descripcion: ""
    };

    $scope.confirmarNuevoProveedor = function () {
        var data = {
            descripcion: $scope.proveedor.descripcion
        };
        $http.post('http://localhost:8080/tp3/service/proveedores', data).then(successCallback, errorCallback);

        function successCallback(response) {
            console.log(1);
        }

        function errorCallback(response) {
            console.log(2);
        }
    }

}