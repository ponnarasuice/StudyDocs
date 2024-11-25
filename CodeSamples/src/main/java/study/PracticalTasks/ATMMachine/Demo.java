package study.PracticalTasks.ATMMachine;

public class Demo {

    public static void main(String[] args) {
        ATMMachine atmMachine = new ATMMachine();
        atmMachine.deposit(1000);
        atmMachine.withdraw(500);
        atmMachine.deposit(200);
        atmMachine.withdraw(1500);
    }
}
