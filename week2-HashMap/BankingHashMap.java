import java.util.*;

class BankAccount {
    int accNo;
    String name;
    double balance;

    BankAccount(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    void display() {
        System.out.println("Account No: " + accNo);
        System.out.println("Name: " + name);
        System.out.println("Balance: ₹" + balance);
    }
}

public class BankingHashMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, BankAccount> bank = new HashMap<>();

        while (true) {
            System.out.println("\n--- BANKING MANAGEMENT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Account Number: ");
                int no = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Initial Balance: ");
                double bal = sc.nextDouble();

                bank.put(no, new BankAccount(no, name, bal));
                System.out.println("Account created successfully!");
            }

            else if (ch == 2 || ch == 3 || ch == 4) {
                System.out.print("Enter Account Number: ");
                int no = sc.nextInt();

                BankAccount a = bank.get(no);

                if (a == null) {
                    System.out.println("Account not found!");
                } 
                else if (ch == 2) {
                    System.out.print("Enter deposit amount: ");
                    a.balance += sc.nextDouble();
                    System.out.println("Deposit successful!");
                } 
                else if (ch == 3) {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= a.balance) {
                        a.balance -= amount;
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                } 
                else {
                    a.display();
                }
            }

            else if (ch == 5) {
                System.out.println("Thank you!");
                break;
            }

            else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
