/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("FacturasController", [
        "$scope",
        FacturasController
    ]);

function FacturasController($scope) {
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'fecha', property: 'fecha', visible: true, sortable: true, searchable: true},
            {name: 'monto', property: 'monto', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/resources/facturas.json',
        globalSearch: true,
        title: 'Lista de facturas',
        detailViewTitle: 'Vista detalle de venta'
    };
}