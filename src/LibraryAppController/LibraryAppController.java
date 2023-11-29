package LibraryAppController;

import LibraryAppModel.Patron;
import LibraryAppModel.User;
import LibraryAppView.EmailException;
import LibraryAppView.LibraryView;
import LibraryAppView.PasswordException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LibraryAppController {
    private LibraryAppModel.Tables model;
    private LibraryAppView.LibraryView view;

    public LibraryAppController(LibraryAppModel.Tables model, LibraryAppView.LibraryView view){
        this.model = model;
        this.view = view;
        this.view.addSigninButtonListener(new SigninListener());
        this.view.addSignupButtonListener(new SignupListener());
        this.view.addShowUsersButtonListener(new ShowUsersButtonListener());
        this.view.addPassVisibilityCheckListener(new passwordVisibilityCheckListener());
        this.view.addForgotPasswordListener(new forgotPasswordButtonListener());
        this.view.addMenuItemListener(new MenuListener());
        this.view.addLogoutButtonListener(new LogoutButtonListener());
        this.view.addCreateAccountButtonListener(new CreateAccountButtonListener());
        this.view.addCheckOutButton(new CheckOutButtonListener());
        this.view.addLogoutFromTableButtonListener(new LogoutFromTableButtonListener());
        this.view.addBookButtonListener(new AddBookButtonListener());
        this.view.addBookButtonForLibrarianListener(new AddBookButtonForLibrarianListener());

        // Populate the table with book data from the database
        Map<Integer, LibraryAppModel.Book> myMap = model.getBooks();
        LibraryAppModel.Book book;
        for (Map.Entry<Integer, LibraryAppModel.Book> entry : myMap.entrySet()) { //Populating table with data
            book = entry.getValue();
            view.getTableModel().addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())});
        }
    }

    class SigninListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                    String out = null; // Variable to hold the output message
                    boolean loggedIn = false; // Flag to track if the user successfully logged in

                    try {
                        // Attempt to retrieve the user from the database using the provided username
                        if (!view.getUsernameInput().getText().isEmpty()) {
                            if (model.getUser(Integer.parseInt(view.getUsernameInput().getText())) != null) {
                                LibraryAppModel.User user = model.getUser(Integer.parseInt(view.getUsernameInput().getText()));
                                // If the user is found, compare the provided password with the one in the database
                                if (user.getPassword().equals(String.valueOf(view.getPasswordInput().getPassword()))) {
                                    // If password matches, set up and display the user's logged-in page
                                    String page = "loggedInPage" + user.getUserID();
                                    view.getCard().show(view.getCardContainer(), page);
                                    view.setCurrentUserID(Integer.parseInt(view.getUsernameInput().getText()));

                                    // Clear the text fields and reset the output message
                                    view.getUsernameInput().setText("");
                                    view.getPasswordInput().setText("");
                                    view.getErrorText().setText("");
                                    loggedIn = true; // Set the flag as the user is successfully logged in
                                }
                            }else{
                                view.getErrorText().setText("Incorrect username/password...");
                            }
                        } else{
                            view.getErrorText().setText("Empty input...");
                        }
                        // Handle the case where the username input is not a number
                    } catch (NumberFormatException ex) {
                        out = "Input is non-numerical...";
                        view.getErrorText().setText(out);
                    }
        }
    }

    class SignupListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //eventListeners for sign up
                    view.getCard().show(view.getCardContainer(), "signUpPage");
                    view.getUsernameCard().show(view.getUserNameScreen(), "signingUp"); //Display different screen
                    view.getUsernameInput().setText(""); //clear text
                    view.getPasswordInput().setText(""); //clear text
                    view.getErrorText().setText(""); //clear text
        }
    }

    class ShowUsersButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                JFrame internalFrame2 = new JFrame("Users");
                internalFrame2.setLayout(new BorderLayout());
                DefaultTableModel internalTableModel = new DefaultTableModel();
                TableRowSorter rowSorter = new TableRowSorter<DefaultTableModel>(internalTableModel); //Used to create new filters for the table(Search filter)
                JTable table = new JTable(internalTableModel);
                table.setRowSorter(rowSorter);
                table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                // Add columns to the table model
                internalTableModel.addColumn("Name");
                internalTableModel.addColumn("Username");
                internalTableModel.addColumn("Password");
                internalTableModel.addColumn("User Type");

                internalTableModel.addRow(new Object[]{"Name", "Username", "Password", "User Type"});
                // Populate the table with book data from the database
                Map<Integer, User> myMap = model.getUsers();
                LibraryAppModel.User user;
                for (Map.Entry<Integer, LibraryAppModel.User> entry : myMap.entrySet()) { //Populating table with data
                    user = entry.getValue();
                    internalTableModel.addRow(new Object[]{user.getName(), user.getUserID(), user.getPassword(), user.getType()});
                }

                // Set up a scroll pane for the table
                JScrollPane scrollPane = new JScrollPane(table);
                table.setFillsViewportHeight(true);

                // Create a panel to hold the table and its associated components
                JPanel panel = new JPanel(new GridBagLayout());

                // Add the table (inside its scroll pane) to the panel
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 1;
                constraints.gridy = 1;
                panel.add(scrollPane, constraints);

                TableColumnModel columnModel = table.getColumnModel();
                columnModel.getColumn(0).setMinWidth(90);
                columnModel.getColumn(1).setMinWidth(90);
                columnModel.getColumn(2).setMinWidth(90);
                columnModel.getColumn(3).setMinWidth(90);

                JPanel panel1 = new JPanel();
                panel1.setBackground(Color.DARK_GRAY);
                JPanel panel2 = new JPanel();
                panel2.setBackground(Color.DARK_GRAY);
                JPanel panel3 = new JPanel();
                panel3.setBackground(Color.ORANGE);
                JPanel panel4 = new JPanel();
                panel4.setBackground(Color.ORANGE);
                panel.add(table);
                internalFrame2.setSize(new Dimension(400,400));
                internalFrame2.add(panel, BorderLayout.CENTER);
                internalFrame2.add(panel3, BorderLayout.WEST);
                internalFrame2.add(panel4, BorderLayout.EAST);
                internalFrame2.add(panel1, BorderLayout.NORTH);
                internalFrame2.add(panel2, BorderLayout.SOUTH);
                table.setBorder(BorderFactory.createLineBorder(Color.black));
                internalFrame2.setVisible(true);
                internalFrame2.setResizable(true);
                internalFrame2.setLocationRelativeTo(view);
                internalFrame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        }

    class passwordVisibilityCheckListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                if(view.getPasswordVisibilityCheck().isSelected()){
                    view.getPasswordInput().setEchoChar((char)0);
                }else{
                    view.getPasswordInput().setEchoChar('*');
                }
            }
        }

     class forgotPasswordButtonListener implements ActionListener{
        @Override
         public void actionPerformed(ActionEvent e){
                    JFrame internalFrame = new JFrame("Forgot Password");
                    internalFrame.setSize(new Dimension(400,200));
                    internalFrame.setLayout(new GridBagLayout());
                    JLabel passwordShowArea = new JLabel();
                    JButton retrievePasswordButton = new JButton("Retrieve Password");
                    retrievePasswordButton.setPreferredSize(new Dimension(40,20));
                    JLabel userNameLabel = new JLabel("Username:");
                    userNameLabel.setPreferredSize(new Dimension(40,20));
                    JTextField userNameInput = new JTextField();
                    userNameLabel.setPreferredSize(new Dimension(40,20));
                    JTextField securityQuestionAnswerInput = new JTextField();
                    JLabel errorText = new JLabel();
                    JLabel securityQuestionLabel = new JLabel();
                    securityQuestionAnswerInput.setPreferredSize(new Dimension(40,20));
                    securityQuestionLabel.setVisible(false);
                    securityQuestionAnswerInput.setVisible(false);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.weighty = 1;
                    gbc.weightx = 1;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    internalFrame.add(userNameLabel, gbc);
                    gbc.gridy = 1;
                    internalFrame.add(securityQuestionLabel, gbc);
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    internalFrame.add(userNameInput, gbc);
                    gbc.gridy = 1;
                    internalFrame.add(securityQuestionAnswerInput, gbc);
                    gbc.gridy = 2;
                    internalFrame.add(retrievePasswordButton, gbc);
                    gbc.gridx = 0;
                    internalFrame.add(errorText, gbc);
                    gbc.gridy = 3;
                    internalFrame.add(passwordShowArea, gbc);
                    internalFrame.setVisible(true);
                    internalFrame.setLocationRelativeTo(view);
                    internalFrame.setResizable(true);
                    internalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    Color ORANGE = new Color(255, 165, 0);
                    internalFrame.getContentPane().setBackground(ORANGE);
                    retrievePasswordButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!userNameInput.getText().isEmpty()){
                                if(model.getUser(Integer.parseInt(userNameInput.getText())) != null){
                                    LibraryAppModel.User user = model.getUser(Integer.parseInt(userNameInput.getText()));
                                    securityQuestionLabel.setText(user.getSecurityQuestion());
                                    securityQuestionLabel.setVisible(true);
                                    securityQuestionAnswerInput.setVisible(true);
                                    if(!securityQuestionAnswerInput.getText().isEmpty()){
                                        if(user.getSecurityQuestionAnswer().equals(securityQuestionAnswerInput.getText())){
                                            passwordShowArea.setText("Your password is " + user.getPassword());
                                            errorText.setText("");
                                        }else{
                                            errorText.setText("Incorrect answer");
                                        }
                                    }else{
                                        errorText.setText("Please answer the security question");
                                    }
                                }else {
                                    errorText.setText("Username doesn't exist");
                                }
                            } else{
                                errorText.setText("Please enter username");
                            }
                        }
                    });
                }
    }

    class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getMyMenuBar().setVisible(false);
            view.getQuestion().setVisible(true);
            view.getQuestion().setText(e.getActionCommand());
            view.setSecurityQuestionSelected(e.getActionCommand());
        }
    }
    class LogoutButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getCard().show(view.getCardContainer(), "signin");
            view.getMyMenuBar().setVisible(true);
            view.getQuestion().setText("");
            view.getQuestion().setVisible(false);
        }
    }
    class CreateAccountButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                    try {
                        // Hide the sign-up header upon successful account creation
                        view.getSignUpHeader().setVisible(false);
                        String pageName;
                        if(!view.getNewFirstName().getText().isEmpty()) {
                            if(!view.getNewLastName().getText().isEmpty()) {
                                if(!view.getNewEmail().getText().isEmpty()) {
                                    view.checkEmailRequirements(view.getNewEmail().getText());
                                    view.getEmailError().setText("");
                                    if(!view.getNewPassword().getText().isEmpty()) {
                                        view.checkPasswordRequirements(view.getNewPassword().getText());
                                        view.getEmailError().setText("");
                                        if(!view.getSecurityQuestionInput().getText().isEmpty()) {
                                            view.getEmailError().setText("");
                                            // Determine the type of account to create based on the librarian checkbox
                                            if (view.getLibrarianCheck().isSelected()) { //If Librarian, create librarian object. Else make Patron object
                                                Date date = new Date();
                                                LibraryAppModel.Librarian newUser = new LibraryAppModel.Librarian(createUsername(),
                                                        view.getNewFirstName().getText() + " " + view.getNewLastName().getText(), view.getNewEmail().getText(), view.getNewPassword().getText(), date, date, view.getSecurityQuestionSelected(), view.getSecurityQuestionInput().getText());

                                                // Set up account details and add the new user to the database
                                                view.setCurrentUserID(newUser.getUserID());
                                                pageName = "loggedInPage" + newUser.getUserID();
                                                view.getUsername().setText("Generated UserID: " + newUser.getUserID());
                                                model.dbAddUser(newUser);
                                            } else {  // If not selected, create a Patron account
                                                Date date = new Date();
                                                LibraryAppModel.Patron newUser = new LibraryAppModel.Patron(createUsername(),
                                                        view.getNewFirstName().getText() + " " + view.getNewLastName().getText(), view.getNewEmail().getText(), view.getNewPassword().getText(), date, view.getSecurityQuestionSelected(), view.getSecurityQuestionInput().getText());

                                                // Set up account details and add the new user to the database
                                                view.setCurrentUserID(newUser.getUserID());
                                                pageName = "loggedInPage" + newUser.getUserID();
                                                view.getUsername().setText("Generated UserID: " + newUser.getUserID());
                                                model.dbAddUser(newUser);
                                            }


                                            // Clear form fields after account creation
                                            view.getNewEmail().setText("");
                                            view.getNewFirstName().setText("");
                                            view.getNewLastName().setText("");
                                            view.getNewPassword().setText("");
                                            view.getSecurityQuestionInput().setText("");
                                            view.getLibrarianCheck().setSelected(false);

                                            // Switch to a screen showing the created username
                                            view.getUsernameCard().show(view.getUserNameScreen(), "loginUsername");

                                            // Create a personalized screen for the logged-in user
                                            JPanel logginPanel = view.createLoggedInScreen(); //Generating screen with user info
                                            view.getDisplayUsername().setText("User: " + model.getUser(view.getCurrentUserID()).getUserID());
                                            view.getDisplayFirstName().setText("Name: " + model.getUser(view.getCurrentUserID()).getName());
                                            view.getDisplayJoinDate().setText("Join Date: " + model.getUser(view.getCurrentUserID()).getDateJoined());
                                            view.getDisplayEmail().setText("Email: " + model.getUser(view.getCurrentUserID()).getEmail());
                                            view.getDisplaySecurityQuestion().setText("Security Question: " + model.getUser(view.getCurrentUserID()).getSecurityQuestion());
                                            view.getDisplaySecurityQuestionAnswer().setText("Security Question Answer: " + model.getUser(view.getCurrentUserID()).getSecurityQuestionAnswer());

                                            view.getCardContainer().add(logginPanel, pageName); //Adding screen with user info
                                            view.addNextButtonListener(new NextButtonListener());
                                            // Clear any error messages
                                            view.getPasswordError().setText("");
                                            view.getEmailError().setText("");
                                        }else{
                                            view.getEmailError().setText("Security Question Answer is empty...");
                                        }
                                    }else{
                                        view.getEmailError().setText("Password is empty...");
                                    }
                                }else{
                                    view.getEmailError().setText("Email is empty...");
                                }
                            }else{
                                view.getEmailError().setText("Last Name is empty...");
                            }
                        }else{
                            view.getEmailError().setText("First Name is empty...");
                        }
                        // Handle specific password validation errors
                    } catch (LibraryAppView.UpperCaseCharacterMissing error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.LowerCaseCharacterMissing error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.Minimum8CharactersRequired error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.SpecialCharacterMissing error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.NumberCharacterMissing error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.PasswordException error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.AtSignMissingException error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.PeriodMissingException error) {
                        view.getEmailError().setText(error.getMessage());
                    } catch (LibraryAppView.EmailException error) {
                        view.getEmailError().setText(error.getMessage());
                    }
        }
    }

    class NextButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                    String userType = model.getUser(view.getCurrentUserID()).getClass().getName();

                    // Logic to determine the next screen based on user type
                    if (userType.equals("LibraryAppModel.Librarian")) { //Setting different access levels for librarian/patron
                        view.getAddBookButton().setVisible(true);
                        view.getCard().show(view.getCardContainer(), "searchScreen");
                    } else {
                        view.getAddBookButton().setVisible(false); // Hide certain options for patrons
                        view.getCardContainer().add(view.createReturnBookScreen(), "returnScreen" + view.getCurrentUserID() + view.getRunner()); //Creating new screen with return information
                        Patron patron = (Patron) model.getUser(view.getCurrentUserID());

                        // Retrieve lists of overdue and returned books
                        List<String> overdueBooks = patron.getOverdueBooks(model.getBooks());
                        List<String> returnedBooks = patron.returnBook(model.getBooks()); //Can create too many overDue books exception here

                        // Building strings to display returned books
                        StringBuilder returned = new StringBuilder();
                        for (int i = 0; i < returnedBooks.size(); i++) {
                            returned.append(returnedBooks.get(i));

                            if ((i + 1) < returnedBooks.size()) {
                                returned.append(", ");
                            }
                        }

                        // Building strings to display overdue books
                        StringBuilder overdue = new StringBuilder();
                        if (!overdueBooks.isEmpty()) {
                            for (int i = 0; i < overdueBooks.size(); i++) {
                                overdue.append(overdueBooks.get(i));

                                if ((i + 1) < overdueBooks.size()) {
                                    overdue.append(", ");
                                }
                            }
                        }
                        view.addNextFromReturnBookScreenButtonListener(new NextFromReturnBookScreenButtonListener());
                        view.setReturned(returned);
                        view.setOverdue(overdue);
                        view.getCard().show(view.getCardContainer(), "returnScreen" + view.getCurrentUserID() + view.getRunner()); //runner used to help with the different screen IDs
                        view.setRunner(view.getRunner()+1);
                    }

                }

    }
    class CheckOutButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                    // Get the indices of the selected rows in the table
                    int[] rows = view.getTable().getSelectedRows();

                    // StringBuilder objects to accumulate messages for checked out and unavailable books
                    StringBuilder checkedOut = new StringBuilder();
                    checkedOut.append("Checked out: ");
                    StringBuilder unavailiableBook = new StringBuilder();
                    unavailiableBook.append("Unavailiable: ");

                    // Retrieve the map of books from the database
                    Map<Integer, LibraryAppModel.Book> map = model.getBooks();

                    if (rows.length <= 5) { //Requirement that only a max of 5 books can be checked out. Exception can be created/handled here
                        for (int i = 0; i < rows.length; i++) {
                            // Check if the book is already checked out
                            if (!model.isCheckedOut(Integer.parseInt((String) view.getTableModel().getValueAt(view.getTable().convertRowIndexToModel(rows[i]), 2)))) {
                                // Append the title of the checked-out book to the StringBuilder
                                checkedOut.append(view.getTableModel().getValueAt(view.getTable().convertRowIndexToModel(rows[i]), 0));

                                if ((i + 1) != rows.length) {
                                    checkedOut.append(", ");
                                }

                                // Get the patron object and borrow the book
                                Patron patron = (Patron) model.getUser(view.getCurrentUserID());
                                patron.borrowBook(map, Integer.parseInt((String) view.getTableModel().getValueAt(view.getTable().convertRowIndexToModel(rows[i]), 2)));

                                // Update the book's status in the database as checked out
                                LibraryAppModel.Book book = new LibraryAppModel.Book(Integer.parseInt((String) view.getTableModel().getValueAt(view.getTable().convertRowIndexToModel(rows[i]), 2)));
                                model.checkOutBook(book);
                            } else {
                                // Append the title of the unavailable book to the StringBuilder
                                unavailiableBook.append(view.getTableModel().getValueAt(view.getTable().convertRowIndexToModel(rows[i]), 0));
                                if ((i + 1) != rows.length) {
                                    unavailiableBook.append(", ");
                                }
                            }

                        }
                    } else {
                        // Set error message if more than 5 books are selected for checkout
                        checkedOut.append("Error --- Book Limit: 5");
                        unavailiableBook.append("");
                    }

                    // Update the labels with the messages for checked out and unavailable books
                    view.getText().setText(checkedOut.toString());
                    view.getText2().setText(unavailiableBook.toString());

                }
            }

    class LogoutFromTableButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                    // On clicking 'Logout', switch back to the sign-in card
                    view.getCard().show(view.getCardContainer(), "signin");
                    // Clear any text and selections from the search screen
                    view.getText().setText("");
                    view.getText2().setText("");
                    view.getTable().clearSelection();
                }
            }

    class AddBookButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
                    // On clicking 'Add Book', switch to the 'addBook' card in the CardLayout
                    view.getCard().show(view.getCardContainer(), "addBook");
                    // Clear any text and selections from the search screen
                    view.getText().setText("");
                    view.getText2().setText("");
                    view.getTable().clearSelection();
                }
            }

    class AddBookButtonForLibrarianListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Action listener for the add book button
            try { // Attempt to parse the ISBN and create a new book object
                int isbnNumber = Integer.parseInt(view.getIsbn().getText()); // Try to parse the ISBN
                LibraryAppModel.Book book = new LibraryAppModel.Book(LibraryAppModel.Book.BookStatus.AVAILABLE, view.getTitle2().getText(), view.getAuthor().getText(), isbnNumber, false, null);

                // Add the book to the database and update the table
                model.dbAddBook(book);
                view.getTableModel().addRow(new Object[]{book.getTitle(), book.getAuthor(), Integer.toString(book.getISBN())}); //Add new data to table

                // Switch back to the search screen
                view.getCard().show(view.getCardContainer(), "searchScreen");

                // Clear the text fields
                view.setTitle2("");
                view.setAuthor("");
                view.setIsbn("");
            } catch (NumberFormatException ex) {
                // Display error message
                JOptionPane.showMessageDialog(view, "Invalid ISBN", "Error", JOptionPane.ERROR_MESSAGE);
                // Optionally, you could also clear the isbn field or keep the erroneous input for correction
                view.setIsbn("");
            }
        }
    }

    class NextFromReturnBookScreenButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getCard().show(view.getCardContainer(), "searchScreen");
        }
    }





    private int createUsername() { //return random user ID
        Random random = new Random();
        StringBuilder username = new StringBuilder();
        username.append(String.format("%06d", random.nextInt(999999))); //generates 6 digit user ID
        return Integer.parseInt(username.toString());
    }


}


