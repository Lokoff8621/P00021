/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p0021.Entity;

import java.util.Comparator;

/**
 *
 * @author admin
 */
public class Student {
    String id, studentName;
    int age, semester;
    String courseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Student() {
    }

    public Student(String id, String studentName, int age, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.semester = semester;
        this.courseName = courseName;
    }
    
}
