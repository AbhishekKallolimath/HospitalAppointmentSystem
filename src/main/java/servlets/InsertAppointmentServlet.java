package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/insertAppointment")
public class InsertAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("patientName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String date = request.getParameter("appointmentDate");
        String time = request.getParameter("appointmentTime");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

            // Insert patient (or get existing)
            PreparedStatement psCheck = con.prepareStatement("SELECT patient_id FROM patients WHERE email=?");
            psCheck.setString(1, email);
            ResultSet rs = psCheck.executeQuery();
            int patientId;
            if (rs.next()) {
                patientId = rs.getInt("patient_id");
            } else {
                PreparedStatement psInsert = con.prepareStatement(
                    "INSERT INTO patients(name, email, phone, address) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, name);
                psInsert.setString(2, email);
                psInsert.setString(3, phone);
                psInsert.setString(4, address);
                psInsert.executeUpdate();
                ResultSet keys = psInsert.getGeneratedKeys();
                keys.next();
                patientId = keys.getInt(1);
            }

            // Insert appointment
            PreparedStatement psApp = con.prepareStatement(
                "INSERT INTO appointments(patient_id, doctor_id, app_date, app_time, status) VALUES(?,?,?,?,?)");
            psApp.setInt(1, patientId);
            psApp.setInt(2, doctorId);
            psApp.setDate(3, Date.valueOf(date));
            psApp.setTime(4, Time.valueOf(time + ":00"));
            psApp.setString(5, "SCHEDULED");
            int i = psApp.executeUpdate();

            if (i > 0) {
                response.sendRedirect("success.html?msg=Appointment Booked");
            } else {
                out.println("<h3>Booking Failed</h3>");
            }
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}