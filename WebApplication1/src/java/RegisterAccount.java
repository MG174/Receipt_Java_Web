

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import bcrypt.BCrypt;
import java.util.concurrent.TimeUnit;

/**

 */
@WebServlet(urlPatterns = {"/RegisterAccount"})
public class RegisterAccount extends HttpServlet {

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
            out.println("<title>Servlet RegisterAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterAccount at " + request.getContextPath() + "</h1>");
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
            
            
  
            String temp_mail = request.getParameter("email");
            String temp_password = request.getParameter("password");
            
            //byte[] salt = getSalt();

            //String securePassword = get_SHA_384_SecurePassword(temp_password, salt);
            

            
            
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                   .prepareStatement("insert into user (`email`, `password`) values(?, ?)");
  
            
            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
            st.setString(1, temp_mail);
  
            // Same for second parameter
            st.setString(2, generatedSecuredPasswordHash);
  
            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
  
            // Close all the connections
            st.close();
            con.close();
  
            // Get a writer pointer 
            // to display the successful result
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Successfully Inserted"
                        + "</b></body></html>");
            
            
            response.sendRedirect("loginAccount.jsp");
       
        }
        catch (Exception e) {
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
