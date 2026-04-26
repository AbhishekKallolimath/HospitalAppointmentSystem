package servlets;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/createTable")
public class CreateTableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_db", "root", "root");

            String sql = "CREATE TABLE IF NOT EXISTS feedback (" +
                         "id INT PRIMARY KEY AUTO_INCREMENT, " +
                         "patient_email VARCHAR(100), " +
                         "message TEXT, " +
                         "submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            out.println("<h3>Table 'feedback' created successfully!</h3>");
            out.println("<a href='index.html'>Go Home</a>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}