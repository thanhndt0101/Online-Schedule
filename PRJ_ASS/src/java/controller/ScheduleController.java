/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LectureDBContext;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Account;
import model.Lecture;
import model.Session;
import model.Week;

/**
 *
 * @author HAICAO
 */
public class ScheduleController  extends BaseRequiredAuthenticationController{


    public ArrayList<Week> getWeeksOfYear() {
        ArrayList<Week> weeks = new ArrayList<>();
        LocalDate startDate = LocalDate.parse("03-01-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        for (int i = 0; i < 365; i += 7) {
            LocalDate endDate = startDate.plusDays(6);
            Week week = new Week();
            week.setStartDate(startDate);
            week.setEndDate(endDate);
            weeks.add(week);
            startDate = endDate.plusDays(1);
        }
        return weeks;
    }

    public Week getWeekByDate(ArrayList<Week> weeks, LocalDate date) {
        Week currentWeek = new Week();
        for (Week w : weeks) {
            for (int i = 0; i < 7; i++) {
                if (w.getStartDate().plusDays(i).equals(date)) {
                    currentWeek = w;
                    break;
                }
            }
        }
        return currentWeek;
    }

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
    
    SessionDBContext sessionDB = new SessionDBContext();
    TimeSlotDBContext slotDB = new TimeSlotDBContext();
    LectureDBContext lecDB = new LectureDBContext();
    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Week> weeks = getWeeksOfYear();
        LocalDate currentDate = LocalDate.now();
        Week currentWeek = getWeekByDate(weeks, currentDate);
        Account user = (Account) request.getSession().getAttribute("account");
        Lecture lec = lecDB.getLectureByUsername(user);
        ArrayList<Session> sessions = sessionDB.listSessionByLecture(lec, currentWeek.getStartDate(), currentWeek.getEndDate());
        session.setAttribute("lecture", lec);
        session.setAttribute("weeks", weeks);
        request.setAttribute("slots", slotDB.list());
        request.setAttribute("date", currentDate);
        request.setAttribute("sessions", sessions);
        request.setAttribute("week", currentWeek);
        request.getRequestDispatcher("view/schedule.jsp").forward(request, response);
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
        
        ArrayList<Week> weeks = (ArrayList<Week>)request.getSession().getAttribute("weeks");
        int index = Integer.parseInt(request.getParameter("week_index"));
        Week w = weeks.get(index);
        LocalDate currentDate = w.getStartDate();
        Week currentWeek = getWeekByDate(weeks, currentDate);
        Lecture lec = (Lecture) request.getSession().getAttribute("lecture");
        ArrayList<Session> sessions = sessionDB.listSessionByLecture(lec, currentWeek.getStartDate(), currentWeek.getEndDate());
        //set attributes
        request.setAttribute("sessions", sessions);
        request.setAttribute("slots", slotDB.list());
        request.setAttribute("week", currentWeek);
        request.setAttribute("date", currentDate);
        request.getRequestDispatcher("view/schedule.jsp").forward(request, response);
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
