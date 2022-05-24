<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pre-thesis</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        :root {
            --main-background-color: #e9ecef;
            --color-error: red;
        }

        .error {
            color: var(--color-error);
        }
    </style>
</head>
<body>
<ul class="nav">
    <li class="nav-item">
        <a class="nav-link active">Menu</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/assessment" />">Online assessment</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/send-feedback" />">Feedback</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/logout" />">Log out</a>
    </li>
</ul>
</body>
</html>
