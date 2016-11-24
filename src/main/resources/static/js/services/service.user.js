angular.module('makeTest.services').factory('userService',function($http,$cookieStore){
    var service = {
        logout : logout,
        showUser : showUser,
        update : update
    }
    return service;

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