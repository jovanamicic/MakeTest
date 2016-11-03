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
            setTimeout(function () {
                window.location.href = "login";
            }, 2000);
        }).error(function () {
            //user.errorMessage = "Error";  -> ne znam gde se implementira
            alert("Error");
        });
    }

    user.showUser = function(){
        userService.showUser().success(function (data) {
            $('#userPhoto').attr("src",data.profilePhotoRelativePath);
            $('#userName').text(data.firstName+" "+data.lastName);
            $('#userEmail').text(data.email);
        }).error(function () {
            alert("Error");
        })
    }
}