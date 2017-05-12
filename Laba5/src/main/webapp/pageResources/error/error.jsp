<%--
  Created by IntelliJ IDEA.
  User: Misha Ro
  Date: 27.02.2017
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error page</title>
    </head>
    <body>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name or type: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.errorData.throwable}
        <br/>
        Error message: ${errorMessage}
        <br/>
        <a href="controller?command=index">To index.jsp</a>
    </body>
</html>
