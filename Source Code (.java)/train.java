import java.util.*;
import java.io.*;

public class train {
    // Data Members;
    private static int nextTrainNo;
    private int trainNo;
    private String trainName;
    private String trainType;
    private int maxSeat;

    // Setter;
    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public void setMaxSeat(int maxSeat) {
        this.maxSeat = maxSeat;
    }

    // Getter;
    public int getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainType() {
        return trainType;
    }

    public int getMaxSeat() {
        return maxSeat;
    }

    // Constructor;
    // No-arg
    public train() {
        trainNo = nextTrainNo;
        trainName = "";
        trainType = "";
        maxSeat = 0;
        nextTrainNo++;
    }

    // Add Train Method;
    public static void addTrain() {
        // Try open the file "trainInfo.txt"
        try {
            Scanner rTrainInfo = new Scanner(new File("trainInfo.txt"));

            // Use delimiter | and \n as content split in "trainInfo.txt" file
            rTrainInfo.useDelimiter("[|\n]");

            // Check if content in "trainInfo.txt" still has next line
            while (rTrainInfo.hasNextLine()) {
                // Set nextTrainNo = trainNo in file and add by 1
                nextTrainNo = rTrainInfo.nextInt() + 1;
                // Move to the next line to get the next trainNo if available
                rTrainInfo.nextLine();
            }

            // Close file read
            rTrainInfo.close();
        }
        // If "trainInfo.txt" not found
        catch (Exception e) {
            // Tell user that the file not found
            System.out.println("File not found.");
            // Automatically set the first trainNo as 1001
            nextTrainNo = 1001;
            // Inform users in advance that new "trainInfo.txt" will be created later on
            System.out.println("New file trainInfo.txt will be created.");
        }

        // Open Scanner function for user input;
        Scanner input = new Scanner(System.in);

        char loop;
        boolean error;
        // Loop the add train process until user enter loop == 'N'
        do {
            train train = new train();

            System.out.println(String.format("%49s", "|===== Add new Train =====|\n"));

            // User Input Train Name;
            System.out.print(" > Enter Train Name: ");
            train.setTrainName(input.nextLine().trim());

            // User Input Train Type;
            System.out.print(" > Enter Train Type: ");
            train.setTrainType(input.nextLine().trim());

            // Loop Max Seat to validate only Digits are entered.
            do {
                System.out.print(" > Enter Max Seat: ");
                // User Input Max Seat
                try {
                    train.setMaxSeat(input.nextInt());
                    error = false;
                }
                // If Max Seat entered not Integer;
                catch (InputMismatchException e) {
                    System.out.println("\nEnter Digits Only.");
                    error = true;
                }
                input.nextLine();
            } while (error);

            // Try to open the file "trainInfo.txt", if it doesn't exist then create new "trainInfo.txt"
            try {
                // BufferedWriter function to write the Train Information in the file "trainInfo.txt"
                BufferedWriter addTrain = new BufferedWriter(new FileWriter("trainInfo.txt", true));
                addTrain.write(String.format("%d|%s|%s|%d\n", train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getMaxSeat()));

                // Close BufferedWriter function
                addTrain.close();

                // Inform user the information update is successful
                System.out.println("\nNew train recorded Successfully.\n");
            }
            // catch errors
            catch (Exception e) {
                System.out.println("Error occurs.");
            }

            // For User select whether they want to continue to add new Train or not
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
            input.nextLine();
        } while (loop == 'Y');
    }

    // Edit Train Method;
    public static void editTrain() {
        try {
            File trainFile = new File("trainInfo.txt");

            if (trainFile.exists()) {
                train train = new train();

                // Scanner function for User Input
                Scanner input = new Scanner(System.in);

                // Display Train List
                System.out.println(String.format("%124s", "|===== Edit Train =====|\n"));

                trainList();

                // Continue looping if Train No entered doesn't match with records in "trainInfo.txt" file
                boolean condition = false;
                boolean interrupt = false;
                while (!condition) {
                    int tempTrainNo = 0;

                    // Train No validation to check user only entered digits
                    boolean errorNo;
                    do {
                        System.out.print(" > Enter Train No to be Edited: ");
                        try {
                            // User input trainNo to be edited
                            tempTrainNo = input.nextInt();
                            errorNo = false;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter only Digits as Train No.");
                            errorNo = true;
                        }
                        input.nextLine();
                    } while (errorNo);

                    try {
                        // Try open file rTrainInfo scanner to scan trainInfo.txt contents
                        Scanner rTrainInfo = new Scanner(trainFile);
                        // Try to open BufferedWriter function and append each line record into new File "tempTrain.txt"
                        BufferedWriter tempW = new BufferedWriter(new FileWriter("tempTrain.txt", false));
                        // Use delimiter | and \n as content split in "trainInfo.txt" file
                        rTrainInfo.useDelimiter("[|\n]");

                        // Loop read "trainInfo.txt" until EOF
                        while (rTrainInfo.hasNext() && !interrupt) {
                            // If next value (1st of line) = int then store in TrainNo
                            if (rTrainInfo.hasNextInt()) {
                                train.setTrainNo(rTrainInfo.nextInt());
                            }
                            // Store next value = String in TrainName
                            train.setTrainName(rTrainInfo.next());
                            // Store next value = String in TrainType
                            train.setTrainType(rTrainInfo.next());
                            // If next value (last of line) = int then store in MaxSeat
                            if (rTrainInfo.hasNextInt()) {
                                train.setMaxSeat(rTrainInfo.nextInt());
                            }

                            // Compare TrainNo from "trainInfo.txt" with tempTrainNo and if are not identical...
                            if (train.getTrainNo() != tempTrainNo) {
                                tempW.write(String.format("%d|%s|%s|%d\n", train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getMaxSeat()));
                            }
                            // Compare TrainNo from "trainInfo.txt" with tempTrainNo and if are identical...
                            else {
                                // Condition looping with user's action confirmation as condition trigger
                                boolean loop = true;
                                do {
                                    System.out.print(" > Enter new Train Name: ");
                                    train.setTrainName(input.nextLine());
                                    System.out.print(" > Enter new Train Type: ");
                                    train.setTrainType(input.nextLine());

                                    boolean seatError;
                                    // Loop Max Seat to validate only Digits are entered
                                    do {
                                        System.out.print(" > Enter Max Seat: ");
                                        // User Input Train Max Seat
                                        try {
                                            train.setMaxSeat(input.nextInt());
                                            seatError = false;
                                        }
                                        // If Max Seat entered not Integer;
                                        catch (InputMismatchException e) {
                                            System.out.println("\nEnter Digits Only.");
                                            seatError = true;
                                        }
                                        input.nextLine();
                                    } while (seatError);

                                    // Print the new Train Information to let user double-check the information to make sure input correctly
                                    System.out.println("\n| TRAIN NO\t\t\t: " + train.getTrainNo() + "\n| NEW TRAIN NAME\t: " + train.getTrainName() + "\n| NEW TRAIN TYPE\t: " + train.getTrainType() + "\n| NEW MAX SEAT\t\t: " + train.getMaxSeat());

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
                                            // condition = break from user input train info loop
                                            condition = true;

                                            tempW.write(String.format("%d|%s|%s|%d\n", train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getMaxSeat()));

                                            System.out.println("Information of Train No: " + train.getTrainNo() + " Updated Successfully.\n");
                                        }
                                        // If user entered 'N'...
                                        else if (action == 'N') {
                                            // loop = break from action confirmation loop
                                            loop = false;
                                            // interrupt = break from the read file loop
                                            interrupt = true;
                                            // condition = break from user input train info loop
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
                        rTrainInfo.close();
                        tempW.close();

                        // If the edit process was successful...
                        if (condition && !interrupt) {
                            // locate file directory "trainInfo.txt" in oriTrain var
                            File oriTrain = new File("trainInfo.txt");
                            // delete the "trainInfo.txt"
                            boolean checkDel = oriTrain.delete();
                            // If deletion failed...
                            if (!checkDel) {
                                System.out.println("Deletion failed.");
                            }

                            // locate file directory "tempTrain.txt" in oldName var
                            File oldName = new File("tempTrain.txt");
                            // locate new file directory as "trainInfo.txt"
                            File newName = new File("trainInfo.txt");
                            // rename oldName("tempTrain.txt") with newName("trainInfo.txt")
                            boolean checkRename = oldName.renameTo(newName);
                            // If rename failed...
                            if (!checkRename) {
                                System.out.println("Rename failed.");
                            }
                        }
                        // If the edit process is interrupted by users by entering 'N' during action confirmation...
                        else if (interrupt) {
                            // locate file directory "tempTrain.txt"
                            File tempFile = new File("tempTrain.txt");
                            // if "tempTrain.txt" exists...
                            if (tempFile.exists()) {
                                // delete the "tempTrain.txt" file
                                tempFile.delete();
                            }

                            // Inform users that the edit process failed
                            System.out.println("Information of Train No: " + train.getTrainNo() + " failed to update.\n");
                        }
                        // If the edit process was unsuccessful
                        else {
                            // locate file directory "tempTrain.txt"
                            File tempFile = new File("tempTrain.txt");
                            // if "tempTrain.txt" exists...
                            if (tempFile.exists()) {
                                // delete the "tempTrain.txt" file
                                tempFile.delete();
                            }

                            // Inform users t hat the Train No is not available
                            System.out.println("\nTrain No: " + tempTrainNo + " not found. Please try Again.");
                        }
                    }
                    // If error occurs during the process...
                    catch (Exception e) {
                        System.out.println("Error occurs.");
                    }
                }
            }
        }
        // If error occurs during the process...
        catch (Exception e) {
            System.out.println("Error occurs.\n");
        }
    }

    // Delete Train Method;
    public static void delTrain() {
        try {
            File trainFile = new File("trainInfo.txt");

            // If "trainInfo.txt" exists...
            if (trainFile.exists()) {
                train train = new train();

                // Continue looping if Train No entered doesn't match with records in "trainInfo.txt" file
                boolean condition = false;
                while (!condition) {
                    // Scanner function for User Input
                    Scanner input = new Scanner(System.in);

                    System.out.println(String.format("%124s", "|===== Delete Train =====|\n"));

                    trainList();

                    int tempTrainNo = 0;
                    // Train No validation to check user only entered digits
                    boolean errorNo;
                    do {
                        System.out.print(" > Enter Train No to be Deleted: ");
                        try {
                            // User input trainNo to be edited
                            tempTrainNo = input.nextInt();
                            errorNo = false;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter only Digits as Train No.");
                            errorNo = true;
                        }
                        input.nextLine();
                    } while (errorNo);

                    try {
                        // Try open file rTrainInfo scanner to scan trainInfo.txt contents
                        Scanner rTrainInfo = new Scanner(trainFile);
                        // Try open BufferedWriter to rewrite the contents of "trainInfo.txt"
                        BufferedWriter tempW = new BufferedWriter(new FileWriter("tempTrain.txt", false));
                        // Use delimiter | and \n as content split in "trainInfo.txt" file
                        rTrainInfo.useDelimiter("[|\n]");

                        // Loop read "trainInfo.txt" until EOF
                        boolean delCheck = false;
                        while (rTrainInfo.hasNext()) {
                            // If next value (1st of line) = int then store in TrainNo
                            if (rTrainInfo.hasNextInt()) {
                                train.setTrainNo(rTrainInfo.nextInt());
                            }
                            // Store next value = String in TrainName
                            train.setTrainName(rTrainInfo.next());
                            // Store next value = String in TrainType
                            train.setTrainType(rTrainInfo.next());
                            // If next value (last of line) = int then store in MaxSeat
                            if (rTrainInfo.hasNextInt()) {
                                train.setMaxSeat(rTrainInfo.nextInt());
                            }

                            // Compare TrainNo from "trainInfo.txt" with tempTrainNo and if are not identical...
                            if (train.getTrainNo() != tempTrainNo) {
                                // If selected Train Information deleted...
                                if (delCheck) {
                                    tempW.write(String.format("%d|%s|%s|%d\n", train.getTrainNo() - 1, train.getTrainName(), train.getTrainType(), train.getMaxSeat()));
                                }
                                // If selected Train Information is not deleted...
                                else {
                                    tempW.write(String.format("%d|%s|%s|%d\n", train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getMaxSeat()));
                                }
                            }
                            // Compare TrainNo from "trainInfo.txt" with tempTrainNo and if are identical...
                            else {
                                // Condition looping with user's action confirmation as condition trigger
                                condition = true;
                                boolean loop = true;
                                do {
                                    // Print the Train Information to let user double-check the train info that they want to delete
                                    System.out.println("\n| TRAIN NO\t\t: " + train.getTrainNo() + "\n| TRAIN NAME\t: " + train.getTrainName() + "\n| TRAIN TYPE\t: " + train.getTrainType() + "\n| MAX SEAT\t\t: " + train.getMaxSeat());

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

                                            System.out.println("\nInformation of Train No: " + train.getTrainNo() + " deleted.\n");
                                        }
                                        // If users entered 'N'...
                                        else if (action == 'N') {
                                            loop = false;

                                            tempW.write(String.format("%d|%s|%s|%d\n", train.getTrainNo(), train.getTrainName(), train.getTrainType(), train.getMaxSeat()));

                                            System.out.println("\nDeletion of Train No: " + train.getTrainNo() + " failed.\n");
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
                        rTrainInfo.close();
                        tempW.close();

                        // If identical Train No. found after read file until EOF...
                        if (condition) {
                            // locate file directory "trainInfo.txt" in oriTrain var
                            File oriTrain = new File("trainInfo.txt");
                            // delete the "trainInfo.txt"
                            boolean checkDel = oriTrain.delete();
                            // If deletion failed...
                            if (!checkDel) {
                                System.out.println("Deletion failed.");
                            }

                            // locate file directory "tempTrain.txt" in oldName var
                            File oldName = new File("tempTrain.txt");
                            // locate new file directory as "trainInfo.txt"
                            File newName = new File("trainInfo.txt");
                            // rename oldName("tempTrain.txt") with newName("trainInfo.txt")
                            boolean checkRename = oldName.renameTo(newName);
                            // If rename failed...
                            if (!checkRename) {
                                System.out.println("Rename failed.");
                            }
                        }
                        // If no identical Train No. found after read file until EOF...
                        else {
                            System.out.println("\nTrain No: " + tempTrainNo + " not found. Please try Again.\n");
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

    // Display Train List Method;
    public static void trainList() {
        int line = 0;
        try {
            File train = new File("trainInfo.txt");

            if (train.exists()) {
                // Open BufferedReader to read "trainInfo.txt"
                BufferedReader lineCount = new BufferedReader(new FileReader(train));
                // Open scanner as rTrainInfo to scan "trainInfo.txt" values
                Scanner rTrainInfo = new Scanner(train);

                // Loop until EOF to count the number of lines
                while (lineCount.readLine() != null) {
                    line++;
                }
                // Close BufferedReader function after obtained the number of lines
                lineCount.close();

                // Open trainList array to record each train Information into different train object
                train trainList[] = new train[line];
                for (int i = 0; i < trainList.length; i++) {
                    trainList[i] = new train();
                }

                // Use delimiter | and \n as content split in "trainInfo.txt" file
                rTrainInfo.useDelimiter("[|\n]");
                int index = 0;
                // Read and scan "trainInfo.txt" until EOF to obtain all train information into each array indexes
                while (rTrainInfo.hasNext() && index < trainList.length) {
                    // If next value (first of line) = int then store in TrainNo
                    if (rTrainInfo.hasNextInt()) {
                        trainList[index].setTrainNo(rTrainInfo.nextInt());
                    }
                    // Store next value = String in TrainName
                    trainList[index].setTrainName(rTrainInfo.next());
                    // Store next value = String in TrainType
                    trainList[index].setTrainType(rTrainInfo.next());
                    // If next value (last of line) = int then store in MaxSeat
                    if (rTrainInfo.hasNextInt()) {
                        trainList[index].setMaxSeat(rTrainInfo.nextInt());
                    }
                    index++;
                }
                // Close rTrainInfo scanner after all the information are successfully jotted into each array indexes
                rTrainInfo.close();

                // To display the Train List
                System.out.println(String.format("%70s==========+===========================================+============+==========+", "+"));
                System.out.println(String.format("%70s %s | \t\t\t\t%-28s| %s | %s |", "|", "Train No", "Train Name", "Train Type", "Max Seat"));
                System.out.println(String.format("%70s==========+===========================================+============+==========+", "+"));

                for (int i = 0; i < trainList.length; i++) {
                    System.out.println(String.format("%70s%7d\t| %-42s| %6s\t |%6d\t|", "|", trainList[i].getTrainNo(), trainList[i].getTrainName(), trainList[i].getTrainType(), trainList[i].getMaxSeat()));
                }

                System.out.println(String.format("%70s==========+===========================================+============+==========+\n", "+"));
            }
        }
        // If there are errors occur during process
        catch (Exception e) {
            System.out.println("Error occurs.");
            e.printStackTrace();
        }
    }

    // Return trainList array
    public static train[] storeTrain() throws Exception
    {
        int line = 0;

        File train = new File("trainInfo.txt");

        // Open BufferedReader to read "trainInfo.txt"
        BufferedReader lineCount = new BufferedReader(new FileReader(train));
        // Open scanner as rTrainInfo to scan "trainInfo.txt" values
        Scanner rTrainInfo = new Scanner(train);

        // Loop until EOF to count the number of lines
        while (lineCount.readLine() != null) {
            line++;
        }
        // Close BufferedReader function after obtained the number of lines
        lineCount.close();

        // Open trainList array to record each train Information into different train object
        train trainList[] = new train[line];
        for (int i = 0; i < trainList.length; i++) {
            trainList[i] = new train();
        }

        // Use delimiter | and \n as content split in "trainInfo.txt" file
        rTrainInfo.useDelimiter("[|\n]");
        int index = 0;
        // Read and scan "trainInfo.txt" until EOF to obtain all train information into each array indexes
        while (rTrainInfo.hasNext() && index < trainList.length) {
            // If next value (first of line) = int then store in TrainNo
            if (rTrainInfo.hasNextInt()) {
                trainList[index].setTrainNo(rTrainInfo.nextInt());
            }
            // Store next value = String in TrainName
            trainList[index].setTrainName(rTrainInfo.next());
            // Store next value = String in TrainType
            trainList[index].setTrainType(rTrainInfo.next());
            // If next value (last of line) = int then store in MaxSeat
            if (rTrainInfo.hasNextInt()) {
                trainList[index].setMaxSeat(rTrainInfo.nextInt());
            }
            index++;
        }
        // Close rTrainInfo scanner after all the information are successfully jotted into each array indexes
        rTrainInfo.close();

        return trainList;
    }

    public double trainPrice(String trainType)
    {
        if (trainType.equals("LRT")) {
            return 20;
        }
        if (trainType.equals("MRT")) {
            return 10;
        }
        return 0;
    }

    public String toString() {
        return
        "TRAIN NAME  : " + trainName + "\n" + "TRAIN TYPE  : " + trainType + "\n" + String.format("TRAIN PRICE : RM %.2f", trainPrice(trainType));
    }
}