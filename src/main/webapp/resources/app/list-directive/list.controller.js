/**
 * Created by alex on 19/08/15.
 */
angular
    .module('GenericList')
    .controller('ListController', [
        '$scope',
        'ListServices',
        ListController
    ]);

function ListController($scope, ListServices) {

    var params = {
        page: 1
    };
    var sortIconsClass = ["circular inverted sort content icon ascending", "circular inverted sort content icon descending"];
    var sortIconsClassDisabled = ["sort content icon ascending", "sort content icon descending "];

    var selectedColumn = '';


    $scope.dataList = [];
    $scope.meta = {};
    $scope.page = 1;
    $scope.sortIconClass = sortIconsClass[0];
    $scope.sortIconClassDisabled = sortIconsClassDisabled[0];

    ListServices.salesServices($scope.config.URL).get(params, function (serverResponse) {
        $scope.dataList = serverResponse.ventas;
        $scope.dataMeta = serverResponse.meta;


        $scope.applyFilter = function (searchValue, column) {

            if (searchValue != '') {
                params['by_' + column] = searchValue;
            }
            else {
                delete params['by_' + column];
            }
            ListServices.salesServices($scope.config.URL).get(params, function (data) {
                $scope.dataList = data.ventas;
                $scope.dataMeta = data.meta;

            });
        };

        $scope.applySort = function (column) {

            if (selectedColumn == column) {
                $scope.sortIconClass = $scope.sortIconClass == sortIconsClass[0] ? sortIconsClass[1] : sortIconsClass[0];
                params[selectedColumn] = $scope.sortIconClass == sortIconsClass[0] ? 'asc' : 'desc';
            }
            else {
                delete params   [selectedColumn];
                selectedColumn = column;
                params[selectedColumn] = $scope.sortIconClass == sortIconsClass[0] ? 'asc' : 'desc';
            }
            ListServices.salesServices($scope.config.URL).get(params, function (data) {
                $scope.dataList = data.ventas;
                $scope.dataMeta = data.meta;

            });
        };

        $scope.isThisColumnSelected = function (column) {
            return column == selectedColumn;
        };


        //Pagination methods
        $scope.checkNumberUp = function () {

            if ($scope.page != parseInt($scope.dataMeta.total_pages)) {
                return true;
            }

        };

        $scope.checkNumberDown = function () {

            if ($scope.page != 1) {
                return true;
            }

        };
        //Pagination methods

    });

    $scope.goBackPage = function () {

        $scope.page -= 1;
        params.page = $scope.page;
        ListServices.salesServices($scope.config.URL).get(params, function (data) {
            $scope.dataList = data.ventas;
            $scope.dataMeta = data.meta;
        });

    };

    $scope.goNextPage = function () {
        $scope.page += 1;
        params.page = $scope.page;
        ListServices.salesServices($scope.config.URL).get(params, function (data) {
            $scope.dataList = data.ventas;
            $scope.dataMeta = data.meta;

        });

    };

    $scope.showDetailsModal = function (data) {
        $scope.data = data;
        $('#detailModal')
            .modal('show')
        ;
    };


}