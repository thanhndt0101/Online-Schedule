/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enroll;
import model.Group;
import model.Session;
import model.Student;

/**
 *
 * @author HAICAO
 */
public class EnrollDBContext extends DBContext<Enroll> {

    public ArrayList<Enroll> listEnrollBySession(Session session) {
        ArrayList<Enroll> enrolls = new ArrayList<>();
        try {
            String sql = "select s.sid, sname, g.gid, taker, code, date from Student s inner join enroll e on s.sid = e.sid\n"
                    + "						inner join [Group] g on g.gid = e.gid\n"
                    + "						inner join [Session] ss on ss.gid = g.gid\n"
                    + "where g.gid = ? and date = ? and slot = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, session.getGroup().getId());
            stm.setDate(2, session.getDate());
            stm.setInt(3, session.getSlot().getSlot());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Enroll enroll = new Enroll();
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setCode(rs.getString("code"));
                Group g = new Group();
                g.setId(rs.getString("gid"));
                g.setLecture(rs.getString("taker"));
                enroll.setGroup(g);
                enroll.setStudent(s);
                enrolls.add(enroll);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enrolls;
    }

    @Override
    public ArrayList<Enroll> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Enroll> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Enroll get(Enroll entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Enroll entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Enroll entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Enroll entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
