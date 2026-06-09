import java.util.*;


abstract class LibraryItem {
    private String title;
    private int year;


    private static int count = 0;


    public LibraryItem() {
        this("Unknown", 0);
    }

    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
        count++;
    }

    public String getTitle() { return title; }
    public int getYear() { return year; }

    
    public abstract void displayInfo();

    public static int getCount() {
        return count;
    }
}



class Book extends LibraryItem {
    private String author;

    public Book(String title, int year, String author) {
        super(title, year);
        this.author = author;
    }

 
    public Book(String title, int year) {
        this(title, year, "Unknown");
    }

   
    @Override
    public void displayInfo() {
        System.out.println("Book: " + getTitle() +
                ", Year: " + getYear() +
                ", Author: " + author);
    }
}



class DVD extends LibraryItem {
    private int duration;
    private String genre;

    public DVD(String title, int year, int duration, String genre) {
        super(title, year);
        this.duration = duration;
        this.genre = genre;
    }


    public DVD(String title, int year) {
        this(title, year, 0, "Unknown");
    }

   
    @Override
    public void displayInfo() {
        System.out.println("DVD: " + getTitle() +
                ", Year: " + getYear() +
                ", Duration: " + duration + " mins" +
                ", Genre: " + genre);
    }
}



public class LibrarySystem {
    public static void main(String[] args) {

  
        List<LibraryItem> items = new ArrayList<>();

        items.add(new Book("Java Programming", 2020, "James Gosling"));
        items.add(new Book("Algorithms", 2015));

        items.add(new DVD("Inception", 2010, 148, "Sci-Fi"));
        items.add(new DVD("Interstellar", 2014));

        for (LibraryItem item : items) {
            item.displayInfo();
        }

        System.out.println("\nTotal Library Items: " + LibraryItem.getCount());
    }
}