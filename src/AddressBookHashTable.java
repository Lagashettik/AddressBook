import java.util.*;

public class AddressBookHashTable {
    Hashtable<String, AddressBook> addressBookTable = new Hashtable<>();    //Table of AddressBook
    public static Scanner scan = new Scanner(System.in);
    static String addressBookName;

    AddressBookHashTable() {
        System.out.println("Welcome to Address Book Program");
    }

    int Start() {
        if (addressBookTable.isEmpty()) {
            System.out.println("Enter First Address book name : ");
            addressBookName = scan.next();
            addressBookTable.put(addressBookName, new AddressBook());
            Begin();
            return 1;
        } else {
            return checkAddressbook();
        }
    }

    int checkAddressbook() {
        System.out.println("Enter Existing Address book Name\n Press 0 to exit\n Press 1 to create new Address book");
        addressBookName = scan.next();
        if (addressBookName.equals("0")) {
            System.out.println("Exit");
            return 0;
        }
        else if (addressBookName.equals("1"))
            newAddressBook();

        else {
            if (addressBookTable.get(addressBookName) == null) {
                System.out.println("Entered address book name is wrong");
                checkAddressbook();
            } else Begin();
        }
        return 1;
    }

    void Begin() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1:Create or Add new Contact\n2:Edit Contact using Person First Name\n3:Delete Contact using Person First Name");
            System.out.println("4:Create new AddressBook\n5:Search person in city or state\n6:Exit Addressbook\n7:View all person by city or state");
            System.out.println("8:Get count of person by city or state\n9:Sort Entries \n0:Exit Program");
            System.out.println("Enter your choice : ");
            choice = new UserInputOutput().getint();

            switch (choice) {
                case 1 -> addressBookTable.get(addressBookName).CreateContact(addressBookName);
                case 2 -> addressBookTable.get(addressBookName).EditContactUsingName("Edit",addressBookName);
                case 3 -> addressBookTable.get(addressBookName).EditContactUsingName("Delete",addressBookName);
                case 4 -> newAddressBook();
                case 5 -> searchInCityOrState();
                case 6 -> choice = Start();
                case 7 -> AddressBook.display(viewByCityOrState());
                case 8 -> System.out.println(viewByCityOrState().size());
                case 9 -> sortAddressBook();
                case 0 -> System.out.println("Exited");
                default -> System.out.println("Enter correct choice");
            }
            addressBookTable.get(addressBookName).sortByPersonName();
        }
    }

    void newAddressBook() {
        System.out.println("Enter new Address book name : ");
        String Name = scan.next();
        if (addressBookTable.containsKey(Name)) {
            System.out.println("Address Book of this name already exits\nUse new name");
        } else {
            addressBookTable.put(Name, new AddressBook());
            addressBookName = Name;
        }
    }

    void searchInCityOrState() {
        Set<String> Keys = addressBookTable.keySet();
        System.out.println("Enter Person details\nFirst name: ");
        String firstname = scan.next();
        System.out.println("Last name: ");
        String lastname = scan.next();
        System.out.println("City or State");
        String location = scan.next();
        boolean check = false;
        for (String key : Keys) {
            if (addressBookTable.get(key).searchInAddressBook(firstname, lastname, location)) {
                System.out.println("Person found in " + key + " AddressBook");
                check = true;
            }
        }
        if (!check)
            System.out.println("Person not found in all AddressBook");
    }

    List<Person> viewByCityOrState() {
        System.out.println("Enter \n1: City\n2: State");
        int choice = 0;
        switch (new UserInputOutput().getint()) {
            case 1 -> {
                System.out.println("Enter City Name : ");
                return countByCity();
            }
            case 2 -> {
                System.out.println("Enter State Name");
                return countByState();
            }
            default -> System.out.println("Incorrect choice");
        }
        return null;
    }

    List<Person> countByCity(){
        String location = scan.next();
        Set<String> Keys = addressBookTable.keySet();
        List<Person> personList = new ArrayList<>();

        for ( String eachkey : Keys) {
            personList.addAll(addressBookTable.get(eachkey).FindInCity(location));
        }

        return personList;
    }

    List<Person> countByState(){
        String location = scan.next();
        Set<String> Keys = addressBookTable.keySet();
        List<Person> personList = new ArrayList<>();

        for ( String eachkey : Keys) {
            personList.addAll(addressBookTable.get(eachkey).FindInState(location));
        }

        return personList;
    }

    void sortAddressBook(){
        System.out.println("1: City\n2: State\n3: Zip");
        int choice = new UserInputOutput().getint();
        List<Person> sortedList = null;
        switch (choice){
            case 1 -> sortedList = addressBookTable.get(addressBookName).sortByCity();
            case 2 -> sortedList = addressBookTable.get(addressBookName).sortByState();
            case 3 -> sortedList = addressBookTable.get(addressBookName).sortByZip();
            default -> System.out.println("Incorrect choice");
        }

        AddressBook.display(sortedList);
    }

}