<%@page 
    contentType="text/html;charset=UTF-8" language="java"
    import="gmall.controller.LoginVerify"
    import="gmall.model.User"
    import="gmall.model.UserHandler"
    import="java.lang.*, java.util.*"
    import="javax.servlet.http.*"
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
    UserHandler handler = new UserHandler();
    Boolean ifLogin = LoginVerify.isLogin(request);
    User user = null;
    if(ifLogin) {
        user = (User) session.getAttribute("user");
    }
%>

<script type='text/javascript'>
    function logout() {
        <%session.setAttribute("isLogin", false);%>
    }
</script>

<html>
    <head>
        <title>home</title>
    </head>
    <link rel="stylesheet" type="text/css" href="css/genyu.css">
    
    <div class="dtop">
        <div class="headright">
            <%
                if(ifLogin!=null && user!=null && ifLogin==true) {
            %>
            <br>
            <%out.print("Welcome, " + user.getUserName());%>
            &nbsp;&nbsp; <a href="<%=basePath %>LogoutServlet"> logout </a>
            <%
                } else {
            %>
            <a href="signup.jsp">signup</a>
            <p></p>
            <a href="login.jsp">login</a>
            <%  } %>
        </div>
        <form role="form" method="post" action="search">
            <table class="searchkuang">
                <tr align="center">
                    <td><input type="text" name="Key" style="position:absolute;top:30%;left:0%;width:90%;height:80%"></td>
                    <td align="center"><input type="submit" value="G" style="position:absolute;top:30%;background:#ff9901;border:#7DC77D;font-size:18;color:black;left:90%;width:10%;height:80%" onclick="javascrtpt:window.location.href='gsearch.jsp'"></td>
                </tr>
            </table>
        </form>
    </div>
</html>