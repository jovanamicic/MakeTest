/**
 * Created by Jovana Micic on 02-Nov-16.
 */
angular.module('makeTest.controllers').controller('UserController', UserController);

UserController.$inject = ['userService', '$cookieStore','$location','$scope'];

//$location, $routeParams
function UserController(userService, $cookieStore,$location,$scope){
    var vm = this;
    vm.user = {}

    $scope.init = function (){
        var loc = $location.absUrl();
        loc = loc.split('=').pop();
        console.log(loc)
        $cookieStore.put('mtt', loc);
    }

    vm.logout = function () {
        userService.logout().then(function (response) {
            setTimeout(function () {
                window.location.href = "login";
            }, 2000);
        }, function () {
            console.log("Error");
        });
    };

    vm.showUser = function(){
            userService.showUser().then(function (response) {
                vm.data = response.data;
            },function () {
                console.log("Error");
            });
    }

    //Nece biti izmena preko popup-a vec cu dodati input fields i btn save
}