var app = angular.module('app.loginform', ['ngCookies']);

app.controller('login.controller', ['$scope', '$http', '$cookies', 'userService', function ($scope, $http, $cookies, userService) {

    $scope.showLoginAlert = false;
    $scope.login = function () {
        if ($scope.showLoginAlert){
            $scope.showLoginAlert = false;
        }
        if ($scope.loginForm.$valid) {
            var data = {
                "email" : $scope.user.email,
                "password" : $scope.user.password
            };
            userService.login(data).then(function (response) {
                var token = response.headers().location.split("/")[6];
                $cookies.put('make-test-token', token);
            }, function (response) {
                $scope.showLoginAlert = true;
            });
        }
    };

}]);
