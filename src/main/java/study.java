import java.lang.*;
import java.util.*;

public class study {

    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<Integer>(3);

        integerList.add(19);
        integerList.add(5);
        integerList.add(1978);

        for (Iterator<Integer> i = integerList.iterator(); i.hasNext();) {
            int value = i.next();
            System.out.println(value);
        }

        List<String> stringList = new ArrayList<String>(4);
        //stringList.iterator();



        Character[] characters = new Character[3];
        String[] strings = new String[3];
        Integer[] integers = new Integer[3];

        strings[0] = "ciao";
        strings[1] = "forse";
        strings[2] = "bye";

        characters[0] = 'b';
        characters[0] = 'y';
        characters[0] = 'e';

        integers[0] = 1;
        integers[0] = 2;
        integers[0] = 3;

        for (int integer:integers) {
            System.out.println(integer);
        }







    }



}
