import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {
    public static Scanner scan = new Scanner(System.in);
    static ArrayList<AddressBookData> Adrlst = new ArrayList();
    static AddressBookData Adr = new AddressBookData();

    AddressBook(){
        System.out.println("Welcome to Address Book Program");
    }

    void Begin(){
        System.out.println("Enter Your Choice");
        System.out.println("1:Create Contact");
        System.out.println("0:Exit Program");
        int choice = scan.nextInt();
        switch (choice){
            case 1 : CreateContact();
                break;
            case 0 :
                System.out.println("Successfully exited");
                break;
            default:
                System.out.println("Enter Correct Option");
        }
    }

    void CreateContact(){
        System.out.println("Enter Following Details: \nFirstName : ");
        Adr.FirstName = scan.next();

        System.out.println("LastName : ");
        Adr.LastName = scan.next();

        System.out.println("Address : ");
        Adr.Address = scan.nextLine();

        System.out.println("City : ");
        Adr.City = scan.next();

        System.out.println("State : ");
        Adr.State = scan.next();

        System.out.println("Email : ");
        Adr.Email = scan.next();

        System.out.println("Zip Code : ");
        Adr.Zip = scan.nextInt();

        System.out.println("Phone Number : ");
        Adr.PhNo = scan.nextInt();

        Adrlst.add(Adr);
    }

    public static void main(String[] args) {
        AddressBook Adr =new AddressBook();
        Adr.Begin();
    }
}

class AddressBookData{
    String FirstName;
    String LastName;
    String Address;
    String City;
    String State;
    String Email;
    int Zip;
    long PhNo;

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getEmail() {
        return Email;
    }

    public int getZip() {
        return Zip;
    }

    public long getPhNo() {
        return PhNo;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setState(String state) {
        State = state;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setZip(int zip) {
        Zip = zip;
    }

    public void setPhNo(long phNo) {
        PhNo = phNo;
    }
}
