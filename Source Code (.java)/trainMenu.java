import java.util.*;

public class trainMenu
{
    public static void trainMenu(){
        // open Scanner function for user input;
        Scanner input = new Scanner(System.in);

        // Loop the Train Menu
        char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        do {
            // Generate and Display Train Information Menu for User to Select
            System.out.println(String.format("\n+==============================+"));
            System.out.println(String.format("|%26s%5s", "Train Information Menu", "|"));
            System.out.println(String.format("+=====+========================+"));
            System.out.println(String.format("|%5s|%14s%11s", "1  ", "Add New Train", "|"));
            System.out.println(String.format("|%5s|%19s%6s", "2  ", "Edit Existed Train", "|"));
            System.out.println(String.format("|%5s|%21s%4s", "3  ", "Delete Existed Train", "|"));
            System.out.println(String.format("|%5s|%19s%6s", "4  ", "Display Train List", "|"));
            System.out.println(String.format("|%5s|%15s%10s", "5  ", "Return to Menu", "|"));
            System.out.println(String.format("+=====+========================+\n"));

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
                    train aTrain = new train();

                    aTrain.addTrain();
                    do {
                        System.out.print(" > Return to Train Menu (Y|N) ? ");
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
                    train eTrain = new train();

                    eTrain.editTrain();
                    do {
                        System.out.print(" > Return to Train Menu (Y|N) ? ");
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
                    train delTrain = new train();

                    delTrain.delTrain();
                    do {
                        System.out.print(" > Return to Train Menu (Y|N) ? ");
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
                    train displayTrain = new train();

                    displayTrain.trainList();
                    do {
                        System.out.print(" > Return to Train Menu (Y|N) ? ");
                        tempLoop = input.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                    }while(loop != 'Y' && loop != 'N');
                    break;

                case 5:
                    System.out.println("Selected Return to Menu...");
                    loop = 'N';
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
        } while(selection < 1 || selection > 5 || loop == 'Y');
    }
}