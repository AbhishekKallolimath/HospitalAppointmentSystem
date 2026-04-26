package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/updateAppointment")
public class UpdateAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int appId = Integer.parseInt(request.getParameter("appointmentId"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE appointments SET status = 'CANCELLED' WHERE app_id = ?");
            ps.setInt(1, appId);
            int i = ps.executeUpdate();

            if (i > 0) {
                response.sendRedirect("success.html?msg=Appointment Cancelled");
            } else {
                out.println("<h3>Update Failed - Appointment not found</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}