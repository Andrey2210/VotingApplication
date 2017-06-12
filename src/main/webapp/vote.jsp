<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3>Voting</h3>
            <form id="voteForm" name="voteForm" data-voteId="{{vote.id}}" method="post">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <span class="glyphicon glyphicon-arrow-right"></span>{{vote.question}}
                        </h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                            <li ng-if="errorVote != null">{{errorVote}}</li>
                            <li ng-if="errorVote == null" class="list-group-item" ng-repeat="answer in vote.answers">
                                <div class="radio">
                                    <label>
                                        <input id="{{answer.id}}" type="radio" name="answersRadios">
                                        {{answer.text}}
                                    </label>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="panel-footer">
                        <button ng-if="errorVote == null" id="sendAnswerButton" type="button"
                                class="btn btn-primary btn-sm" ng-click="toVote()">
                            Vote
                        </button>
                        <a href="#!/votings/{{vote.id}}/result">View Result</a></div>
                </div>
            </form>
        </div>
    </div>
</div>

