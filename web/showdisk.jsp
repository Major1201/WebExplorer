<%@ include file="include/jsp/commons.jsp"%>

<html>
<head>
    <title>Category</title>
    <script src="scripts/base64.js"></script>
    <script type="text/javascript">
        function showDirectory(str) {
            window.location.href = "ShowDirectory?currentDirectory=" + encodeURIComponent(base64encode(utf16to8(str)));
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