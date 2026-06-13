public class Future_Value {

    // Since each recursive call reduces the input size by 1 so there will be n+1 recursive calls.
    // Thus Time Complexity becomes O(n).
    public double futureValue(double amount, double p_growthRate, int investment_duration){
        if(investment_duration == 0) return amount;

        else return futureValue(amount,p_growthRate,investment_duration-1) * (1+p_growthRate);
    }
//     Optimization :
//     As same multiplication operation is done in each call so we can do it using iterative approach
//    which reduces Space complexity to O(1).
//    and more over we can do mathematical optimization like in each case after hitting base case we multiply same
//    (1+p_growthRate) with amount, investment_duration times so rather than recursive approach directly calculate
//    (1+p_growthRate)^investment_duration and multiply it with amount
//    this approach reduces Time Complexity to O(log(n))
}
