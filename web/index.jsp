<%--
  User: Minjie
  Date: 13-11-8
  Time: 下午8:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="include/css/jquery-ui.min.css"/>
        <script src="include/js/jquery-1.10.2.min.js"></script>
        <script src="include/js/jquery-ui.min.js"></script>
        <script type="text/javascript">
            $(function() {
                var availableTags = [
                    "admin",
                    "user"
                ];
                $("#userName").autocomplete({
                    source: availableTags
                });
            });
        </script>
    </head>
    <body>
        <div class="ui-widget">
            <form action="ValidateSignature" method="post">
                <table>
                    <tr>
                        <td><label for="userName">UserName: </label></td>
                        <td><input id="userName" name="userName" value="${userName}"/></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password: </label></td>
                        <td><input id="password" name="password" type="password"/></td>
                    </tr>
                    <tr>
                        <td><input id="signIn" type="submit" value="Sign In"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>