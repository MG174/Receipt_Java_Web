/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(urlPatterns = {"/EditRecord"})
public class EditRecord extends HttpServlet {

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
            out.println("<title>Servlet EditRecord</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRecord at " + request.getContextPath() + "</h1>");
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
        
        try {

            Object receipt_info = request.getParameter("bt");

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();
            
            PreparedStatement statement = con.prepareStatement("SELECT price, description, category, image_recepit FROM receipt WHERE id_receipt = ?");
            statement.setString(1, (String) receipt_info);
            ResultSet rs = statement.executeQuery();
            PrintWriter out = response.getWriter();



            while (rs.next()) {

                String pr = rs.getString(1);
                String dsc = rs.getString(2);
                String cat = rs.getString(3);

//                out.println("<form action=\"./UpdateRecord\" method=\"post\" enctype=\"multipart/form-data\">");
//                out.println("<p>Price:</p> ");
//                out.println("<input type=\"text\" name=\"price\" value="+pr+" />");
//                out.println("<br/>");
//                out.println("<p>Description:</p> ");
//                out.println("<input type=\"text\" name=\"description\" value="+dsc+" />");
//                out.println("<br/>");
//                out.println("<p>Category:</p> ");
//                out.println("<input type=\"text\" name=\"category\" value="+cat+" />");
//                out.println("<br/>");
//                out.println("<p>Image</p> ");
//                out.println("<input type=\"file\" name=\"image\"/>");
//                out.println("<br/>");
//                out.println("<br/><br/><br/>");
//                out.println("<input type=\"hidden\" name=\"bt\" value="+receipt_info+">");
//                out.println("<input type=\"submit\"/>");
//                out.println("</form>");
                
                                out.println("<html>");
                            out.println("<head>");
   
                            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
out.println("        <link href=\"/WebApplication1/css/form-div-style.css\" rel=\"stylesheet\" type=\"text/css\">");
out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
out.println("        <script");
out.println("            src=\"https://code.jquery.com/jquery-2.2.4.min.js\"");
out.println("            integrity=\"sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=\"");
out.println("        crossorigin=\"anonymous\"></script>");
out.println("        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\" integrity=\"sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS\" crossorigin=\"anonymous\"></script>");
out.println("        <title>Add Record</title>");
                            
            out.println("</head>");
out.println("<body>");
                out.println("<div class=\"centered-div-form\">");
out.println("            <h1>Edit Record</h1>");
out.println("<form action=\"./UpdateRecord\" method=\"post\" enctype=\"multipart/form-data\">");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Price</label>");
out.println("    <input type=\"text\" name=\"price\" class=\"form-control\" id=\"exampleInputName2\" value="+pr+" />");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Description</label>");
out.println("    <input type=\"text\" name=\"description\" class=\"form-control\" id=\"exampleInputName2\" value="+dsc+" />");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputName2\">Category</label>");
out.println("    <input type=\"text\" name=\"category\" class=\"form-control\" id=\"exampleInputName2\" value="+cat+" />");
out.println("  </div>");
out.println("  <div class=\"form-group\">");
out.println("    <label for=\"exampleInputFile\">Image input</label>");
out.println("    <input type=\"file\" name=\"image\" id=\"exampleInputFile\">");
out.println("  </div>");
out.println("<input type=\"hidden\" name=\"bt\" value="+receipt_info+">");
out.println("  <button type=\"submit\" class=\"btn btn-primary\">Edit Record</button>");
out.println("</form>");
            out.println("<a href=\"DisplayRecord\"><button class=\"btn btn-default\">Back to records</button></a>");
out.println("        </div>");
                out.println("</body>");
                out.println("</html>");

            }



            // Close all the connections
            statement.close();
            con.close();

            // Get a writer pointer 
            // to display the successful result
            out.println("</br>");
            out.println("</br>");
            out.println("</br>");


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
