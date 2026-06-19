public class Logger {
    private static Logger logger;

    private Logger(){
        if(logger != null) throw new RuntimeException("Use getInstance() method");
    }

    public static Logger getInstance(){
        if (logger == null){
            logger = new Logger();
        }

        return logger;
    }

    public void get(String m){
        System.out.println(m);
    }
}
