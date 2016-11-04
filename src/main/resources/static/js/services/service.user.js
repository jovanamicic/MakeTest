angular.module('makeTest.services').factory('userService',function($http){
    var service = {
        logout : logout,
        showUser : showUser
    }
    return service;

    function logout() {
        return $http.get(apiRoot+'users/logout');  //-> odavde gadja rest funckiju u UserController
    };

    function showUser() {
        return $http.get(apiRoot+'users/userProfile');  //(headers:{mtt : $cookieStore.get('myFavorite')}, url)
    }

});