package uts;
import java.util.Scanner;
import java.util.UUID;

// Interface for Nontification
interface Notification {
    void notifyUser(); //interface
}

//abstract class Display 
abstract class Display implements Notification {
    abstract void showToken(UUID token);
}

//sub class User (inherits from Display)
class User extends Display {
    //Encapsulation
    private String username;
    private String password;
    private UUID token;

    void tokenCreation() {
        token = UUID.randomUUID();
    }

    //setter
    void setUsername(String username) {
        this.username = username;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setToken(UUID token) {
        this.token = token;
    }
    
    //getter
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    UUID getToken() {
        return token;
    }
     // Method to set username, password, and generate token
    void register(String username, String password) {
        setUsername(username);
        setPassword(password);
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
        tokenCreation();
    }
    
    //Polimorphism
    @Override
    void showToken(UUID token) {
        System.out.println("Token generated: " + token);
    }
    
    //Polimorphism
    @Override
    public void notifyUser() {
        System.out.println("User created by user");
        showToken(getToken());
    }
}

//subclass Admin (inherits from User)
class Admin extends User {
    private final String role = "admin";
    
    //Polimorphism
    @Override
    public void notifyUser() {
        System.out.println("User created by " + role);
        showToken(getToken());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("+------------+");
        System.out.println("| REGISTRATION |");
        System.out.println("+------------+");

        boolean registerAgain;
        do {
            System.out.print("Enter Username: ");
            String username = input.nextLine();

            System.out.print("Enter Password: ");
            String password = input.nextLine();

            System.out.println("---------------------");
            System.out.println("Choose user type:\n1. User\n2. Admin");
            int userTypeChoice = input.nextInt();

            if (userTypeChoice == 1) {
                User user1 = new User();
                System.out.println("---------------------");
                user1.register(username, password);
                user1.notifyUser();
                System.out.println("---------------------");
            } else if (userTypeChoice == 2) {
                //Polimorphism
                User admin = new Admin();
                System.out.println("---------------------");
                admin.register(username, password);
                admin.notifyUser();
                System.out.println("---------------------");
            } else {
                System.out.println("Invalid choice!");
            }

            input.nextLine(); // Membuang newline character dari input sebelumnya
            System.out.print("Do you want to register another account? (yes/no): ");
            String choice = input.nextLine();
            registerAgain = choice.equalsIgnoreCase("yes");
        } while (registerAgain);

        input.close();
    }
}
