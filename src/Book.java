public class Book extends Library{

    private String title;
    private String author;
    private String ID;
    private String genre;
    private int publicationYear;
    private boolean available;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Book(String bookTitle){
        this.title = bookTitle;
        this.author = "";
        this.genre = "";
    }

    public Book(String bookTitle, String bookAuthor) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = "";
    }

    public Book(String bookTitle, String bookAuthor, String bookGenre) {
        this.title = bookTitle;
        this.author = bookAuthor;
        this.genre = bookGenre;
    }
}
