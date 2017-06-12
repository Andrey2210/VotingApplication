<div data-ng-controller="VoteResultController" class="panel panel-primary" >
    <div class="panel-heading">
        <h3 class="panel-title">
            {{vote.question}}
        </h3>
    </div>
    <div  class="panel-body"  style="margin:0px; width:115%;">
        <div id="Main" data-ng-init="createPollBar()">
            <div ng-repeat="answer in vote.answers" >
            <i class="poll_bar ">{{answer.text}} ({{answer.votersNumber}}) </i>
            <span class="poll_val ">{{answer.votersNumber * 100 / amount | number : 1}}%</span><br/>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/voteResult.js" type="text/javascript"></script>
