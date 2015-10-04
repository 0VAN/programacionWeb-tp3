/**
 * Created by alex on 28/09/15.
 */
angular
    .module("storeApp")
    .config(function storeConfig($routeProvider) {
        $routeProvider
            .when('/ventas', {
                templateUrl: 'resources/app/ventas/ventas.html',
                controller: 'VentasController'
            })
            .when('/clientes', {
                templateUrl: 'resources/app/clientes/clientes.html',
                controller: 'ClientesController'
            })
            .when('/productos', {
                templateUrl: 'resources/app/productos/productos.html',
                controller: 'ProductosController'
            })
            .when('/compras', {
                templateUrl: 'resources/app/compras/compras.html',
                controller: 'ComprasController'
            })
            .when('/home', {
                templateUrl: 'home.html',
                controller: 'HomeController'
            })
            .when('/facturas', {
                templateUrl: 'resources/app/facturas/facturas.html',
                controller: 'FacturasController'
            })
            .when('/proveedores', {
                templateUrl: 'resources/app/proveedores/proveedores.html',
                controller: 'ProveedoresController'
            });
    });

//angular
//    .module('storeApp')
//    .config(function($routeProvider){
//        $routeProvider
//            .when("/home",{
//                controller: "HomeController",
//                controllerAs: "Home",
//                templateUrl: "/templates/home/home.html"
//            })
//            .when("/forecast/list", {
//                controller: "ListController",
//                controllerAs:"List",
//                templateUrl: "/templates/list/list.html"
//            })
//            .when("/forecast/worksheet", {
//                controller: "WorksheetController",
//                controllerAs:"Worksheet",
//                templateUrl: "/templates/worksheet/worksheet.html"
//            })
//            .when("/forecast/worksheet/:id", {
//                controller: "WorksheetEditController",
//                controllerAs:"WorksheetEdit",
//                templateUrl: "/templates/worksheet/worksheet_forecast.html"
//            })
//            .otherwise({
//                redirectTo: "/"
//            });
//    });