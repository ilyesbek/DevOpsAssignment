import java.util.ArrayList;

public class Book {
    private ArrayList<String> authors;
    private String title;
    private String publisher;
    private int publicationYear;

    //Constructor
    public Book(String title, String publisher, int publicationYear)
    {
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.authors = new ArrayList<String>();
    }

    //Constructor that takes default parameters
    public Book(){
        this("N/A",  "N/A", 2019);
    }

    //Method to add one or more authors to a book
    public void addAuthor(String authorName) {
        this.authors.add(authorName);
    }

    //Getters & Setters
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getAuthors(){
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    //Method to display the books with information
    public void display() {
        System.out.println("Book: ");
        for(String author : authors){
            System.out.println("Author: "+author);
        }
        System.out.println("Title: "+getTitle());
        System.out.println("Publisher: "+getPublisher());
        System.out.println("Published: "+getPublicationYear());
        System.out.println();

    }
}


