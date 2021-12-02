/*
 * NAME: Kevin Morales-Nguyen
 * PID:  A17186624
 */
import java.util.ArrayList;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Kevin Morales-Nguyen
 * @since  11/1/21
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public static void InsertionSort(ArrayList<Person> list, int start, int end) {
        int n = end;
        for (int i = 1; i <= n; ++i) { // outer loop traverses n times
            Person key =  list.get(i); // get elem at i
            int j = i - 1;

            //keep on shifting down sorted section till key is not less that previous
            while (j >= 0 && list.get(j).compareTo(key)  > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j+1, key);//we found proper index for key, so set
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }

        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
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
    private void swap(ArrayList<T> arr, int i, int j){
        T temp_obj = arr.get(i);
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
    int partition(ArrayList<T> arr, int l, int h) {
        int middle = l + (h - l) / 2;
        T pivot = arr.get(middle); // choose middle index a pivot

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

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if(((end - start) + 1) <= cutoff){ // check cutoff condition, size of subarray
            //InsertionSort(list,start,end);
        }

        if (start < end) {
            int partitionIndex = partition(list, start, end);

            Modified_QuickSort(list, start, partitionIndex-1, cutoff - 1);
            Modified_QuickSort(list, partitionIndex, end, cutoff - 1);
        }
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){

        boolean swapped = true;
        int start_inside = start; // starting index
        int end_inside = end; // ending index

        while (swapped == true) //keep sorting until no swaps, means all are in order
        {

            swapped = false; // reset swap boolean from previous iteration

            //forward pass through the list
            for (int i = start_inside; i < end_inside ; ++i)
            {       // compare adjacent element to see if less than current node
                if (list.get(i).compareTo(list.get(i+1)) > 0) {
                    T temp_elem = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp_elem);
                    swapped = true;
                }
            }


            if (swapped == false) // if nothing has swapped on forward pass all
                break;            // are in correct order so stop sorting


            swapped = false; // reset swapped because we made a swap


            end_inside--;

            //backwards pass exact same as forward pass just decrementing down
            for (int i = end_inside - 1; i >= start_inside; i--)
            {
                if (list.get(i).compareTo(list.get(i+1)) > 0)
                {
                    T temp_elem = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp_elem);
                    swapped = true;
                }
            }

            start_inside++;
        }
    }
}
