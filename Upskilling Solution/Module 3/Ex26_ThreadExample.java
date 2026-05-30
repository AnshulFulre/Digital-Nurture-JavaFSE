class MyThread extends Thread {

    private String message;

    public MyThread(String message) {
        this.message = message;
    }

    public void run() {

        for (int i = 1; i <= 5; i++) {

            System.out.println(message + " : " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Ex26_ThreadExample {

    public static void main(String[] args) {

        MyThread t1 = new MyThread("Thread One");
        MyThread t2 = new MyThread("Thread Two");

        t1.start();
        t2.start();
    }
}