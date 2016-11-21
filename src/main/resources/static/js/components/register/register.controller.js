var app = angular.module('app.registerform', ['ngCookies']);

app.controller('register.controller', ['$scope', '$http', '$cookies', 'userService', function ($scope, $http, $cookies, userService) {

    $scope.showRegisterAlert = false;
    $scope.showPasswordAlert = false;
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
                var redirection = response.headers().location.login.html;
                $scope.showRegisterAlert = true;

            }, function (response) {
                alert("Error");
            });

            } else {
                $scope.showPasswordAlert = true;
            }
        }
    };

}]);

