var app = angular.module('app.loginform', []);

app.controller('login.controller', ['$scope', '$http', function ($scope, $http) {
    $scope.login = function () {
        if ($scope.loginForm.$valid) {
            $http({
                method: 'POST',
                url: "/api/users/sessions",
                data: {
                    email: $scope.user.email,
                    password: $scope.user.password
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(function (response) {
                console.log(response.data);
            }, function (response) {
                console.log(response.data);
            });
        }
    };


    // $.ajax({
    //     method: "POST",
    //     data: loginJSON(email, psw),
    //     contentType: "application/json",
    //     url: "/api/users/sessions",
    // }).then(function successCallback(res, status, xhr) {
    //     var url = xhr.getResponseHeader("Location");
    //     var token = url.split('/').pop();
    //     setTimeout(function () {
    //         window.location.href = "api/users/sessions/"+token;
    //     }, 2000);
    // }, function errorCallback(xhr, e) {
    //     console.log(xhr.responseText);
    //     toastr.error("Wrong email or password. Please try again.");
    //     $("#pwd").val("");
    // });
}]);