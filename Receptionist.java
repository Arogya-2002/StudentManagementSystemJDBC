package smswithjdbc;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class Receptionist {
    private Connection connection;

    public Receptionist(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.executeUpdate();
            System.out.println("Student added: " + student);
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
            System.out.println("Student updated: " + student);
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                students.add(new Student(id, name, email));
            }
        }
        return students;
    }
}
