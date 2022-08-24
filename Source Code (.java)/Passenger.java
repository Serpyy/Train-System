import java.util.*;

public class Passenger extends Person{
	
	public Passenger(){
	}
	public Passenger(String name, String ic, String phoneNo){
		super(name, ic, phoneNo);
	}
	
    public static void passengerMenu() throws Exception{
        Scanner input = new Scanner(System.in);
        char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        
        do{
        	System.out.println(String.format("\n\n+=====================+"));
        	System.out.println(String.format("|%17s%5s", "Passenger Menu", "|"));
        	System.out.println(String.format("+=====+===============+"));
        	System.out.println(String.format("|%5s|%14s%2s", "1  ", "Book a Ticket", "|"));
        	System.out.println(String.format("|%5s|%8s%8s", "2  ", "Log out", "|"));
        	System.out.println(String.format("+=====+===============+\n")); 
        		
        	do {
                System.out.print(" > Enter Selection (1-2) : ");
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
            
            switch(selection)
            {
                case 1:
                    Ticket ticketmenu = new Ticket();
                    ticketmenu.ticketMenu();
                    break;
                     
                case 2:
                    loop = 'N';
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
            
        }while(selection < 1 || selection > 5 || loop == 'Y');
    }
}
	

/*
             do {
                        System.out.print("\n > Return to Menu (Y|N) ? ");
                        tempLoop = scanner.next().charAt(0);
                        loop = Character.toUpperCase(tempLoop);
                        System.out.print("\n");

                        if (loop != 'Y' && loop != 'N') {
                            System.out.println("Invalid Choice.");
                        }
                        
                    }while(loop != 'Y' && loop != 'N');
             try {
                int num = input.nextInt();
                if (num == 1 || num == 0) {
                    return num;
                } else {
                    System.err.println("Invalid Option");
                    input.nextLine();
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Invalid input. Number required");
                input.nextLine();
            }*/