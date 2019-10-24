<html>
    <head>
        <title>home</title>
    </head>
    <link rel="stylesheet" type="text/css" href="css/genyu.css">
    <jsp:include flush="true" page="head.jsp"></jsp:include>

    <div class="dleft">
    </div>

    <div class="dright">
        <body text="#0ff">
            <p align="center"><font size ="10">welcome</font></p>
            <br/>
            <p align="center"><font size ="5">
            <form role="form" method="post" action="Login">
                <table>
                    <tr><td>userId</td><td><input type="text" name="username"></td></tr>
                    <tr><td>password</td><td><input type="password" name="password"></td></tr>
                    <tr><td></td><td align="center"><input type="submit" value="submit">
                            <input type="button" value="return" onclick="javascrtpt:window.location.href='index.jsp'"></td><tr>
                </table>
            </form>
            </font>
            </p>
        </body>
    </div>


</html>
