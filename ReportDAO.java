/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p0021.DAO;

import java.util.ArrayList;
import p0021.Entity.Report;

/**
 *
 * @author admin
 */
public class ReportDAO {

    ArrayList<Report> reports = new ArrayList<>();

    public void addReport(Report report) {
        reports.add(report);
    }
    
    public ArrayList<Report> getReportData() {
        return reports;
    }
    
    public void clearReports () {
        reports.clear();
    }

    public boolean checkReportExist(String id, String studentName, String courseName) {
        for (Report item : reports) {
            if (item.getId().equalsIgnoreCase(id)
                    && item.getStudentName().equalsIgnoreCase(studentName)
                    && item.getCourseName().equalsIgnoreCase(courseName)) {
                return true;
            }
        }
        return false;
    }
}
