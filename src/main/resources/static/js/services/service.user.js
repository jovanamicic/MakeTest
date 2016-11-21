angular.module('makeTest.services').factory('userService',function($http,$cookieStore){
    var service = {
        logout : logout,
        showUser : showUser,
        update : update,
        login : login,
        register : register
    };
    return service;

    function login(data) {
        return $http({
            method: 'POST',
            url: "/api/users/sessions",
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        });


    };
    function register(data) {
        return $http({
            method: 'POST',
            url: "/api/users/sessions",
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        });


    };
    function logout() {
        return $http.delete(apiRoot+'users/sessions/'+ $cookieStore.get('mtt'));
    };

    function showUser() {
        url = apiRoot+'users/userProfile';
        return $http.get(url, {
            headers:{ "mtt" : $cookieStore.get('mtt')}
        });
    };

    function update(user) {
      url = apiRoot+'users';
        return $http.put(url, user , {
            headers:{ "mtt" : $cookieStore.get('mtt')}
        });
    };

});