package study.PracticalTasks.ATMMachine;

public class ATMMachine {
    private int balance;

    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("deposited amount is" + amount + "total balance is:" + balance);
        } else {
            System.out.println("invalid amount");
        }
    }

    public synchronized void withdraw(int amount) {
        if (amount > balance ) {
            System.out.println("withdrawl amount is greater than balance");
        } else if (amount >0 && amount <= balance) {
            balance = balance-amount;
            System.out.println(("available amount is "+ balance));
        }
    }
}
