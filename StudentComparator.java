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
public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        //sort accending by age
        if (s1.getAge() > s2.getAge()) {
            return 1;
        } else if (s1.getAge() < s2.getAge()) {
            return -1;
        } else {
            // acceding by semester
            if (s1.getSemester() > s2.getSemester()) {
                return 1;
            } else if (s1.getSemester() < s2.getSemester()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
