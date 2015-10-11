/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ClientesController", [
        "$scope",
        "$http",
        ClientesController
    ]);

function ClientesController($scope, $http) {
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: false, sortable: true, searchable: true},
            {name: 'Nombre', property: 'nombre', visible: true, sortable: true, searchable: true},
            {name: 'Cedula', property: 'cedulaIdentidad', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/service/clientes',
        globalSearch: true,
        title: 'Lista de clientes',
        detailViewTitle: 'Vista detalle de venta',
        file: 'clientes.json'
    };


    $scope.cliente = {
        nombre: "",
        cedula: ""
    };

    $scope.confirmarNuevoCliente = function () {
        $('.ui.modal')
            .modal('show')
        ;
        //var data = {
        //    nombre: $scope.cliente.nombre,
        //    cedula: $scope.cliente.cedula
        //};
        //$http.post('http://localhost:8080/tp3/service/clientes', data).then(successCallback, errorCallback);
        //
        //function successCallback(response) {
        //    console.log(1);
        //}
        //
        //function errorCallback(response) {
        //    console.log(2);
        //}
    }
}