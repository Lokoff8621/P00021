/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p0021;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Validate {

    Scanner sc = new Scanner(System.in);

    public boolean checkYN() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.print("Please enter Y/y for agree or N/n for disagree: ");
            }
        }
    }

    public boolean checkUD() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.equalsIgnoreCase("U")) {
                return true;
            } else if (result.equalsIgnoreCase("D")) {
                return false;
            } else {
                System.out.print("Please enter U/u for update student's imformation or D/d for delete student: ");
            }
        }
    }

    public int checkOption(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result > max || result < min) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException nfe) {
                System.out.print("Invalid. Enter again (" + min + "-" + max + "): ");
            }
        }
    }

    public String checkString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty() || result == null) {
                System.out.print("Please re-enter: ");
            } else {
                return result;
            }
        }
    }

    public String checkNotContainDigit() {
        String regex = ".*\\d.*";
        while (true) {
            String result = checkString();
            if (result.matches(regex)) {
                System.out.println("Student name is not digit.");
                System.out.print("Enter again: ");
            }
            else return result;
        }
    }

    public int checkSemester() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < 1 || result > 9) {
                    System.out.println("Semester start from 1 and end at 9.");
                    System.out.print("Enter again: ");
                }
                else return result;
            } catch (NumberFormatException nfe) {
                System.out.println("Semester is digit.");
                System.out.print("Enter again: ");
            }
        }
    }

    public int checkAge() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < 18) {
                    System.out.println("Age greater than 18.");
                    System.out.print("Enter again: ");
                }
                else return result;
            } catch (NumberFormatException nfe) {
                System.out.println("Age is digit.");
                System.out.print("Enter again: ");
            }
        }
    }

    public String checkCourse() {
        while (true) {
            String input = checkString();
            if (input.equalsIgnoreCase("Java")) {
                return "Java";
            } else if (input.equalsIgnoreCase(".Net")) {
                return ".Net";
            } else if (input.equalsIgnoreCase("C/C++")) {
                return "C/C++";
            } else {
                System.out.print("Please enter Java, .Net or C/C++ : ");
            }
        }
    }
    
}
