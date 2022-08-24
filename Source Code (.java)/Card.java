/**
 * @(#)Card.java
 *
 *
 * @author 
 * @version 1.00 2021/8/18
 */
import java.util.*;

public class Card {
	
	private String cardNo;
	private String cardHolderName;
	private Date expiryDate;

    public Card(String cardNo,String cardHolderName, Date expiryDate) {
    	this.cardNo = cardNo;
    	this.cardHolderName= cardHolderName;
    	this.expiryDate = expiryDate;
    }
    
    public Card(String cardNo,String cardHolderName) {
    	this.cardNo = cardNo;
    	this.cardHolderName= cardHolderName;
    	
    }
    
    public Card(){
    	this("0000000000000000","",null);
    }
    
    //getter
    public String getCardNo(){
    	return cardNo;
    }
    
    public String getCardHolderName(){
    	return cardHolderName;
    }
    
    public Date getExpiryDate(){
    	return expiryDate;
    }
    
    //setter
    public void setCardNo(String cardNo){
    	this.cardNo = cardNo;
    }
    
    public void setCardHolderName(String cardHolderName){
    	this.cardHolderName = cardHolderName;
    }
    
    public void setExpiryDate(Date expiryDate){
    	this.expiryDate = expiryDate;
    }
    
    
    //method
    public boolean verifyCardInfo(String cardNo){
    	char a;
    	int ch = 0;
    	
    	if(cardNo.length() != 16){
    		return false;
    	}
    	
    	for(int i=0;i<cardNo.length();i++){
    		a = cardNo.charAt(i);
    		if(Character.isDigit(a) == false){
    			ch++;
    		}
    	}
    	
    	if(ch > 0){
    		return false;
    	}
    	
    	return true;
    	
    }
    
    public boolean verifyName(String cardHolderName){
    	int notChar = 0;
    	char a;
    	for(int i = 0;i<cardHolderName.length();i++){
    		a = cardHolderName.charAt(i);
    		if(Character.isLetter(a) == false){
    			if(a != ' '){
    			notChar++;
    		}
    		}
    	}
    	
    	if(notChar > 0){
    		return false;
    	}
    	return true;
    }
    
}