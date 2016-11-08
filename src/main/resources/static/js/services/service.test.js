/**
 * Created by Jovana Micic on 08-Nov-16.
 */
angular.module('makeTest.services').factory('testService',function ($http, $cookieStore) {
   var service = {
       save : save

   };
   return service;

    function save(data) {
        return $http.post(apiRoot+'tests/',data,{
            headers:{ "mtt" : $cookieStore.get('mtt')}
        });
    };
});