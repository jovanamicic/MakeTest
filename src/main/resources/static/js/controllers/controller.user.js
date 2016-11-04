/**
 * Created by Jovana Micic on 02-Nov-16.
 */
angular.module('makeTest.controllers').controller('UserController', UserController);

UserController.$inject = ['userService', '$cookieStore'];

//$location, $routeParams
function UserController(userService, $cookieStore){
    var vm = this;
    vm.user = {}

    $cookieStore.put('mtt', tokenString); //iz logine responsa location treba da se sacuva cookie -> prebacim login na angular

    vm.logout = function () {
        userService.logout().then(function (response) {
            setTimeout(function () {
                window.location.href = "login";
            }, 2000);
        }, function () {
            //user.errorMessage = "Error";  -> ne znam gde se implementira
            alert("Error");
        });
    };

    vm.showUser = function(){
            userService.showUser().then(function (response) {
                vm.data = response.data;
            },function () {
                alert("Error");
            });
    }

    //Nece biti izmena preko popup-a vec cu dodati input fields i btn save
}