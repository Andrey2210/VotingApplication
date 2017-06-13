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
            .when('/users/:userId/votings/', {
                templateUrl: 'userVotings.jsp',
                controller: 'UserController'
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

VoteApp.factory('VoteResult', [
    '$resource', function ($resource) {
        return $resource('votings/:voteId/result', {voteId: '@voteId'});
    }
]);

VoteApp.factory('UserVote', [
    '$resource', function ($resource) {
        return $resource('users/:userId/votings', {userId: '@userId'});
    }
]);

VoteApp.controller('VoteController', [
    '$scope', '$http', '$location', '$routeParams', 'Vote', 'UserVote',
    function ($scope, $http, $location, $routeParams, Vote, UserVote) {
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
        };
    }
]);

VoteApp.controller('VoteDetailController', [
    '$scope', '$http', '$location', '$routeParams', 'Vote',
    function ($scope, $http, $location, $routeParams, Vote) {
        $scope.voteId = $routeParams.voteId;
        Vote.get({voteId: $scope.voteId}, function (data, headersGetter, status) {
            if (status == 203) {
                $scope.errorVote = "You already voted";
            } else if (status == 204) {
                $scope.errorVote = "Vote closed";
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
            var url = $location.path() + "?answerId=" + answer;
            $.ajax({
                accept: "application/json",
                contentType: "application/json",
                dataType: "json",
                type: 'PUT',
                url: url,
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
    '$scope', '$rootScope', '$http', '$location', '$routeParams', 'VoteResult', 'dataService',
    function ($scope, $rootScope, $http, $location, $routeParams, VoteResult, dataService) {

        $scope.voteId = $routeParams.voteId;
        var promiseObj = dataService.getData($routeParams.voteId);
        promiseObj.then(function (value) {
            $scope.vote = value;
            var answers = $scope.vote.answers;
            $scope.amount = answers.reduce(function (sum, item) {
                return sum + item.votersNumber;
            }, 0);
            $scope.vote.answers.forEach(function (answer) {
                var width = answer.votersNumber * 100 / $scope.amount;
                var backgroundColor;
                var borderColor;
                if (width >= 50) {
                    backgroundColor = "#337ab7";
                    borderColor = "#2e6da4";
                }
                if (width < 50) {
                    backgroundColor = "#f0ad4e";
                    borderColor = "#eea236";
                }
                if (width < 10) {
                    backgroundColor = "#d9534f";
                    borderColor = "#d43f3a";
                }
                width = width + "%";
                answer.style = {
                    "width": width,
                    "background-color": backgroundColor,
                    "border-color": borderColor
                };
            })
        });

    }]);

VoteApp.factory('dataService', [
    '$http', '$q', 'VoteResult',
    function ($http, $q, VoteResult) {
        return {
            getData: function (voteId) {
                var deferred = $q.defer();
                VoteResult.get({voteId: voteId}, function (data, headersGetter, status) {
                    deferred.resolve(data);
                });
                return deferred.promise;
            }
        }
    }]);

VoteApp.controller('UserController', [
    '$scope', '$rootScope', '$http', '$location', '$routeParams', 'UserVote',
    function ($scope, $rootScope, $http, $location, $routeParams, UserVote) {
        $scope.userId = $routeParams.userId;
        UserVote.get({userId: $scope.userId}, function (data, headersGetter, status) {
            $scope.votes = data;
        });
        $scope.closeVote = function (id) {
            var elem = angular.element(document.querySelector(".vote"));
            $.ajax({
                accept: "application/json",
                contentType: "application/json",
                dataType: "json",
                type: 'DELETE',
                url: '/votings/' + id,
                data: {},
                success: function (data) {
                    elem.html("<div class=\"vote\" ng-include=\"template\">" +
                        "<h1>" + data.responseText + "</h1></div>");
                },
                error: function (data) {
                    elem.html("<div class=\"vote\" ng-include=\"template\">" +
                        "<h1>" + data.responseText + "</h1></div>");
                }
            });

            $scope.userId = $routeParams.userId;
            UserVote.get({userId: $scope.userId}, function (data, headersGetter, status) {
                $scope.votes = data;
            });
        };


    }]);