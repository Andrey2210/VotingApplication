var VoteApp = angular.module('VoteApp', ["ngRoute"])
    .config(function($routeProvider){
        $routeProvider.when('/login',
            {
                templateUrl:'login.jsp',
                controller:'LoginController'
            });
        $routeProvider.when('/registration',
            {
                templateUrl:'registration.jsp',
                controller:'RegistrationController'
            });
        $routeProvider.otherwise({redirectTo: '/votings'});
    });

VoteApp.controller('LoginController',
    function AnswerController($scope, $http){
        // $scope.response={};
        // $scope.save = function (answer, answerForm){
        //     if(answerForm.$valid){
        //
        //         $http.post("postAnswer.php", answer).then(function success(response) {
        //             $scope.response=response.data;
        //         });
        //     }
        // };
    }
);

VoteApp.controller('RegistrationController',
    function AnswerController($scope, $http){
        // $scope.response={};
        // $scope.save = function (answer, answerForm){
        //     if(answerForm.$valid){
        //
        //         $http.post("postAnswer.php", answer).then(function success(response) {
        //             $scope.response=response.data;
        //         });
        //     }
        // };
    }
);