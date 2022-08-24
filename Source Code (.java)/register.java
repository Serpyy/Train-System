import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class register
{
    // Data members;
    private String registerID;
    private String registerPW;

    // Setter;
    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }
    public void setRegisterPW(String registerPW) {
        this.registerPW = registerPW;
    }
    // Getter;
    public String getRegisterID() {
        return registerID;
    }
    public String getRegisterPW() {
        return registerPW;
    }

    // Validate Register Method;
    public static boolean validateRegister(String registerID, String registerPW)
    {
        // Check the Register ID to ensure it is between 12 - 16 words;
        if (registerID.length() < 12 || registerID.length() > 16)
        {
            System.out.println("\nPlease enter ID of length 12 - 16.\n");

            return false;
        }
        else
        {
            // Check the Register ID to ensure it contains only letters and digits;
            for (int index = 0; index < registerID.length(); index++)
            {
                if(!Character.isLetter(registerID.charAt(index)) && !Character.isDigit(registerID.charAt(index)))
                {
                    System.out.println("\nPlease enter only letters and digits as ID.\n");

                    return false;
                }
            }

            // Read custLogin.txt to ensure that no same records exists;
            String tempID;
            boolean same = false;
            try
            {
                Scanner rCustLogin = new Scanner(new File("custLogin.txt"));

                rCustLogin.useDelimiter("[|\n]");

                while (rCustLogin.hasNext() && !same) {

                    tempID = rCustLogin.next();

                    // Check whether if Register ID is identical to the records in custLogin.txt
                    if (tempID.trim().equals(registerID)) {
                        same = true;
                    }
                }
                rCustLogin.close();
            }
            // If custLogin.txt not found;
            catch(Exception e)
            {
                // Open new custLogin.txt;
                try
                {
                    BufferedWriter writeRegister = new BufferedWriter(new FileWriter("custLogin.txt", true));
                    writeRegister.write(String.format("%s|%s\n", registerID, registerPW));

                    writeRegister.close();

                    System.out.println("\nAccount created Successfully.");
                }
                catch(Exception e2)
                {
                    System.out.println("Error Occurs.");
                    e.printStackTrace();
                }

                return true;
            }

            // If !same (no existing "login id" found in custLogin.txt)
            if (!same)
            {
                // try writing the new entered id and pw into custLogin.txt file
                try
                {
                    File register = new File("custLogin.txt");

                    FileWriter writeRegister = new FileWriter(register, true);
                    writeRegister.write(String.format("%s|%s\n", registerID, registerPW));

                    writeRegister.close();

                    System.out.println("\nAccount created Successfully.");
                }
                // If error occurs during the process
                catch (Exception e)
                {
                    System.out.println("Error Occurs.");
                    e.printStackTrace();
                }

                return true;
            }
            // Return false to continue looping;
            else
            {
                System.out.println("\nAccount already Existed.\n");

                return false;
            }
        }
    }

    public static void register()
    {
        register reg = new register();
        Scanner register = new Scanner(System.in);

        boolean validated = false;

        while(!validated)
        {
            System.out.print(" > Register ID: ");
            reg.setRegisterID(register.nextLine().trim());

            System.out.print(" > Password: ");
            reg.setRegisterPW(register.nextLine().trim());

            validated = validateRegister(reg.getRegisterID(), reg.getRegisterPW());
        }
    }
}
