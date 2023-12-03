package CustomerManagementApp;
import java.util.ArrayList;
import java.util.Scanner;

class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}

public class CustomerManagementApp {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    deleteCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        Customer newCustomer = new Customer(id, name, email);
        customers.add(newCustomer);

        System.out.println("Customer added successfully!");
    }

    private static void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        String idToDelete = scanner.nextLine();

        customers.removeIf(customer -> customer.getId().equals(idToDelete));

        System.out.println("Customer deleted successfully!");
    }

    private static void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        String idToUpdate = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getId().equals(idToUpdate)) {
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new email: ");
                String newEmail = scanner.nextLine();

                customer = new Customer(customer.getId(), newName, newEmail);
                System.out.println("Customer updated successfully!");
                return;
            }
        }

        System.out.println("Customer not found!");
    }

    private static void generateReport() {
        System.out.println("Customer Report:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
