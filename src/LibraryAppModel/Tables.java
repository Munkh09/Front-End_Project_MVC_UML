package LibraryAppModel;

import java.util.HashMap;
import java.util.Map;

public class Tables {
    private Map<Integer, Book> books; // Using ISBN as the key for the backend.Book map
    private Map<Integer, User> users; // Using userID as the key for the backend.User map

    public Tables() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public String getUserType(){ //return what type of User it is
        return String.valueOf(users.getClass());
    }

    public User getUser(int userID){
        return users.get(userID);
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    // Method to add a backend.Book
    public void dbAddBook(Book book) {
        books.put(book.getISBN(), book);
    }

    // Method to remove a backend.Book by ISBN
    public void dbRemoveBook(Book book) {
        books.remove(book.getISBN());
    }

    // Method to add a backend.User
    public void dbAddUser(User user) {
        users.put(user.getUserID(), user);
    }

    // Method to remove a backend.User by userID
    public void dbRemoveUser(User user) {
        users.remove(user.getUserID());
    }

    // Search for a backend.Book by Title
    public boolean searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equals(title)) {
                return true; // backend.Book found
            }
        }
        return false; // backend.Book not found
    }

    // Search for a backend.Book by Author
    public boolean searchBookByAuthor(String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                return true; // backend.Book found
            }
        }
        return false; // backend.Book not found
    }

    // Search for a backend.Book by ISBN
    public boolean searchBookByISBN(int ISBN) {
        return books.containsKey(ISBN); // Returns true if the ISBN exists in the map, false otherwise
    }

    public boolean isCheckedOut(int ISBN){
        return books.get(ISBN).isCheckedOut();
    }


    // Method to check out a book by ISBN
    public boolean checkOutBook(Book ISBN) {
        Book book = books.get(ISBN.getISBN());
        if (book != null && book.getStatus() == Book.BookStatus.AVAILABLE) {
            book.checkOut();
            return true;
        }
        else return false;
    }

    // Method to check in a book by ISBN
    public boolean checkInBook(Book ISBN) {
        Book book = books.get(ISBN.getISBN());
        if (book != null && book.getStatus() == Book.BookStatus.CHECKED_OUT) {
            book.checkIn();
            return true;
        }
        else return false;
    }
}
