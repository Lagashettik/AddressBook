
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public static Scanner scan = new Scanner(System.in);
    private ArrayList<Person> addressbook = new ArrayList<>();

    void CreateContact(String addressBookName){
        Person data = new Person();

        System.out.println("Enter Following Details: \nFirstName : ");
        data.setFirstName(scan.next());

        System.out.println("LastName : ");
        data.setLastName(scan.next());

        if (!addressbook.contains(data)){
            System.out.println("Address : ");
            scan.nextLine();
            data.setAddress(scan.nextLine());

            System.out.println("City : ");
            data.setCity(scan.next());

            System.out.println("State : ");
            data.setState(scan.next());

            System.out.println("Email : ");
            data.setEmail(scan.next());

            GetCorrectZip(data);
            GetCorrectPhNo(data);

            addressbook.add(data);
            addressbook = (ArrayList<Person>) addressbook.stream().sorted(Comparator.comparing( Person :: getFirstName)).collect(Collectors.toList());
        }
        else
            System.out.println("Entered Person already exist in this address book" + addressBookName);

    }

    void EditContactUsingName(String Operation, String addressBookName){
        try {
        System.out.println("Enter Person Name : ");
        int Index = CheckPersonIndex(scan.next());
        if(Index == -1)
            System.out.println("Person Not exist in " + addressBookName + "\nCreate contact");
        else
            EditOrDeleteContact(Index,Operation);
        }
        catch (NullPointerException e){
            System.out.println("Address Book is Empty");
        }
    }

    int CheckPersonIndex(String Name){
        int index = -1;
        for (Person adr : addressbook) {
            if(Name.equals(adr.getFirstName()))
                index = addressbook.indexOf(adr);
        }
        return index;
    }

    void EditOrDeleteContact(int index, String choice) {
        if(choice.equals("Edit")){
            Person data = new Person();
            System.out.println("First Name : ");
            data.setFirstName(scan.next());

            System.out.println("Last Name : ");
            data.setLastName(scan.next());

            System.out.println("Address : ");
            scan.nextLine();
            data.setAddress(scan.nextLine());

            System.out.println("City : ");
            data.setCity(scan.next());

            System.out.println("State : ");
            data.setEmail(scan.next());

            GetCorrectZip(data);
            GetCorrectPhNo(data);

            addressbook.set(index, data);
            System.out.println("Successfully Contact Edited");
        }

        if(choice.equals("Delete")){
            addressbook.remove(index);
        }
    }

    void GetCorrectZip(Person data){
        try {

            System.out.println("Zip Code : ");
            data.setZip(new UserInputOutput().inputint());
        }
        catch (InputMismatchException ex){
           GetCorrectZip(data);
        }
    }

    void GetCorrectPhNo(Person data){
        try {

            System.out.println("Phone Number : ");
            data.setPhNo(new UserInputOutput().inputint());
        }
        catch (InputMismatchException ex){
            GetCorrectPhNo(data);
        }
    }

    boolean searchInAddressBook(String firstname, String lastname, String city){
        for(Person data : addressbook){
            if((data.getFirstName().equals(firstname) && data.getLastName().equals(lastname)) && (data.getCity().equals(city) || data.getState().equals(city)))
                return true;
        }
        return false;
    }

    ArrayList<Person> getAddressBook(){
        return addressbook;
    }
    void setAddressbook(ArrayList<Person> persons){
        addressbook = persons;
    }

    List<Person> FindInCity(String city){
        return addressbook.stream().filter(e -> e.getCity().equals(city)).collect(Collectors.toList());
    }

    List<Person> FindInState(String state){
        return addressbook.stream().filter(e -> e.getState().equals(state)).collect(Collectors.toList());
    }

    void sortByPersonName(){
        setAddressbook((ArrayList<Person>) addressbook.stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList()));
    }


    List<Person> sortByCity() {
        return addressbook.stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList());
    }

    public List<Person> sortByState() {
        return addressbook.stream().sorted(Comparator.comparing(Person::getState)).collect(Collectors.toList());
    }

    public List<Person> sortByZip() {
        return addressbook.stream().sorted(Comparator.comparing(Person::getZip)).collect(Collectors.toList());
    }

    public static void display(List<Person> personList){
        if(personList != null)
        personList.forEach(System.out::println);
        else
            System.out.println("No data\nNothing to sort");
    }
    void display(){
        for (Person person : addressbook){
            System.out.println("Firstname : "+person.getFirstName());
            System.out.println("Lastname : "+person.getLastName());
            System.out.println("Address : "+person.getAddress());
            System.out.println("City : "+person.getCity());
            System.out.println("State : "+person.getState());
            System.out.println("Email : "+person.getEmail());
            System.out.println("Zip : "+person.getZip());
            System.out.println("Phone Number : "+person.getPhNo()+"\n");
        }
    }

    public static void main(String[] args) {
        AddressBookHashTable addressBookHashTable =new AddressBookHashTable();
        addressBookHashTable.Start();
    }

}