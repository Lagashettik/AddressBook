import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class AddressBookHashTable {
    Hashtable<String,AddressBook> AddressBookTable = new Hashtable<>();    //Table of AddressBook
    public static Scanner scan = new Scanner(System.in);
    static String addressBookName;

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
            checkAddressbook();
        }
    }
    void checkAddressbook(){
        System.out.println("Enter Existing Address book Name\n Press 0 to exit\n Press 1 to create new Address book");
        addressBookName= scan.next();
        if(addressBookName.equals("0"))
            System.out.println("Exit");

        else if(addressBookName.equals("1"))
            newAddressBook();

        else {
            if (AddressBookTable.get(addressBookName) == null){
                System.out.println("Entered address book name is wrong");
                checkAddressbook();
            }
            else {
                Begin();
            }
        }
    }
    void Begin() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1:Create or Add new Contact\n2:Edit Contact using Person First Name\n3:Delete Contact using Person First Name");
            System.out.println("4:Create new AddressBook\n5:Search person in city or state\n6:Exit Addressbook\n0:Exit Program");
            System.out.println("Enter your choice : ");
            choice = new UserInputOutput().getint();

            switch (choice){
                case 1: AddressBookTable.get(addressBookName).CreateContact();
                    break;
                case 2: AddressBookTable.get(addressBookName).EditContactUsingName("Edit");
                    break;
                case 3: AddressBookTable.get(addressBookName).EditContactUsingName("Delete");
                    break;
                case 4: newAddressBook();
                    break;
                case 5: searchInCityOrState();
                    break;
                case 6: Start();
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
        System.out.println("Enter new Address book name : ");
        String Name = scan.next();
        if(AddressBookTable.containsKey(Name)){
            System.out.println("Address Book of this name already exits\nUse new name");
            addressBookName = Name;
        }
        else {
            AddressBookTable.put(Name,new AddressBook());
        }
    }

    void searchInCityOrState(){
        Set<String> Keys = AddressBookTable.keySet();
        System.out.println("Enter Person details\nFirst name: ");
        String firstname = scan.next();
        System.out.println("Last name: ");
        String lastname = scan.next();
        System.out.println("City or State");
        String location = scan.next();
        boolean check = false;
        for(String key : Keys){
            if(AddressBookTable.get(key).searchInAddressBook(firstname,lastname,location)){
                System.out.println("Person found in "+ key +" AddressBook");
                check = true;
            }
        }
        if(!check)
            System.out.println("Person not found in all AddressBook");
    }

}