import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ex36_HttpClientExample {

    public static void main(String[] args) {

        try {

            HttpClient client =
                    HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(new URI(
                                    "https://api.github.com"))
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            System.out.println(
                    "Status Code: "
                            + response.statusCode());

            System.out.println(response.body());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}