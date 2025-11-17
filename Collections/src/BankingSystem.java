import java.util.*;

class BankAccount {
    String accountNumber;
    String customerName;
    double balance;

    public BankAccount(String accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - $%.2f", accountNumber, customerName, balance);
    }
}

public class BankingSystem {
    private Map<String, BankAccount> accounts;
    private Map<Double, List<BankAccount>> accountsByBalance;
    private Queue<String> withdrawalQueue;

    public BankingSystem() {
        accounts = new HashMap<>();
        accountsByBalance = new TreeMap<>(Collections.reverseOrder());
        withdrawalQueue = new LinkedList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.accountNumber, account);
        accountsByBalance.computeIfAbsent(account.balance, k -> new ArrayList<>()).add(account);
    }

    public void requestWithdrawal(String accountNumber) {
        withdrawalQueue.add(accountNumber);
        System.out.println("Withdrawal request queued for account: " + accountNumber);
    }

    public void processWithdrawals() {
        System.out.println("\nProcessing withdrawal requests:");
        while (!withdrawalQueue.isEmpty()) {
            String accountNumber = withdrawalQueue.poll();
            BankAccount account = accounts.get(accountNumber);
            if (account != null) {
                System.out.println("Processed withdrawal for: " + account.customerName);
            }
        }
    }

    public void displayAccountsByBalance() {
        System.out.println("\nAccounts sorted by balance (highest first):");
        for (Map.Entry<Double, List<BankAccount>> entry : accountsByBalance.entrySet()) {
            System.out.printf("Balance $%.2f: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        bank.addAccount(new BankAccount("001", "Alice", 5000.0));
        bank.addAccount(new BankAccount("002", "Bob", 2500.0));
        bank.addAccount(new BankAccount("003", "Carol", 7500.0));
        bank.addAccount(new BankAccount("004", "David", 1000.0));

        bank.requestWithdrawal("001");
        bank.requestWithdrawal("003");
        bank.requestWithdrawal("002");

        bank.displayAccountsByBalance();
        bank.processWithdrawals();
    }
}