<html data-ng-app="storeApp">

<link rel="stylesheet" type="text/css" href="resources/static/css/table.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/semantic.min.css">

<script type="text/javascript" src="resources/static/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="resources/static/js/angular.js"></script>
<script type="text/javascript" src="resources/static/js/angular-resource.js"></script>
<script type="text/javascript" src="resources/static/js/angular-route.js"></script>
<script type="text/javascript" src="resources/static/js/semantic.min.js"></script>

<script type="text/javascript" src="resources/app/list-directive/list.directive.js"></script>
<script type="text/javascript" src="resources/app/list-directive/list.services.js"></script>
<script type="text/javascript" src="resources/app/list-directive/list.controller.js"></script>

<script type="text/javascript" src="resources/app/storeApp.js"></script>
<script type="text/javascript" src="resources/app/config/store.config.js"></script>
<script type="text/javascript" src="resources/app/clientes/clientes.controller.js"></script>
<script type="text/javascript" src="resources/app/compras/compras.controller.js"></script>
<script type="text/javascript" src="resources/app/productos/productos.controller.js"></script>
<script type="text/javascript" src="resources/app/proveedores/proveedores.controller.js"></script>
<script type="text/javascript" src="resources/app/ventas/ventas.controller.js"></script>
<script type="text/javascript" src="resources/app/facturas/facturas.controller.js"></script>


<body>

<h2 class="ui header">
    <div class="content">
        Sistema Compra-Venta
    </div>
</h2>
<div class="ui grid">
    <div class="two wide column">
        <div class="ui vertical menu inverted">
            <div class="ui simple dropdown item">
                <a data-ng-href="#/clientes">Clientes</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Cliente</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Cliente/s</a>
                    <a class="item"><i class="settings icon"></i>Editar Cliente</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <a data-ng-href="#/productos">Productos</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Producto</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Producto/s</a>
                    <a class="item"><i class="settings icon"></i>Editar Producto</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <a data-ng-href="#/proveedores">Proveedores</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Proveedor</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Proveedor/es</a>
                    <a class="item"><i class="settings icon"></i>Editar Proveedor</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <a data-ng-href="#/compras">Compras</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Compra</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Compras/s</a>
                    <a class="item"><i class="settings icon"></i>Editar Compras</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <a data-ng-href="#/ventas">Ventas</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Venta</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Venta/s</a>
                    <a class="item"><i class="settings icon"></i>Editar Venta</a>
                </div>
            </div>
            <div class="ui simple dropdown item">
                <a data-ng-href="#/facturas">Facturas</a>
                <i class="dropdown icon"></i>

                <div class="menu inverted">
                    <a class="item"><i class="edit icon"></i>Agregar Venta</a>
                    <a class="item"><i class="globe icon"></i>Eliminar Venta/s</a>
                    <a class="item"><i class="settings icon"></i>Editar Venta</a>
                </div>
            </div>
        </div>
    </div>
    <div class="thirteen wide column">
        <div class="ui segment" data-ng-view=" ">
        </div>
    </div>
</div>

<div class="ui bottom attached label">
    Alexis Ojeda - Saul Zalimben - Nabil Chamas 2015
</div>
</body>
</html>