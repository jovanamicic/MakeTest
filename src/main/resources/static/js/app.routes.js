( function(){
    var makeTest = angular.module('makeTest.routes',['ngRoute']);
makeTest.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/homePage.html'
        })
        .when('/myProfile',{
            templateUrl : "html/myProfile.html",
            controller : 'UserController',
            controllerAs : 'user'
        })
        .when('/home',{
            templateUrl : "html/homePage.html"
        })
        .when('/createTest',{
            templateUrl : "html/createTest.html",
            controller : "TestController",
            controllerAs : "test"
        })
        .when('/login',{
            templateUrl : "../js/components/login/login.html",
            controller : "login.controller"

        })
        .when('/register',{
            templateUrl : "../js/components/register/register.html",
            controller : "register.controller"

        })
        .otherwise({
           redirectTo : '/'
        });
}]);
     }) ();