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
import model.Group;
import model.Session;
import model.Student;

/**
 *
 * @author HAICAO
 */
public class StudentDBContext extends DBContext<Student> {
    
    public ArrayList<Student> listStudentByGroup(Group group) {
        ArrayList<Student> list = new ArrayList<>();
        try {
            String sql = "select e.gid, sname, code, e.sid from enroll e inner join Student s on e.sid =s.sid where e.gid = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, group.getId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setCode(rs.getString("code"));
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Error when list student by group");
        }
        return list;
    }

    public ArrayList<Student> listStudentBySession(Session session) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "select sname, g.gid, taker, code, date from Student s inner join enroll e on s.sid = e.sid\n"
                    + "						inner join [Group] g on g.gid = e.gid\n"
                    + "						inner join [Session] ss on ss.gid = g.gid\n"
                    + "where g.gid = ? and date = ? and slot = ?";
            PreparedStatement stm = conection.prepareStatement(sql);
            stm.setString(1, session.getGroup().getId());
            stm.setDate(2, session.getDate());
            stm.setInt(3, session.getSlot().getSlot());
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  students;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> list(String identity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
