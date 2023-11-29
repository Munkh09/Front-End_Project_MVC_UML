package LibraryAppModel;

import java.util.Calendar;
import java.util.Date;

public class Book implements Borrowable{

    public enum BookStatus{
        AVAILABLE, CHECKED_OUT, LOST, DAMAGED
    }

    private BookStatus status;
    private String title;
    private String author;
    private int ISBN;
    private String searchBook = null;
    private boolean isCheckedOut;
    private Date dueDate;

    public Book(int ISBN){
        this.ISBN = ISBN;
    }

    public Book(BookStatus status, String title, String author, int ISBN, boolean isCheckedOut, Date dueDate) {
        this.status = status;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isCheckedOut = isCheckedOut;
        this.dueDate = dueDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getSearchBook() {
        return searchBook;
    }

    public void setSearchBook(String searchBook){
        this.searchBook = searchBook;
    }

    public void checkIn(){
        status = BookStatus.AVAILABLE;
        this.dueDate = null;
        isCheckedOut = false;
    }

    public void checkOut(){ //I set the return date as 14 days from the time it was checked out
        Date orderDate = new Date();
        Calendar tempCalendar = Calendar.getInstance();

        tempCalendar.setTime(orderDate);
        tempCalendar.add(Calendar.DAY_OF_YEAR, 14);

        status = BookStatus.CHECKED_OUT;
        this.dueDate = tempCalendar.getTime();
        isCheckedOut = true;
    }

}
