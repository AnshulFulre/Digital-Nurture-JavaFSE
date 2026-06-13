public class Main {
    public static void main(String[] args) {
        Future_Value futureValue = new Future_Value();

        double principle_amt = 100000.0;
        double rate = 0.064; // 6.40%
        int duration = 7; //years
        System.out.println("In future value of " + principle_amt + " after investing for " + duration+" years with interest rate of "+rate+"% make your principle amount "+futureValue.futureValue(principle_amt,rate,duration));
    }
}