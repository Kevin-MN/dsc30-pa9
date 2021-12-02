import java.util.*;


public class ContactList {

        // Add instance variables here
        private int size;
        private ArrayList<Person> persons;
        private BSTree<Person> person_tree;
        private ArrayList<String> names;


        public ContactList(){
          this.size = 0;
          this.person_tree = new BSTree<Person>();
          this.persons = new ArrayList<Person>();
          this.names = new ArrayList<String>();
        }


        public boolean createContact(Person person) {
            if(lookupContact(person.getName())){
                return false;
            }else{
                this.person_tree.insert(person);
                this.persons.add(person);
                this.names.add(person.getName());

                this.size++;
                return true;
            }
        }

        public boolean lookupContact(String name) {
            ArrayList<String> temps = new ArrayList<String>();
            Person temp = new Person(name, temps);
            if(person_tree.findKey(temp)){
                return true;
            }else{
                return false;
            }
        }

        public Person getContact(String name) {
            for(int i = 0; i < this.persons.size();i++){
                if(this.persons.get(i).getName().compareTo(name) == 0){
                    return this.persons.get(i);
                }
            }
            return null;
        }


        public Person[] getContactByRange(String start, String end) {
            if(start.compareTo(end) >= 0 ){
                throw new IllegalArgumentException();
            }


            ArrayList<Person> temp = new ArrayList<Person>();

            for(int i = 0; i < this.persons.size();i++){
                if(this.persons.get(i).getName().compareTo(start) >= 0 &&  this.persons.get(i).getName().compareTo(end) < 0 )
                temp.add(this.persons.get(i));
            }

            InsertionSort(temp, 0, temp.size() - 1);
            Person[] temp2 = new Person[temp.size()];
            temp2 = temp.toArray(temp2);
            return temp2;
        }

        public boolean deleteContact(String name) {
            Person temp = new Person(name, new ArrayList<String>());
            if(this.person_tree.findKey(temp)){
                this.person_tree.deleteKey(temp);
                this.persons.remove(temp);
                this.names.remove(name);

                this.size--;
                return true;
            }
            else{
                return false;
            }
        }

        public int size() {
            return this.size;
        }

        public String[] fetchAllNames() {

            InsertionSort2(this.names, 0, this.names.size() - 1);
            String[] all_names = new String[this.names.size()];
            all_names = this.names.toArray(all_names);

            return all_names;
        }


        public String[] fetchAllPhoneNumbers() {


            ArrayList<String> all_numbers2 = new ArrayList<String>();
            for(int i = 0; i < this.persons.size();i++) {
                for(int k = 0;  k < this.persons.get(i).getPhoneNumbers().size(); k++){
                    all_numbers2.add(this.persons.get(i).getPhoneNumbers().get(k));
                }
            }


            String[] all_numbers = new String[all_numbers2.size()];
            all_numbers = all_numbers2.toArray(all_numbers);
            return all_numbers;











            /*
            ArrayList<Integer> all_numbers2 = new ArrayList<Integer>();
            for(int i = 0; i < this.persons.size();i++) {
                for(int k = 0;  k < this.persons.get(i).getPhoneNumbers().size(); k++){
                    if(!all_numbers2.contains(Integer.parseInt(this.persons.get(i).getPhoneNumbers().get(k)))) {
                        all_numbers2.add(Integer.parseInt(this.persons.get(i).getPhoneNumbers().get(k)));
                    }
                }
            }

            InsertionSort3(all_numbers2, 0 , all_numbers2.size() - 1);
            String[] all_numbers = new String[all_numbers2.size()];
            for(int i = 0 ; i< all_numbers.length;i++){
                all_numbers[i] = Integer.toString(all_numbers2.get(i));
            }

            return all_numbers;

             */
        }

    public void InsertionSort2(ArrayList<String> list, int start, int end) {
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
    public void InsertionSort3(ArrayList<Integer> list, int start, int end) {
        int n = end;
        for (int i = 1; i <= n; ++i) { // outer loop traverses n times
            Integer key =  list.get(i); // get elem at i
            int j = i - 1;

            //keep on shifting down sorted section till key is not less that previous
            while (j >= 0 && list.get(j).compareTo(key)  > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j+1, key);//we found proper index for key, so set
        }
    }

        public void InsertionSort(ArrayList<Person> list, int start, int end) {
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



    public void QuickSort(ArrayList<String> list, int start, int end) {
        if (start < end) { // while boundary has not been crossed
            int partitionIndex = partition(list, start, end); // partition array/subarrays

            //recursively sort left and right halves
            QuickSort(list, start, partitionIndex-1);
            QuickSort(list, partitionIndex, end);
        }
    }

    public void QuickSort2(ArrayList<Person> list, int start, int end) {
        if (start < end) { // while boundary has not been crossed
            int partitionIndex = partition(list, start, end,0); // partition array/subarrays

            //recursively sort left and right halves
            QuickSort2(list, start, partitionIndex-1);
            QuickSort2(list, partitionIndex, end);
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

    private void swap(ArrayList<Person> arr, int i, int j,int p){
        Person temp_obj = arr.get(i);
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
    public int partition(ArrayList<String> arr, int l, int h) {
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

    private int partition(ArrayList<Person> arr, int l, int h, int p) {
        int middle = l + (h - l) / 2;
        Person pivot = arr.get(middle); // choose middle index a pivot

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
                swap(arr, i, j,0); // swap the elements
                i++;
                j--;
            }
        }
        return i; //return index of pivot for recursive calls
    }





    public class BSTree<T extends Comparable<? super T>> implements Iterable {

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


        void deleteKey(T key) {
            root = delete_helper(root, key);
        }


        BSTNode delete_helper(BSTNode root, T key)
        {

            if (root == null)
                return root;

            /* Otherwise, recur down the tree */
            if (key.compareTo(root.key) < 0)
                root.left = delete_helper(root.left, key);
            else if (key.compareTo(root.key) > 0)
                root.right = delete_helper(root.right, key);

                // if key is same as root's
                // key, then This is the
                // node to be deleted
            else {
                // node with only one child or no child
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                root.key = minValue(root.right);

                // Delete the inorder successor
                root.right = delete_helper(root.right, root.key);
            }

            return root;
        }

        T minValue(BSTNode root)
        {
            T minv = root.key;
            while (root.left != null)
            {
                minv = root.left.key;
                root = root.left;
            }
            return minv;
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

        /* * * * * BST Iterator * * * * */


        /**
         * Class to implement iterator
         */
        public class BSTree_Iterator implements Iterator<T> {
            private Stack<BSTNode> node_stack;


            /**
             * Constructor that initializes stack with
             * left most nodes for traversal
             */
            public BSTree_Iterator() {
                node_stack = new Stack<BSTNode>();
                BSTNode start = getRoot();

                while (start != null) {
                    node_stack.push(start);
                    start = start.getLeft();
                }
            }


            /**
             * This method returns a boolean for an iterator,
             * to indicate if there is a next node or not
             *
             * @return boolean if there is element to travers in stack
             */
            public boolean hasNext() {
                if (this.node_stack.isEmpty() || node_stack.peek() == null) {
                    return false;
                } else {
                    return true;
                }
            }


            /**
             * This method returns the next key value in order from a BSTree
             *
             * @return T key of type T from node
             */
            public T next() {
                BSTNode next_return = this.node_stack.pop(); // pop node from stack to return

                if (next_return == null) { //reached end of traversal
                    throw new NoSuchElementException();
                }

                //no succesor to add to stack, return element
                if (next_return.getRight() == null && next_return.getLeft() == null) {
                    return next_return.getKey();
                } else { // we need to find successor to add to stack and mantain order
                    find_succesor(next_return);
                }
                return next_return.getKey();
            }


            /**
             * This helper method helps find th successor node
             * so that the iterator can properly return the next least
             * smallest element and update stack correctly
             *
             * @param current current node for traversal
             */
            private void find_succesor(BSTNode current) {
                node_stack.push(current.getRight()); // push the right node onto the stack
                current = current.getRight();

                if (current == null) { // no more nodes, return
                    return;
                }

                while (current.getLeft() != null) { // keep traversing down left and add to stack

                    current = current.getLeft();

                    node_stack.push(current);
                }
            }
        }

        public Iterator<T> iterator() {
            return new BSTree_Iterator();
        }

    }



    }





