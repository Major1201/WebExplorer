<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Category</title>
    <script src="scripts/base64.js"></script>
    <script type="text/javascript">
        function showDirectory(str) {
            window.location.href = "ShowDirectory?currentDirectory=" + base64encode(str);
        }
    </script>
</head>
<body>
    <table border="1">
        <tr>
            <th>Roots</th>
        </tr>
        <c:forEach var="root" items="${rootDirectories}">
            <tr>
                <td><a href="#" onclick="showDirectory('${root.name}')">${root.name}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>