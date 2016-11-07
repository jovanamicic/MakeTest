/**
 * Created by Jovana Micic on 02-Nov-16.
 */
angular.module('makeTest.controllers').controller('UserController', UserController);

UserController.$inject = ['userService', '$cookieStore', '$location', '$scope'];

//$location, $routeParams
function UserController(userService, $cookieStore, $location, $scope) {
    var vm = this;
    vm.user = {};

    $scope.init = function () {
        var loc = $location.absUrl();
        loc = loc.split('=').pop();
        console.log("cookie->"+loc);
        $cookieStore.put('mtt', loc);
    };

    vm.logout = function () {
        userService.logout().then(function (response) {
            setTimeout(function () {
                window.location.href = "login";
            }, 2000);
        }, function () {
            console.log("Error");
        });
    };

    vm.showUser = function () {
        console.log(vm.data);
        console.log($cookieStore.get('mtt'));
        userService.showUser().then(function (response) {
            vm.data = response.data;
        }, function () {
            console.log("Error");
        });
    };

    vm.update = function () {
            userService.update(vm.data).then(function(response){
                vm.data = response.data;
                vm.data.password = "";
                vm.data.repeatPassword = "";
                toastr.success("Your profile is successfully updated.");
            },function () {
                console.log("Error");
            });
    };
}