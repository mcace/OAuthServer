<%--
  Created by IntelliJ IDEA.
  User: Mc
  Date: 2017/4/17
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="../asset/js/libs/jquery.js"></script>
    <script type="text/javascript">
        function login() {
            var username = $.trim($('#username').val());
            var pwd = $.trim($('#pwd').val());

            var login_url = "sign-in";
            var param = {"username": username, "pwd": pwd};

            $.ajax({
                url: login_url,
                data: param,
                type: "post",
                dataType: 'json',
                async: true,
                success: function (result) {
                    console.log(result);
                }

            })
        }

    </script>
</head>
<body>
<form>
    <table>
        <tr>
            <td>
                用户名
            </td>
            <td>
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>
                密码
            </td>
            <td>
                <input type="password" name="pwd" id="pwd">
            </td>
        </tr>
        <tr>
            <td>

            </td>
            <td>
                <input type="button" value="提交" onclick="login()">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
