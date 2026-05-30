public class Ex37_BytecodeDemo {

    public int add(int a, int b) {

        return a + b;
    }

    public static void main(String[] args) {

        Ex37_BytecodeDemo obj =
                new Ex37_BytecodeDemo();

        System.out.println(
                obj.add(10, 20));
    }
}