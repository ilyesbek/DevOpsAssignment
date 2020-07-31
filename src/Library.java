import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Library {

    private ArrayList<Book>  booksList;

    //Constructor
    public Library(String input) {
        this.booksList = new ArrayList<>(readBooks(input));

    }

    //
    private List<Book> readBooks(String input) throws ArrayIndexOutOfBoundsException {
        ArrayList<Book> booksList = new ArrayList<>();

        String[] lines = input.split( "\n");
        Book book = null;

        for (String line : lines) {

            //If there's new book, we add it
            if(line.startsWith("Book:")){
                book = new Book();

            } else if (line.trim().equals("")){
                booksList.add(book); /* Creating the book and adding fields, if we arrive into an empty line, we add
                                       the book and set it to null and we wait for the next one*/
                book = null;
            }else if (book != null){
               String value = line.split(":")[1].trim(); //removes the spaces that are before and after the string

            if(line.startsWith("Title")){ book.setTitle(value);}
            if(line.startsWith("Author")){ book.addAuthor(value);}
            if(line.startsWith("Publisher")){ book.setPublisher(value);}
            if(line.startsWith("Published")){ book.setPublicationYear(Integer.parseInt(value));}
            } else {
                System.err.println("Error : Bad input format");
            }

        }

        //Account for no empty lines at the end of the file
        if (book !=  null){
            booksList.add(book);
        }

        return booksList;
    }

    public void displayBooks(){
        for (Book book : booksList) {
            book.display();
        }
    }

    // 2nd Method
    private List<Book> findBooks(String searchString) {
        ArrayList<Book> foundBook = new ArrayList<>();

        String[] conditions = searchString.split(" & ");


        for (Book book : this.booksList) {

            boolean satisfyAllConditions = true ; //Assume first it's true until found false

            for (String s : conditions){

                s =s.substring(1, s.length()-1); //Remove surrounding stars **

                //Search in all fields
                boolean contains = book.getTitle().contains(s) ||  book.getPublisher().contains(s) ||
                        String.valueOf(book.getPublicationYear()).contains(s);

                for(String author: book.getAuthors()){
                    if(author.contains(s)){
                        foundBook.add(book);

                    }
                }

                if(!contains){
                    satisfyAllConditions=false;
                    break;
                }

            }  if (satisfyAllConditions){
                foundBook.add(book);

            }
        }
        return foundBook;
    }

    //Method for reading the file
    public static String readInput(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }

    // Main method
    public static void main(String[] args) {
        Library library = null;

        try {

            String input = Library.readInput("C:/Users/Ilyes-B/Desktop/DevOpsAssignment/Book.txt");

            library = new Library(input);

            System.out.println("Read " + library.booksList.size() + " books");
            library.displayBooks();

            System.out.println("Search for *20* :");
            ArrayList<Book> foundBook = (ArrayList<Book>) library.findBooks("*20*");
            System.out.println("Found " + foundBook.size() + " books");

            System.out.println("Search for *20* & *Peter* :");
            foundBook = (ArrayList<Book>) library.findBooks("*20* & *Peter*");
            System.out.println("Found " + foundBook.size() + " books");



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}