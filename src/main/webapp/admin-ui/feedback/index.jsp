<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pre-thesis</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div style="background: var(--main-background-color)" class="p-4">
    <div class="container p-4" style="background: white">
        <table class="table text-center">
            <thead class="thead-light">
            <tr>
                <th scope="col">No.</th>
                <th scope="col">Name</th>
                <th scope="col">Feedback date</th>
                <th scope="col" class="text-left">Content</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="feedback" items="${feedbacks}" varStatus="loopStatus">
                <tr>
                    <th scope="row">${loopStatus.index + 1}</th>
                    <td>${feedback.username}</td>
                    <td>${feedback.createDate}</td>
                    <td class="text-left">
                        <p>${feedback.content}</p>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
