import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();

        // Sample accounts
        accounts.add(new Account("1001", "lavkush", 1234, 10000));
        accounts.add(new Account("1002", "rahul", 5678, 5000));
        accounts.add(new Account("1003", "amit", 4321, 8000));
    }

    // User ID se account find karega
    public Account findAccountByUserId(String userId) {

        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    // Account ID se account find karega
    public Account findAccountByAccountId(String accountId) {

        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }
}