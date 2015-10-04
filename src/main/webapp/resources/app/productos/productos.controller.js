/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("ProductosController", [
        "$scope",
        ProductosController
    ]);

function ProductosController($scope){
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'descripcion', property: 'descripcion', visible: true, sortable: true, searchable: true},
            {name: 'stock', property: 'stock', visible: true, sortable: true, searchable: true},
            {name: 'precio', property: 'precio', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/resources/productos.json',
        globalSearch: true,
        title: 'Lista de productos',
        detailViewTitle: 'Vista detalle de venta'
    };
}