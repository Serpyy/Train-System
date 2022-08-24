import java.util.*;

public class routeMenu
{
    public static void routeMenu(){
        // open Scanner function for user input;
        Scanner input = new Scanner(System.in);

        // Loop the Location Menu
        char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        do {
            // Generate and Display Location Menu for User to Select
            System.out.println(String.format("\n+=================================+"));
            System.out.println(String.format("|%21s%13s", "Route Menu", "|"));
            System.out.println(String.format("+=====+===========================+"));
            System.out.println(String.format("|%5s|%15s%13s", "1  ", "Add New Route", "|"));
            System.out.println(String.format("|%5s|%20s%8s", "2  ", "Edit Existed Route", "|"));
            System.out.println(String.format("|%5s|%22s%6s", "3  ", "Delete Existed Route", "|"));
            System.out.println(String.format("|%5s|%20s%8s", "4  ", "Display Route List", "|"));
            System.out.println(String.format("|%5s|%16s%12s", "5  ", "Return to Menu", "|"));
            System.out.println(String.format("+=====+===========================+\n"));

            // User Input their Selection
            do {
                System.out.print(" > Enter Selection (1-5) : ");
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
            switch (selection) {
                case 1:
                    System.out.print("\n");
                    route aRoute = new route();

                    aRoute.addRoute();
                    do {
                        System.out.print(" > Return to Route Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 2:
                    System.out.print("\n");
                    route eRoute = new route();

                    eRoute.editRoute();
                    do {
                        System.out.print(" > Return to Route Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 3:
                    System.out.print("\n");
                    route delRoute = new route();

                    delRoute.delRoute();
                    do {
                        System.out.print(" > Return to Route Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 4:
                    System.out.print("\n");
                    route displayRoute = new route();

                    displayRoute.routeList();
                    do {
                        System.out.print(" > Return to Route Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 5:
                    System.out.println("\nSelected Return to Menu...\n");
                    loop = 'N';
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
        } while(selection < 1 || selection > 5 || loop == 'Y');
    }
}
