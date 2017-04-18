<%--
  Created by IntelliJ IDEA.
  User: Mc
  Date: 2017/4/17
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证授权界面</title>
    <script src="../asset/js/libs/jquery.js"></script>
    <script>
        (function () {
            var url = "../account/user-info"
            var userInfo;
            $.ajax({
                url: url,
                type: 'get',
                success: function (result) {
                    if (result.code = 200) {
                        userInfo = result.data;
                        $('#username').text(userInfo.username);
                        $('#nickname').text(userInfo.nickname);
                    } else {

                    }
                }
            });
        })();

        function auth() {
            var locale = window.location.search;
            locale = locale.substring(1);//去掉问号
            var params = locale.split('&');

            var authParam = {'update_auth':'1'};

            params.forEach(function (param) {
                var kv = param.split('=');
                if (kv.length > 1) {
                    authParam[kv[0]] = kv[1];
                }
            });

            var url = "authorize"
            var userInfo;
            $.ajax({
                url: url,
                type: 'post',
                data: authParam
            });

            console.log(authParam);
        }
    </script>
</head>
<body>
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <td style="width: 300px;">
                        <div>
                            <span id="username"></span>
                            <br>
                            <span id="nickname"></span>
                            <br>
                            <span>请确认授权<br></span>
                            <span><input type="button" onclick="auth()" value="授权！！！"></span>
                        </div>
                    </td>
                    <td style="width: 300px;">
                        <div>
                            <span>
                                应用名：${requestScope.app_name}
                            </span>
                            <br>
                            <span>
                                授权范围：${requestScope.scope}
                            </span>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
