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
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;

/**
 *
 * @author HAICAO
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    public boolean isExist(Attendance entity) {
        boolean isExist = false;
        ArrayList<Attendance> list = list();
        for (Attendance a : list) {
            if (a.getSid().getId() == entity.getSid().getId() && a.getSession().getId() == entity.getSession().getId()) {
                isExist = true;
            }
        }
        return isExist;
    }

    @Override
    public Attendance get(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Attendance entity) {
        try {
            String sql = "INSERT INTO [Attendance]\n"
                    + "           ([sid]\n"
                    + "           ,[sessionID]\n"
                    + "           ,[attend]\n"
                    + "           ,[comment])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setInt(1, entity.getSid().getId());
            stm.setInt(2, entity.getSession().getId());
            stm.setBoolean(3, entity.isAttend());
            stm.setString(4, entity.getComment());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Attendance entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance entity) {
        try {
            String sql = "UPDATE [Attendance]\n"
                    + "   SET [attend] = ?\n"
                    + "      ,[comment] = ?\n"
                    + " WHERE [sid] = ? and [sessionID] = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setBoolean(1, entity.isAttend());
            stm.setString(2, entity.getComment());
            stm.setInt(3, entity.getSid().getId());
            stm.setInt(4, entity.getSession().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Attendance> list() {
        ArrayList<Attendance> list = new ArrayList<>();
        try {

            String sql = "select aid,sid, a.sessionID, date, s.gid, attend \n"
                    + "from Attendance a inner join Session s on s.sessionID = a.sessionID\n"
                    + "order by date asc";
            PreparedStatement stm = conection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student s = new Student();
                Session session = new Session();
                Group g = new Group();
                g.setId(rs.getString("gid"));
                s.setId(rs.getInt("sid"));
                session.setId(rs.getInt("sessionID"));
                session.setGroup(g);
                a.setSid(s);
                a.setSession(session);
                a.setAttend(rs.getBoolean("attend"));
                list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public ArrayList<Attendance> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
