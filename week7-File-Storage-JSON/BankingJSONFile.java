import java.io.*;
import java.util.*;

public class BankingJSONFile {

    static final String FILE_NAME = "account.json";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Banking Management - JSON File Storage");
        System.out.println("1. Add Account");
        System.out.println("2. View Account");
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

            try (FileWriter writer = new FileWriter(FILE_NAME)) {
                writer.write("{\n");
                writer.write("  \"accountNumber\": \"" + accountNumber + "\",\n");
                writer.write("  \"name\": \"" + name + "\",\n");
                writer.write("  \"balance\": " + balance + "\n");
                writer.write("}\n");

                System.out.println("Account details saved successfully.");

            } catch (IOException e) {
                System.out.println("Error saving account details.");
            }

        } else if (choice == 2) {

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;

                System.out.println("\nSaved Account Details:");

                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (FileNotFoundException e) {
                System.out.println("No account data found.");
            } catch (IOException e) {
                System.out.println("Error reading account details.");
            }

        } else {
            System.out.println("Invalid choice.");
        }

        sc.close();
    }
}
