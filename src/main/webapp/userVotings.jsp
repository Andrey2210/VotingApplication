<h2 class="page-header">Your Votes</h2>
<div id="votesList" class="votesList">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>
                Questions
            </th>
            <th>
            State
        </th>
            <th>
                Result
            </th>
            <th>
               Close Vote
            </th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="vote in votes">
            <td><a href="#!/votings/{{vote.id}}">{{vote.question}}</a></td>
            <td>{{vote.state}}</td>
            <td><a href="#!/votings/{{vote.id}}/result">Show</a></td>
            <td ><a href="#" ng-click="closeVote(vote.id)">Close</a></td>
        </tr>
        </tbody>
    </table>
</div>