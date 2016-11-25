<<<<<<< HEAD
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

=======
angular.module('makeTest.services').factory('userService',function($http,$cookies,$localStorage){
    var service = {
        logout : logout,
        showUser : showUser,
        update : update,
        login : login,
        register : register,
        checkToken : checkToken,
        setUserId: setUserId,
        getUserId: getUserId,
        deleteUserId: deleteUserId
    };
    return service;

    function setUserId(id){
        $localStorage.mttUserId = id;
    }

    function getUserId(){
        if($localStorage.mttUserId){
            return $localStorage.mttUserId;
        }
    }
    function deleteUserId() {
        delete $localStorage.mttUserId;
    }

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
            url: "/api/users",
            data: data,
            headers: {
                'Content-Type': 'application/json'
            }
        });


    };

    function checkToken() {
        return $http.get(apiRoot+'users/sessions/'+ $cookies.get('mtt'));
    };


    function logout() {
        return $http.delete(apiRoot+'users/sessions/'+ $cookies.get('mtt'));
    };

    function showUser() {
        var id = getUserId();
        url = apiRoot+'users/'+id;
        return $http.get(url, {
            headers:{ "mtt" : $cookies.get('mtt')}
        });
    };

    function update(user) {
        return $http({
            method: 'PUT',
            url: "/api/users",
            data: user,
            headers: {
                'Content-Type': 'application/json'
            }
        });
    };

>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
});