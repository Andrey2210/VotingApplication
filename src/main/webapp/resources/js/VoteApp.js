/* Controllers */
var VoteApp = angular.module('VoteApp', ['ngRoute', "ngResource"]);

/* Config */
VoteApp.config([
    '$routeProvider', '$locationProvider',
    function ($routeProvide, $locationProvider) {
        $routeProvide
            .when('/votings', {
                templateUrl: 'votings.jsp',
                controller: 'VoteController'
            })
            .when('/votings/:voteId', {
                templateUrl: 'vote.jsp',
                controller: 'VoteDetailController'
            })
            .when('/votings/:voteId/result', {
                templateUrl: 'voteResult.jsp',
                controller: 'VoteResultController'
            })
            .otherwise({
                redirectTo: '/votings'
            });
    }
]);

/* Factory */
VoteApp.factory('Vote', [
    '$resource', function ($resource) {
        return $resource('votings/:voteId', {voteId: '@voteId'});
    }
]);

VoteApp.controller('VoteController', [
    '$scope', '$http', '$location', '$routeParams', 'Vote',
    function ($scope, $http, $location, $routeParams, Vote) {
        $scope.votes = Vote.query();

        $scope.createVotePage = function () {
            $scope.template = 'newVote.jsp';
        };

        $scope.save = function () {
            var elem = angular.element(document.querySelector(".vote"));
            var voteElements = document.forms["voteForm"].elements;
            var question = voteElements["question"].value;
            if (!question) return;
            var answers = [];
            for (var index = 0; index < voteElements.length; index++) {
                if (voteElements[index].name == "answer") {
                    if (voteElements[index].value) {
                        answers.push({"text": voteElements[index].value, "votersNumber": 0});
                    }
                }
            }
            if (answers.length == 0) {
                return;
            }
            var voting = {
                "question": '' + question,
                "answers": answers
            };
            $.ajax({
                accept: "application/json",
                contentType: "application/json",
                dataType: "json",
                type: 'POST',
                url: '/votings',
                data: JSON.stringify(voting),
                success: function (data) {
                    elem.html("<div class=\"vote\" ng-include=\"template\">" +
                    "<h1>" + data.message + "</h1></br><p>Link to vote: </p>" +
                        "<a href=" + data.url + ">" + data.url + "</a></div>");
                },
                error: function (data) {
                    var response = JSON.parse(data.responseText);
                    elem.html("<div class=\"vote\" ng-include=\"template\">" +
                        "<h1>" + response.message + "</h1></div>");
                }
            });
            $scope.votes = Vote.query();
        };
    }
]);

VoteApp.controller('VoteDetailController', [
    '$scope', '$http', '$location', '$routeParams', 'Vote',
    function ($scope, $http, $location, $routeParams, Vote) {
        $scope.voteId = $routeParams.voteId;
        Vote.get({voteId: $scope.voteId}, function (data) {
            if(data.status == 204) {
                $scope.errorVote = "You already voted";
            }
                $scope.vote = data;
        });

        $scope.toVote = function () {
            var elem = angular.element(document.querySelector(".vote-message"));
            var voteElements = document.forms["voteForm"].elements;
            var answer;
            for (var index = 0; index < voteElements.length; index++) {
                if (voteElements[index].name == "answersRadios" && voteElements[index].checked) {
                        answer = voteElements[index].id;
                }
            }
            if (!answer) {
                return;
            }
            $.ajax({
                accept: "application/json",
                contentType: "application/json",
                dataType: "json",
                type: 'PUT',
                url: '/answers/' + answer,
                data: {},
                success: function (data) {
                    elem.html("<div class=\"vote\" ng-include=\"template\">" + "<h1>Your vote is accepted.</h1></br>" +
                        "<a href=" + "#!/votings/" + data.id + "/result" + ">" + "View Result" + "</a></div>");
                },
                error: function (data) {
                    elem.html("<div class=\"vote\" ng-include=\"template\">" +
                        "<h1>Error, your vote wasn't accepted, try again later.</h1></div>");
                }
            });
        };
    }
]);


VoteApp.controller('VoteResultController', [
    '$scope', '$rootScope', '$http', '$location', '$routeParams', 'Vote',
    function ($scope, $rootScope, $http, $location, $routeParams, Vote) {
        $scope.amount = 0;
        $scope.voteId = $routeParams.voteId;
        Vote.get({voteId: $scope.voteId}, function (data) {
            $scope.vote = data;
            var answers = $scope.vote.answers;
            $scope.amount = answers.reduce(function (sum, item) {
                return sum + item.votersNumber;
            }, 0);
        });

    }]);