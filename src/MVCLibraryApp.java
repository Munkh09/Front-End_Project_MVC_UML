import LibraryAppModel.Book;
import LibraryAppModel.Librarian;
import LibraryAppModel.Patron;
import LibraryAppModel.User;
import LibraryAppView.LibraryView;

import java.util.Date;

public class MVCLibraryApp {
    public static void main(String[] args) {
        LibraryView theView = new LibraryView();
        LibraryAppModel.Tables theModel = new LibraryAppModel.Tables();

        //Adding Books in the Database
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Book Thief", "Markus Zusak", 9283, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Catcher in the Rye", "J.D.Salinger", 9284, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Great Gatsby", "F.Scott Fitzgerald", 9285, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Of Mice and Men", "John Steinbeck", 9286, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Dracula", "Bram Stoker", 9287, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Frankenstein", "Mary Shelley", 9288, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Persuasion", "Jane Austen", 9282, false, null));


        LibraryAppController.LibraryAppController theController = new LibraryAppController.LibraryAppController(theModel, theView);
        theView.setVisible(true);

    }

}
