package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/deletePatient")
public class DeleteAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int patientId = Integer.parseInt(request.getParameter("patientId"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

            // Delete appointments of that patient first (foreign key)
            PreparedStatement ps1 = con.prepareStatement("DELETE FROM appointments WHERE patient_id = ?");
            ps1.setInt(1, patientId);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement("DELETE FROM patients WHERE patient_id = ?");
            ps2.setInt(1, patientId);
            int i = ps2.executeUpdate();

            if (i > 0) {
                response.sendRedirect("success.html?msg=Patient and their appointments deleted");
            } else {
                out.println("<h3>Delete Failed - Patient not found</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}