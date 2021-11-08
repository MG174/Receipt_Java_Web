
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/WebApplication1/css/form-div-style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script
            src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
        <title>Add Record</title>
    </head>
    <body>
        <%
            
            Object user_info = session.getAttribute("user_info");
            
            if (user_info!=null) {

out.println("<div class=\"centered-div-form\">");
out.println("            <h1>Add Record</h1>");
out.println("<form action=\"./AddRecord\" method=\"post\" enctype=\"multipart/form-data\">");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Price</label>");
out.println("    <input type=\"text\" name=\"price\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"14.99\">");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Description</label>");
out.println("    <input type=\"text\" name=\"description\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"description\">");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Category</label>");
out.println("    <input type=\"text\" name=\"category\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"category\">");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputFile\">Image input</label>");
out.println("    <input type=\"file\" name=\"image\" id=\"exampleInputFile\">");
out.println("  </div>");
out.println("  <button type=\"submit\" class=\"btn btn-default\">Add Record</button>");
out.println("                <div class=\"form-group\">");
out.println("                </br>");
out.println("                        <a href=\"index.html\"><button type=\"button\" class=\"btn btn-default\">Back to start</button></a>");
out.println("                </div>");
out.println("</form>");
out.println("        </div>");
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
