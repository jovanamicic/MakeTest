/**
 * Created by Jovana Micic on 02-Nov-16.
 */
angular.module('makeTest.controllers').controller('UserController', UserController);

//Cemu ovo sluzi?
UserController.$inject = ['$location', '$routeParams','userService'];

function UserController($location, $routeParams, userService){
    var user = this;

    user.logout = function () {
        userService.logout().success(function (data) {  //-> poziva funkciju iz service.user.js
            alert("Success");
        }).error(function () {
            //user.errorMessage = "Error";  -> ne znam gde se implementira
            alert("Error");
        });
    }
}