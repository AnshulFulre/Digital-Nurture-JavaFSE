import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ex22_FileWriting {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text to write into file: ");
        String text = sc.nextLine();

        try {

            FileWriter writer = new FileWriter("output.txt");
            writer.write(text);
            writer.close();

            System.out.println("Data written successfully.");

        } catch (IOException e) {

            System.out.println("Error while writing file.");
        }

        sc.close();
    }
}