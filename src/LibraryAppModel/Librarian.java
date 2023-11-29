package LibraryAppModel;

import java.util.Date;

public class Librarian extends User{
    private Date employmentDate;

    public Librarian(int userID, String name, String email, String password, Date dateJoined, Date employmentDate, String securityQuestion, String securityQuestionAnswer) {
        super(userID, name, email, password, dateJoined, securityQuestion, securityQuestionAnswer);
        this.employmentDate = employmentDate;
    }

    public void addBook(Book book){
        this.getData().dbAddBook(book); // Using the getter method to access data
    }


    public void removeBook(Book book){
        this.getData().dbRemoveBook(book); //need this implemented -> Munkh
    }

    public void removeUser(User user){
        this.getData().dbRemoveUser(user); //need this implemented -> Munkh
    }


    public String getType(){
        return "Librarian";
    }
}
