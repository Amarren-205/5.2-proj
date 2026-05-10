/**
 * Abstract Account class implements Transactions
 * Base class for Checking and Savings accounts
 */
public abstract class Account implements Transaction {
    protected String AccountNum;
    protected double Balance;
    protected User User;

    public Account(String accountNum, double balance, User user){
        AccountNum = accountNum;
        Balance = balance;
        User = user;
    }

    @Override
    public void deposit(double amount){
        Balance += amount;
        System.out.println("Deposited $" + amount + " successfully.");
    }

    @Override
    public abstract void withdrawl(double amount);

    public String getAccountNum() {
        return AccountNum;
    }

    public double getBalance() {
        return Balance;
    }

    public User getUser() {
        return User;
    }

    public void display() {
        System.out.println("Account Number: " + AccountNum);
        System.out.println("Balance: $" + Balance);
        System.out.println("Owner: " + User.getName());
    }
}
