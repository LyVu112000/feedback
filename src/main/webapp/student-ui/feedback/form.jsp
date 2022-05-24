<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../navbar.jsp"/>
<div style="background: var(--main-background-color)" class="p-4">
    <div class="p-4 container">
        <form method="post">
            <p class="error">${error}</p>
            <div>
                <b>Student name: ${user.fullName}</b>
            </div>
            <div class="form-group">
                <label for="content">Feedback content: </label>
                <textarea class="form-control" id="content" rows="16" name="content" required></textarea>
            </div>
            <button class="btn btn-primary">Send</button>
        </form>
    </div>
</div>
</body>
</html>
