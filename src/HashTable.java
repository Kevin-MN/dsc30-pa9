
/*
 * Name: Kevin Morales-Nguyen
 * PID: A17186624
 */

import java.util.Arrays;

/**
 * This class implements a hastable that holds strings,
 *
 * @author Kevin Morales-Nguyen
 * @since 11/22/21
 */
public class HashTable implements IHashTable {

    /* the bridge for lazy deletion */
    private static final String BRIDGE = new String("[BRIDGE]".toCharArray());
    private static final int DEFAULT_CAPACITY = 15;
    private static final double LOAD_FACTOR = 0.55;

    /* instance variables */
    private int size; // number of elements stored
    private String[] table; // data table
    private int collisions;
    private String stats_log;
    private int num_rehash;

    /**
     * This is the default constructor for capacity of 15
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * This is the argument constructor which sets the
     * initial capacity of the array, also initializes
     * all instance variables
     * @param capacity
     */
    public HashTable(int capacity) {
        if(capacity < 5){
            throw new IllegalArgumentException();
        }

        this.table = new String[capacity];
        this.size = 0;
        this.stats_log = "";
        this.collisions = 0;
        this.num_rehash = 0;
    }

    @Override
    public boolean insert(String value) {
        if(value == null){
            throw new NullPointerException();
        }

        if(lookup(value) == true){ // if value already exists return false
            return false;
        }

        this.size++;

        double load_calc = (double) this.size/this.table.length; //calculate load

        if(load_calc > LOAD_FACTOR){ // if load threshhold passed, rehash
            rehash();
            this.size++;
        }

        //hash the string and get iterator and table size so we can place element
        int hash_index = hashString(value);
        int iterator = 0;
        int table_size = this.table.length;

        //linear prob placementm increment until element is null, that means it's empty
        while(this.table[(hash_index + iterator) % table_size] != null && this.table[(hash_index + iterator) % table_size] != BRIDGE){
            iterator++;
            this.collisions++;
        }
        //insert element
        this.table[(hash_index + iterator) % table_size] = value;
        return true;
    }


    @Override
    public boolean delete(String value) {
        if(value == null){
            throw new NullPointerException();
        }

        if(lookup(value) == false){ // if it is not in table return false
            return false;
        }

        //hash the string and get iterator and table size so we can traverse
        int search_index = hashString(value);
        int iterator = 0;
        int table_size = this.table.length;

        while(this.table[(search_index + iterator) % table_size] != null){
            //found bridge keep iterating
            if(this.table[(search_index + iterator) % table_size] == BRIDGE){
                iterator++;
            } // found the element so set it to bridge and decrement size, return true
            else if(value.compareTo(this.table[(search_index + iterator) % table_size]) == 0){
                this.table[(search_index + iterator) % table_size] = BRIDGE;
                this.size--;
                return true;
            }
            else{ // found valid element that is not the element we are seaching for
                iterator++;
            }
        }

        return false;
    }

    @Override
    public boolean lookup(String value) {
        if(value == null){
            throw new NullPointerException();
        }

        //hash the string and get iterator and table size so we can traverse
        int search_index = hashString(value);
        int iterator = 0;
        int table_size = this.table.length;

        while(this.table[(search_index + iterator) % table_size] != null){
            //keep iterating across bridge
            if(this.table[(search_index + iterator) % table_size] == BRIDGE){
                iterator++;
            } // we found the element and it matches
            else if(value.compareTo(this.table[(search_index + iterator) % table_size]) == 0){
                return true;
            }
            else{ // found element through linear probing but doesnt match
                iterator++;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.table.length;
    }

    /**
     * This method returns the relevant statistics recorded from rehashing
     * @return a string that contains rehash info
     */
    public String getStatsLog() {
        return this.stats_log;
    }


    /**
     * This helper method helps rehash the valid elements when the
     * load threshhold is passed
     */
    private void rehash() {
        ++this.num_rehash;
        double load_calc = (double)this.size/this.table.length; //calculate load factor

        //concatenate to string relevant indformation
        this.stats_log = this.stats_log.concat("Before rehash # " + this.num_rehash
                + ": load factor " + String.format("%.2f", load_calc)
                + ", " + this.collisions + " collision(s).\n");

        //reset collisions and size of the hashtable
        this.collisions = 0;
        this.size = 0;

        //create temp variable to iterate old elements and reinsert with
        //hash function based on new parameters
        String[] new_table = new String[this.table.length * 2];
        String[] old_table = this.table;
        this.table = new_table;

        //reinsert all valid elements
        for(int i = 0; i < old_table.length;i++) {
            if (old_table[i] != null && old_table[i] != BRIDGE) {
                insert(old_table[i]);
            }

        }
    }

    /**
     * This helper method creates a key from a string object
     * @param value string to be hashed
     * @return index of object
     */
    private int hashString(String value) {
        int hash_value = 0;

        for(int i = 0; i < value.length();i++){
            int left_shift = hash_value<<5;

            int right_shift = hash_value >>>27;

            hash_value = (left_shift | right_shift) ^ value.charAt(i);
        }

        return Math.abs(hash_value) % this.table.length;
    }

    /**
     * Returns the string representation of the hash table.
     * This method internally uses the string representation of the table array.
     * DO NOT MODIFY. You can use it to test your code.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
