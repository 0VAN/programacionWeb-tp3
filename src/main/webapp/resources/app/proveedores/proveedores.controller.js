/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ProveedoresController", [
        "$scope",
        ProveedoresController
    ]);

function ProveedoresController($scope) {
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
}