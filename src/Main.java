import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        Library library = new Library();

        while(true){
            System.out.println("Select an option");
            System.out.println("Type \"A\" to add book"); //implemented
            System.out.println("Type \"D\" to delete book"); //implemented
            System.out.println("Type \"R\" to read content of book"); //implemented
            System.out.println("Type \"E\" to edit book contents"); //implemented
            System.out.println("Type \"L\" to list books"); //implemented
            System.out.println("Type \"O\" to add books to all locations"); //implemented
            System.out.println("Type \"Q\" to quit application"); //implemented

            String option = scnr.next();

            scnr.nextLine();

            switch (option.toUpperCase()){
                case "A":
                    library.setUpAddBookMethod();
                    break;
                case "D":
                    library.setUpDeleteBooksMethod();
                    break;
                case "R":
                    library.readContents();
                    break;
                case "E":
                    library.accessFile();
                    System.out.println("You have edited the book! \n");
                    break;
                case "L":
                    library.listBooks();

                    System.out.println();

                    break;
                case "O" :
                    System.out.println("Give a name for all of the files");
                    String name = scnr.nextLine();
                    library.addAllFile(name);
                    break;
                case "Q":
                    scnr.close();
                    System.out.println("You have quit the application!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option: Please try again.");
                    System.out.println();
            }
        }
    }
}
/*
Library

Inputs:
Add book
    -Ask for book name
    -Maybe ask if they want to write content to that book. For now, just ask for book name and make a new text file with nothing in it.
Delete book
    -Ask for book name to delete.
List books
    -Display book name
    -and ID later

Classes:
    -Library
        Methods:
            addBook(String bookName)
                 -Take bookName and then make a new text file with that name.

            deleteBook(String bookName)
                -Take bookName and then delete it
                -If bookName not found, then return exception

            listBooks()
                 -Display book names currently in file
    -Book
        Methods:
        default constructor

 */