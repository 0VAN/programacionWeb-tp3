<html data-ng-app="storeApp">

<link rel="stylesheet" type="text/css" href="resources/static/css/table.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/semantic.css">

<script type="application/javascript" src="resources/static/js/jquery-2.1.4.min.js"></script>
<script type="application/javascript" src="resources/static/js/angular.js"></script>
<script type="application/javascript" src="resources/static/js/angular-resource.js"></script>
<script type="application/javascript" src="resources/static/js/angular-route.js"></script>
<script type="application/javascript" src="resources/static/js/semantic.js"></script>

<script type="application/javascript" src="resources/app/list-directive/list.directive.js"></script>
<script type="application/javascript" src="resources/app/list-directive/list.services.js"></script>
<script type="application/javascript" src="resources/app/list-directive/list.controller.js"></script>

<script type="application/javascript" src="resources/app/storeApp.js"></script>


<body data-ng-controller="listSalesController as listCtrl">

<list-directive data-config="listCtrl.listConfig"></list-directive>

</body>
</html>