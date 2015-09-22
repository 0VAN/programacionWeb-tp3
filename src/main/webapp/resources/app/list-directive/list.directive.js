/**
 * Created by alex on 19/08/15.
 */
angular
    .module('GenericList',[])
    .directive('listDirective', [
        ListDirective
    ]);

function ListDirective() {
    return {
        templateUrl: 'resources/app/list-directive/list-directive.html',
        restrict: 'E',
        controller: 'ListController',
        scope: {
            config: '='
        }
    };
}