<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pre-thesis</title>
</head>
<body>
    <jsp:include page="../navbar.jsp"/>
    <div style="background: var(--main-background-color)" class="p-4">
        <div class="container d-flex justify-content-between">
            <h3>Online assessment</h3>
            <div>
                <form method="get">
                    <select class="form-control" name="code">
                        <c:forEach varStatus="statusLoop" var="subject" items="${subjects}">
                            <option value="${subject.code}" ${param.code == subject.code || statusLoop.index == 0 ? 'selected' : ''}>${subject.name}</option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </div>
        <div class="container p-4" style="background: white">
            <table class="table text-center">
                <thead class="thead-light">
                <tr>
                    <th scope="col">No.</th>
                    <th scope="col">Course</th>
                    <th scope="col">Student name</th>
                    <th scope="col">Evaluation date</th>
                    <th scope="col" class="text-left">Content</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="review" items="${reviews}" varStatus="loopStatus">
                    <tr>
                        <th scope="row">${loopStatus.index + 1}</th>
                        <td>${review.subjectName}</td>
                        <td>${review.studentName}</td>
                        <td>${review.createDate}</td>
                        <td>
                            <input title="${review.content}" class="w-100 form-control" value="${review.content}" placeholder="Đánh giá môn: ${subject.name}" disabled />
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</body>
<script>
    const selectDOM = document.querySelector('select')
    const formDOM = document.querySelector('form')
    selectDOM.addEventListener('change', event => {
        formDOM.submit();
    })
</script>
</html>
