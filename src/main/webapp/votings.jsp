<h2 class="page-header">List of Votes</h2>
<div id="votesList" class="votesList">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>
                Questions
            </th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="vote in votes | filter:'ACTIVE'">
            <td "><a href="#!/votings/{{vote.id}}">{{vote.question}}</a></td>
        </tr>
        </tbody>
    </table>
</div>
