var app = angular.module('app.loginform', []);

app.controller('login.controller', ['$scope', function($scope){
    $scope.submit = function(){
        var username = $scope.username;
        var password = $scope.password;
    }
}]);