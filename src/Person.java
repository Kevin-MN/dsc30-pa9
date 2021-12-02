import java.util.*;   

public class Person implements Comparable<Person> {
	
    // Add instance variables here
    private String name;
    private ArrayList<String> phone_numbers;
    private int size;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.name = name;
        this.phone_numbers = pnArray;
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
            this.size++;
            return true;
        }
    }

    public ArrayList<String> getPhoneNumbers() {
        QuickSort(this.phone_numbers, 0 , this.phone_numbers.size() - 1);
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


    public void QuickSort(ArrayList<String> list, int start, int end) {
        if (start < end) { // while boundary has not been crossed
            int partitionIndex = partition(list, start, end); // partition array/subarrays

            //recursively sort left and right halves
            QuickSort(list, start, partitionIndex-1);
            QuickSort(list, partitionIndex, end);
        }
    }

    /**
     * private helper method to swap elements of arraylist
     *
     * @param arr The arraylist we want swap on
     * @param i element of first item to swap
     * @param j element of second item to swap
     */
    private void swap(ArrayList<String> arr, int i, int j){
        String temp_obj = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp_obj);
    }


    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     */
    int partition(ArrayList<String> arr, int l, int h) {
        int middle = l + (h - l) / 2;
        String pivot = arr.get(middle); // choose middle index a pivot

        int i = l, j = h;
        while (i <= j) {
            // find element that is greater than pivot
            while (arr.get(i).compareTo(pivot) < 0) {
                i++;
            }
            //find element that is less than pivot
            while (arr.get(j).compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j); // swap the elements
                i++;
                j--;
            }
        }
        return i; //return index of pivot for recursive calls
    }
}
