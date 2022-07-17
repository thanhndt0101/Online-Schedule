/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author HAICAO
 */
public class Student {
    private int id;
    private String name, code;
    private boolean gender;
    private Date dob;
    private ArrayList<Enroll> enrolls = new ArrayList<>();
    private ArrayList<Attendance> attends = new ArrayList<>();
    
    public boolean isAttend(Session s) {
        for(Attendance a: attends) {
            if(a.getSession().getId() == s.getId()) {
                if(a.isAttend()) return true;
            }
        }
        return false;
    }
    
    public String getComment(Session s) {
        String comment= "";
        for(Attendance a: attends) {
            if(a.getSession().getId() == s.getId()) {
                comment = a.getComment();
            }
        }
        return comment;
    }
    public ArrayList<Attendance> getAttends() {
        return attends;
    }

    public void setAttends(ArrayList<Attendance> attends) {
        this.attends = attends;
    }
    
    public ArrayList<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(ArrayList<Enroll> enrolls) {
        this.enrolls = enrolls;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public int getAbsent() {
        int count=0;
        for(Attendance a : attends) {
            if(!a.isAttend()) count++;
        }
        return count;
    }
}
