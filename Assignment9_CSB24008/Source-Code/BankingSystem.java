import java.util.*;

class Account {
    private int accountNumber;
    private String ownerName;
    private double balance;

   
    public Account() {
        this(0, "Unknown", 0.0);
    }

    public Account(int accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }


    protected void updateBalance(double newBalance) {
        this.balance = newBalance;
    }

    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid deposit amount");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        balance -= amount;
    }

    public void display() {
        System.out.println("Account No: " + accountNumber +
                ", Owner: " + ownerName +
                ", Balance: " + balance);
    }
}



class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(int accNo, String name, double balance, double interestRate) {
        super(accNo, name, balance);
        this.interestRate = interestRate;
    }

    
    @Override
    public void display() {
        super.display();

        double interest = getBalance() * interestRate / 100;

        System.out.println("Interest Rate: " + interestRate +
                "%, Interest: " + interest);
    }
}



class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(int accNo, String name, double balance, double overdraftLimit) {
        super(accNo, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    
    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }

        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalArgumentException("Overdraft limit exceeded");
        }

        updateBalance(getBalance() - amount);
    }

    
    @Override
    public void display() {
        super.display();
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}


public class BankingSystem {
    public static void main(String[] args) {

        List<Account> accounts = new ArrayList<>();

        accounts.add(new SavingsAccount(101, "Karan", 5000, 5));
        accounts.add(new CurrentAccount(102, "Rahul", 3000, 2000));

        accounts.get(0).deposit(1000);
        accounts.get(1).withdraw(4000);

        for (Account acc : accounts) {
            acc.display();
            System.out.println("-------------------");
        }
    }
}