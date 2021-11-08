

import bcrypt.BCrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



@WebServlet(urlPatterns = {"/LoginAccount"})
public class LoginAccount extends HttpServlet {

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
            out.println("<title>Servlet LoginAccount</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAccount at " + request.getContextPath() + "</h1>");
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

            Connection con = DatabaseConnection.initializeDatabase();

            PreparedStatement statement = con.prepareStatement("SELECT id_user, password FROM user WHERE email = ?");
            statement.setString(1, request.getParameter("email"));
            ResultSet rs = statement.executeQuery();

            String password_from_database = null;
            String id_user = null;
            while (rs.next()) {
                password_from_database = rs.getString(2);
                id_user = rs.getString(1);
            }

            String password_by_user = request.getParameter("password");
            

            //String generatedSecuredPasswordHash = BCrypt.hashpw(password_from_database, BCrypt.gensalt(12));
            boolean matched = BCrypt.checkpw(password_by_user, password_from_database);

            statement.close();
            con.close();
            
            HttpSession session = request.getSession();
            session.setAttribute("user_info", id_user);

            PrintWriter out = response.getWriter();
            if (matched) {
                out.println("<html>");
                            out.println("<head>");
                out.println("<link href=\"/WebApplication1/css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
            out.println("</head>");
out.println("<body>");


                out.println("<div class=\"index-button-styles\">");
out.println("            <h1 class=\"text-center\">Successfully logged!</h1>");
out.println("            <a class=\"index-a-styles\" href=\"index.html\"><button type=\"button\" class=\"btn btn-primary btn-lg btn-block\">Back to start</button></a>");
out.println("        </div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                session.invalidate();
out.println("<html>");
                            out.println("<head>");
                out.println("<link href=\"/WebApplication1/css/style.css\" rel=\"stylesheet\" type=\"text/css\">");
out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
            out.println("</head>");
out.println("<body>");


                out.println("<div class=\"index-button-styles\">");
out.println("            <h1 class=\"text-center\">Wrong password!</h1>");
out.println("            <a class=\"index-a-styles\" href=\"loginAccount.jsp\"><button type=\"button\" class=\"btn btn-primary btn-lg btn-block\">Try again</button></a>");
out.println("        </div>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
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
