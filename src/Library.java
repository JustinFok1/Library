import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Library {

    Scanner scnr = new Scanner(System.in);
    public Library() {
    }

    public void setUpAddBookMethod() {
        System.out.println("What is the book title: ");
        String bookTitle = scnr.nextLine();

        if (fileAlreadyExist(bookTitle)){
            return; // stops processing if it returns true
        }

        String bookAuthor = "";
        System.out.println("Do you know the author? (Yes/No)");
        String knowAuthor = scnr.next();
        scnr.nextLine();

        if (knowAuthor.equalsIgnoreCase("Yes")) {
            System.out.println("What is the author's name?");
            bookAuthor = scnr.nextLine();
        } else if (knowAuthor.equalsIgnoreCase("No")) {
            bookAuthor = "";
        }

        String bookGenre = "";
        System.out.println("Do you know the book's genre? (Yes/No)");
        String knowGenre = scnr.next();
        scnr.nextLine();

        if (knowGenre.equalsIgnoreCase("Yes")) {
            System.out.println("What is the book's genre? \nGenres: \nAction \nRomance");
            bookGenre = scnr.next();
        } else if (knowGenre.equalsIgnoreCase("No")) {
            bookGenre = "";
        }

        Book newBook = new Book(bookTitle, bookAuthor, bookGenre);
        addBooks(newBook);

        System.out.println("You have added a book! \n");
    }

    public void setUpDeleteBooksMethod(){
        System.out.println("Do you want to delete one file or all files? (one/all)");
        String optionOne = scnr.next();

        scnr.nextLine(); // Consume the newline character
        switch (optionOne.toUpperCase()){
            case "ONE":
                System.out.println("What is the book title to delete: ");
                String bookTitleToDelete = scnr.nextLine();
                deleteBooks(bookTitleToDelete);
                break;
            case "ALL":
                deleteAllBooks();
        }
    }

    private void addBooks(Book book) {
        String getFileTitle = generateFileName(book.getTitle()); // initialize variable and converts the name to acceptable file name input
        File generalFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\General\\", getFileTitle);
        File actionFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\Action\\", getFileTitle);
        File romanceFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\Romance\\", getFileTitle);

        File fileLocation;

        if (book.getGenre().equalsIgnoreCase("Action")) {
            fileLocation = actionFileLocation;
        } else if (book.getGenre().equalsIgnoreCase("Romance")) {
            fileLocation = romanceFileLocation;
        } else {
            fileLocation = generalFileLocation;
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileLocation))) {
            if (book.getAuthor() != "") {
                writer.write("Author: " + book.getAuthor());
                writer.println();
            }
            writer.println("ID: ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBooks(String bookName) {
        String getFileTitle = generateFileName(bookName);
        File generalFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\General\\" + getFileTitle);
        File actionFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\Action\\" + getFileTitle);
        File romanceFileLocation = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\Romance\\" + getFileTitle);

        File fileLocation;

        boolean titleTheSame = generalFileLocation.exists() && actionFileLocation.exists() && romanceFileLocation.exists() ||
                generalFileLocation.exists() && actionFileLocation.exists() ||
                generalFileLocation.exists() && romanceFileLocation.exists() ||
                actionFileLocation.exists() && romanceFileLocation.exists();

        if (titleTheSame){
            System.out.println("Multiple files with the same name found, please enter a category: \nGeneral\nAction\nRomance");
            String option = scnr.next();

            switch (option.toUpperCase()) {
                case "GENERAL" :
                    deleteFile(generalFileLocation);
                    break;
                case "ACTION" :
                    deleteFile(actionFileLocation);
                    break;
                case "ROMANCE" :
                    deleteFile(romanceFileLocation);
                    break;
                default :
                    System.out.println("Invalid category choice");
            }
        } else if (generalFileLocation.exists()) {
                fileLocation = generalFileLocation;
                deleteFile(fileLocation);
        } else if (actionFileLocation.exists()) {
                fileLocation = actionFileLocation;
                deleteFile(fileLocation);
        } else if (romanceFileLocation.exists()) {
                fileLocation = romanceFileLocation;
                deleteFile(fileLocation);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteFile(File fileLocation){
        try {
            if (fileLocation.delete()) {
                System.out.println("Book successfully removed");
            } else {
                System.out.println("Failed to delete the file");
            }
        } catch (SecurityException e) {
            System.err.println("SecurityException: " + e.getMessage());
        }
    }

    public void deleteAllBooks() {
        String[] genre = {"General", "Action", "Romance"};
        File generalFilePath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");
        boolean filesDeleted = false;


        for (String specificGenre : genre) {
            File specificPath = new File(generalFilePath, specificGenre);
            File[] moreSpecificPathsInArray = specificPath.listFiles();

            if (moreSpecificPathsInArray != null) {
                for (File file : moreSpecificPathsInArray) {
                    file.delete();
                    filesDeleted = true;
                }
            } else {
                System.out.println("Error: No file detected");
            }
        }
        if (filesDeleted) {
            System.out.println("All files deleted");
        }
    }

    public void listBooks() {
        String[] genres = {"General", "Romance", "Action"};
        File generalPath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");

        for (String genre : genres){
            File[] specificPath = new File(generalPath, genre).listFiles();
            if (genre.equalsIgnoreCase("Action")){
                System.out.println("Action: ");
                for(File file : specificPath){
                    if (file != null){
                        System.out.println(file.getName());
                    }
                    else{
                        System.out.println("Error: No files found.");
                    }
                }
                System.out.println();
            } else if (genre.equalsIgnoreCase("Romance"))
            {
                System.out.println("Romance: ");
                for (File file : specificPath) {
                    if (file != null) {
                        System.out.println(file.getName());
                    } else {
                        System.out.println("Error: No files found.");
                    }
                }
                System.out.println();
            }
                else if (genre.equalsIgnoreCase("General")){
                System.out.println("General: ");
                for(File file : specificPath){
                        if (file != null){
                            System.out.println(file.getName());
                        }
                        else{
                            System.out.println("Error: No files found.");
                        }
                    }
                System.out.println();
                }
            }
        }


    public void addAllFile(String name) {
        String[] genre = {"General", "Action", "Romance"};
        File generalFilePath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");

        for (String specificGenre : genre) {
            File specificPath = new File(generalFilePath, specificGenre);
            File newFile = new File(specificPath, name + ".txt");

            try (PrintWriter writer = new PrintWriter(new FileWriter(newFile))) {
                writer.write("New File");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean fileAlreadyExist(String bookTitle) {
        String[] genre = {"General", "Action", "Romance"};
        File filePath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");

        for (String specificGenre : genre) {
            File specificPath = new File(filePath, specificGenre);
            File bookFile = new File(specificPath, generateFileName(bookTitle));

            if (bookFile.exists()) {
                System.out.println("Error: This book already exists in " + specificGenre + ", please try again.");
                return true;
            }
        }
        return false;
    }



    public void readContents(){
        System.out.println("What book do you want to read?");
        String bookTitle = scnr.next();

        System.out.println("What genre is this book?");
        String pickedGenre = scnr.next();

        File filePath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");

        File specificPath = new File(filePath, pickedGenre);
        File bookFile = new File(specificPath, generateFileName(bookTitle));

        if(bookFile.exists()){
            try(BufferedReader br = new BufferedReader(new FileReader(bookFile))){
                String line;
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public void accessFile() {
        System.out.println("What book do you want to edit?");
        String bookTitle = scnr.next();

        System.out.println("What genre is this book?");
        String pickedGenre = scnr.next();

        File filePath = new File("C:\\Users\\Justin\\IdeaProjects\\Library\\Collection\\");

        File specificPath = new File(filePath, pickedGenre);
        File bookFile = new File(specificPath, generateFileName(bookTitle));

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            if (bookFile.exists()) {
                try {
                    System.out.println("Opening file: " + bookFile.getAbsolutePath());
                    desktop.open(bookFile);
                } catch (IOException e) {
                    System.out.println("Error opening file:");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File not found: " + bookFile.getAbsolutePath());
            }
        } else {
            System.out.println("Desktop operations not supported on this platform");
        }
    }


    private String generateFileName(String title) { // makes whitespace into underscores so that it generates file names correctly
        return title.replaceAll("\\s", "_") + ".txt";
    }
}

/*
Library
        Methods:
            addBook(String bookName)
                 -Take bookName and then make a new text file with that name.

            deleteBook(String bookName)
                -Take bookName and then delete it
                -If bookName not found, then return exception

            listBooks()
                 -Display book names currently in file

            ID Method:
 */