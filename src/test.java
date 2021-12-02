import java.sql.Array;
import java.util.*;
public class test {


    public static void main(String[] args){
        ContactList test = new ContactList();


        ArrayList<String> nums = new ArrayList<String>();
        nums.add("800");
        nums.add("75");
        nums.add("60");
        nums.add("1000");

        ArrayList<String> nums2 = new ArrayList<String>();

        nums.add("801");
        nums.add("80");
        nums.add("65");
        nums.add("1000");

        Person p1 = new Person("kevin", nums);

        Person p2 = new Person("victor", nums2);

        test.createContact(p1);
        test.createContact(p2);

        String[] all_nums = test.fetchAllPhoneNumbers();

        for(int i = 0; i < all_nums.length;i++){
            System.out.print(all_nums[i] + " ");
        }

        System.out.println();




    }


}
