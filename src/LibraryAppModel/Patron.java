package LibraryAppModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Patron extends User{
    private List<Integer> borrowedBooks;
    private static final int maxBookLimit = 5;
    private List<String> overdueBooks;

    public Patron(int userID, String name, String email, String password, Date dateJoined, String securityQuestion, String securityQuestionAnswer) {
        super(userID, name, email, password, dateJoined, securityQuestion, securityQuestionAnswer);
        borrowedBooks = new ArrayList<Integer>();
        overdueBooks = new ArrayList<String>();
    }

    public void borrowBook(Map<Integer, Book> books, int isbn){
        //getData().checkOutBook(book); //Use the backend.Book.CheckOut() method for this in Table
        //for(int i = 0; i < isbn.size(); i++){
        books.get(isbn).checkOut();
        borrowedBooks.add(isbn);
        //}
    }

    public List<String> returnBook(Map<Integer, Book> books){
        //getData().checkInBook(book); //Use the backend.Book.CheckIn() method for this in Table
        List<String> returnedBooks = new ArrayList<String>();

        for(int i = 0; i < borrowedBooks.size(); i++){
            returnedBooks.add(books.get(borrowedBooks.get(i)).getTitle());
            books.get(borrowedBooks.get(i)).checkIn();
        }

        borrowedBooks.clear();

        return returnedBooks;
    }

    public List<String> getOverdueBooks(Map<Integer, Book> books){

        Date currentDate = new Date();
        overdueBooks.clear();

        for(int i = 0; i < borrowedBooks.size(); i++){
            if(currentDate.compareTo(books.get(borrowedBooks.get(i)).getDueDate()) > 0){
                overdueBooks.add(books.get(borrowedBooks.get(i)).getTitle());
            }
        }

        return overdueBooks;
    }

    public boolean hasOverdueBooks(){

        if(overdueBooks.isEmpty()){
            return false;
        }

        return true;
    }

    public String getType(){
        return "Patron";
    }

}
