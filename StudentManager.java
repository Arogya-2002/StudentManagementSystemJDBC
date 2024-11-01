package smswithjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static final String URL = "jdbc:postgresql://localhost:5433/demodb"; // Change to your database URL
    private static final String USER = "postgres"; // Change to your database username
    private static final String PASSWORD = "oooo"; // Change to your database password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Receptionist receptionist = new Receptionist(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Student Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. View All Students");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter student ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter student email: ");
                        String email = scanner.nextLine();
                        receptionist.addStudent(new Student(id, name, email));
                        break;
                    case 2:
                        System.out.print("Enter student ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        receptionist.updateStudent(new Student(updateId, newName, newEmail));
                        break;
                    case 3:
                        List<Student> students = receptionist.getAllStudents();
                        System.out.println("Students:");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
