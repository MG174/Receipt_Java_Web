
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/WebApplication1/css/form-div-style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Object user_info = session.getAttribute("user_info");
        Object result_query = session.getAttribute("result_query");

if (user_info!=null) {
response.sendRedirect(request.getContextPath() + "/DisplayRecord");
} else {
                out.println("<div class=\"centered-div-alert\">");
                out.println("<h2>You are not logged in!</h2>");
                out.println("<a href=\"registerAccount.jsp\"><button class=\"btn btn-primary\" type=\"button\">Register</button></a>");
                out.println("<a href=\"loginAccount.jsp\"><button class=\"btn btn-primary\" type=\"button\">Login in</button></a>");
                out.println("<a href=\"index.html\"><button class=\"btn btn-default\" type=\"button\">Back to start</button></a>");
                out.println("        </div>");
}



        %>
    </body>
</html>
