import java.io.IOException;
import java.util.*;

public class loginMenu
{
    public static void main(String[] args) throws Exception
    {
        // open Scanner function for user input;
        Scanner input = new Scanner(System.in);

        // Loop the Login Menu
        char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        do {
            // Generate and Display Login Menu for User to Select
            System.out.println(String.format("\n+==================+"));
            System.out.println(String.format("|%14s%5s", "Login Menu", "|"));
            System.out.println(String.format("+=====+============+"));
            System.out.println(String.format("|%5s|%7s%6s", "1  ", "Login", "|"));
            System.out.println(String.format("|%5s|%10s%3s", "2  ", "Register", "|"));
            System.out.println(String.format("|%5s|%6s%7s", "3  ", "Quit", "|"));
            System.out.println(String.format("+=====+============+\n"));

            // User Input their Selection
            
            
			do {
                System.out.print(" > Enter Selection (1-3) : ");
                try {
                    selection = input.nextInt();
                    error = false;
                }
                catch(InputMismatchException e) {
                    System.out.println("\nEnter Digits only.");
                    error = true;
                }
                input.nextLine();
            } while(error);
            
            // Switch case to validate User Selection
            switch(selection)
            {
                // Case 1 = Login Function
                // Case 2 = Register Function
                // Case 3 = Quit the Program
                case 1:
                    System.out.print("\n");
                    login login = new login();

                    System.out.println(String.format("%35s", "|===== Login  =====|\n"));
                    login.login();
                    break;

                case 2:
                    System.out.print("\n");
                    register register = new register();

                    System.out.println(String.format("%47s", "|===== Register Account =====|\n"));
                    register.register();

                    do {
                        System.out.print("\n > Return to Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 3:
                	loop = 'N';
                    System.out.println("\n|--------------------------------------------------------------------------------------------------- Program Ended Successfully ---------------------------------------------------------------------------------------------------|\n");
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
        } while(selection < 1 || selection > 3 ||loop == 'Y');
    }
}
