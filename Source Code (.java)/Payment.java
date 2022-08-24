/**
 * @(#)Payment.java
 *
 *
 * @author 
 * @version 1.00 2021/8/19
 */

import java.time.*;
import java.util.*;


public class Payment {
	private UUID paymentID;
	private String paymentDesc;
	private static double paymentAmount;
	private LocalDate paymentDate;
	public Ticket ticket = new Ticket();

    public Payment(String paymentDesc, double paymentAmount, UUID ticketNo) {
    	paymentID = UUID.randomUUID();
    	this.paymentDesc = paymentDesc;
    	this.paymentAmount = paymentAmount;
    	paymentDate = LocalDate.now();
    	if (ticketNo != null) {
    		ticket.setTicketNo(ticketNo);
		}
    	else {
			ticket.setTicketNo(UUID.randomUUID());
		}
    }

    public Payment(){
    	paymentDate = LocalDate.now();
    	paymentID = UUID.randomUUID();
    }
    
    //getter
    public UUID getPaymentID(){
    	return paymentID;
    }
    
    public String getPaymentDesc(){
    	return paymentDesc;
    }
    
    public static double getPaymentAmount(){
    	return paymentAmount;
    }
    
    public LocalDate getPaymentDate(){
    	return paymentDate;
    }
    
    //setter
    public void setPaymentDesc(String paymentDesc){
    	this.paymentDesc = paymentDesc;
    }
    
    public static void setPaymentAmount(double paymentAmount){
    	Payment.paymentAmount = paymentAmount;
    }

    
    //method
    public void paymentSummary(){
    	System.out.println("Ticket No			: " + ticket.getTicketNo());
    	System.out.println("Payment ID          : " + paymentID);
    	System.out.println("Payment Description : " + paymentDesc);
    	System.out.printf("Payment Amount      : RM%.2f\n", paymentAmount);
    	System.out.println("Payment Date        : " + paymentDate);
    }
    
    public void discount(String memberStatus){
    	double discountRate = 0.00;
    	memberStatus = memberStatus.toUpperCase();
    	if(memberStatus.charAt(0) == 'G'){
    		discountRate = 0.1;
    	}else if(memberStatus.charAt(0) == 'S'){
    		discountRate = 0.05;
    	}else if(memberStatus.charAt(0) == 'B'){
    		discountRate = 0.025;
    	}
    	
    	paymentAmount -= paymentAmount*discountRate;
    }
}