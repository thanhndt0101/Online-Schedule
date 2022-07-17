/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AttendanceDBContext;
import dal.EnrollDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Attendance;
import model.Enroll;
import model.Session;

/**
 *
 * @author HAICAO
 */
@WebServlet(name="ViewSessionAttendanceController", urlPatterns={"/view"})
public class ViewSessionAttendanceController extends BaseRequiredAuthenticationController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ViewSessionAttendanceController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewSessionAttendanceController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    SessionDBContext dbSession = new SessionDBContext();
    EnrollDBContext dbEnroll = new EnrollDBContext();
    AttendanceDBContext dbAttend = new AttendanceDBContext();
    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String sid = request.getParameter("sessionID");
        Session s = new Session();
        s.setId(Integer.parseInt(sid));
        Session session = dbSession.get(s);
        ArrayList<Enroll> enrolls = dbEnroll.listEnrollBySession(session);
        ArrayList<Attendance> attends = dbAttend.list();
        for (Enroll enroll : enrolls) {
            for (Attendance attend : attends) {
                if(attend.getSid().getId() == enroll.getStudent().getId()) {
                    enroll.getStudent().getAttends().add(attend);
                }
            }
        }
        request.getSession().setAttribute("session", session);
        request.setAttribute("enrolls", enrolls);
        request.getRequestDispatcher("view/view_sessison_attendance.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Session session = (Session) request.getSession().getAttribute("session");
        response.sendRedirect("attend?sessionID="+session.getId());
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
