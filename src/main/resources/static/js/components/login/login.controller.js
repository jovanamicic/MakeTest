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
                //pozvati iz user service checkToken
                //response bi trebao biti profil korisnika
                // da li ovde sad treba da se napise get api/users/sessions/{token} a to vraca profil korisnika
            }, function (response) {
                $scope.showLoginAlert = true;
            });
        }
    };


    /users/5 -> /api/users/5 (header mtt -. )  <-

}]);
