import java.io.*;
import java.util.*;

public class BankingSerialization {

    static final String FILE_NAME = "account.ser";

    static class Account implements Serializable {
        private static final long serialVersionUID = 1L;

        String accountNumber;
        String name;
        double balance;

        Account(String accountNumber, String name, double balance) {
            this.accountNumber = accountNumber;
            this.name = name;
            this.balance = balance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Banking Management - Serialization");
        System.out.println("1. Save Account");
        System.out.println("2. Retrieve Account");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("Enter Account Number: ");
            String accountNumber = sc.nextLine();

            System.out.print("Enter Account Holder Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Balance: ");
            double balance = sc.nextDouble();

            Account account = new Account(accountNumber, name, balance);

            try (ObjectOutputStream out =
                         new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

                out.writeObject(account);
                System.out.println("Account saved successfully.");

            } catch (IOException e) {
                System.out.println("Error saving account.");
            }

        } else if (choice == 2) {

            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(FILE_NAME))) {

                Account account = (Account) in.readObject();

                System.out.println("\nAccount Details:");
                System.out.println("Account Number: " + account.accountNumber);
                System.out.println("Account Holder: " + account.name);
                System.out.println("Balance: " + account.balance);

            } catch (FileNotFoundException e) {
                System.out.println("No saved account found.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error retrieving account.");
            }

        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
