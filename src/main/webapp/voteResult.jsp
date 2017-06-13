<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">
            {{vote.question}}
        </h3>
    </div>
    <div class="panel-body">
        <div id="Main" data-ng-init="createPollBar()">
            <div class="answer-group" ng-repeat="answer in vote.answers">
                <i ng-style="answer.style" class="poll_bar btn btn-default">{{answer.text}} ({{answer.votersNumber}})
                </i>
                <span class="poll_val ">{{answer.votersNumber * 100 / amount | number : 1}}%</span>
            </div>
        </div>
    </div>
</div>
