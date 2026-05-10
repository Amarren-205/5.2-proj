/**
 * Savings account with fixed interest rate
 */
public class Savings extends Account {
    private static final double INTEREST = 0.04;

    public Savings(String accountNum, double balance, User user){
        super(accountNum, balance, user);
    }

    @Override
    public void withdrawl(double amount){
        if(amount <= Balance){
            Balance -= amount;
            System.out.println("Withdrew $" + amount + " from savings.");
        } else {
            System.out.println("Cannot withdraw more than balance.");
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: Savings");
        System.out.println("Interest Rate: " + (INTEREST*100) + "%");
    }
}
