// /**
//  * Created by Milan on 16/11/2016.
//  */
// var app = angular.module('app.registerform', []);
//
// app.controller('register.controller', ['$scope', '$http', function ($scope, $http) {
//     $scope.login = function () {
//         if ($scope.registerForm.$valid) {
//             $http({
//                 method: 'POST',
//                 url: "/api/users/sessions",
//                 data: {
//                     email: $scope.user.email,
//                     password: $scope.user.password
//                 },
//                 headers: {
//                     'Content-Type': 'application/json'
//                 }
//             }).then(function (response) {
//                 console.log(response.data);
//             }, function (response) {
//                 console.log(response.data);
//             });
//         }
//     };
//
//
// }]);