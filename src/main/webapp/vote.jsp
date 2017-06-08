<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Voting App</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body ng-app="VoteApp">
<ng-view></ng-view>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>Voting</h3>
            <form id="voteForm" data-answerId="${answer.id}" action="${contextPath}/votings" method="post" onsubmit="sendAnswerForm()">
                <h4 id="question">$</h4>
                <div>
                    <input type="radio" name="answer" value=""/>
                    <br/>
                </div>
                <button id="sendAnsverButton" type="submit">Vote</button>
            </form>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/angular.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/angular-route.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/vote.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/VoteApp.js" type="text/javascript"></script>
</body>
</html>