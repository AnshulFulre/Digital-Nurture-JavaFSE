import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex27_LambdaExample {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Rohan");
        names.add("Amit");
        names.add("Vikas");
        names.add("Deepak");

        Collections.sort(names, (a, b) -> a.compareTo(b));

        System.out.println("Sorted Names:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}