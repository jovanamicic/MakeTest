var app = angular.module('makeTest',
[
    'makeTest.controllers',
    'makeTest.services',
    'makeTest.routes',
    'makeTest.directives',
    'ngCookies',
    'app.loginform',
    'app.registerform',
    'app.profile',
    'ngStorage'

]);

var apiRoot = 'http://localhost:8080/api/';