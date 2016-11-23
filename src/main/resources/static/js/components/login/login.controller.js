var app = angular.module('app.loginform', ['ngCookies']);

app.controller('login.controller', ['$scope', '$http', '$cookies', 'userService', '$localStorage', '$location', function ($scope, $http, $cookies, userService, $localStorage, $location) {
    var vm = this;

    vm.showLoginAlert = false;
    vm.login = function () {
        if (vm.showLoginAlert){
            vm.showLoginAlert = false;
        }
        if ($scope.loginForm.$valid) {
            var data = {
                "email" : vm.user.email,
                "password" : vm.user.password
            };
            userService.login(data).then(function (response) {
                var token = response.headers().location.split("/")[6];
                $cookies.put('mtt', token);
                userService.checkToken().then(function (response) {
                    userService.setUserId(response.data.id);
                    $location.path("/profile");
                }, function (response) {

                });
            }, function (response) {
                vm.showLoginAlert = true;
            });
        }
    };


   // /users/5 -> /api/users/5 (header mtt -. )  <-

}]);
