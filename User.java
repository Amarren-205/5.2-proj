import java.util.ArrayList;

/**
 * User class holds multiple accounts
 */
public class User {
    private String Name;
    private ArrayList<Account> accounts;

    public User(String name){
        Name = name;
        accounts = new ArrayList<>();
    }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts(){ return accounts; }

    public Account findAccount(String accNum){
        for(Account acc : accounts){
            if(acc.getAccountNum().equals(accNum)) return acc;
        }
        return null;
    }
}