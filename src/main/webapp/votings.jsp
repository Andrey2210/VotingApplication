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
<a href="#!login">login</a>
<a href="#!registration">registr</a>
<div class="container">
    <div class="row">
        <div class="col-md-5">
            <button id="addVote">Add Vote</button>
            <span>${message}</span>
            <div id="votesList" class="votesList">
            <c:forEach var="vote" items="${Votings}">
                <span>${vote.question}</span>
                <a href="${contextPath}/votings/${vote.id}">Vote</a> <br/>
            </c:forEach>
            </div>
        </div>
        <div  class="col-md-7 ">
            <h3>Crete new Vote</h3>
            <form id="createVote" action="${contextPath}/votings" method="post" onsubmit="sendVoteForm()">
            <label>Вопрос</label>
            <input id="question" type="text" name="question" placeholder="Введите вопросс"/> <br/>
            <div>
                <input type="text" name="answer" placeholder="Вариант ответа"/>
                <button class="deleteAnswer">удалить</button>
                <br/>
            </div>
            <div>
                <input type="text" name="answer" placeholder="Вариант ответа"/>
                <button class="deleteAnswer">удалить</button>
                <br/>
            </div>
            <button id="crateNewVote" type="submit">Создать голосование</button>
            </form>
            <button id="addAnswer">Добавить ответ</button>
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