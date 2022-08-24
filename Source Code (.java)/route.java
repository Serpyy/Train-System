import java.util.*;
import java.io.*;

public class route {
    // Data Members;
    private static int nextRouteNo;
    private int routeNo;
    private String fromLocation;
    private String toLocation;
    private double routePrice;

    // Setter;
    public void setRouteNo(int routeNo) {
        this.routeNo = routeNo;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public void setRoutePrice(double routePrice) {
        this.routePrice = routePrice;
    }

    // Getter;
    public int getRouteNo() {
        return routeNo;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public double getRoutePrice() {
        return routePrice;
    }

    // Constructor;
    // No-arg
    public route() {
        routeNo = nextRouteNo;
        fromLocation = "";
        toLocation = "";
        nextRouteNo++;
    }

    // Add Location method
    public static void addRoute()
    {
        // Try open routeInfo.txt file
        try {
            Scanner rRouteInfo = new Scanner(new File("routeInfo.txt"));

            // Use delimiter | and \n as content split in "rRouteInfo" file
            rRouteInfo.useDelimiter("[|>\n]");

            // Check if content in "rRouteInfo" still has next line
            while (rRouteInfo.hasNextLine()) {
                // Set nextRouteNo = routeNo in file and add by 1
                nextRouteNo = rRouteInfo.nextInt() + 1;
                // Move to the next line to get the next routeNo if available
                rRouteInfo.nextLine();
            }

            // Close file read
            rRouteInfo.close();
        }
        // If error occurs during the process
        catch (Exception e) {
            // Tell user that the file not found
            System.out.println("File not found.");
            // Automatically set the first routeNo as 1
            nextRouteNo = 1;
            // Inform users in advance that new "routeInfo.txt" will be created later on
            System.out.println("New file routeInfo.txt will be created.\n");
        }

        // Open Scanner function for user input;
        Scanner input = new Scanner(System.in);

        char loop;
        boolean same = true;
        boolean error;
        // Loop add route process until user enter loop == 'N'
        do {
            route route = new route();

            System.out.println(String.format("%52s", "|===== Add new Route =====|\n"));

            // Looping to check fromLocation and toLocation are different
            do {
                // User enter from location
                System.out.print(" > Enter from Location: ");
                route.setFromLocation(input.nextLine());

                // User enter to location / Destination
                System.out.print(" > Enter the Destination: ");
                route.setToLocation(input.nextLine());

                if (route.getFromLocation().equals(route.getToLocation())) {
                    System.out.println("\nFrom Location and Destination must be different. Please Try Again.");
                    same = true;
                } else {
                    same = false;
                }
            } while (same);

            // Loop route price to validate only digits are entered
            do {
                // User enter route price
                System.out.print(" > Enter Route Price: ");
                try {
                    route.setRoutePrice(input.nextDouble());
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.println("\nEnter Digits only.");
                    error = true;
                }
                input.nextLine();
            } while (error);

            // Try to open routeInfo.txt file, if it doesn't exist then create new routeInfo.txt
            try {
                // BufferedWriter function to write the new Location into routeInfo.txt
                BufferedWriter addRoute = new BufferedWriter(new FileWriter("routeInfo.txt", true));
                addRoute.write(String.format("%d|%s>%s|%.2f\n", route.getRouteNo(), route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));

                // Close the BufferedWriter
                addRoute.close();

                // Inform user the information update is successful
                System.out.println("\nNew route recorded Successfully.\n");
            }
            // catch errors
            catch (Exception e) {
                System.out.println("Error occurs.");
            }

            // For User select whether they want to continue to add new route or not
            do {
                System.out.print(" > Continue Insert Record (Y|N) ? ");
                char tempLoop = input.next().charAt(0);
                loop = Character.toUpperCase(tempLoop);
                System.out.print("\n");

                // If User enter character other than 'Y' and 'N'...
                if (loop != 'Y' && loop != 'N') {
                    System.out.println("Invalid Choice.");
                }
            } while (loop != 'Y' && loop != 'N');

            // rewind stdin
            input.nextLine();
        } while (loop == 'Y');
    }

    // Edit Location method
    public static void editRoute()
    {
        try {
            File routeFile = new File("routeInfo.txt");

            if (routeFile.exists()) {
                route route = new route();

                // Scanner function for User Input
                Scanner input = new Scanner(System.in);

                // Display Route List
                System.out.println(String.format("%128s", "|===== Edit Route =====|\n"));

                routeList();

                // Continue looping if Route No entered doesn't match with records in "routeFile.txt" file
                boolean condition = false;
                boolean interrupt = false;
                while (!condition) {
                    int tempRouteNo = 0;

                    // Train No validation to check user only entered digits
                    boolean errorNo;
                    do {
                        System.out.print(" > Enter Route No to be Edited: ");
                        try {
                            // User input routeNo to be edited
                            tempRouteNo = input.nextInt();
                            errorNo = false;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter only Digits as Route No.");
                            errorNo = true;
                        }
                        input.nextLine();
                    } while (errorNo);

                    try {
                        // Try open file rRouteInfo scanner to scan routeInfo.txt contents
                        Scanner rRouteInfo = new Scanner(routeFile);
                        // Try to open BufferedWriter function and append each line record into new File "tempRoute.txt"
                        BufferedWriter tempW = new BufferedWriter(new FileWriter("tempRoute.txt", false));
                        // Use delimiter |, > and \n as content split in "routeInfo.txt" file
                        rRouteInfo.useDelimiter("[|>\n]");

                        // Loop read "routeInfo.txt" until EOF
                        while (rRouteInfo.hasNext() && !interrupt) {
                            // If next value (1st of line) = int then store in routeNo
                            if (rRouteInfo.hasNextInt()) {
                                route.setRouteNo(rRouteInfo.nextInt());
                            }
                            // Store next value = String in fromLocation
                            route.setFromLocation(rRouteInfo.next());
                            // Store next value = String in toLocation
                            route.setToLocation(rRouteInfo.next());
                            // If next value (last of line) = int then store in routePrice
                            if (rRouteInfo.hasNextDouble()) {
                                route.setRoutePrice(rRouteInfo.nextDouble());
                            }

                            // Compare routeNo from "routeInfo.txt" with tempRouteNo and if are not identical...
                            if (route.getRouteNo() != tempRouteNo) {
                                tempW.write(String.format("%d|%s>%s|%.2f\n", route.getRouteNo(), route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));
                            }
                            // Compare routeNo from "routeInfo.txt" with tempRouteNo and if are identical...
                            else {
                                // Condition looping with user's action confirmation as condition trigger
                                boolean loop = true;
                                do {
                                    boolean same;
                                    do {
                                        // User enter new From Location
                                        System.out.print(" > Enter new From Location: ");
                                        route.setFromLocation(input.nextLine());

                                        // User enter new To location / Destination
                                        System.out.print(" > Enter new Destination: ");
                                        route.setToLocation(input.nextLine());

                                        if (route.getFromLocation().equals(route.getToLocation())) {
                                            System.out.println("\nFrom Location and Destination must be different. Please Try Again.");
                                            same = true;
                                        } else {
                                            same = false;
                                        }
                                    } while (same);

                                    boolean error;
                                    // Loop route price to validate only digits are entered
                                    do {
                                        // User enter route price
                                        System.out.print(" > Enter Route Price: ");
                                        try {
                                            route.setRoutePrice(input.nextDouble());
                                            error = false;
                                        } catch (InputMismatchException e) {
                                            System.out.println("\nEnter Digits only.");
                                            error = true;
                                        }
                                        input.nextLine();
                                    } while (error);

                                    // Print the new Route Information to let user double-check
                                    System.out.println("\n| ROUTE NO\t\t\t\t: " + route.getRouteNo() + "\n| NEW FROM LOCATION\t\t: " + route.getFromLocation() + "\n| NEW DESTINATION\t\t: " + route.getToLocation() + String.format("\n| NEW ROUTE PRICE(RM)\t: %.2f", route.getRoutePrice()));

                                    char tempAction;
                                    char action;
                                    // Let user make confirmation regarding the edit.
                                    System.out.print("\n");
                                    do {
                                        System.out.print(" > Confirm the Action (Y|N) ? ");
                                        tempAction = input.next().charAt(0);
                                        action = Character.toUpperCase(tempAction);
                                        System.out.print("\n");

                                        // If user entered 'Y'...
                                        if (action == 'Y') {
                                            // loop = break from action confirmation loop
                                            loop = false;
                                            // condition = break from user input route info loop
                                            condition = true;

                                            tempW.write(String.format("%d|%s>%s|%.2f\n", route.getRouteNo(), route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));

                                            System.out.println("Information of Train No: " + route.getRouteNo() + " Updated Successfully.\n");
                                        }
                                        // If user entered 'N'...
                                        else if (action == 'N') {
                                            // loop = break from action confirmation loop
                                            loop = false;
                                            // interrupt = break from the read file loop
                                            interrupt = true;
                                            // condition = break from user input route info loop
                                            condition = true;
                                        }
                                        // If user entered char other than 'Y' or 'N'...
                                        else {
                                            System.out.println("Invalid Choice.");
                                        }
                                    } while (action != 'Y' && action != 'N');
                                    input.nextLine();
                                } while (loop);
                            }
                        }
                        // Close all the File functions
                        rRouteInfo.close();
                        tempW.close();

                        // If the edit process was successful...
                        if (condition && !interrupt) {
                            // locate file directory "routeInfo.txt" in oriRoute var
                            File oriRoute = new File("routeInfo.txt");
                            // delete the "routeInfo.txt"
                            boolean checkDel = oriRoute.delete();
                            // If deletion failed...
                            if (!checkDel) {
                                System.out.println("Deletion failed.");
                            }

                            // locate file directory "tempRoute.txt" in oldName var
                            File oldName = new File("tempRoute.txt");
                            // locate new file directory as "routeInfo.txt"
                            File newName = new File("routeInfo.txt");
                            // rename oldName("tempRoute.txt") with newName("routeInfo.txt")
                            boolean checkRename = oldName.renameTo(newName);
                            // If rename failed...
                            if (!checkRename) {
                                System.out.println("Rename failed.");
                            }
                        }
                        // If the edit process is interrupted by users by entering 'N' during action confirmation...
                        else if (interrupt) {
                            // locate file directory "tempRoute.txt"
                            File tempFile = new File("tempRoute.txt");
                            // if "tempRoute.txt" exists...
                            if (tempFile.exists()) {
                                // delete the "tempRoute.txt" file
                                tempFile.delete();
                            }

                            // Inform users that the edit process failed
                            System.out.println("Information of Train No: " + route.getRouteNo() + " failed to update.\n");
                        }
                        // If the edit process was unsuccessful
                        else {
                            // locate file directory "tempRoute.txt"
                            File tempFile = new File("tempRoute.txt");
                            // if "tempRoute.txt" exists...
                            if (tempFile.exists()) {
                                // delete the "tempRoute.txt" file
                                tempFile.delete();
                            }

                            // Inform users that the Train No is not available
                            System.out.println("\nRoute No: " + tempRouteNo + " not found. Please try Again.");
                        }
                    }
                    // If error occurs during the process...
                    catch (Exception e) {
                        System.out.println("Error occurs.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurs.");
        }
    }

    // Delete Location method
    public static void delRoute()
    {
        try {
            File routeFile = new File("routeInfo.txt");

            // If "routeInfo.txt" exists...
            if (routeFile.exists()) {
                route route = new route();

                // Continue looping if Route No entered doesn't match with records in "routeInfo.txt" file
                boolean condition = false;
                while (!condition) {
                    // Scanner function for User Input
                    Scanner input = new Scanner(System.in);

                    System.out.println(String.format("%128s", "|===== Delete Route =====|\n"));

                    routeList();

                    int tempRouteNo = 0;
                    // Route No validation to check user only entered digits
                    boolean errorNo;
                    do {
                        System.out.print(" > Enter Route No to be Deleted: ");
                        try {
                            // User input routeNo to be deleted
                            tempRouteNo = input.nextInt();
                            errorNo = false;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter only Digits as Route No.");
                            errorNo = true;
                        }
                        input.nextLine();
                    } while (errorNo);

                    try {
                        // Try open file rRouteInfo scanner to scan routeInfo.txt contents
                        Scanner rRouteInfo = new Scanner(routeFile);
                        // Try open BufferedWriter to rewrite the contents of "routeInfo.txt"
                        BufferedWriter tempW = new BufferedWriter(new FileWriter("tempRoute.txt", false));
                        // Use delimiter |, > and \n as content split in "routeInfo.txt" file
                        rRouteInfo.useDelimiter("[|>\n]");

                        // Loop read "routeInfo.txt" until EOF
                        boolean delCheck = false;
                        while (rRouteInfo.hasNext()) {
                            // If next value (1st of line) = int then store in routeNo
                            if (rRouteInfo.hasNextInt()) {
                                route.setRouteNo(rRouteInfo.nextInt());
                            }
                            // Store next value = String in fromLocation
                            route.setFromLocation(rRouteInfo.next());
                            // Store next value = String in toLocation
                            route.setToLocation(rRouteInfo.next());

                            if (rRouteInfo.hasNextDouble()) {
                                route.setRoutePrice(rRouteInfo.nextDouble());
                            }

                            // Compare routeNo from "routeInfo.txt" with tempRouteNo and if are not identical...
                            if (route.getRouteNo() != tempRouteNo) {
                                // If selected route deleted...
                                if (delCheck) {
                                    tempW.write(String.format("%d|%s>%s.2f\n", route.getRouteNo() - 1, route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));
                                }
                                // If selected route is not deleted...
                                else {
                                    tempW.write(String.format("%d|%s>%s|%.2f\n", route.getRouteNo(), route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));
                                }
                            }
                            // Compare routeNo from "routeInfo.txt" with tempRouteNo and if are identical...
                            else {
                                // Condition looping with user's action confirmation as condition trigger
                                condition = true;
                                boolean loop = true;
                                do {
                                    // Print the Route Information to let user double-check
                                    System.out.println("\n| ROUTE NO\t\t\t: " + route.getRouteNo() + "\n| FROM LOCATION\t\t: " + route.getFromLocation() + "\n| DESTINATION\t\t: " + route.getToLocation() + String.format("\n| ROUTE PRICE(RM)\t: %.2f", route.getRoutePrice()));

                                    char tempAction;
                                    char action;
                                    // Let user make confirmation regarding the edit.
                                    System.out.print("\n");
                                    do {
                                        System.out.print(" > Confirm the Deletion (Y|N) ? ");
                                        tempAction = input.next().charAt(0);
                                        action = Character.toUpperCase(tempAction);

                                        // If user entered 'Y'...
                                        if (action == 'Y') {
                                            condition = true;
                                            delCheck = true;
                                            loop = false;

                                            System.out.println("\nRoute No: " + route.getRouteNo() + " deleted.\n");
                                        }
                                        // If users entered 'N'...
                                        else if (action == 'N') {
                                            loop = false;

                                            tempW.write(String.format("%d|%s>%s|%.2f\n", route.getRouteNo(), route.getFromLocation(), route.getToLocation(), route.getRoutePrice()));

                                            System.out.println("\nDeletion of Route No: " + route.getRouteNo() + " failed.\n");
                                        }
                                        // If user entered char other than 'Y' or 'N'...
                                        else {
                                            System.out.println("\nInvalid Choice.");
                                        }
                                    } while (action != 'Y' && action != 'N');
                                    input.nextLine();
                                } while (loop);
                            }
                        }
                        // Close all the File functions
                        rRouteInfo.close();
                        tempW.close();

                        // If identical Route No. found after read file until EOF...
                        if (condition) {
                            // locate file directory "routeInfo.txt" in oriRoute var
                            File oriRoute = new File("routeInfo.txt");
                            // delete the "routeInfo.txt"
                            boolean checkDel = oriRoute.delete();
                            // If deletion failed...
                            if (!checkDel) {
                                System.out.println("Deletion failed.");
                            }

                            // locate file directory "tempRoute.txt" in oldName var
                            File oldName = new File("tempRoute.txt");
                            // locate new file directory as "routeInfo.txt"
                            File newName = new File("routeInfo.txt");
                            // rename oldName("tempRoute.txt") with newName("routeInfo.txt")
                            boolean checkRename = oldName.renameTo(newName);
                            // If rename failed...
                            if (!checkRename) {
                                System.out.println("Rename failed.");
                            }
                        }
                        // If no identical Route No. found after read file until EOF...
                        else {
                            System.out.println("\nRoute No: " + tempRouteNo + " not found. Please try Again.\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Error occurs.\n");
                    }
                }
            }
        }
        // If error occurs during the process...
        catch (Exception e) {
            System.out.println("Error occurs.\n");
        }
    }

    // Display Route List method
    public static void routeList()
    {
        int line = 0;
        try {
            File route = new File("routeInfo.txt");

            if (route.exists()) {
                // Open BufferedReader to read "routeInfo.txt"
                BufferedReader lineCount = new BufferedReader(new FileReader(route));
                // Open scanner as rRouteInfo to scan "routeInfo.txt" values
                Scanner rRouteInfo = new Scanner(route);

                // Loop until EOF to count the number of lines
                while (lineCount.readLine() != null) {
                    line++;
                }
                // Close BufferedReader function after obtained the number of lines
                lineCount.close();

                // Open routeList array to record each route Information into different route object
                route routeList[] = new route[line];
                for (int i = 0; i < routeList.length; i++) {
                    routeList[i] = new route();
                }

                // Use delimiter |, > and \n as content split in "routeInfo.txt" file
                rRouteInfo.useDelimiter("[|>\n]");
                int index = 0;
                // Read and scan "routeInfo.txt" until EOF to obtain all route information into each array indexes
                while (rRouteInfo.hasNext() && index < routeList.length) {
                    // If next value (first of line) = int then store in routeNo
                    if (rRouteInfo.hasNextInt()) {
                        routeList[index].setRouteNo(rRouteInfo.nextInt());
                    }
                    // Store next value = String in fromLocation
                    routeList[index].setFromLocation(rRouteInfo.next());
                    // Store next value = String in toLocation
                    routeList[index].setToLocation(rRouteInfo.next());

                    if (rRouteInfo.hasNextDouble()) {
                        routeList[index].setRoutePrice(rRouteInfo.nextDouble());
                    }

                    index++;
                }
                // Close rRouteInfo scanner after all the information are successfully jotted into each array indexes
                rRouteInfo.close();

                // To display the Route List
                System.out.println(String.format("%67s==============+================================+===============================+===========+", "+"));
                System.out.println(String.format("%67s %10s %3s %21s %10s %20s %10s %9s %1s", "|", "Route No", "|", "From Location", "|", "Destination", "|", "Price(RM)", "|"));
                System.out.println(String.format("%67s==============+================================+===============================+===========+", "+"));

                for (int i = 0; i < routeList.length; i++) {
                    System.out.println(String.format("%67s %7d %6s %-30s %s %-29s %s %7.2f %3s", "|", routeList[i].getRouteNo(), "|", routeList[i].getFromLocation(), "|", routeList[i].getToLocation(), "|", routeList[i].getRoutePrice(), "|"));
                }

                System.out.println(String.format("%67s==============+================================+===============================+===========+\n", "+"));
            }
        }
        // If there are errors occur during process
        catch (Exception e) {
            System.out.println("Error occurs.");
            e.printStackTrace();
        }
    }

    // Return routeList array
    public static route[] storeRoute() throws Exception
    {
        int line = 0;

        File route = new File("routeInfo.txt");

        // Open BufferedReader to read "routeInfo.txt"
        BufferedReader lineCount = new BufferedReader(new FileReader(route));
        // Open scanner as rRouteInfo to scan "routeInfo.txt" values
        Scanner rRouteInfo = new Scanner(route);

        // Loop until EOF to count the number of lines
        while (lineCount.readLine() != null) {
            line++;
        }
        // Close BufferedReader function after obtained the number of lines
        lineCount.close();

        // Open routeList array to record each route Information into different route object
        route[] routeList = new route[line];
        for (int i = 0; i < routeList.length; i++) {
            routeList[i] = new route();
        }

        // Use delimiter |, > and \n as content split in "routeInfo.txt" file
        rRouteInfo.useDelimiter("[|>\n]");
        int index = 0;
        // Read and scan "routeInfo.txt" until EOF to obtain all route information into each array indexes
        while (rRouteInfo.hasNext() && index < routeList.length) {
            // If next value (first of line) = int then store in routeNo
            if (rRouteInfo.hasNextInt()) {
                routeList[index].setRouteNo(rRouteInfo.nextInt());
            }
            // Store next value = String in fromLocation
            routeList[index].setFromLocation(rRouteInfo.next());
            // Store next value = String in toLocation
            routeList[index].setToLocation(rRouteInfo.next());
            // If next value (last of line) = double then store in routePrice
            if (rRouteInfo.hasNextDouble()) {
                routeList[index].setRoutePrice(rRouteInfo.nextDouble());
            }
            index++;
        }
        // Close rRouteInfo scanner after all the information are successfully jotted into each array indexes
        rRouteInfo.close();

        return routeList;
    }
}