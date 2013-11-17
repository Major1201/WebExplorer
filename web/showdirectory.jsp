<%@ include file="include/jsp/commons.jsp"%>

<html>
<head>
    <title>Category</title>
    <script src="scripts/base64.js"></script>
    <script src="include/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            if (${showHidden}) {
                $("#showHidden").attr("checked",true);
            }
        });
        function showCurrentDirectory() {
            showDirectoryFull('${currentDirectory}', '${sortBy}', '${ascend}');
        }
        function showDirectory(str) {
            showDirectoryFull(str, "1", "true");
        }
        function showDirectoryFull(str, sortBy, ascend) {
            window.location.href = "ShowDirectory?currentDirectory=" + encodeURIComponent(base64encode(utf16to8(str))) + "&sortBy=" + sortBy +"&ascend=" + ascend + "&showHidden=" + $("#showHidden").is(":checked");
        }
        function downloadFile(str) {
            window.location.href = "Download?file=" + encodeURIComponent(base64encode(utf16to8(str)));
        }
        function showPictures(str) {
            window.location.href = "ShowPictures?currentDirectory=" + encodeURIComponent(base64encode(utf16to8(str)));
        }
    </script>
</head>
<body>
    <h1>Index of&nbsp;${currentDirectory}</h1>
    <label><input type="checkbox" id="showHidden" name="showHidden" onclick="showCurrentDirectory();"/>Show Hidden</label><br/>
    <a href="#" onclick="showPictures('${currentDirectory}');">Show Pictures</a>
    <a href="#" onclick="alert('Sorry')">Upload</a>
    <br/><br/>
    <table border="1">
        <tr>
            <th><a href="#" onclick="showDirectoryFull('${currentDirectory}','1','${sortBy == 1 ? !ascend : true}');">File Name <c:if test="${sortBy == 1}"><img src="images/icons/${ascend ? 'up' : 'down'}.gif" width="15px"/></c:if></a></th>
            <th><a href="#" onclick="showDirectoryFull('${currentDirectory}','2','${sortBy == 2 ? !ascend : true}');">Last Modified <c:if test="${sortBy == 2}"><img src="images/icons/${ascend ? 'up' : 'down'}.gif" width="15px"/></c:if></a></th>
            <th><a href="#" onclick="showDirectoryFull('${currentDirectory}','3','${sortBy == 3 ? !ascend : true}');">Size <c:if test="${sortBy == 3}"><img src="images/icons/${ascend ? 'up' : 'down'}.gif" width="15px"/></c:if></a></th>
            <th>Operation</th>
        </tr>
        <tr>
            <td>
                <c:if test="${parentDirectory == ''}"><img src="images/icons/back.gif"/>&nbsp;<a href="ShowDiskAction">...</a></c:if>
                <c:if test="${parentDirectory != ''}"><img src="images/icons/back.gif"/>&nbsp;<a href="#" onclick="showDirectory('${parentDirectory}');">...</a></c:if>
            </td>
            <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
        </tr>
        <c:forEach var="file" items="${fileEntities}">
            <tr>
                <td>
                    <c:if test="${file.directory == true}">
                        <img src="images/icons/folder.gif"/>
                        <a href="#" onclick="showDirectory('${currentDirectory}${file.name}');">${file.name}</a>
                    </c:if>
                    <c:if test="${file.directory == false}">
                        <img src="images/icons/generic.gif"/>
                        <a href="#" onclick="downloadFile('${currentDirectory}${file.name}');">${file.name}</a>
                    </c:if>
                </td>
                <td>${file.lastModified}</td>
                <td style="text-align: right">
                    <c:if test="${file.directory == true}">-</c:if>
                    <c:if test="${file.directory == false}">${file.size}</c:if>
                </td>
                <td>
                    <c:if test="${file.directory == true}">
                        <a href="#" onclick="alert('Sorry');">Zip</a>
                    </c:if>
                    <c:if test="${file.directory == false}">
                        <a href="#" onclick="alert('Sorry');">Execute</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>