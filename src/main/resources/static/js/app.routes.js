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
     }) ();