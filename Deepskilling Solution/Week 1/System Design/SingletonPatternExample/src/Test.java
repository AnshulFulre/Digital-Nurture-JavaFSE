public class Test {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Logger logger3 = Logger.getInstance();

        logger1.get("This is logger 1.");
        logger2.get("This is logger 2.");
        logger3.get("This is logger 3.");

        if (logger1 == logger2 && logger2 == logger3){
            System.out.println("Success!!");
        }
        else System.out.println("Failed!!");
    }
}
