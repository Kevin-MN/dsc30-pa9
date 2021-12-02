import java.util.*;   

public class Person implements Comparable<Person> {
	
    // Add instance variables here
    private String name;
    private ArrayList<String> phone_numbers;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.name = name;
        this.phone_numbers = new ArrayList<String>();
        for(int i = 0; i < pnArray.size();i++){
            this.phone_numbers.add(pnArray.get(i));
        }
	}
	
    public String getName() {
        return this.name;
    }

    public boolean addPhoneNumber(String pn) {
        if(this.phone_numbers.contains(pn)){
            return false;
        }
        else{
            this.phone_numbers.add(pn);
            return true;
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        return this.phone_numbers;
    }

    public boolean deletePhoneNumber(String pn) {
        if(this.phone_numbers.size() <= 1){
            throw new IllegalArgumentException();
        }
        else if(this.phone_numbers.contains(pn)){
            this.phone_numbers.remove(pn);
            return true;
        }
        else{
            return false;
        }
    }

    public int compareTo(Person t){
        return this.name.compareTo(t.getName());
    }
}
