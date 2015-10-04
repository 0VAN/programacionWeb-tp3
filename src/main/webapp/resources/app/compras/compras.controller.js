/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ComprasController", [
        "$scope",
        ComprasController
    ]);

function ComprasController($scope){
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'proveedorId', property: 'proveedorId', visible: true, sortable: true, searchable: true},
            {name: 'fecha', property: 'fecha', visible: true, sortable: true, searchable: true},
            {name: 'monto', property: 'monto', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/resources/compras.json',
        globalSearch: true,
        title: 'Lista de compras',
        detailViewTitle: 'Vista detalle de venta'
    };
}