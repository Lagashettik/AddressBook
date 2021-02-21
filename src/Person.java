public class Person {
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private String Email;
    private int Zip;
    private long PhNo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return FirstName.equals(person.FirstName) && LastName.equals(person.LastName);
    }


}