/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;


/**
 *
 * @author goryc
 */
@WebServlet(urlPatterns = {"/DisplayRecordExpandFiltered"})
public class DisplayRecordExpandFiltered extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayRecordExpandFiltered</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayRecordExpandFiltered at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Blob image = null;
        byte[] imgData = null ;

        try {
            // without filter
//            HttpSession session = request.getSession();
//            Object user_info = session.getAttribute("user_info");
//
//            // Initialize the database
//            Connection con = DatabaseConnection.initializeDatabase();
//            
//            PreparedStatement statement = con.prepareStatement("SELECT price, description, category, add_date, id_receipt , image_recepit FROM receipt WHERE id_user = ?");
//            statement.setString(1, (String) user_info);
//            ResultSet rs = statement.executeQuery();
//            PrintWriter out = response.getWriter();

HttpSession session = request.getSession();
            Object user_info = session.getAttribute("user_info");
            String price_from = request.getParameter("price_from");
            String price_to = request.getParameter("price_to");
            

            if (price_from == "") {
            price_from = "0";
            }
            
            if (price_to == "") {
            price_to = "999999999999";
            }
            
            String description = request.getParameter("description");
            String category = request.getParameter("category");
  

            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement statement;

            if (request.getParameter("description") == "" && request.getParameter("category") == "") {
                statement = con.prepareStatement("SELECT price, description, category, add_date, id_receipt , image_recepit FROM receipt WHERE id_user = ? and price>? and price<?");
                statement.setString(1, (String) user_info);
                statement.setString(2, price_from);
                statement.setString(3, price_to);
                System.out.println("first if");
                System.out.println(price_from + " " +price_to);
            } else if (request.getParameter("category") == "") {
                statement = con.prepareStatement("SELECT price, description, category, add_date, id_receipt , image_recepit FROM receipt WHERE id_user = ? and price>? and price<? and description like ?");
                statement.setString(1, (String) user_info);
                statement.setString(2, price_from);
                statement.setString(3, price_to);
                statement.setString(4, description);
                System.out.println("second if");
            } else if (request.getParameter("description") == "") {
                statement = con.prepareStatement("SELECT price, description, category, add_date, id_receipt , image_recepit FROM receipt WHERE id_user = ? and price>? and price<? and category like ?");
                statement.setString(1, (String) user_info);
                statement.setString(2, price_from);
                statement.setString(3, price_to);
                statement.setString(4, category);
                System.out.println("third if");
            } else {
                statement = con.prepareStatement("SELECT price, description, category, add_date, id_receipt , image_recepit FROM receipt WHERE id_user = ? and price>? and price<? and description like ? and category like ?");
                statement.setString(1, (String) user_info);
                statement.setString(2, price_from);
                statement.setString(3, price_to);
                statement.setString(4, description);
                statement.setString(5, category);
                System.out.println("full if");
            }

            System.out.println(price_from + " " +price_to);

            // Initialize the database
            ResultSet rs = statement.executeQuery();
            PrintWriter out = response.getWriter();

            //out.println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /></head><body>");
            out.println("<head>");
//            out.println("<link href=\"/WebApplication1/css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
//            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
//            out.println("<link href=\"/WebApplication1/js/CollapseExpand.js\" rel=\"script\" type=\"text/javascript\">");
//            out.println("<link href=\"/WebApplication1/css/datatables.css\" rel=\"stylesheet\" type=\"text/css\">");
//            out.println("<script src=\"/WebApplication1/js/jquery-3.6.0.js\"></script>");
            out.println("<link href=\"/WebApplication1/css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
            out.println("<link href=\"/WebApplication1/js/CollapseExpand.js\" rel=\"script\" type=\"text/javascript\">");
            out.println("<link href=\"/WebApplication1/css/datatables.css\" rel=\"stylesheet\" type=\"text/css\">");
//            out.println("<script src=\"/WebApplication1/js/jquery-3.6.0.js\"></script>");
            out.println("<script");
            out.println("            src=\"https://code.jquery.com/jquery-2.2.4.min.js\"");
            out.println("            integrity=\"sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=\"");
            out.println("        crossorigin=\"anonymous\"></script>");
            out.println("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>");
            out.println("<script src=\"/WebApplication1/js/imagemodal.js\"></script>");
            out.println("</head>");
            
            out.println("</br>");
            out.println("<a href=\"DisplayRecord\"><button type=\"submit\">Collapse Images</button></a>");
            out.println("</br>");
            
            out.println("<button type=\"button\" class=\"collapsible\">"
                    + "<span class=\"glyphicon glyphicon-filter\" aria-hidden=\"true\"></span> Filter"
                    + "</button>");
            out.println("<div class=\"content\">");
            out.println("</br>");
            out.println("<form class=\"form-inline\" action=\"./DisplayRecordExpandFiltered\" method=\"post\">");
            out.println("  <div class=\"form-group\">");
            out.println("    <label for=\"exampleInputName2\">Price from: </label>");
            out.println("    <input type=\"text\" name=\"price_from\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"15\">");
            out.println("  </div>");
            out.println("  <div class=\"form-group\">");
            out.println("    <label for=\"exampleInputName2\">Price to: </label>");
            out.println("    <input type=\"text\" name=\"price_to\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"15\">");
            out.println("  </div>");
            out.println("  <div class=\"form-group\">");
            out.println("    <label for=\"exampleInputName2\">Description: </label>");
            out.println("    <input type=\"text\" name=\"description\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"apple\">");
            out.println("  </div>");
            out.println("  <div class=\"form-group\">");
            out.println("    <label for=\"exampleInputName2\">Category: </label>");
            out.println("    <input type=\"text\" class=\"form-control\" id=\"exampleInputName2\" placeholder=\"food\">");
            out.println("  </div>");
            out.println("  <button type=\"submit\" name=\"category\" class=\"btn btn-default\">Filter</button>");
            out.println("</form>");
//            out.println("<form action=\"./DisplayRecordFiltered\" method=\"post\">");
//            out.println("<p>Price from:</p> ");
//            out.println("</br>");
//            out.println("<input type=\"text\" name=\"price\"/>");
//            out.println("</br>");
//            out.println("<p>Price to:</p> ");
//            out.println("</br>");
//            out.println("<input type=\"text\" name=\"price\"/>");
//            out.println("</br>");
//            out.println("<p>Descpriction:</p> ");
//            out.println("</br>");
//            out.println("<input type=\"text\" name=\"descpriction\"/>");
//            out.println("</br>");
//            out.println("<p>Category:</p> ");
//            out.println("</br>");
//            out.println("<input type=\"text\" name=\"category\"/>");
//            out.println("</br>");
//            out.println("<input type=\"submit\"/>");
//            out.println("</form>");
            out.println("</div>");
            out.println("<script src=\"/WebApplication1/js/CollapseExpand.js\"></script>");
            
            
            out.println("</br>");
            out.println("</br>");
            out.println("<div class=\"container\">");
            out.println("		<h2>Select Number Of Rows</h2>");
            out.println("				<div class=\"form-group\"> 	<!--		Show Numbers Of Rows 		-->");
            out.println("			 		<select class  =\"form-control\" name=\"state\" id=\"maxRows\">");
            out.println("						 <option value=\"5000\">Show ALL Rows</option>");
            out.println("						 <option value=\"5\">5</option>");
            out.println("						 <option value=\"10\">10</option>");
            out.println("						 <option value=\"15\">15</option>");
            out.println("						 <option value=\"20\">20</option>");
            out.println("						 <option value=\"50\">50</option>");
            out.println("						 <option value=\"70\">70</option>");
            out.println("						 <option value=\"100\">100</option>");
            out.println("						</select>");
            out.println("</div>");
            
            out.println("<table class=\"table table-striped table-class\" id= \"table-id\" border=1 width=80% height=50% text-align: center ");
            out.println("<tr><thead><th>Price</th><th>Descrption</th><th>Category</th><th>Date</th><th>Image</th><th>Edit</th><th>Delete</th></thead></tr>");

            while (rs.next()) {

                String pr = rs.getString(1);
                String dsc = rs.getString(2);
                String cat = rs.getString(3);
                String ad = rs.getString(4);
                String idr = rs.getString(5);
                Blob blob = rs.getBlob(6);

                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                inputStream.close();
                outputStream.close();

                out.println("<tbody><tr><td>" + pr + "</td><td>" + dsc + "</td><td>" + cat + "</td><td>" + ad + "</td><td>");
                
                //This code is used when using DisplayImage Servlet
//                out.println("<form action=\"./DisplayImage\" method=\"post\">");
//                out.println("<input type=\"hidden\" name=\"bt\" value="+idr+">");
//                out.println("<input type=\"submit\" value=\"Display Photo\"/>");
//                out.println("</form>");


                out.println("<img src=\"data:image/jpg;base64," + base64Image + "\" width=\"400\" height=\"300\" />");
                
                out.println("</td><td>");
                
                out.println("<form action=\"./EditRecord\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"bt\" value="+idr+">");
                out.println("<input type=\"submit\" value=\"Edit Record\"/>");
                out.println("</form>");
                
                out.println("</td><td>");
                
//                out.println("<form action=\"./deleteRecord.jsp\" method=\"post\">");
//                out.println("<input type=\"hidden\" name=\"bt\" value="+idr+">");
//                out.println("<input type=\"submit\" value=\"Delete Record\"/>");
//                out.println("</form>");

                out.println("<button class=\"openmodal1 myBtn\">Delete record</button>");
                out.println("");
                out.println("<!-- The Modal -->");
                out.println(" <div class=\"modal1 myModal\">");
                out.println("");
                out.println("  <!-- Modal content -->");
                out.println("  <div class=\"modal-content\">");
                out.println("  <span class=\"close\">&times;</span>");
                out.println("<form action=\"./DeleteRecord\" method=\"post\" class=\"deleteform\">");
                out.println("<p>Are you sure you want to delete this record ?</p>");
                out.println("<input type=\"hidden\" name=\"bt\" value=" + idr + ">");
                out.println("<input type=\"submit\" value=\"Delete Record\"/>");
                out.println("</form>");
                out.println("<button class=\"closemodal myBtn\">Cancel</button>");
                out.println("  </div>");
                out.println("</div>");
                
                out.println("</td></tr>");


            }

            out.println("</tbody></table>");
            
            out.println("<div class='pagination-container' >");
            out.println("				<nav>");
            out.println("				  <ul class=\"pagination\">");
            out.println("            ");
            out.println("            <li data-page=\"prev\" >");
            out.println("								     <span> < <span class=\"sr-only\">(current)</span></span>");
            out.println("								    </li>");
            out.println("				   <!--	Here the JS Function Will Add the Rows -->");
            out.println("        <li data-page=\"next\" id=\"prev\">");
            out.println("								       <span> > <span class=\"sr-only\">(current)</span></span>");
            out.println("								    </li>");
            out.println("				  </ul>");
            out.println("				</nav>");
            out.println("			</div>");
            out.println("</div>");

            // Close all the connections
            statement.close();
            con.close();
            
            out.println("<script type=\"text/javascript\" src=\"/WebApplication1/js/datatables.js\"></script>");
                        out.println("<script src=\"/WebApplication1/js/imagemodal.js\"></script>");

            // Get a writer pointer 
            // to display the successful result
            out.println("</br>");
            out.println("<a href=\"index.html\"><button type=\"submit\">Back to start</button></a>");
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
