

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="./DeleteRecord" method="post">
            <p>Confirm deleting record</p>


            <%
                String idr = request.getParameter("bt");
                
                
                out.println("<form action=\"./DeleteRecord\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"bt\" value="+idr+">");
                out.println("<input type=\"submit\" value=\"Delete Record\"/>");
                out.println("</form>");

                
            %>


            </br></br>

            <a href="displayRecord.jsp"><button type="submit">Back to records</button></a>
    </body>
</html>
