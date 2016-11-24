var app = angular.module('makeTest',
[
    'makeTest.controllers',
    'makeTest.services',
    'makeTest.routes',
    'makeTest.directives',
    'ngCookies'
]);

var apiRoot = 'http://localhost:8080/api/';