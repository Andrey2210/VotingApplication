<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Create an account</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
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
                                <i class="fa fa-user-circle" aria-hidden="true"><jsp:text/></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">My votes</a></li>
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
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center signup-title">Sign Up in Vote Service</h1>
            <div class="account-wall">
                <div class="form-group ">
                </div>
                <form:form method="POST" modelAttribute="userForm" class="form-signup">
                    <form:input id="email" type="email" path="email" class="form-control" placeholder="Enter Email"
                                autofocus="true"/>
                    <form:errors path="email"/>
                    <form:input  id="password" type="password" path="password" class="form-control" placeholder="Enter Password"/>
                    <form:errors path="password"/>
                    <input id="confirmPassword" type="password" class="form-control confPassword" placeholder="Confirm your password"/>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
                </form:form>
            </div>
            <a href="${contextPath}/login" class="text-center new-account">Sig In </a>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>