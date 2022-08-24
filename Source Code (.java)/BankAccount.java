import java.io.*;
import java.util.*;

public class BankAccount {
	
	private String accNo;
	private String accType;
	private double accBalance;
	private int noOfTransaction;
	

    public BankAccount(String accNo, String accType, double accBalance,int noOfTransaction) {
    	this.accNo = accNo;
    	this.accType = accType;
    	this.accBalance = accBalance;
    	this.noOfTransaction = noOfTransaction;
    }
    
    public BankAccount(String accNo, String accType) {
    	this(accNo,accType,0.00,0);
    }
    
    public BankAccount(){
    	this("000000000000","NA",0.00,0);
    }
    
    //getter 
    public String getAccNo(){
    	return accNo;
    
    }
    
    public String getAccType(){
    	return accType;
    }
    
    public double getAccBalance(){
    	return accBalance;
    }
    
    public int getNoOfTransaction(){
    	return noOfTransaction;
    }
    
    //setter
    
    public void setAccNo(String accNo){
    	this.accNo = accNo;
    }
    
    public void setAccType(String accType){
    	this.accType = accType;
    }
    
    public void setAccBalance(double accBalance){
    	this.accBalance = accBalance;
    }
    
    public void setNoOfTransaction(int noOfTransaction){
    	this.noOfTransaction = noOfTransaction;
    }
    
    
    //method
    public void storePayment(Payment payment){
    	double amount = payment.getPaymentAmount(); 
    	 accBalance += amount ;
    	 noOfTransaction++;
    	 try {
            FileWriter wAccountInfo = new FileWriter("AccountInfo.txt");
            wAccountInfo.write(String.format("%s|%s|%.2f|%d",accNo,accType,accBalance,noOfTransaction));
            wAccountInfo.close();
        }
        // If "accountInfo.txt" not found
        catch (Exception e) {
            // Tell user that the file not found
            System.out.println("File not found.");
            
        }

    }
    
    //staff menu
    public void checkBal(){
    	try {
       		File file = new File ("accountInfo.txt");
        
            Scanner rAccountInfo = new Scanner(file );
            

            // Use delimiter / as content split in "trainInfo.txt" file
            rAccountInfo.useDelimiter("[|\n]");

            // Check if content in "AccountInfo.txt" still has next line
            while (rAccountInfo.hasNext()) {
               accNo = rAccountInfo.next();
                accType = rAccountInfo.next();
                accBalance = rAccountInfo.nextDouble();
                noOfTransaction = rAccountInfo.nextInt();
                 
            }
           
            // Close file read
           rAccountInfo.close();
            
            
        }
        // If "accountInfo.txt" not found
        catch (FileNotFoundException ff) {
            // Tell user that the file not found
            System.out.println("File not found.");
            
        }
    	
    	System.out.printf("\nAccount Balance : RM%.2f", accBalance);
    	
    }
    
    //refund
    public void provideRefund(double refundAmount){
    	accBalance -= refundAmount;
    	noOfTransaction++;
    	 try {
            FileWriter wAccountInfo = new FileWriter("AccountInfo.txt");
            wAccountInfo.write(String.format("%s|%s|%.2f|%d",accNo,accType,accBalance,noOfTransaction));
            wAccountInfo.close();
        }
        // If "accountInfo.txt" not found
        catch (Exception e) {
            // Tell user that the file not found
            System.out.println("File not found.");
            
        }

    	
    }
    
    //staff menu
    public void printAccSummary(){
    	try {
       		File file = new File ("accountInfo.txt");
        
            Scanner rAccountInfo = new Scanner(file );
            

            // Use delimiter / as content split in "trainInfo.txt" file
            rAccountInfo.useDelimiter("[|\n]");

            // Check if content in "AccountInfo.txt" still has next line
            while (rAccountInfo.hasNext()) {
               accNo = rAccountInfo.next();
                accType = rAccountInfo.next();
                accBalance = rAccountInfo.nextDouble();
                noOfTransaction = rAccountInfo.nextInt();
                 
            }
           
            // Close file read
           rAccountInfo.close();
            
            
        }
        // If "accountInfo.txt" not found
        catch (FileNotFoundException ff) {
            // Tell user that the file not found
            System.out.println("File not found.");
            
        }
    	 System.out.println(String.format("\n%130s", "|===== Account summary =====|"));
    	 System.out.println(String.format("\n%70s=================+============================================+=============+=================+", "+"));
         System.out.println(String.format("%70s %14s  | \t\t\t\t%-28s| %9s   | %s |", "|", "Account no.", "Acccount Type", "Balance", "Transaction no."));
         System.out.println(String.format("%70s=================+============================================+=============+=================+", "+"));
         System.out.println(String.format("%70s%16s | %29s\t\t\t\t|  RM%6.2f  | %10d\t\t|", "|", accNo, accType, accBalance, noOfTransaction));
         System.out.println(String.format("%70s=================+============================================+=============+=================+\n", "+"));
 
    }
    

    	
    public static void bankMenu() {
    	Scanner scanner = new Scanner(System.in);
    	
    	char tempLoop;
        char loop = 'Y';
        int selection = 0;
        boolean error;
        do {
    	 System.out.println(String.format("\n\n+=====+=======================+"));
         System.out.println(String.format("|%20s%10s", "Bank Menu", "|"));
         System.out.println(String.format("+=====+=======================+"));
         System.out.println(String.format("|%5s|%22s%2s", "1  ", "Check Account Balance", "|"));
         System.out.println(String.format("|%5s|%22s%2s", "2  ", "Print Account Summary", "|"));
         System.out.println(String.format("|%5s|%15s%9s", "3  ", "Return to Menu", "|"));
         System.out.println(String.format("+=====+=======================+\n"));
         
         do {
                System.out.print(" > Enter Selection (1-3) : ");
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
         
         switch(selection)
            {
                case 1:
                	BankAccount bank = new BankAccount();
                    bank.checkBal();
                    break;
                    
                case 2:
                	BankAccount Acc = new BankAccount();
                    Acc.printAccSummary();
                    break;
                    
                case 3:
                    loop = 'N';
                    break;

                default:
                    System.out.println("\nInvalid Selection. Please Try Again.");
                    break;
            }
            
        } while(selection < 1 || selection > 5 || loop == 'Y');
    }
}