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

        //Adding Users in the model/database
        theModel.dbAddUser(new Patron(101928, "John Doe", "doe@gmail.com", "Doe1234.", new Date(), "What is your favorite dish?", "Pizza"));
        theModel.dbAddUser(new Librarian(438492, "Andrew Johnson", "johnson@gmail.com", "Johnson1234.", new Date(),new Date(), "What is the city you were born in?", "San Francisco"));
        theModel.dbAddUser(new Patron(289231, "Derek Thomas", "thomas@gmail.com", "Thomas1234.", new Date(), "What is your favorite dish?", "Lasagna"));
        theModel.dbAddUser(new Librarian(303912, "Malcolm Gladwell", "gladwell@gmail.com", "Gladwell1234.", new Date(), new Date(), "What is your favorite sport?", "Swimming"));

        //Adding Books in the model/database
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Hunger Games", "Suzanne Collins", 33414, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "RedReding1", "David Peace", 101321, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Perks of Being a Wallflower", "Stephen Chbosky", 19214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Book Thief", "Markus Zusak", 27214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Of Mice and Men", "John Steinbeck", 63214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Paper Towns", "John Green", 43214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Fault In Our Stars", "John Green", 31214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Kite Runner", "Khaled Hosseini", 83214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "RedRiding3", "David Peace", 902141, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Catcher in the Rye", "J. D. Salinger", 52214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "The Great Gatsby", "F.Scott Fitzgerald", 12214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "RedRiding2", "David Peace", 312122, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Fahrenheit 451", "Ray Bradbury", 53214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Dracula", "Bram Stoker", 93214, false, null));
        theModel.dbAddBook(new Book(Book.BookStatus.AVAILABLE, "Mrs. Dalloway", "Virginia Woolf", 73214, false, null));


        LibraryAppController.LibraryAppController theController = new LibraryAppController.LibraryAppController(theModel, theView);
        theView.setVisible(true);

    }

}
