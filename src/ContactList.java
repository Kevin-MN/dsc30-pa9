import java.util.*;

public class ContactList {

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
        if(start.compareTo(end) < 0){
            throw new IllegalArgumentException();
        }

        int start_index = this.name_list.indexOf(start);
        int end_index = this.name_list.indexOf(end);

        Person[] persons = new Person[end_index - start_index + 1];

        for(int i = 0; i < persons.length;i++){
            persons[i] = getContact(this.name_list.get(start_index + i));
        }

        return persons;
    }

    public boolean deleteContact(String name) {
        if(this.name_tree.findKey(name)){
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
        for(int i = 0; i < name_list.size();i++){
            names[i] = this.name_list.get(i);
        }
        return names;
    }

    public String[] fetchAllPhoneNumbers() {
        String[] numbers = new String[this.numbers_list.size()];
        for(int i = 0; i < numbers_list.size();i++){
            numbers[i] = this.numbers_list.get(i);
        }
        return numbers;
    }
}
