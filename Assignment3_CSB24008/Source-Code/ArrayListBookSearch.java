import java.util.*;

public class ArrayListBookSearch {
    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        books.add("Wings of Fire");
        books.add("It Ends With Us");
        books.add("The Story of My Experiments with Truth");
        books.add("Harry Potter and the Chamber of Secrets");
        books.add("Geetanjali");

        System.out.println("Enter the word to search: ");
        String searchWord = sc.nextLine();

        boolean found = false;

        for(String book : books) {
            if (book.toLowerCase().contains(searchWord.toLowerCase())) {
                System.out.println("Found '" + book +"'");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No book title found having the word you entered!");
        }

        sc.close();
    }
}
