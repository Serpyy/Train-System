import java.util.Scanner;
import java.io.*;

public class login {
    // Data members;
    private String loginID;
    private String loginPW;
    private static String userAccess;

    // Setter;
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    public void setLoginPW(String loginPW) {
        this.loginPW = loginPW;
    }
    public static void setUserAccess(String userAccess) {
        login.userAccess = userAccess;
    }
    // Getter;
    public String getLoginID() {
        return loginID;
    }
    public String getLoginPW() {
        return loginPW;
    }
    public String getUserAccess() {
        return userAccess;
    }

    // Verify Login Method;
    public static boolean verifyLogin(String loginID, String loginPW)
    {
        boolean verify = false;
        String tempID, tempPW;
        // Check whether if UserAccess is from staffLogin.txt file;
        try
        {
            Scanner rStaffLogin = new Scanner(new File("staffLogin.txt"));

            rStaffLogin.useDelimiter("[|\n]");

            while (rStaffLogin.hasNext() && !verify) {
                tempID = rStaffLogin.next();
                tempPW = rStaffLogin.next();

                if (tempID.trim().equals(loginID) && tempPW.trim().equals(loginPW)) {
                    setUserAccess("Staff");
                    verify = true;
                }
            }
            rStaffLogin.close();
        }
        catch(Exception e)
        {
            System.out.println("Error Occurs.");
            e.printStackTrace();
        }

        // Return True to stop looping
        if (!verify)
        {
            // Check whether if UserAccess is from custLogin.txt file;
            try
            {
                Scanner rCustLogin = new Scanner(new File("custLogin.txt"));

                rCustLogin.useDelimiter("[|\n]");

                while (rCustLogin.hasNext() && !verify) {
                    tempID = rCustLogin.next();
                    tempPW = rCustLogin.next();

                    if (tempID.trim().equals(loginID) && tempPW.trim().equals(loginPW)) {
                        setUserAccess("Customer");
                        verify = true;
                    }
                }
                rCustLogin.close();
            }
            catch (Exception e)
            {
                System.out.println("Error Occurs.");
                e.printStackTrace();
            }
        }
        // Return False to continue looping
        if (!verify)
        {
            System.out.println("\nIncorrect ID/Password.\n");
            return false;
        }

        return true;
    }

    // Main Login;
    public static void login() throws Exception
    {
        login log = new login();
        Scanner login = new Scanner(System.in);

        boolean validated;

        do {
            // Input field for User's ID;
            System.out.print(" > Login ID: ");
            log.setLoginID(login.nextLine().trim());

            // Input field for User's Password;
            System.out.print(" > Password: ");
            log.setLoginPW(login.nextLine().trim());

            // Carry Verification Login Method from LoginInfo Class;
            validated = verifyLogin(log.getLoginID(), log.getLoginPW());
        }while (!validated);

        // Validate the User's Access;
        if (log.getUserAccess().equals("Staff"))
        {
            Employee emp = new Employee();
            emp.employeeMenu();
        }
        else if(log.getUserAccess().equals("Customer"))
        {
            Passenger ctm = new Passenger();
            ctm.passengerMenu();
        }
    }
}