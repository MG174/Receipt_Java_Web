/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bcrypt.BCrypt;
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



@MultipartConfig(maxFileSize = 16177215)

@WebServlet(urlPatterns = {"/AddRecord"})
public class AddRecord extends HttpServlet {

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
            out.println("<title>Servlet AddRecord</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRecord at " + request.getContextPath() + "</h1>");
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

            HttpSession session = request.getSession();
            Object user_info = session.getAttribute("user_info");
            String user = String.valueOf(user_info);

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();
            
            InputStream inputStream = null;
            Part filePart = request.getPart("image");
            inputStream = filePart.getInputStream();

            String temp_price = request.getParameter("price");
            String temp_descpription = request.getParameter("description");
            String temp_category = request.getParameter("category");

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                    .prepareStatement("insert into receipt (`id_user`,`price`, `description`, `category`, `add_date`,`image_recepit`) values(?,?, ?, ?, NOW(), ?)");

            // For the first parameter,
            // get the data using request object
            st.setString(1, user);
            // sets the data to st pointer
            st.setString(2, temp_price);

            // Same for second parameter
            st.setString(3, temp_descpription);

            st.setString(4, temp_category);
            
            st.setBlob(5, inputStream);

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
            out.println("<body>");
            out.println("<b>Successfully added record</b>");
            out.println("<a href=\"index.html\"><button type=\"submit\">Back to start</button></a>");
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
