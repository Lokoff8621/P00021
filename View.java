/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p0021;

import java.util.ArrayList;
import java.util.Scanner;
import p0021.DAO.ReportDAO;
import p0021.DAO.StudentDAO;
import p0021.Entity.Report;
import p0021.Entity.Student;

/**
 *
 * @author admin
 */
public class View {

    Scanner sc = new Scanner(System.in);
    Validate v = new Validate();
    StudentDAO sdao = new StudentDAO();
    ReportDAO rdao = new ReportDAO();

    public void menu() {
        generateStudents();
        while (true) {
            System.out.println("WELCOME TO STUDENT MANAGEMENT");
            System.out.println("        1. Create");
            System.out.println("        2. Find and Sort");
            System.out.println("        3. Update/Delete");
            System.out.println("        4. Report");
            System.out.println("        5. Exit");
            System.out.println("(Please choose 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit program)");
            System.out.print("Your choice: ");
            int option = v.checkOption(1, 5);
            switch (option) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSortStudents();
                    break;
                case 3:
                    updateOrDeleteStudent();
                    break;
                case 4:
                    reportStudent();
                    break;
                case 5:
                    return;
            }
        }
    }

    public void createStudent() {
        System.out.println("-------------------- Create Students --------------------");
        while (true) {
            int count = 1;
            while (count < 6) {
                System.out.print("Enter ID: ");
                String id = v.checkString();
                System.out.print("Enter name: ");
                String name = v.checkNotContainDigit();
                System.out.print("Enter age: ");
                int age = v.checkAge();
                System.out.print("Enter semester: ");
                int semester = v.checkSemester();
                System.out.print("Enter course: ");
                String course = v.checkCourse();
                Student stu = new Student(id, name, age, semester, course);
                if (sdao.checkStudentInforExist(stu)) {
                    sdao.addStudent(stu);
                    count++;
                }
            }
            System.out.print("Do you want to continue (Y/N)? ");
            if (!v.checkYN()) {
                return;
            }
        }
    }

    public void findAndSortStudents() {
        System.out.println("------------------ Find & Sort Students ------------------");
        System.out.print("Enter name: ");
        String value = v.checkNotContainDigit();
        ArrayList<Student> results1 = sdao.searchStudent(value, "name");
        //kiểm tra nếu danh sách trả về trống thì báo không có sinh viên có tên hoặc trong tên chứa value và quay về main screen
        if (results1.isEmpty()) {
            System.out.println("Cannot find student(s) has name or contain '" + value + "' in database.");
            return;
        }
        ArrayList<Student> results2 = sdao.sortStudent(results1);
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Student name", "Age",
                "semester", "Course Name");
        for (Student student : results2) {
            System.out.printf("%-10s%-15s%-15s%-15s\n", student.getStudentName(),
                    student.getAge(), student.getSemester(),
                    student.getCourseName());
        }
    }

    public void updateOrDeleteStudent() {
        System.out.println("------------------ Update/Delete Students ------------------");
        System.out.print("Enter ID: ");
        String id = v.checkString();
        ArrayList<Student> results = sdao.searchStudent(id, "id");
        //kiểm tra nếu danh sách trả về trống thì báo không có sinh viên có ID = id và quay về main screen
        if (results.isEmpty()) {
            System.out.println("Cannot find student(s) has ID = " + id);
            return;
        }
        int count = 1;
        System.out.printf("%-10s%-15s%-15s%-15s%-15s\n", "Number", "Student name", "Age",
                "semester", "Course Name");
        // in ra danh sách chứa các sinh viên có ID = id có đánh số thứ tự để người dùng có thể chọn sinh viên cần update/delete
        for (int i = 0; i < results.size(); i++) {
            System.out.printf("%-10s%-15s%-15s%-15s%-15s\n", count, results.get(i).getStudentName(),
                    results.get(i).getAge(), results.get(i).getSemester(),
                    results.get(i).getCourseName());
            count++;
        }
        // yêu cầu người dùng chọn sinh viên muốn update/delete
        System.out.print("Choose data your wanna update/delete: ");
        int index = v.checkOption(1, count--);
        Student data = results.get(index - 1);
        System.out.print("Do you wanna update (U) or delete (D)? ");
        //nếu người dùng chọn update (U) hệ thống yêu cầu người dùng nhập thông tin mới (semester/course)
        if (v.checkUD()) {
            System.out.print("Enter semester: ");
            int semester = v.checkSemester();
            System.out.print("Enter course: ");
            String course = v.checkCourse();
            Student stu = new Student(data.getId(), data.getStudentName(), data.getAge(), semester, course);
            // kiểm tra xem thông tin sinh viên mới có trùng lặp với thông tin sinh viên nào đã có trong database không, nếu không thì update
            if (sdao.checkUpdateStudentInforExist(stu, data)) {
                sdao.updateStudent(data, stu);
                System.out.println("Successful");
            }
        } // nếu người dùng chọn delete (D) hệ thống xóa sinh viên mà người dùng chọn trong database 
        else {
            sdao.deleteStudent(data);
            System.out.println("Successful");
        }
    }

    public void reportStudent() {
        System.out.println("-------------------- Student's Report --------------------");
        // lấy ra data sinh viên. kiểm tra nếu data rỗng thì báo không có data và chuyển về main screen
        ArrayList<Student> students = sdao.getStudents();
        if (students.isEmpty()) {
            System.out.println("No data.");
            return;
        }
        // kiểm tra nếu đã có data report thì xóa sạch data đã có
        if (!rdao.getReportData().isEmpty()) {
            rdao.clearReports();
        }
        // chạy vòng lặp danh sách sinh viên, lấy thông tin student id, student name, course name
        for (Student student : students) {
            int total = 0;
            String id = student.getId();
            String studentName = student.getStudentName();
            String courseName = student.getCourseName();
            if (!rdao.checkReportExist(id, studentName, courseName)) {
                for (Student studentCountTotal : students) {
                    if (id.equalsIgnoreCase(studentCountTotal.getId())
                            && studentName.equalsIgnoreCase(studentCountTotal.getStudentName())
                            && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                        total++;
                    }
                }
                Report report = new Report(student.getId(), student.getStudentName(), student.getCourseName(), total);
                rdao.addReport(report);
            }
        }
        ArrayList<Report> reports = rdao.getReportData();
        for (Report item : reports) {
            System.out.printf("%-15s|%-10s|%-5d\n", item.getStudentName(),
                    item.getCourseName(), item.getTotalCourse());
        }
    }

    public void generateStudents() {
        sdao.addStudent(new Student("s1", "duc", 18, 1, "Java"));
        sdao.addStudent(new Student("s1", "duc", 18, 2, "Java"));
        sdao.addStudent(new Student("s1", "duc", 18, 3, "Java"));
        sdao.addStudent(new Student("s2", "duc", 18, 1, "Java"));
        sdao.addStudent(new Student("s2", "duc", 18, 2, "C/C++"));
        sdao.addStudent(new Student("s2", "duc", 18, 3, "C/C++"));
        sdao.addStudent(new Student("s3", "chi", 20, 1, ".Net"));
        sdao.addStudent(new Student("s3", "chi", 20, 2, ".Net"));
        sdao.addStudent(new Student("s3", "chi", 20, 3, ".Net"));
        sdao.addStudent(new Student("s4", "dua", 20, 1, "C/C++"));
        sdao.addStudent(new Student("s4", "dua", 20, 2, "Java"));
        sdao.addStudent(new Student("s4", "dua", 20, 3, ".Net"));
    }
}
