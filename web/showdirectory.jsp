<%@ include file="include/jsp/commons.jsp"%>

<html>
<head>
    <title>Category</title>
</head>
<body>
    <h1>Index of&nbsp;${currentDirectory}</h1>
    <table border="1">
        <tr>
            <th>File Name</th>
            <th>Last Modified</th>
            <th>Size</th>
            <th>Operation</th>
        </tr>
        <tr>
            <td>
                <c:if test="${parentDirectory == ''}"><img src="images/icons/back.gif"/>&nbsp;<a href="ShowDiskAction">...</a></c:if>
                <c:if test="${parentDirectory != ''}"><img src="images/icons/back.gif"/>&nbsp;<a href="ShowDirectory?currentDirectory=${parentDirectory}">...</a></c:if>
            </td>
            <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
        <c:forEach var="file" items="${fileEntities}">
            <tr>
                <td>
                    <c:if test="${file.directory == true}">
                        <img src="images/icons/folder.gif"/>
                        <a href="ShowDirectory?currentDirectory=${currentDirectory}${file.name}">${file.name}</a>
                    </c:if>
                    <c:if test="${file.directory == false}">
                        <img src="images/icons/generic.gif"/>&nbsp;${file.name}
                    </c:if>
                </td>
                <td>${file.lastModified}</td>
                <td style="text-align: center">
                    <c:if test="${file.directory == true}">-</c:if>
                    <c:if test="${file.directory == false}">${file.size}</c:if>
                </td>
                <td>&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>