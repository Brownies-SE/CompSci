import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NoOfTimesEachWordAppeared {
// Creating a HashMap
static Map < String, Integer > m = new HashMap < String, Integer > ();

public static void main(String[] args) {

String str = "";
/*
* Creating an Scanner class object which is used to get the inputs
* entered by the user
*/
Scanner sc = new Scanner(System.in);

System.out.print("Enter words (Seperated by space) :");
str = sc.nextLine();
String words[] = str.split(" ");

for (int i = 0; i < words.length; i++) {
// calling the method
checkWordInMap(words[i]);
}

System.out.println();
Set < String > keys = m.keySet();

for (String i: keys) {
System.out.println("'" + i + "'" + " appeared " + m.get(i) + " times.");
}

}

private static void checkWordInMap(String word) {
// Checking whether the word is available in the HashMap
if (m.containsKey(word)) {
m.put(word, m.get(word) + 1);
} else {
// If not available Adding the word to the HashMap
m.put(word, 1);
}

}

}