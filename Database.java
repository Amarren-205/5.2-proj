import java.sql.*;

/**
 * Handles SQLite database operations
 * Users table: id, name
 * Accounts table: accountNum, type, balance, overdraftUsed, userId
 */
public class Database {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public Database() { createTables(); }

    private void createTables(){
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()){
            String users = "CREATE TABLE IF NOT EXISTS Users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL)";
            String accounts = "CREATE TABLE IF NOT EXISTS Accounts (" +
                    "accountNum TEXT PRIMARY KEY," +
                    "type TEXT NOT NULL," +
                    "balance REAL NOT NULL," +
                    "overdraftUsed REAL," +
                    "userId INTEGER NOT NULL," +
                    "FOREIGN KEY(userId) REFERENCES Users(id))";
            stmt.execute(users);
            stmt.execute(accounts);
        } catch(SQLException e){
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    public int addUser(String name){
        int userId = -1;
        String sql = "INSERT INTO Users(name) VALUES(?)";
        try(Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) userId = rs.getInt(1);
        } catch(SQLException e){
            System.out.println("Error adding user: " + e.getMessage());
        }
        return userId;
    }

    public void addAccount(String accNum, String type, double balance, double overdraftUsed, int userId){
        String sql = "INSERT INTO Accounts(accountNum,type,balance,overdraftUsed,userId) VALUES(?,?,?,?,?)";
        try(Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, accNum);
            pstmt.setString(2, type);
            pstmt.setDouble(3, balance);
            pstmt.setDouble(4, overdraftUsed);
            pstmt.setInt(5, userId);
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    public void updateBalance(String accNum, double newBalance, double newOverdraft){
        String sql = "UPDATE Accounts SET balance=?, overdraftUsed=? WHERE accountNum=?";
        try(Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1, newBalance);
            pstmt.setDouble(2, newOverdraft);
            pstmt.setString(3, accNum);
            pstmt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    public void displayAllAccounts(){
        String sql = "SELECT Accounts.accountNum,type,balance,overdraftUsed,Users.name " +
                     "FROM Accounts JOIN Users ON Accounts.userId = Users.id";
        try(Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                System.out.println("Account Number: "+rs.getString("accountNum"));
                System.out.println("Type: "+rs.getString("type"));
                System.out.println("Balance: $"+rs.getDouble("balance"));
                System.out.println("Overdraft Used: $"+rs.getDouble("overdraftUsed"));
                System.out.println("Owner: "+rs.getString("name"));
                System.out.println("----------------------");
            }
        } catch(SQLException e){ System.out.println("Error displaying accounts: "+e.getMessage()); }
    }

    public void deleteAccount(String accNum){
        String sql = "DELETE FROM Accounts WHERE accountNum=?";
        try(Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, accNum);
            pstmt.executeUpdate();
        } catch(SQLException e){ System.out.println("Error deleting account: "+e.getMessage()); }
    }

    public void deleteUser(int userId){
        try(Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM Accounts WHERE userId=?");
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM Users WHERE id=?")){
            pstmt1.setInt(1,userId);
            pstmt1.executeUpdate();
            pstmt2.setInt(1,userId);
            pstmt2.executeUpdate();
        } catch(SQLException e){ System.out.println("Error deleting user: "+e.getMessage()); }
    }
}
