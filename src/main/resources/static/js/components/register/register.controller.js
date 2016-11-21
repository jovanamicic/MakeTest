var app = angular.module('app.registerform', ['ngCookies']);

app.controller('register.controller', ['$location', '$scope', '$http', '$cookies', 'userService', function ($location, $scope, $http, $cookies, userService) {

    $scope.showRegisterAlert = false;
    $scope.showPasswordAlert = false;
    $scope.showMailAlert = false;
    $scope.register = function () {
        if ($scope.showRegisterAlert){
            $scope.showRegisterAlert = false;
        }

        if ($scope.registerForm.$valid) {
            if ($scope.user.password == $scope.user.repeatpassword){
            var data = {
                "email" : $scope.user.email,
                "firstName" : $scope.user.firstname,
                "lastName" : $scope.user.lastname,
                "password" : $scope.user.password
            };

            userService.register(data).then(function (response) {
                $scope.showRegisterAlert = true;
                $location.path("/login");

            }, function (response) {
                $scope.showMailAlert = true;
            });

            } else {
                $scope.showPasswordAlert = true;
            }
        }
    };

}]);

