import java.util.*;   

public class Person {
	
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
        if(this.phone_numbers.size() == 1){
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < this.phone_numbers.size();i++){
            if(this.phone_numbers.get(i).compareTo(pn) == 0){
                this.phone_numbers.remove(i);
                return true;
            }
        }

        return false;
    }

}
