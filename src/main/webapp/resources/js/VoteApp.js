var VoteApp = angular.module('VoteApp', ["ngRoute"])
    .config(function($routeProvider){
        $routeProvider.when('/login',
            {
                templateUrl:'resources/login.jsp',
                controller:'LoginController'
            });
        $routeProvider.when('/registration',
            {
                templateUrl:'resources/registration.jsp',
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

