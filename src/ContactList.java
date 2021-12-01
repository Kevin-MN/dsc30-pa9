import java.util.*;
import java.util.ArrayList;





public class ContactList {

    public class BSTree<T extends Comparable<? super T>>{

        /* * * * * BST Instance Variables * * * * */

        private int nelems; // number of elements stored
        private BSTNode root; // reference to root node

        /* * * * * BST Node Inner Class * * * * */

        protected class BSTNode {

            T key;
            LinkedList<T> dataList;
            BSTNode left;
            BSTNode right;

            /**
             * A constructor that initializes the BSTNode instance variables.
             *
             * @param left     Left child
             * @param right    Right child
             * @param dataList Linked list of related info
             * @param key      Node's key
             */
            public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
                this.left = left;
                this.right = right;
                this.key = key;
                this.dataList = dataList;
            }

            /**
             * A constructor that initializes BSTNode variables. Note: This constructor is
             * used when you want to add a key with no related information yet. In this
             * case, you must create an empty LinkedList for the node.
             *
             * @param left  Left child
             * @param right Right child
             * @param key   Node's key
             */
            public BSTNode(BSTNode left, BSTNode right, T key) {
                this.left = left;
                this.right = right;
                this.key = key;
                this.dataList = new LinkedList<T>();
            }

            /**
             * Return the key
             *
             * @return The key
             */
            public T getKey() {
                return this.key;
            }

            /**
             * Return the left child of the node
             *
             * @return The left child of the node
             */
            public BSTNode getLeft() {
                return this.left;
            }

            /**
             * Return the right child of the node
             *
             * @return The right child of the node
             */
            public BSTNode getRight() {
                return this.right;
            }

            /**
             * Return the linked list of the node
             *
             * @return The linked list of the node
             */
            public LinkedList<T> getDataList() {
                return this.dataList;
            }

            /**
             * Setter for left child of the node
             *
             * @param newleft New left child
             */
            public void setleft(BSTNode newleft) {
                this.left = newleft;
            }

            /**
             * Setter for right child of the node
             *
             * @param newright New right child
             */
            public void setright(BSTNode newright) {
                this.right = newright;
            }

            /**
             * Setter for the linked list of the node
             *
             * @param newData New linked list
             */
            public void setDataList(LinkedList<T> newData) {
                this.dataList = newData;
            }

            /**
             * Append new data to the end of the existing linked list of the node
             *
             * @param data New data to be appended
             */
            public void addNewInfo(T data) {
                this.dataList.add(data);
            }

            /**
             * Remove 'data' from the linked list of the node and return true. If the linked
             * list does not contain the value 'data', return false.
             *
             * @param data Info to be removed
             * @return True if data was found, false otherwise
             */
            public boolean removeInfo(T data) {
                if (this.dataList.contains(data)) {
                    this.dataList.remove(data);
                    return true;
                } else {
                    return false;
                }
            }
        }

        /* * * * * BST Methods * * * * */

        /**
         * 0-arg constructor that initializes root to null and nelems to 0
         */
        public BSTree() {
            this.root = null;
            this.nelems = 0;
        }

        /**
         * Return the root of BSTree. Returns null if the tree is empty
         *
         * @return The root of BSTree, null if the tree is empty
         */
        public BSTNode getRoot() {
            return this.root;
        }

        /**
         * Return the BST size
         *
         * @return The BST size
         */
        public int getSize() {
            return this.nelems;
        }

        /**
         * Insert a key into BST
         *
         * @param key
         * @return true if insertion is successful and false otherwise
         */
        public boolean insert(T key) {
            if (key == null) { // check if data is null
                throw new NullPointerException();
            }

            if (root == null) { // case for empty tree, set root to new node
                this.root = new BSTNode(null, null, key);
                this.nelems++;
                return true;
            }

            if (this.findKey(key)) { // check if tree already contains key, no duplicates
                return false;
            }

            boolean add_attempt = recur_add(this.root, key); // use helper method to add

            return add_attempt;
        }


        /**
         * Helper method to insert key, implemented recursively
         *
         * @param current the current node of traversal
         * @param key     the value for comparison throughout traversal
         * @return true if insertion is successful and false otherwise
         */
        private boolean recur_add(BSTNode current, T key) {
            int compare = key.compareTo(current.getKey()); // store comparison in variable

            if (compare == 0) { // key already exists, do not add, false
                return false;
            } else if (compare < 0) { // key is less than
                if (current.getLeft() == null) { // condition to add new node
                    current.setleft(new BSTNode(null, null, key));
                    this.nelems++;
                    return true;
                } else { // can't add sp keep traversing to left
                    return recur_add(current.getLeft(), key);
                }
            } else {// key is greater than
                if (current.getRight() == null) {// condition to add new node
                    current.setright(new BSTNode(null, null, key));
                    this.nelems++;
                    return true;
                } else {// can't add sp keep traversing to right
                    return recur_add(current.getRight(), key);
                }
            }
        }


        /**
         * Return true if the 'key' is found in the tree, false otherwise
         *
         * @param key To be searched
         * @return True if the 'key' is found, false otherwise
         * @throws NullPointerException If key is null
         */
        public boolean findKey(T key) {
            if (key == null) { // throw conditional
                throw new NullPointerException();
            }

            if (this.root == null) { // tree is empty return false
                return false;
            }

            return findKey_helper(this.root, key); // use recursive helper to find key

        }

        /**
         * Helper method for finding key, implemented recursively
         *
         * @param key To be searched
         * @return True if the 'key' is found, false otherwise
         */
        public boolean findKey_helper(BSTNode current, T key) {
            if (current == null) { // reached null node from traversal, doesn't exist, false
                return false;
            }

            int compare = key.compareTo(current.getKey());

            if (compare == 0) {
                return true;
            } else if (compare < 0) { //traverse left key is less than
                return findKey_helper(current.getLeft(), key);
            } else {//traverse right key is greater than
                return findKey_helper(current.getRight(), key);
            }
        }

        /**
         * Insert 'data' into the LinkedList of the node whose key is 'key'
         *
         * @param key  Target key
         * @param data To be added to key's LinkedList
         * @throws NullPointerException     If eaither key or data is null
         * @throws IllegalArgumentException If key is not found in the BST
         */
        public void insertData(T key, T data) {
            if (key == null || data == null) { // invalid data/key
                throw new NullPointerException();
            }

            if (!findKey(key)) { // key doesn't exist in tree
                throw new IllegalArgumentException();
            }

            LinkedList<T> target_add = findDataList(key); // key does exist so find the dataLis
            target_add.add(data); //add data

        }

        /**
         * Return the LinkedList of the node with key value 'key'
         *
         * @param key Target key
         * @return LinkedList of the node whose key value is 'key'
         * @throws NullPointerException     If key is null
         * @throws IllegalArgumentException If key is not found in the BST
         */
        public LinkedList<T> findDataList(T key) {
            if (key == null) {
                throw new NullPointerException();
            }

            if (!findKey(key)) {
                throw new IllegalArgumentException();
            }

            LinkedList<T> target_data_list = findDataList_helper(this.root, key);
            return target_data_list;
        }


        /**
         * Helper method that finds dataList given key
         * implemented recursively
         *
         * @param key Target key
         * @return LinkedList of the node whose key value is 'key'
         */
        public LinkedList<T> findDataList_helper(BSTNode node, T key) {
            if (node == null) { // traversed through tree and couldn't find
                return null;
            }

            int compare = key.compareTo(node.getKey());

            if (compare == 0) { //key matched found node
                return node.getDataList();
            } else if (compare < 0) { // key is less, traverse left
                return findDataList_helper(node.getLeft(), key);
            } else {  // key is less, traverse right
                return findDataList_helper(node.getRight(), key);
            }
        }

        /**
         * Return the height of the tree
         *
         * @return The height of the tree, -1 if BST is empty
         */
        public int findHeight() {
            if (this.root == null) {
                return -1;
            }

            if (this.nelems == 1) {
                return 0;
            }

            return findHeightHelper(this.root); // use helper method
        }

        /**
         * Helper for the findHeight method
         *
         * @param next Root node
         * @return The height of the tree, -1 if BST is empty
         */
        private int findHeightHelper(BSTNode next) {
            if (next == null) {
                return -1;
            } // traverse list and add 1 for each recursive call, stack unwinds adding max
            return 1 + Math.max(findHeightHelper(next.left), findHeightHelper(next.right));
        }


        /**
         * Sorts class.
         *
         * @param <T> Generic type
         * @author Kevin Morales-Nguyen
         * @since 11/1/21
         */
        public class Sorts<T extends Comparable<? super T>> {

            private static final int MIDDLE_IDX = 2;

            /**
             * This method performs insertion sort on the input arraylist
             *
             * @param list  The arraylist we want to sort
             * @param start The initial index on subsection of Arraylist we want to sort
             * @param end   The final index of the subsection of Arraylist we want to sort
             */
            public void InsertionSort(ArrayList<String> list, int start, int end) {
                int n = end;
                for (int i = 1; i <= n; ++i) { // outer loop traverses n times
                    String key = list.get(i); // get elem at i
                    int j = i - 1;

                    //keep on shifting down sorted section till key is not less that previous
                    while (j >= 0 && list.get(j).compareTo(key) > 0) {
                        list.set(j + 1, list.get(j));
                        j = j - 1;
                    }

                    list.set(j + 1, key);//we found proper index for key, so set
                }
            }

            /**
             * This method performs merge sort on the input arraylist
             *
             * @param list  The arraylist we want to sort
             * @param start The inital index on subsection of Arraylist we want to sort
             * @param end   The final index of the subsection of Arraylist we want to sort
             */
            public void MergeSort(ArrayList<T> list, int start, int end) {

                if (start < end) {
                    int mid = start + (end - start) / MIDDLE_IDX;
                    MergeSort(list, start, mid);
                    MergeSort(list, mid + 1, end);

                    merge(list, start, mid, end);
                }
            }

            /**
             * merge helper function for MergeSort
             *
             * @param arr The arraylist we want to sort
             * @param l   left-most index we want to merge
             * @param m   the middle index we want to merge
             * @param r   right-most index we want to merge
             */
            private void merge(ArrayList<T> arr, int l, int m, int r) {

                int mergedSize = r - l + 1;

                ArrayList<T> mergedNums = new ArrayList<>();
                int left = l, right = m + 1;
                while (left <= m && right <= r) {
                    if (arr.get(left).compareTo(arr.get(right)) <= 0) {
                        mergedNums.add(arr.get(left));
                        left++;
                    } else {
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
             * @param list  The arraylist we want to sort
             * @param start The inital index on subsection of Arraylist we want to sort
             * @param end   The final index of the subsection of Arraylist we want to sort
             */
            public void QuickSort(ArrayList<T> list, int start, int end) {
                if (start < end) { // while boundary has not been crossed
                    int partitionIndex = partition(list, start, end); // partition array/subarrays

                    //recursively sort left and right halves
                    QuickSort(list, start, partitionIndex - 1);
                    QuickSort(list, partitionIndex, end);
                }
            }

            /**
             * private helper method to swap elements of arraylist
             *
             * @param arr The arraylist we want swap on
             * @param i   element of first item to swap
             * @param j   element of second item to swap
             */
            private void swap(ArrayList<T> arr, int i, int j) {
                T temp_obj = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp_obj);
            }


            /**
             * partition helper function for QuickSort
             *
             * @param arr The arraylist we want to sort
             * @param l   left-most index we want to merge
             * @param h   right-most index we want to merge
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
             * @param list   The arraylist we want to sort
             * @param start  The inital index on subsection of Arraylist we want to sort
             * @param end    The final index of the subsection of Arraylist we want to sort
             * @param cutoff the minimum length of an subsection of the arraylist
             *               such that we switch to Insertion Sort
             */
            public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
                if (((end - start) + 1) <= cutoff) { // check cutoff condition, size of subarray
                    //InsertionSort(list,start,end);
                }

                if (start < end) {
                    int partitionIndex = partition(list, start, end);

                    Modified_QuickSort(list, start, partitionIndex - 1, cutoff - 1);
                    Modified_QuickSort(list, partitionIndex, end, cutoff - 1);
                }
            }

            /**
             * This method performs cocktail sort on the input list
             *
             * @param list  The arraylist we want to sort
             * @param start The inital index on subsection of Arraylist we want to sort
             * @param end   The final index of the subsection of Arraylist we want to sort
             */
            public void cocktailSort(ArrayList<T> list, int start, int end) {

                boolean swapped = true;
                int start_inside = start; // starting index
                int end_inside = end; // ending index

                while (swapped == true) //keep sorting until no swaps, means all are in order
                {

                    swapped = false; // reset swap boolean from previous iteration

                    //forward pass through the list
                    for (int i = start_inside; i < end_inside; ++i) {       // compare adjacent element to see if less than current node
                        if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                            T temp_elem = list.get(i);
                            list.set(i, list.get(i + 1));
                            list.set(i + 1, temp_elem);
                            swapped = true;
                        }
                    }


                    if (swapped == false) // if nothing has swapped on forward pass all
                        break;            // are in correct order so stop sorting


                    swapped = false; // reset swapped because we made a swap


                    end_inside--;

                    //backwards pass exact same as forward pass just decrementing down
                    for (int i = end_inside - 1; i >= start_inside; i--) {
                        if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                            T temp_elem = list.get(i);
                            list.set(i, list.get(i + 1));
                            list.set(i + 1, temp_elem);
                            swapped = true;
                        }
                    }

                    start_inside++;
                }
            }
        }


        // Add instance variables here
        private int size;
        private BSTree name_tree;
        private ArrayList<String> name_list;
        private ArrayList<String> numbers_list;
        private Sorts sorter;


        public boolean createContact(Person person) {
            sorter = new Sorts();
            if (this.name_tree.findKey(person.getName())) {
                return false;
            } else {
                this.name_tree.insert(person.getName());
                this.name_list.add(person.getName());


                ArrayList<String> temp_numbers = person.getPhoneNumbers();
                for (int i = 0; i < person.getPhoneNumbers().size(); i++) {
                    this.numbers_list.add(temp_numbers.get(i));
                    this.name_tree.insertData(person.getName(), temp_numbers.get(i));
                }

                sorter.InsertionSort(this.numbers_list, 0, this.numbers_list.size());
                sorter.InsertionSort(this.name_list, 0, this.name_list.size());
                this.size++;
                return true;
            }
        }

        public boolean lookupContact(String name) {
            if (this.name_tree.findKey(name)) {
                LinkedList<String> deleted = this.name_tree.findDataList(name);
                if (deleted.getLast().compareTo("-1") != 0) {
                    return true;
                }
            } else {
                return false;
            }
            return false;
        }

        public Person getContact(String name) {
            if (this.name_tree.findKey(name)) {
                LinkedList<String> deleted = this.name_tree.findDataList(name);
                if (deleted.getLast().compareTo("-1") != 0) {
                    LinkedList<String> temp = this.name_tree.findDataList(name);
                    ArrayList<String> temp2 = new ArrayList<String>();
                    for (int i = 0; i < temp.size(); i++) {
                        temp2.add(temp.get(i));
                    }
                    Person per = new Person(name, temp2);
                    return per;
                }
            } else {
                return null;
            }
            return null;
        }


        public Person[] getContactByRange(String start, String end) {
            if (start.compareTo(end) < 0) {
                throw new IllegalArgumentException();
            }

            int start_index = this.name_list.indexOf(start);
            int end_index = this.name_list.indexOf(end);

            Person[] persons = new Person[end_index - start_index + 1];

            for (int i = 0; i < persons.length; i++) {
                persons[i] = getContact(this.name_list.get(start_index + i));
            }

            return persons;
        }

        public boolean deleteContact(String name) {
            if (this.name_tree.findKey(name)) {
                this.name_tree.insertData(name, "-1");
                return true;
            }
            return false;
        }

        public int size() {
            return this.size;
        }

        public String[] fetchAllNames() {
            String[] names = new String[this.name_list.size()];
            for (int i = 0; i < name_list.size(); i++) {
                names[i] = this.name_list.get(i);
            }
            return names;
        }

        public String[] fetchAllPhoneNumbers() {
            String[] numbers = new String[this.numbers_list.size()];
            for (int i = 0; i < numbers_list.size(); i++) {
                numbers[i] = this.numbers_list.get(i);
            }
            return numbers;
        }
    }

}
