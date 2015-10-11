
angular
    .module("storeApp")
    .controller("ProductosController", [
        "$scope",
        "$http",
        ProductosController
    ]);

function ProductosController($scope, $http) {
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

    $scope.producto = {};

    $http.get('http://localhost:8080/tp3/service/proveedores/all').then(
        function (proveedores) {
            $scope.proveedores = proveedores.data;
        }
    );
    $scope.confirmarNuevoProducto = function () {
        var data = {
            proveedorId: $scope.producto.proveedorId,
            precio: $scope.producto.precio,
            descripcion: $scope.producto.descripcion
        };
        $http.post('http://localhost:8080/tp3/service/productos', data).then(successCallback, errorCallback);

        function successCallback(response) {
            console.log(1);
        }

        function errorCallback(response) {
            console.log(2);
        }
    }
}