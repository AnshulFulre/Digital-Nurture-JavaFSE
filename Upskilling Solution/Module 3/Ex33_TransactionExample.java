import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Ex33_TransactionExample {

    public static void main(String[] args) {

        String url =
                "jdbc:mysql://localhost:3306/bankdb";

        String username = "root";
        String password = "root";

        try {

            Connection con =
                    DriverManager.getConnection(
                            url, username, password);

            con.setAutoCommit(false);

            PreparedStatement debit =
                    con.prepareStatement(
                            "UPDATE accounts SET balance = balance - 1000 WHERE id = 1");

            PreparedStatement credit =
                    con.prepareStatement(
                            "UPDATE accounts SET balance = balance + 1000 WHERE id = 2");

            debit.executeUpdate();
            credit.executeUpdate();

            con.commit();

            System.out.println("Transaction Successful");

            con.close();

        } catch (Exception e) {

            System.out.println("Transaction Failed");
            e.printStackTrace();
        }
    }
}