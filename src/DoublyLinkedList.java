/*
 * NAME: Kevin Morales-Nguyen
 * PID: A17186624
 */

import java.util.AbstractList;

/**
 * This class implements a DoublyLinkedList by utilizing nodes
 * and associated methods to perform operations on the list
 * such as adding and removing data
 * @author Kevin Morales-Nguyen
 * @since 10/22/21
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }


        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.setNext(this.next);
            this.next.setPrev(this.prev);
        }
    }


    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null, null, this.head); // set prev to head
        this.head.setNext(this.tail); // set heads next to tail
        this.nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if(element == null){
            throw new NullPointerException();
        }

        //create new node with argument data and set newnode->next to tail
        // and newnode ->prev to node before tail
        Node new_node = new Node(element, this.tail, this.tail.getPrev());
        // update node before tail, so next is new node
        this.tail.getPrev().setNext(new_node);
        this.tail.setPrev(new_node);// set tails prev to new node
        this.nelems++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index the index to inset the element at
     * @param element the data to put in the node
     * @throws IndexOutOfBoundsException if the index is not in acceptable range
     * @throws NullPointerException if the element to put in node is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        if(element == null){
            throw new NullPointerException();
        }

        if(index < 0 || index > nelems){
            throw new IndexOutOfBoundsException();
        }

        Node new_node = new Node(element); // create new node
        Node prev_node = this.head; // traversal node that points to current - 1
        Node current_node = this.head.getNext(); // traversal node that points to current
        for(int i = 0; i < index;i++){ // traverse unitl at desired index, increment nodes
            prev_node = prev_node.getNext();
            current_node =current_node.getNext();
        }

        prev_node.setNext(new_node); // set prev nodes next
        current_node.setPrev(new_node); // set next node prev
        //set new node next and prev
        new_node.setPrev(prev_node);
        new_node.setNext(current_node);
        nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        //point head to tail and tail to head, nodes are cleaned up by GC
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element object to check for existence in list
     * @return boolean indicating if the list contains a node with argument data
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;

        Node current_node = this.head.getNext(); // create traversal node

        while(current_node.getNext() != null){// traverse until at end of list
            //if node data is equal then return true
            if(current_node.getElement().equals(data)){
                return true;
            }
            current_node = current_node.getNext();
        }
        return false; // reached end of list and not found, return false
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index to retrieve node data from
     * @return the data in the node at index, of type T
     * @throws IndexOutOfBoundsException if the index is not in acceptable range
     *
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= nelems){
            throw new IndexOutOfBoundsException();
        }

        Node index_node = getNth(index); // get Nth node

        return index_node.getElement(); // return the data in the node
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param  index the index of the node to get
     * @return node at Nth index
     */
    private Node getNth(int index) {
        Node current_node = this.head.getNext(); // traversal node

        for(int i = 0; i < index; i++) { // traverse
            current_node = current_node.getNext();
        }

        return current_node;
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false if not empty
     */
    @Override
    public boolean isEmpty() {
        if(this.nelems == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Remove the element from position index in the list
     * return the data inside the node
     * @param index the index of the node to remove
     * @return data inside remove node, of type T
     * @throws IndexOutOfBoundsException if the index is not in range
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= nelems){
            throw new IndexOutOfBoundsException();
        }

        //create traversal nodes
        Node current_node = this.head.getNext();
        Node prev_node = this.head;

        for(int i = 0; i < index; i++) {// traverse
            current_node = current_node.getNext();
            prev_node = prev_node.getNext();
        }

        prev_node.setNext(current_node.getNext()); //set prev's next to current->next
        current_node.getNext().setPrev(prev_node);// set current->next prev to prev_node
        this.nelems--;
        return current_node.getElement(); // return the data inside removed node
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the index of the node where data will be set
     * @param element the new data to insert into node
     * @return data of type T, that was inside node before replacing
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if(index < 0 || index >= nelems){
            throw new IndexOutOfBoundsException();
        }

        if(element == null){
            throw new NullPointerException();
        }

        Node current_node = this.head.getNext();

        for(int i = 0; i < index; i++) {
            current_node = current_node.getNext();
        }

        T data = current_node.getElement(); // get data from node
        current_node.setElement(element); // set new data

        return data;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return int that represents how many elements in the dll
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return a string that is formated, with elements printed in order
     */
    @Override
    public String toString() {
        Node current_node = this.head.getNext();
        String contents = "[(head) -> ";

        while(current_node.getNext() != null){
            contents = contents.concat(current_node.getElement() + " -> " );
            current_node = current_node.getNext();
        }
        contents = contents.concat("(tail)]");

        return contents;
    }


    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * @param base the base for indeces to be removed
     */
    public void removeMultipleOf(int base) {
        if(base < 1){
            throw new IllegalArgumentException();
        }
        //create dll to hold indeces to be removed
        DoublyLinkedList<Integer> indeces = new DoublyLinkedList<Integer>();

        //loop through indeces to determine which to remove
       for(int i = 0;i < this.nelems;i++){
           if(i % base == 0){
               indeces.add(i);
           }
       }

       int copy_nelems = indeces.size();

       for(int l = 0; l < copy_nelems ;l++ ){ // for stored indeces
           // when an index is removed we shift by 1, so n indeces removed requires
           //n shifts, hence subtract l
           this.remove((int)indeces.get(l) - l);
       }
    }


    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * @param other dll to swap with
     * @param splitIndex the index to split at
     */
    public void swapSegment(DoublyLinkedList other, int splitIndex) {
        //get references to relevant nodes
        Node split_index_one = this.getNth(splitIndex);
        Node split_index_one_plus = split_index_one.getNext();
        Node this_head = this.head;
        Node this_head_plus = this.head.getNext();

        Node split_index_two = other.getNth(splitIndex);
        Node split_index_two_plus = split_index_two.getNext();
        Node other_head = other.getNth(0).getPrev();
        Node other_head_plus = other_head.getNext();

        //setnext for splitindex to other lists targetindex + 1
        this.getNth(splitIndex).setNext(split_index_two.getNext());
        other.getNth(splitIndex).setNext(split_index_one_plus);
        //set previous to other lists target_index node
        split_index_two_plus.setPrev(split_index_one);
        split_index_one_plus.setPrev(split_index_two);

        //update head to store others zero index
        this_head.setNext(other_head_plus);
        other_head.setNext(this_head_plus);
    }

}