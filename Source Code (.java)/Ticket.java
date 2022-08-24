import java.util.*;

public class Ticket {
    private UUID ticketNo;
    private int noOfPax;
    public static Passenger passenger = new Passenger();
    
    public Ticket(){
        ticketNo = UUID.randomUUID();
    }
    
    public Ticket(int noOfPax){
    	ticketNo = UUID.randomUUID();
    	this.noOfPax = noOfPax;
    }

    public void setTicketNo(UUID ticketNo) {
        this.ticketNo = ticketNo;
    }

    public UUID getTicketNo() {
        return ticketNo;
    }

    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }
    
    public int getNoOfPax() {
        return noOfPax;
    }

    public static void ticketMenu() throws Exception
    {
    	Ticket ticket = new Ticket();
        Scanner input = new Scanner(System.in);
        
        boolean nameLoop = true;
		System.out.println(String.format("\n%47s", "|===== Book Ticket =====|"));
			
		do{
			System.out.printf("\n > Enter your Name: ");
			passenger.setName(input.nextLine());

		 for (int i = 0; i < passenger.getName().length(); i++)
                {
                    if (!Character.isLetter(passenger.getName().charAt(i)) && !Character.isWhitespace(passenger.getName().charAt(i))) {
                        nameLoop = true;
                        System.out.println("\nPlease enter letters only.");
                        break;
                    }
                    nameLoop = false;
                }
		}while(nameLoop);
		
        boolean icLoop = true;
        do {
            System.out.printf(" > Enter your IC Number [NO (-)]: ");
            passenger.setIC(input.nextLine());
            if (passenger.getIC().length() != 12)
            {
                icLoop = true;
                System.out.println("\nInvalid IC Number. Please Try Again.");
            }
            else {
                for (int i = 0; i < passenger.getIC().length(); i++)
                {
                    if (!Character.isDigit(passenger.getIC().charAt(i))) {
                        icLoop = true;
                        System.out.println("\nPlease enter digits only.");
                        break;
                    }
                    icLoop = false;
                }
            }
        } while (icLoop) ;

        boolean phoneLoop = true;
        do {
            System.out.print(" > Enter your Phone Number [NO (-)]: ");
            passenger.setPhoneNo(input.nextLine());
            if (passenger.getPhoneNo().length() < 10 || passenger.getPhoneNo().length() > 11)
            {
                phoneLoop = true;
                System.out.println("\nInvalid Phone Number. Please Try Again.");
            }
            else {
                for (int i = 0; i < passenger.getPhoneNo().length(); i++)
                {
                    if (!Character.isDigit(passenger.getPhoneNo().charAt(i))) {
                        phoneLoop = true;
                        System.out.println("\nPlease enter digits only.");
                        break;
                    }
                    phoneLoop = false;
                }
            }
        } while (phoneLoop) ;

        // User select route
        route[] routeList = route.storeRoute();
            // temp route var
            route tempRoute = new route();
            double tempTrainPrice = 0.0;

        // To display the Route List
        System.out.println(String.format("\n%67s==============+================================+===============================+===========+", "+"));
        System.out.println(String.format("%67s %10s %3s %21s %10s %20s %10s %9s %1s", "|", "Route No", "|", "From Location", "|", "Destination", "|", "Price(RM)", "|"));
        System.out.println(String.format("%67s==============+================================+===============================+===========+", "+"));

        for (int i = 0; i < routeList.length; i++) {
            System.out.println(String.format("%67s %7d %6s %-30s %s %-29s %s %7.2f %3s", "|", routeList[i].getRouteNo(), "|", routeList[i].getFromLocation(), "|", routeList[i].getToLocation(), "|", routeList[i].getRoutePrice(), "|"));
        }
        System.out.println(String.format("%67s==============+================================+===============================+===========+\n", "+"));

        int tempRouteNo = 0;
        boolean errorNo;
        do {
            do {
                System.out.printf(" > Enter desired Route (1-%d): ", routeList.length);
                try {
                    // User input routeNo
                    tempRouteNo = input.nextInt();
                    errorNo = false;
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease enter only Digits as Route No.");
                    errorNo = true;
                }
                input.nextLine();
            } while (errorNo);
            if (tempRouteNo < 1 || tempRouteNo > routeList.length) {
                System.out.println("\nPlease enter only Digits 1 to " + routeList.length + " as Route No.");
            }
        } while(tempRouteNo < 1 || tempRouteNo > routeList.length);

        // Store user chosen route into temp var
        for (int i = 0; i < routeList.length; i++) {
            if (tempRouteNo == routeList[i].getRouteNo()) {
                tempRoute.setFromLocation(routeList[i].getFromLocation());
                tempRoute.setToLocation(routeList[i].getToLocation());
                tempRoute.setRoutePrice(routeList[i].getRoutePrice());
            }
        }

        // User select train
        train[] trainList = train.storeTrain();
            // temp train var
            train tempTrain = new train();

        int lastIndex = trainList.length - 1;

        // To display the Train List
        System.out.println(String.format("\n%70s==========+===========================================+============+==========+", "+"));
        System.out.println(String.format("%70s %s | \t\t\t\t%-28s| %s | %s |", "|", "Train No", "Train Name", "Train Type", "Max Seat"));
        System.out.println(String.format("%70s==========+===========================================+============+==========+", "+"));

        for (int i = 0; i < trainList.length; i++) {
            System.out.println(String.format("%70s%7d\t| %-42s| %6s\t |%6d\t|", "|", trainList[i].getTrainNo(), trainList[i].getTrainName(), trainList[i].getTrainType(), trainList[i].getMaxSeat()));
        }

        System.out.println(String.format("%70s==========+===========================================+============+==========+\n", "+"));

        int tempTrainNo = 0;
        do {
            do {
                System.out.print(" > Enter desired Train: ");
                try {
                    // User input trainNo
                    tempTrainNo = input.nextInt();
                    errorNo = false;
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease enter only Digits as Train No.");
                    errorNo = true;
                }
                input.nextLine();
            } while (errorNo);

            if (tempTrainNo < trainList[0].getTrainNo() || tempTrainNo > trainList[lastIndex].getTrainNo())
            {
                System.out.println("\nPlease enter only " + trainList[0].getTrainNo() + " to " + trainList[lastIndex].getTrainNo() + " as Train No.");
            }
        } while(tempTrainNo < trainList[0].getTrainNo() || tempTrainNo > trainList[lastIndex].getTrainNo());

        // Store user chosen train into temp var
        for (int i = 0; i < trainList.length; i++) {
            if (tempTrainNo == trainList[i].getTrainNo()) {
                tempTrain.setTrainName(trainList[i].getTrainName());
                tempTrain.setTrainType(trainList[i].getTrainType());
                tempTrainPrice = trainList[i].trainPrice(tempTrain.getTrainType());
            }
        }

        System.out.print(" > Purchase Quantity: ");
        ticket.setNoOfPax(input.nextInt());

        System.out.println("\nPlease check the details below:");
        System.out.println("+----------------------------------------------+");
        System.out.println("                Personal Details:\n");
        System.out.println("NAME        : " + passenger.getName());
        System.out.println("IC NUMBER   : " + passenger.getIC());
        System.out.println("PHONE NUMBER: " + passenger.getPhoneNo());
        System.out.println("+----------------------------------------------+");
        System.out.println("                Route Details:\n");
        System.out.println("FROM LOCATION   : " + tempRoute.getFromLocation());
        System.out.println("DESTINATION     : " + tempRoute.getToLocation());
        System.out.printf("ROUTE PRICE     : RM%.2f\n", tempRoute.getRoutePrice());
        System.out.println("+----------------------------------------------+");
        System.out.println("                 Train Details:\n");
        System.out.println(tempTrain.toString());
        System.out.println("+----------------------------------------------+");
        System.out.println("TICKET NO   : " + ticket.getTicketNo());
        System.out.println("NO OF PAX   : " + ticket.getNoOfPax());
        System.out.printf("FINAL PRICE : RM%.2f\n", ticket.calcTotal(tempRoute.getRoutePrice(), tempTrainPrice, ticket.noOfPax));
        System.out.println("+----------------------------------------------+\n");
		
		double tempfinal = ticket.calcTotal(tempRoute.getRoutePrice(), tempTrainPrice, ticket.noOfPax);
		
        PaymentMain payment = new PaymentMain();
		payment.payment(tempRoute.getFromLocation(), tempRoute.getToLocation(), tempTrain.getTrainName(), tempTrain.getTrainType(), tempfinal, ticket.noOfPax, ticket.getTicketNo());
        
    }
    
    public static double calcTotal(double routePrice, double trainPrice, int noOfPax) {
        double finalTotal = (routePrice + trainPrice) * noOfPax;
        return finalTotal;
    }
}
