/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p0021.DAO;

import p0021.Entity.Student;
import java.util.ArrayList;
import java.util.Collections;
import p0021.Entity.StudentComparator;

/**
 *
 * @author admin
 */
public class StudentDAO {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student stu) {
        students.add(stu);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> sortStudent(ArrayList<Student> list) {
        StudentComparator comparator = new StudentComparator();
        Collections.sort(list, comparator);
        return list;
    }

    public ArrayList<Student> searchStudent(String value, String type) {
        ArrayList<Student> results = new ArrayList<>();
        for (Student item : students) {
            if (type.equalsIgnoreCase("name")) {
                if (item.getStudentName().contains(value)) {
                    results.add(item);
                }
            } else {
                if (item.getId().equalsIgnoreCase(value)) {
                    results.add(item);
                }
            }
        }
        return results;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void updateStudent(Student data, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).equals(data)) {
                students.set(i, student);
            }
        }
    }

    public boolean checkStudentInforExist(Student stu) {
        for (Student item : students) {
            if (stu.getId().equalsIgnoreCase(item.getId())
                    && (!stu.getStudentName().equalsIgnoreCase(item.getStudentName()) || stu.getAge() != item.getAge())) {
                System.out.println("Different students cannot have the same ID.");
                return false;
            } else if (stu.getId().equalsIgnoreCase(item.getId())
                    && stu.getStudentName().equalsIgnoreCase(item.getStudentName())
                    && stu.getAge() == item.getAge()
                    && stu.getSemester() == item.getSemester()
                    && stu.getCourseName().equalsIgnoreCase(item.getCourseName())) {
                System.out.println("Student cannot learn the same course in one semester.");
                return false;
            }
        }
        return true;
    }

    public boolean checkUpdateStudentInforExist(Student newStu, Student oldStu) {
        for (Student item : students) {
            if (!oldStu.getId().equalsIgnoreCase(item.getId())
                    && !oldStu.getStudentName().equalsIgnoreCase(item.getStudentName())
                    && oldStu.getAge() != item.getAge()
                    && oldStu.getSemester() != item.getSemester()
                    && !oldStu.getCourseName().equalsIgnoreCase(item.getCourseName())) {
                if (newStu.getId().equalsIgnoreCase(item.getId())
                        && newStu.getStudentName().equalsIgnoreCase(item.getStudentName())
                        && newStu.getAge() == item.getAge()
                        && newStu.getSemester() == item.getSemester()
                        && newStu.getCourseName().equalsIgnoreCase(item.getCourseName())) {
                    System.out.println("Student cannot learn the same course in one semester.");
                    return false;
                }
            }
        }
        return true;
    }
}
