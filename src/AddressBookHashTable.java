import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AddressBookHashTable {
    Hashtable<String, AddressBook> addressBookHashtable = new Hashtable<>();    //Table of AddressBook
    public static Scanner scan = new Scanner(System.in);
    static String addressBookName;

    AddressBookHashTable() {
        System.out.println("Welcome to Address Book Program");
    }

    void Start() {
        if (addressBookHashtable.isEmpty()) {
            System.out.println("Enter First Address book name : ");
            addressBookName = scan.next();
            addressBookHashtable.put(addressBookName, new AddressBook());
            Begin();
        } else {
            checkAddressbook();
        }
    }

    void checkAddressbook() {
        System.out.println("Enter Existing Address book Name\n Press 0 to exit\n Press 1 to create new Address book");
        addressBookName = scan.next();
        if (addressBookName.equals("0"))
            System.out.println("Exit");

        else if (addressBookName.equals("1"))
            newAddressBook();

        else {
            if (addressBookHashtable.get(addressBookName) == null) {
                System.out.println("Entered address book name is wrong");
                checkAddressbook();
            } else {
                Begin();
            }
        }
    }

    void Begin() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1:Create or Add new Contact\n2:Edit Contact using Person First Name\n3:Delete Contact using Person First Name");
            System.out.println("4:Create new AddressBook\n5:Search person in city or state\n6:Exit Addressbook\n7:View all person by city or state\n0:Exit Program");
            System.out.println("Enter your choice : ");
            choice = new UserInputOutput().getint();

            switch (choice) {
                case 1:
                    addressBookHashtable.get(addressBookName).CreateContact();
                    break;
                case 2:
                    addressBookHashtable.get(addressBookName).EditContactUsingName("Edit");
                    break;
                case 3:
                    addressBookHashtable.get(addressBookName).EditContactUsingName("Delete");
                    break;
                case 4:
                    newAddressBook();
                    break;
                case 5:
                    searchInCityOrState();
                    break;
                case 6:
                    Start();
                    break;
                case 7:
                    viewByCityOrState();
                    break;
                case 0:
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Enter correct choice");
            }
        }
    }

    void newAddressBook() {
        System.out.println("Enter new Address book name : ");
        String Name = scan.next();
        if (addressBookHashtable.containsKey(Name)) {
            System.out.println("Address Book of this name already exits\nUse new name");
            addressBookName = Name;
        } else {
            addressBookHashtable.put(Name, new AddressBook());
        }
    }

    void searchInCityOrState() {
        Set<String> Keys = addressBookHashtable.keySet();
        System.out.println("Enter Person details\nFirst name: ");
        String firstname = scan.next();
        System.out.println("Last name: ");
        String lastname = scan.next();
        System.out.println("City or State");
        String location = scan.next();
        boolean check = false;
        for (String key : Keys) {
            if (addressBookHashtable.get(key).searchInAddressBook(firstname, lastname, location)) {
                System.out.println("Person found in " + key + " AddressBook");
                check = true;
            }
        }
        if (!check)
            System.out.println("Person not found in all AddressBook");
    }

    void viewByCityOrState() {
        Hashtable<String,List<String>> personLocation = new Hashtable<>();
        Set<String> Keys = addressBookHashtable.keySet();
        List<String> locations = new ArrayList<>();
        System.out.println("Enter \n1: City\n2: State");
        int choice;
        switch (new UserInputOutput().getint()){
            case 1 : choice = 1;
                    personLocation = getPersonLocationTable(Keys,locations,personLocation,choice);
                    break;
            case  2 : choice =2;
                    personLocation = getPersonLocationTable(Keys,locations,personLocation,choice);
                    break;
            default : System.out.println("Enter correct choice");
        }

    }

    Hashtable<String,List<String>> getPersonLocationTable(Set<String> Keys, List<String> locations, Hashtable<String,List<String>> personLocation, int choice){
        for(String key : Keys) {
            if(choice == 1)
                locations.addAll(addressBookHashtable.get(key).getAddressBook().stream().map(Person::getCity).distinct().collect(Collectors.toList()));
            else
                locations.addAll(addressBookHashtable.get(key).getAddressBook().stream().map(Person::getState).distinct().collect(Collectors.toList()));

        }
        locations = locations.stream().distinct().collect(Collectors.toList());

        for(String key : Keys){
            for(String location : locations) {
                List<String> personName;
                if(choice == 1)
                    personName = addressBookHashtable.get(key).getAddressBook().stream().filter( e -> e.getCity().equals(location)).map(Person::toString).collect(Collectors.toList());
                else
                    personName = addressBookHashtable.get(key).getAddressBook().stream().filter( e -> e.getState().equals(location)).map(Person::toString).collect(Collectors.toList());

                personLocation.put(location,personName);
            }
        }

        for (String location : locations){
            if (choice == 1)
                System.out.println("City : "+location);
            else
                System.out.println("State : "+location);
            personLocation.get(location).forEach(System.out::println);
        }
        return personLocation;
    }

}