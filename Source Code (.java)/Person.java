import java.io.*;

public class Person {
	//data members
	private String name;
	private String ic;
	private String phoneNo;
    
    public Person(String name, String ic, String phoneNo) {
    	this.name = name;
    	this.ic = ic;
    	this.phoneNo = phoneNo;
    }
    
    public Person(){
    }
    
    
    //getter
    public String getName() {
    	return name;
    }
    
    public String getIC() {
    	return ic;
    }
    
    public String getPhoneNo() {
    	return phoneNo;
    }
    
    //setter
  	public void setName(String name) {
  		this.name = name;
  	}
  	
  	public void setIC(String ic) {
  		this.ic = ic;
  	}
  	
  	public void setPhoneNo(String phoneNo) {
  		this.phoneNo = phoneNo;
  	}
  	
}