<<<<<<< HEAD
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
        .when('/register',{
            templateUrl : "../js/components/register/register.html"
            // controller : "components/register/register/controller",
        })
        .when('/login',{
            templateUrl : "login.html"
        })
        .otherwise({
           redirectTo : '/'
        });
}]);
=======
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
        .when('/profile',{
            templateUrl : "../js/components/profile/profile.html",
            controller : "profile.controller"
        })
        .when('/createtest',{
            templateUrl : "../js/components/createTest/createTest.html",
            controller : "createTest.controller"
        })
        .otherwise({
           redirectTo : '/'
        });
}]);
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
     }) ();