/**
 * Checking account with overdraft logic
 */
public class Checking extends Account {
    private static final double OverdraftLimit = 500;
    private double overdraftUsed = 0;

    public Checking(String accountNum, double balance, User user){
        super(accountNum, balance, user);
    }

    @Override
    public void withdrawl(double amount){
        double available = Balance + (OverdraftLimit - overdraftUsed);
        if(amount <= Balance){
            Balance -= amount;
            System.out.println("Withdrew $" + amount + " from balance.");
        } else if(amount <= available){
            double fromBal = Balance;
            double fromOD = amount - Balance;
            Balance = 0;
            overdraftUsed += fromOD;
            System.out.println("Withdrew $" + amount + " (Balance: $" + fromBal + ", Overdraft: $" + fromOD + ")");
        } else {
            System.out.println("Cannot withdraw, overdraft limit reached.");
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Account Type: Checking");
        System.out.println("Overdraft Used: $" + overdraftUsed);
        System.out.println("Overdraft Remaining: $" + (OverdraftLimit - overdraftUsed));
    }
}