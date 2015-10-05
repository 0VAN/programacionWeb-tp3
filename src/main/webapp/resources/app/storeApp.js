/**
 * Created by ALEX on 8/14/2015.
 */

angular
    .module("storeApp", [
        "ngResource",
        "ngRoute",
        "GenericList"]);
//    .controller('listSalesController', listSalesController);
//
//function listSalesController() {
//
//
//    var vm = this;
//    vm.listConfig = {
//        columns: [
//            {name: 'Id', property: 'id', visible: true, sortable: true, searchable: true},
//            {name: 'Cliente Id', property: 'montoTotal', visible: true, sortable: true, searchable: true},
//            {name: 'Nombre del cliente', property: 'nombreCliente', visible: true, sortable: true, searchable: true},
//            {name: 'RUC del cliente', property: 'rucCliente', visible: true, sortable: false, searchable: true},
//            {name: 'Fecha', property: 'fecha', visible: true, sortable: true, searchable: true}
//        ],
//        URL: 'http://localhost:8080/tp3/service/ventas',
//        globalSearch: true,
//        title: 'Lista de ventas',
//        detailViewTitle: 'Vista detalle de venta'
//    };
//}