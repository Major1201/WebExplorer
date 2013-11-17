<%@ include file="include/jsp/commons.jsp"%>
<html>
<head>
    <title>Show Pictures</title>
</head>
<body>
    <h1>Pictures on&nbsp;${currentDirectory}</h1>
    <c:forEach var="image" items="${links}">
        <img src="${image}"/><br/>
    </c:forEach>
</body>
</html>