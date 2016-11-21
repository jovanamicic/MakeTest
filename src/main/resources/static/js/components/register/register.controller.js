var app = angular.module('app.registerform', ['ngCookies']);

app.controller('register.controller', ['$scope', '$http', '$cookies', 'userService', function ($scope, $http, $cookies, userService) {

    $scope.showRegisterAlert = false;
    $scope.register = function () {
        if ($scope.showRegisterAlert){
            $scope.showRegisterAlert = false;
        }
        if ($scope.registerForm.$valid) {
            var data = {
                "email" : $scope.user.email,
                "password" : $scope.user.password,
                "firstName" : $scope.user.firstname,
                "lastName" : $scope.user.lastname
            };
            userService.register(data).then(function (response) {
                var redirection = response.headers().location.login.html;
                $scope.showRegisterAlert = true;
            }, function (response) {
                alert("Error");
            });
        }
    };

}]);
