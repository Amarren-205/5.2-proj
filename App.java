import java.util.Scanner;

/**
 * Main banking app with SQLite persistence and CRUD operations
 */
public class App {
    public static void main(String[] args){
        Database db = new Database();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Amarren Banking!");
        System.out.println("Create users, create accounts, deposit/withdraw, display, update, delete.");

        boolean running = true;
        while(running){
            System.out.println("\nMenu:");
            System.out.println("1: Add User");
            System.out.println("2: Add Account");
            System.out.println("3: Deposit");
            System.out.println("4: Withdraw");
            System.out.println("5: Display All Accounts");
            System.out.println("6: Delete Account");
            System.out.println("7: Delete User");
            System.out.println("8: Exit");
            System.out.print("Choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch(choice){
                case 1:
                    System.out.print("User name: ");
                    String name = input.nextLine();
                    int userId = db.addUser(name);
                    System.out.println("User added with ID: " + userId);
                    break;
                case 2:
                    System.out.print("Account number: "); String accNum = input.nextLine();
                    System.out.print("User ID: "); int uid = input.nextInt(); input.nextLine();
                    System.out.print("Type (Checking/Savings): "); String type = input.nextLine();
                    System.out.print("Initial balance: "); double bal = input.nextDouble(); input.nextLine();
                    db.addAccount(accNum, type, bal, 0, uid);
                    System.out.println("Account added.");
                    break;
                case 3:
                    System.out.print("Account number to deposit: "); String depAcc = input.nextLine();
                    System.out.print("Amount: "); double depAmt = input.nextDouble(); input.nextLine();
                    db.updateBalance(depAcc, depAmt, 0);
                    System.out.println("Deposit processed.");
                    break;
                case 4:
                    System.out.print("Account number to withdraw: "); String witAcc = input.nextLine();
                    System.out.print("Amount: "); double witAmt = input.nextDouble(); input.nextLine();
                    db.updateBalance(witAcc, -witAmt, 0);
                    System.out.println("Withdrawal processed.");
                    break;
                case 5:
                    db.displayAllAccounts();
                    break;
                case 6:
                    System.out.print("Account number to delete: "); String delAcc = input.nextLine();
                    db.deleteAccount(delAcc);
                    System.out.println("Account deleted.");
                    break;
                case 7:
                    System.out.print("User ID to delete: "); int delUser = input.nextInt(); input.nextLine();
                    db.deleteUser(delUser);
                    System.out.println("User and their accounts deleted.");
                    break;
                case 8:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
