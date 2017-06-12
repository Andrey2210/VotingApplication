<h2 class="page-header">Crete new Vote</h2>
<form id="voteForm" name="voteForm" method="post" >
    <label for="question">Your question:</label>
    <input type="text" class="form-control" id="question" name="question" placeholder="Enter question" required="required">
    <div class="form-group">
        <input type="text" name="answer" class="form-control" placeholder="Possible answer" required="required"/>
        <button type="button" class="deleteAnswer btn btn-danger">Delete</button>
    </div >
    <div class="form-group">
        <input type="text" name="answer" class="form-control" placeholder="Possible answer" required="required"/>
        <button type="button"  class="deleteAnswer btn btn-danger" >Delete</button>
    </div>
    <div class="btn-group">
        <button id="crateNewVote" class="btn btn-primary" type="button" ng-click="save()">Create Vote</button>
        <button id="addAnswer" class="btn btn-primary">Add answer</button>
    </div>
</form>
<script src="${contextPath}/resources/js/vote.js" type="text/javascript"></script>

