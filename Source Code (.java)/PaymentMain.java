/**
 * @(#)PaymentMain.java
 *
 *
 * @author 
 * @version 1.00 2021/8/25
 */
 
import java.util.*;
import java.text.*;
import java.time.*;
import java.io.*;

public class PaymentMain  {
   
    public static void payment( String fromLocation, String toLocation, String trainName, String trainType, double paymentAmount, int noOfPax, UUID ticketNo) throws ParseException {

    	Scanner scanner = new Scanner(System.in);
    	char firstLetter = 'A';
    	while(firstLetter != 'Y' && firstLetter != 'N') {
			System.out.print(" > Do you want to make payment (Y|N) ? ");
			String confirmPayment = scanner.next();
			confirmPayment = confirmPayment.toUpperCase();
			firstLetter = confirmPayment.charAt(0);
			System.out.println("");
		}
    	
    	if(firstLetter == 'Y'){
    	boolean validCard = false;
    	boolean validName = false;
    	boolean validDate = false;
    	Card card = new Card();
    	String memberStatus = "c";
    	double accBalance = 0.00;
    	String accNo =  " ";
    	String accType = " ";
    	int noOfTransaction = 0;
    	String paymentDesc = noOfPax + " Ticket(s) from " + fromLocation + " to " + toLocation; 
    	DateValidator validator = new DateValidatorUsingDateFormat("MM/yy");
    	
    	Payment payment = new Payment(paymentDesc,paymentAmount, ticketNo);
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
        
    	boolean mbrYN = false;
    	boolean mbrTypeLoop = false;
    	do {
			System.out.print(" > Are you a member(Y/N)? ");
			char mbr = scanner.next().charAt(0);

			if (mbr == 'Y' || mbr == 'y') {
				do {
					mbrYN = false;

					System.out.print(" > Which member applies to you (G - gold , S - silver, B - bronze)? ");
					char mtype = scanner.next().charAt(0);
					switch (Character.toUpperCase(mtype)) {

						case 'G':
							memberStatus = "g";
							mbrTypeLoop = false;
							break;
						case 'S':
							memberStatus = "s";
							mbrTypeLoop = false;
							break;
						case 'B':
							memberStatus = "b";
							mbrTypeLoop = false;
							break;
						default:
							System.out.println("\nInvalid Member Type.");
							mbrTypeLoop = true;
							break;
					}
				} while(mbrTypeLoop);

			} else if (mbr == 'N' || mbr == 'n') {
				memberStatus = "c";
				mbrYN = false;
			} else {
				System.out.println("\nSorry only Y or N. Please reenter.");
				mbrYN = true;
			}
			scanner.nextLine();
		}while(mbrYN);
    	
    	BankAccount bankAcc = new BankAccount(accNo,accType,accBalance,noOfTransaction);
    	while(validName == false) {
			System.out.print(" > Enter your card holder name : ");
			String cardName = scanner.nextLine();
			validName = card.verifyName(cardName);
			if (validName == false) {
				System.out.println("\nSorry your name is invalid(all must be alphabet(a-z/A-Z), please reenter.");
			} else if (validName == true) {
				card.setCardHolderName(cardName);
			}
		}
    	
    	
    	while(validCard == false) {
			System.out.print(" > Enter your card number : ");
			String cardNo = scanner.nextLine();
			validCard = card.verifyCardInfo(cardNo);
			if (validCard == false) {
				System.out.println("\nSorry your card number is invalid(must be 16 digit), please reenter.");
			} else if (validCard == true) {
				card.setCardNo(cardNo);
			}
		}
    	
    	while(validDate == false) {
			Date expiryDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
			System.out.print(" > Enter card expiry date (MM/YY): ");
			String dateInput = scanner.nextLine();
			validDate = validator.isValid(dateInput);
			if (validDate == false) {
				System.out.println("\nSorry expiry date invalid, please reenter.");
			} else if (validDate == true) {
				if (null != dateInput && dateInput.trim().length() > 0) {
					expiryDate = sdf.parse(dateInput);
					card.setExpiryDate(expiryDate);
				}
			}
		}
    	
    	System.out.println();
    	payment.discount(memberStatus);
    	bankAcc.storePayment(payment);
    	
    	payment.paymentSummary();
    	System.out.println();
    	
    	System.out.println("Payment successful!!!!");
    	}else if(firstLetter == 'N'){
    		System.out.println("Payment cancelled !!!");
    	}
        
    }
}
