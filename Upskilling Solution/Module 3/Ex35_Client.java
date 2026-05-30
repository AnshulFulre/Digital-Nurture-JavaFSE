import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Ex35_Client {

    public static void main(String[] args) {

        try {

            Socket socket =
                    new Socket("localhost", 5000);

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            out.println("Hello Server");

            System.out.println(in.readLine());

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}