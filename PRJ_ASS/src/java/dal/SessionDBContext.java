/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Lecture;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author HAICAO
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> listSessionByLecture(Lecture lec, LocalDate startDate, LocalDate endDate) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {

            String sql = "select sessionID, g.gid, room, subjectID, s.slot, [date], [status]  \n"
                    + "		from [Group] g inner join [Session] s on g.gid = s.gid\n"
                    + "					   inner join TimeSlot t on t.slot = s.slot\n"
                    + "where date >= ? and date <= ? and taker = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setDate(1, Date.valueOf(startDate));
            stm.setDate(2, Date.valueOf(endDate));
            stm.setString(3, lec.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setId(rs.getInt("sessionID"));
                s.setDate(rs.getDate("date"));
                s.setRoom(rs.getString("room"));
                s.setStatus(rs.getBoolean("status"));
                Group g = new Group();
                g.setSubject(rs.getString("subjectID"));
                g.setId(rs.getString("gid"));
                s.setGroup(g);
                TimeSlot slot = new TimeSlot();
                slot.setSlot(rs.getInt("slot"));
                s.setSlot(slot);
                sessions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(Session entity) {
        Session s = new Session();
        try {
            String sql = "select sessionID, gid, room, slot, date, taker, status from Session where sessionID = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s.setId(rs.getInt("sessionID"));
                s.setDate(rs.getDate("date"));
                Group group = new Group();
                group.setId(rs.getString("gid"));
                s.setGroup(group);
                TimeSlot time = new TimeSlot();
                time.setSlot(rs.getInt("slot"));
                s.setSlot(time);
                Lecture lecture = new Lecture();
                lecture.setId(rs.getString("taker"));
                s.setTaker(lecture);
                s.setRoom(rs.getString("room"));
                s.setStatus(rs.getBoolean("status"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public void insert(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateStatus(Session entity) {
        try {
            String sql = "UPDATE [dbo].[Session]\n"
                    + "   SET [status] = ?\n"
                    + " WHERE sessionID = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setBoolean(1, entity.isStatus());
            stm.setInt(2, entity.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
