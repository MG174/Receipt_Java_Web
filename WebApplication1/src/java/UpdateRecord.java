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
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author goryc
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(urlPatterns = {"/UpdateRecord"})
public class UpdateRecord extends HttpServlet {

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
            out.println("<title>Servlet UpdateRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRecord at " + request.getContextPath() + "</h1>");
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

        try {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            InputStream inputStream = null;
            InputStream inputStreamtest = null;
            Part filePart = request.getPart("image");
            inputStreamtest = filePart.getInputStream();
            inputStream = filePart.getInputStream();
            
            int bytesRead = inputStreamtest.read(new byte[1]);

            String temp_price = request.getParameter("price");
            String temp_descpription = request.getParameter("description");
            String temp_category = request.getParameter("category");
            Object receipt_info = request.getParameter("bt");
            PreparedStatement st = null;

            
            if (bytesRead!=-1) {
                st = con.prepareStatement("update receipt set `price`=?, `description`=?, `category`=?, `image_recepit`=? where id_receipt=?");
                st.setString(1, temp_price);
                st.setString(2, temp_descpription);
                st.setString(3, temp_category);
                st.setBlob(4, inputStream);
                st.setString(5, (String) receipt_info);
            } else {
                st = con.prepareStatement("update receipt set `price`=?, `description`=?, `category`=? where id_receipt=?");
                st.setString(1, temp_price);
                st.setString(2, temp_descpription);
                st.setString(3, temp_category);
                st.setString(4, (String) receipt_info);

            }

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer 
            // to display the successful result
            PrintWriter out = response.getWriter();
                out.println("<html>");
                            out.println("<head>");
                out.println("<link href=\"/WebApplication1/css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
            out.println("</head>");
out.println("<body>");


                out.println("<div class=\"index-button-styles\">");
out.println("            <h1 class=\"text-center\">Successfully updated record!</h1>");
out.println("            <a class=\"index-a-styles\" href=\"DisplayRecord\"><button type=\"button\" class=\"btn btn-primary btn-lg btn-block\">Back to records</button></a>");
out.println("        </div>");
                out.println("</body>");
                out.println("</html>");

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
