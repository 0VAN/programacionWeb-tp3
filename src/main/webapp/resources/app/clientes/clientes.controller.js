/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ClientesController", [
        "$scope",
        ClientesController
    ]);

function ClientesController($scope) {
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'Nombre', property: 'nombre', visible: true, sortable: true, searchable: true},
            {name: 'Cedula', property: 'cedulaIdentidad', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/resources/clientes.json',
        globalSearch: true,
        title: 'Lista de clientes',
        detailViewTitle: 'Vista detalle de venta'
    };
}