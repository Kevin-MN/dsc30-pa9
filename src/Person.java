import java.util.*;   

public class Person implements Comparable<Person> {
	
    // Add instance variables here
    private String name;
    private ArrayList<String> phone_numbers;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.name = name;
        this.phone_numbers = pnArray;
	}
	
    public String getName() {
        return this.name;
    }

    public boolean addPhoneNumber(String pn) {
        if(this.phone_numbers.contains(pn)){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        return this.phone_numbers;
    }

    public boolean deletePhoneNumber(String pn) {
        if(this.phone_numbers.size() == 1){
            return false;
        }
        else{
            this.phone_numbers.remove(pn);
            return true;
        }
    }

    public int compareTo(Person t){
        return this.name.compareTo(t.getName());
    }
}
