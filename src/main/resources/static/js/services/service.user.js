angular.module('makeTest.services').factory('userService',function($http){
    var service = {
        logout : logout
    }
    return service;

    //url to rest
    function logout() {
        return $http.get(apiRoot+'user/logout');  //-> odavde gadja rest funckiju u UserController
    };
})