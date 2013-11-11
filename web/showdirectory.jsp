<%@ include file="include/jsp/commons.jsp"%>

<html>
<head>
    <title>Category</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>IsDir</th>
            <th>File Name</th>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <c:if test="${parentDirectory == ''}"><a href="ShowDiskAction">...</a></c:if>
                <c:if test="${parentDirectory != ''}"><a href="ShowDirectory?currentDirectory=${parentDirectory}">...</a></c:if>
            </td>
        </tr>
        <c:forEach var="file" items="${fileEntities}">
            <tr>
                <td>
                    <c:if test="${file.directory == true}">
                        True
                    </c:if>
                    <c:if test="${file.directory == false}">
                        False
                    </c:if>
                </td>
                <td>
                    <a href="ShowDirectory?currentDirectory=${currentDirectory}${file.name}">${file.name}</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>