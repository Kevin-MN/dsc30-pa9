import java.util.*;   

public class Person {
	
    // Add instance variables here
    private String name;
    private ArrayList<String> phone_numbers;
    private int size;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.name = name;
        this.phone_numbers = pnArray;
        InsertionSort(this.phone_numbers, 0 ,this.phone_numbers.size() - 1);
        this.size = pnArray.size();
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
            InsertionSort(this.phone_numbers, 0 ,this.phone_numbers.size() - 1);
            this.size++;
            return true;
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        return this.phone_numbers;
    }

    public boolean deletePhoneNumber(String pn) {
        for(int i = 0; i < this.phone_numbers.size();i++){
            if(this.phone_numbers.get(i).compareTo(pn) == 0){
                if(this.size == 1){
                    throw new IllegalArgumentException();
                }
                this.phone_numbers.remove(i);
                this.size--;
                return true;
            }
        }

        return false;
    }

    public int compareTo(Person t){
        return this.name.compareTo(t.getName());
    }


    public static void InsertionSort(ArrayList<String> list, int start, int end) {
        int n = end;
        for (int i = 1; i <= n; ++i) { // outer loop traverses n times
            String key =  list.get(i); // get elem at i
            int j = i - 1;

            //keep on shifting down sorted section till key is not less that previous
            while (j >= 0 && list.get(j).compareTo(key)  > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j+1, key);//we found proper index for key, so set
        }
    }
}
