import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressBook {
    public static Scanner scan = new Scanner(System.in);
    static ArrayList<AddressBookData> Adrlst = new ArrayList<AddressBookData>();
    static AddressBookData Adr = new AddressBookData();

    AddressBook(){
        System.out.println("Welcome to Address Book Program");
    }

    void Begin(){
        int choice = 1;
        while (choice != 0) {
            System.out.println("1:Create or Add new Contact\n2:Edit Contact using Person First Name\n3:Delete Contact using Person First Name");
            System.out.println("0:Exit Program");
            System.out.println("Enter Your Choice");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    CreateContact();
                    break;
                case 2:
                    EditContactUsingName("Edit");
                    break;
                case 3:
                    EditContactUsingName("Delete");
                    break;
                case 0:
                    System.out.println("Successfully exited");
                    choice = 0;
                    break;
                default:
                    System.out.println("Enter Correct Option");
            }
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

        try {
            System.out.println("Zip Code : ");
            Adr.setZip(scan.nextInt());

            System.out.println("Phone Number : ");
            Adr.setPhNo(scan.nextLong());
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Only Number");

            System.out.println("Zip Code : ");
            Adr.setZip(scan.nextInt());

            System.out.println("Phone Number : ");
            Adr.setPhNo(scan.nextLong());
        }

        Adrlst.add(Adr);
    }

    void EditContactUsingName(String Operation){
        try {
        System.out.println("Enter Person Name : ");
        int Index = CheckPersonIndex(scan.next());
        if(Index == -1)
            System.out.println("Person Not exist\nCreate contact");
        else
            EditOrDeleteContact(Index,Operation);
        }
        catch (NullPointerException e){
            System.out.println("Address Book is Empty");
        }
    }

    int CheckPersonIndex(String Name){
        int index = -1;
        for (AddressBookData adr : Adrlst) {
            if(Name.equals(adr.FirstName))
                index = Adrlst.indexOf(adr);
        }
        return index;
    }

    void EditOrDeleteContact(int index, String choice) {
        if(choice.equals("Edit")){
            System.out.println("First Name : ");
            Adr.setFirstName(scan.next());

            System.out.println("Last Name : ");
            Adr.setLastName(scan.next());

            System.out.println("Address : ");
            Adr.setAddress(scan.nextLine());

            System.out.println("City : ");
            Adr.setCity(scan.next());

            System.out.println("State : ");
            Adr.setEmail(scan.next());

            try {
                System.out.println("Zip Code : ");
                Adr.setZip(scan.nextInt());

                System.out.println("Phone Number : ");
                Adr.setPhNo(scan.nextLong());
            }
            catch (InputMismatchException ex){
                System.out.println("Enter Only Number");

                System.out.println("Zip Code : ");
                Adr.setZip(scan.nextInt());

                System.out.println("Phone Number : ");
                Adr.setPhNo(scan.nextLong());
            }
            Adrlst.set(index,Adr);
            System.out.println("Successfully Contact Edited");
        }
        if(choice.equals("Delete")){
            Adrlst.remove(index);
        }
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
