
import java.util.*;

public class AddressBook {
    public static Scanner scan = new Scanner(System.in);
    static ArrayList<AddressBookData> Adrlst = new ArrayList<AddressBookData>();
    static AddressBookData Adr = new AddressBookData();

    void CreateContact(){
        System.out.println("Enter Following Details: \nFirstName : ");
        Adr.FirstName = scan.next();

        System.out.println("LastName : ");
        Adr.LastName = scan.next();

        System.out.println("Address : ");
        scan.nextLine();
        Adr.Address = scan.nextLine();

        System.out.println("City : ");
        Adr.City = scan.next();

        System.out.println("State : ");
        Adr.State = scan.next();

        System.out.println("Email : ");
        Adr.Email = scan.next();

        GetCorrectZip();
        GetCorrectPhNo();

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

            GetCorrectZip();
            GetCorrectPhNo();

            Adrlst.set(index,Adr);
            System.out.println("Successfully Contact Edited");
        }

        if(choice.equals("Delete")){
            Adrlst.remove(index);
        }
    }

    void GetCorrectZip(){
        try {

            System.out.println("Zip Code : ");
            Adr.setZip(new UserInputOutput().inputint());
        }
        catch (InputMismatchException ex){
           GetCorrectZip();
        }
    }

    void GetCorrectPhNo(){
        try {

            System.out.println("Phone Number : ");
            Adr.setPhNo(new UserInputOutput().inputint());
        }
        catch (InputMismatchException ex){
            GetCorrectPhNo();
        }
    }

    public static void main(String[] args) {
        AddressBookHashTable addressBookHashTable =new AddressBookHashTable();
        addressBookHashTable.Start();
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

class AddressBookHashTable {
    Hashtable<String,AddressBook> AddressBookTable = new Hashtable<String,AddressBook>();    //Table of AddressBook
    public static Scanner scan = new Scanner(System.in);
    String addressBookName;

    AddressBookHashTable(){
        System.out.println("Welcome to Address Book Program");
    }

    void Start() {
        if(AddressBookTable.isEmpty()) {
            System.out.println("Enter First Address book name : ");
            addressBookName = scan.next();
            AddressBookTable.put(addressBookName,new AddressBook());
            Begin();
        }
        else {
            System.out.println("Enter Existing Address book Name/n Press 0 to enter");
            addressBookName = scan.next();
            switch (Integer.parseInt(addressBookName)){
                case 0:
                    System.out.println("Exit");
                    break;
                default:
                    if (AddressBookTable.get(addressBookName) == null){
                        System.out.println("Entered address book name is wrong");
                        System.out.println("Re-Enter Address book name");
                        addressBookName = scan.next();
                    }
                    else {
                        Begin();
                    }
            }
        }
    }
    void Begin() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1:Create or Add new Contact\n2:Edit Contact using Person First Name\n3:Delete Contact using Person First Name");
            System.out.println("4:Create new AddressBook\n0:Exit Program");
            System.out.println("Enter your choice : ");
            choice = new UserInputOutput().getint();

            switch (choice){
                case 1:
                    AddressBookTable.get(addressBookName).CreateContact();
                    break;
                case 2: AddressBookTable.get(addressBookName).EditContactUsingName("Edit");
                    break;
                case 3: AddressBookTable.get(addressBookName).EditContactUsingName("Delete");
                    break;
                case 4: newAddressBook();
                    break;
                case 0:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Enter correct choice");
            }
        }
    }

    void newAddressBook(){
        System.out.println("Enter Address book name : ");
        String Name = scan.next();
        if(AddressBookTable.containsKey(Name)){
            System.out.println("Address Book of this name already exits\nUse new name");
        }
        else {
            AddressBookTable.put(Name,new AddressBook());
            addressBookName = Name;
        }
    }

}

class UserInputOutput {
    int a;
    public int getint(){
        try {
            a = new UserInputOutput().inputint();
        }
        catch (InputMismatchException ex){
            System.out.println("Enter only number");
            getint();
        }
        finally {
            return a;}

    }

    public int inputint(){
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        return value;
    }

}
