import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex35_Server {

    public static void main(String[] args) {

        try {

            ServerSocket server =
                    new ServerSocket(5000);

            System.out.println("Server Started");

            Socket socket = server.accept();

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter out =
                    new PrintWriter(
                            socket.getOutputStream(),
                            true);

            String message = in.readLine();

            System.out.println("Client: " + message);

            out.println("Hello Client");

            socket.close();
            server.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}