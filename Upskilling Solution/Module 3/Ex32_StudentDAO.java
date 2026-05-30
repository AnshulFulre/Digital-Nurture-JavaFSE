import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex32_StudentDAO {

    static String url =
            "jdbc:mysql://localhost:3306/studentdb";

    static String username = "root";
    static String password = "root";

    public static void insertStudent(int id, String name)
            throws Exception {

        Connection con =
                DriverManager.getConnection(url, username, password);

        String query =
                "INSERT INTO students(id,name) VALUES(?,?)";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setInt(1, id);
        ps.setString(2, name);

        ps.executeUpdate();

        con.close();
    }

    public static void updateStudent(int id, String name)
            throws Exception {

        Connection con =
                DriverManager.getConnection(url, username, password);

        String query =
                "UPDATE students SET name=? WHERE id=?";

        PreparedStatement ps =
                con.prepareStatement(query);

        ps.setString(1, name);
        ps.setInt(2, id);

        ps.executeUpdate();

        con.close();
    }

    public static void main(String[] args) {

        try {

            insertStudent(101, "Rahul");

            updateStudent(101, "Aman");

            System.out.println("Record inserted and updated.");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}