/**
 * Created by Jovana Micic on 08-Nov-16.
 */
angular.module('makeTest.controllers').controller('TestController', TestController);

UserController.$inject = ['testService'];

function TestController(testService) {
    var vm = this;

    vm.save = function () {
        testService.save(vm.data).then(function (response) {
            vm.data = response.data;
            toastr.success("Your test is successfully saved.");
        }, function () {
            console.log("Error");
        });
    };
}