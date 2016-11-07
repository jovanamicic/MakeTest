angular.module('makeTest.services').factory('userService',function($http,$cookieStore){
    var service = {
        logout : logout,
        showUser : showUser
    }
    return service;

    function logout() {
        return $http.delete(apiRoot+'users/sessions/'+ $cookieStore.get('mtt'));
    };

    function showUser() {
        url = apiRoot+'users/userProfile';
        return $http.get(url,{
            headers:{ "mtt" : $cookieStore.get('mtt')}
        });
    };

});