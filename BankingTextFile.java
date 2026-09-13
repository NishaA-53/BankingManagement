import java.util.*;
import java.io.*;

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

class BankDAO {

    private ArrayList<BankAccount> bank = new ArrayList<>();

    void createAccount(BankAccount account) {
        for (BankAccount a : bank) {
            if (a.accNo == account.accNo) {
                System.out.println("Account number already exists!");
                return;
            }
        }

        bank.add(account);
        saveAccounts();
        System.out.println("Account created successfully!");
    }

    BankAccount findAccount(int accNo) {
        for (BankAccount account : bank) {
            if (account.accNo == accNo) {
                return account;
            }
        }
        return null;
    }

    void saveAccounts() {
        try {
            FileWriter writer = new FileWriter("accounts.txt");

            for (BankAccount account : bank) {
                writer.write(account.accNo + "," + account.name + "," + account.balance + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving accounts!");
        }
    }

    void loadAccounts() {
        try {
            File file = new File("accounts.txt");

            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                int accNo = Integer.parseInt(data[0]);
                String name = data[1];
                double balance = Double.parseDouble(data[2]);

                bank.add(new BankAccount(accNo, name, balance));
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading accounts!");
        }
    }

    void viewAllAccounts() {
        if (bank.isEmpty()) {
            System.out.println("No accounts available!");
            return;
        }

        System.out.println("\n--- ALL ACCOUNTS ---");

        for (BankAccount account : bank) {
            account.display();
            System.out.println("--------------------");
        }
    }
}

public class BankingTextFile {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankDAO dao = new BankDAO();

        dao.loadAccounts();

        while (true) {

            System.out.println("\n--- BANKING MANAGEMENT SYSTEM ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. View All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            if (ch == 1) {

                System.out.print("Account Number: ");
                int no = sc.nextInt();

                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Initial Balance: ");
                double balance = sc.nextDouble();

                BankAccount account = new BankAccount(no, name, balance);
                dao.createAccount(account);
            }

            else if (ch == 2) {

                System.out.print("Enter Account Number: ");
                int no = sc.nextInt();

                BankAccount account = dao.findAccount(no);

                if (account == null) {
                    System.out.println("Account not found!");
                } else {
                    System.out.print("Enter deposit amount: ");
                    double amount = sc.nextDouble();

                    account.balance += amount;
                    dao.saveAccounts();

                    System.out.println("Deposit successful!");
                }
            }

            else if (ch == 3) {

                System.out.print("Enter Account Number: ");
                int no = sc.nextInt();

                BankAccount account = dao.findAccount(no);

                if (account == null) {
                    System.out.println("Account not found!");
                } else {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= account.balance) {
                        account.balance -= amount;
                        dao.saveAccounts();

                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                }
            }

            else if (ch == 4) {

                System.out.print("Enter Account Number: ");
                int no = sc.nextInt();

                BankAccount account = dao.findAccount(no);

                if (account == null) {
                    System.out.println("Account not found!");
                } else {
                    account.display();
                }
            }

            else if (ch == 5) {
                dao.viewAllAccounts();
            }

            else if (ch == 6) {
                System.out.println("Thank you!");
                break;
            }

            else {
                System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
