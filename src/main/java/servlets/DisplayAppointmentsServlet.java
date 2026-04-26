package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/displayAppointments")
public class DisplayAppointmentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT a.app_id, p.name AS patient, d.name AS doctor, a.app_date, a.app_time, a.status " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.patient_id " +
                "JOIN doctors d ON a.doctor_id = d.doctor_id");

            out.println("<html><head><title>All Appointments</title></head><body>");
            out.println("<h2>Appointment List</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Patient</th><th>Doctor</th><th>Date</th><th>Time</th><th>Status</th></tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("app_id") + "</td>");
                out.println("<td>" + rs.getString("patient") + "</td>");
                out.println("<td>" + rs.getString("doctor") + "</td>");
                out.println("<td>" + rs.getDate("app_date") + "</td>");
                out.println("<td>" + rs.getTime("app_time") + "</td>");
                out.println("<td>" + rs.getString("status") + "</td>");
                out.println("</tr>");
            }
            out.println("</table><br><a href='index.html'>Back to Home</a>");
            out.println("</body></html>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}