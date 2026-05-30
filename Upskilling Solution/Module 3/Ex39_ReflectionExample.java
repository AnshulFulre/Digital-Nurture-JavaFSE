import java.lang.reflect.Method;

class Student {

    public void show() {

        System.out.println(
                "Reflection Method Called");
    }
}

public class Ex39_ReflectionExample {

    public static void main(String[] args)
            throws Exception {

        Class<?> c =
                Class.forName("Student");

        Object obj =
                c.getDeclaredConstructor()
                        .newInstance();

        Method[] methods =
                c.getDeclaredMethods();

        for (Method m : methods) {

            System.out.println(
                    "Method: " + m.getName());
        }

        Method method =
                c.getDeclaredMethod("show");

        method.invoke(obj);
    }
}