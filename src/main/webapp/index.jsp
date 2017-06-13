<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html ng-app="VoteApp">
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

<body ng-controller="VoteController">
<header class="header">
    <div id="navigation_bar" class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                </button>
                <a class="navbar-brand" href="${contextPath}/home">Vote Service</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="${contextPath}/home">Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="isAnonymous()">
                        <li><a href="${contextPath}/login">Sign In /</a></li>
                        <li><a href="${contextPath}/registration">Sign Up</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user" aria-hidden="true"><jsp:text/></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#!/users/${User.id}/votings">My votes</a></li>
                                <li class="divider"><jsp:text/></li>
                                <li><a href="${contextPath}/logout">Logout</a></li>
                            </ul>
                        </li>
                        <button type="button" class="btn btn-default navbar-btn" ng-click="createVotePage()">
                            Create New Vote
                        </button>
                    </sec:authorize>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</header>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <ng-view></ng-view>
        </div>
        <div  class="col-md-6 vote-message">
            <div class="vote" ng-include="template"></div>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/angular.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/angular-route.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.2/angular-resource.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/VoteApp.js" type="text/javascript"></script>
</body>
</html>