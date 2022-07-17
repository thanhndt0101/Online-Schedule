/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author HAICAO
 */
public class Group {
    private String id, subject, lecture;
    private ArrayList<Enroll> enrolls = new ArrayList<>();
    
    public String getId() {
        return id;
    }

    public ArrayList<Enroll> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(ArrayList<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }
    
}
