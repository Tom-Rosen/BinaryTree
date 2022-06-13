// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
public class Employer implements Comparable<Employer> { // Employer information
  private String name; // name of this employer
  private String address; // address of this employer
  private String phoneNumber; // phone number of this employer
  
  

  public Employer(String name, String phoneNumber, String address) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public String getName() {  return this.name;  } // name accessor

  public String getPhoneNumber() {  return this.phoneNumber;  } // phoneNumber accessor
  
  public String getAddress() { return this.address; } // address accessor

  public int compareTo(Employer other) { return this.name.compareTo(other.name);  }
}