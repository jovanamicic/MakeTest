/**
 * Created by Milan on 16/11/2016.
 */
var app = angular.module('app.registerform', ['ngCookies']);

app.controller('register.controller', ['$scope', '$http', '$cookies', 'userService', function ($scope, $http, $cookies, userService) {

    $scope.register = function () {
        if ($scope.loginForm.$valid) {
            var data = {
                "email" : $scope.user.email,
                "password" : $scope.user.password
            };
            userService.login(data).then(function (response) {
                var token = response.headers().location.split("/")[6];
                $cookies.put('make-test-token', token);
            }, function (response) {
                // TODO: show failure message
            });
        }
    };

}]);
