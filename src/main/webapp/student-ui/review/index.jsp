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
            <div class="d-flex justify-content-between">
                <h3>Online assessment</h3>
                <div>
                    <form method="get" class="d-flex">
                        <input class="form-control" placeholder="Course" name="name" value="${param.name}">
                        <button class="ml-2 btn btn-primary">Find</button>
                    </form>
                </div>
            </div>

            <form method="post">
                <table class="table text-center">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Course</th>
                        <th scope="col" class="text-left">Content</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="subject" items="${subjects}" varStatus="loopStatus">
                        <tr>
                            <th scope="row">${loopStatus.index + 1}</th>
                            <td>${subject.name}</td>
                            <td>
                                <input title="${subject.contentReview}" class="w-100 form-control" name="${subject.code}" value="${subject.contentReview}" placeholder="Online assessment: ${subject.name}" ${subject.isReview ? 'disabled' : ''} />
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <div class="d-flex justify-content-center">
                    <button class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
