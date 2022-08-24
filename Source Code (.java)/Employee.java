import java.util.*;

public class Employee {
    private  int employeeId;
    private  String name;
    private  String designation;
	
	public Employee() {
		employeeId = 0;
		name = "";
		designation = "";
	}
	
    public Employee(int employeeId, String name, String designation) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
    }

    public Employee(String name,String designation) {
        this.employeeId = generateEmployeeID();
        this.name = name;
        this.designation = designation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    private int generateEmployeeID(){
        int minimum = 100;
        int maximum = 999;
        Random rand = new Random();
        return minimum + rand.nextInt((maximum - minimum) + 1);
    }
	
	
	public static void employeeMenu() {
		 Scanner scanner = new Scanner(System.in);
		char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        do {
		 System.out.println(String.format("\n\n+=======================+"));
         System.out.println(String.format("|%14s%10s", "Employee Menu", "|"));
         System.out.println(String.format("+=====+=================+"));
         System.out.println(String.format("|%5s|%13s%5s", "1  ", "Access Train", "|"));
         System.out.println(String.format("|%5s|%13s%5s", "2  ", "Access Route", "|"));
         System.out.println(String.format("|%5s|%12s%6s", "3  ", "Access Bank", "|"));
         System.out.println(String.format("|%5s|%8s%10s", "4  ", "Log out", "|"));
         System.out.println(String.format("+=====+=================+\n"));
         

		do {
            System.out.print(" > Enter Selection (1-4) : ");
                try {
                    selection = scanner.nextInt();
                    error = false;
                }
                catch(InputMismatchException e) {
                    System.out.println("\nEnter Digits only.");
                    error = true;
                }
                scanner.nextLine();
            } while(error);
            
            // Switch case to validate User Selection
            switch(selection)
            {
                case 1:
                    trainMenu train = new trainMenu();
                    train.trainMenu();
                    
                    break;
                case 2:
                    routeMenu route = new routeMenu();
                    route.routeMenu();
                     
                    break;
                case 3: 
                	BankAccount bank = new BankAccount();
                	bank.bankMenu();
                	 
                    break;
                case 4:
                    loop = 'N';
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
        } while(selection < 1 || selection > 4 || loop == 'Y');
	}
}
