var makeTest = angular.module('makeTest.routes',['ngRoute']);
makeTest.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/homePage.html'
        })
        .when('/myProfile',{
            templateUrl : "html/myProfile.html"
        })
        .when('/home',{
            templateUrl : "html/homePage.html"
        })
        .when('/createTest',{
            templateUrl : "html/createTest.html"
        })
        .otherwise({
           redirectTo : '/'
        });
}]);