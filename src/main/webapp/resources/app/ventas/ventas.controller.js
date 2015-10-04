/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .controller("VentasController", [
        "$scope",
        "$http",
        VentasController
    ]);

function VentasController($scope, $http) {
    $scope.var = {
        columns: [
            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
            {name: 'Cliente Id', property: "cliente.nombre", visible: true, sortable: true, searchable: true},
            {name: 'Fecha', property: 'fecha', visible: true, sortable: true, searchable: true},
            {name: 'Factura Id', property: 'factura.id', visible: true, sortable: false, searchable: true},
            {name: 'Monto', property: 'monto', visible: true, sortable: true, searchable: true}
        ],
        URL: 'http://localhost:8080/tp3/service/ventas',
        globalSearch: true,
        title: 'Lista de ventas',
        detailViewTitle: 'Vista detalle de venta'
    };

    $scope.createVentas = function () {
        var data = {
            clienteId: 1,
            fecha: "25/25/25",
            detalles: [
                {
                    productoId: 1,
                    cantidad: 2
                }, {
                    productoId: 2,
                    cantidad: 2
                }
            ]
        };
        $http.post('http://localhost:8080/tp3/service/ventas', data).then(successCallback, errorCallback);

        function successCallback(response) {
            console.log(1);
        }

        function errorCallback(response) {
            console.log(2);
        }
    }

}