package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.AttendanceDBContext;
import dal.EnrollDBContext;
import dal.SessionDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
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
public class AttendanceController extends BaseRequiredAuthenticationController {

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
        request.getSession().setAttribute("enrolls", enrolls);
        request.getRequestDispatcher("view/attendance.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AttendanceDBContext attendDB = new AttendanceDBContext();
        SessionDBContext sessionDB = new SessionDBContext();
        ArrayList<Enroll> enrolls = (ArrayList<Enroll>) request.getSession().getAttribute("enrolls");
        //add attendace obj to database
        for (Enroll enroll : enrolls) {
            Attendance attendance = new Attendance();
            boolean attend = request.getParameter("check_" + enroll.getStudent().getId()).equals("true");
            String comment = request.getParameter("comment_" + enroll.getStudent().getId());
            attendance.setAttend(attend);
            attendance.setComment(comment);
            attendance.setSid(enroll.getStudent());
            Session s = (Session) request.getSession().getAttribute("session");
            s.setStatus(true);
            sessionDB.updateStatus(s);
            attendance.setSession(s);
            if (!attendDB.isExist(attendance)) {
                attendDB.insert(attendance);
            }
            else attendDB.update(attendance);
        }
        request.getSession().removeAttribute("session");
        request.getSession().removeAttribute("enrolls");
        response.sendRedirect("schedule");
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
