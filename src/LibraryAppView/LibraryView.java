package LibraryAppView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;

public class LibraryView extends JFrame{
    private JPanel createdSignInPage;
    private JPanel createdSignUpPage;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private CardLayout card;
    private JPanel cardContainer;
    private CardLayout usernameCard;
    private JPanel usernameScreen;
    private JTable table;
    private JTextField filterText;
    private DefaultTableModel internalTableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton addBookButton;
    private JCheckBox passwordVisibilityCheck;
    private JButton signinButton;
    private JButton signupButton;
    private JButton forgotPasswordButton;
    private JButton showUsersButton;
    private JLabel errorText;
    private JButton logoutButton;
    private JMenuBar menuBar;
    private JMenu menu;
    private JTextField author;
    private JButton nextFromReturnBookScreenButton;
    private JTextField title;
    private JTextField isbn;
    private JLabel question;
    private String securityQuestionSelected;
    private JButton createAccountButton;
    private JLabel signUpHeader;
    private JTextField newFirstName;
    private JTextField newLastName;
    private JTextField newEmail;
    private JTextField newPassword;
    private JLabel emailError;
    private JTextField securityQuestionInput;
    private JCheckBox librarianCheck;
    private int currentUserID;
    private JLabel username;
    private JLabel passwordError;
    private JLabel displayUsername;
    private JLabel displayFirstName;
    private JLabel displayJoinDate;
    private JLabel displayEmail;
    private JLabel displaySecurityQuestion;
    private JLabel displaySecurityQuestionAnswer;
    private JButton nextButton;
    private JButton checkOutButton;
    private JButton addBookButtonForLibrarian;
    private JButton logoutFromTableButton;
    private JLabel text;
    private JLabel text2;
    private StringBuilder returned;
    private StringBuilder overdue;
    private int runner = 0;

    public LibraryView() {

        // Initialize cardContainer first
        cardContainer = new JPanel(new CardLayout());

        // Retrieve the CardLayout instance from cardContainer
        card = (CardLayout) (cardContainer.getLayout());
        this.setTitle("Library Application");
        this.setLayout(new BorderLayout());
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add sign-in and sign-up pages first
        createdSignInPage = this.createSignInPage(); //creating sign in page
        createdSignUpPage = this.createSignUpPage(); //creating sign up page

        cardContainer.add(createdSignInPage, "signin");
        cardContainer.add(createdSignUpPage, "signUpPage");
        // Add other pages
        cardContainer.add(this.createSearchScreen(), "searchScreen");
        cardContainer.add(this.addBooksPage(), "addBook");


        this.add(cardContainer);

    }


    public JPanel createSignInPage() {
        // Initialize components for the sign-in page
        JLabel username = new JLabel();
        username.setText("Username:");
        username.setBounds(50, 50, 20, 40);

        JLabel password = new JLabel();
        password.setText("Password:");
        password.setBounds(200, 105, 20, 40);

        errorText = new JLabel();
        errorText.setBounds(100, 100, 100, 100);

        //Check box for password visibility
        passwordVisibilityCheck = new JCheckBox("Set Password Visible");
        passwordVisibilityCheck.setPreferredSize(new Dimension(40, 20));

        // Text fields for user input
        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(40, 20));

        passwordInput = new JPasswordField();
        passwordInput.setEchoChar('*');
        passwordInput.setPreferredSize(new Dimension(40, 20));

        // Setup the panel for the login form
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.cyan);
        loginPanel.setPreferredSize(new Dimension(250, 150));
        loginPanel.setLayout(new GridBagLayout());

        // Buttons for sign-in and sign-up actions
        signinButton = new JButton("Sign In");
        signinButton.setPreferredSize(new Dimension(20,20));
        signupButton = new JButton("Sign Up");
        signupButton.setPreferredSize(new Dimension(20,20));
        forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setPreferredSize(new Dimension(20, 20));

        //Button for showing the list of users
        showUsersButton = new JButton("Show Users");
        showUsersButton.setForeground(Color.ORANGE);
        showUsersButton.setMargin(new Insets(0, 0, 0, 0));

        // Layout constraints for components in the GridBagLayout
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        loginPanel.add(username, c);

        c.gridx = 2;
        loginPanel.add(usernameInput, c);

        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(password, c);

        c.gridx = 2;
        c.gridy = 1;
        loginPanel.add(passwordInput, c);

        c.gridx = 0;
        c.gridy = 2;
        loginPanel.add(signinButton, c);

        c.gridx = 2;
        c.gridy = 2;
        loginPanel.add(signupButton, c);

        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 3;
        loginPanel.add(forgotPasswordButton, c);

        c.gridx = 0;
        c.gridy = 4;
        loginPanel.add(passwordVisibilityCheck, c);

        c.gridx = 0;
        c.gridy = 5;
        loginPanel.add(errorText, c);

        // Setting up borders for aesthetics
        loginPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Additional layout setup for branding and alignment
        c = new GridBagConstraints();
        Color ORANGE = new Color(255, 165, 0);
        JLabel myspaceLogo = new JLabel("Library Management");
        myspaceLogo.setFont(new Font("Serof", Font.BOLD + Font.ITALIC, 40));
        myspaceLogo.setForeground(ORANGE);

        JPanel loginWrapper = new JPanel(); //Wrapper panel to contain the login panel
        loginWrapper.setBackground(Color.WHITE);
        loginWrapper.setLayout(new GridBagLayout());
        loginWrapper.setPreferredSize(new Dimension(100, 100));

        c.gridx = 1;
        c.gridy = 1;
        loginWrapper.add(loginPanel, c);

        c.gridx = 0;
        c.gridy = 2;
        loginWrapper.add(showUsersButton, c);

        c.gridx = 1;
        c.gridy = 0;
        loginWrapper.add(myspaceLogo, c);
        loginWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true), BorderFactory.createLineBorder(Color.BLACK, 2, true)));

        JPanel finalFrontPage = new JPanel(); //A second wrapper panel for the first wrapper panel. Used to implement BorderLayout
        finalFrontPage.setBackground(Color.WHITE);
        finalFrontPage.setLayout(new BorderLayout());
        finalFrontPage.add(loginWrapper, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel1.setPreferredSize(new Dimension(150, 100));
        panel2.setPreferredSize(new Dimension(150, 100));
        panel3.setPreferredSize(new Dimension(0, 100));
        panel4.setPreferredSize(new Dimension(100, 100));
        panel1.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.LIGHT_GRAY);
        panel3.setBackground(ORANGE);
        panel4.setBackground(ORANGE);

        finalFrontPage.add(panel1, BorderLayout.WEST);
        finalFrontPage.add(panel2, BorderLayout.EAST);
        finalFrontPage.add(panel3, BorderLayout.NORTH);
        finalFrontPage.add(panel4, BorderLayout.SOUTH);

        return finalFrontPage;
    }

    public JButton getNextButton(){
        return nextButton;
    }
    public JButton getSigninButton(){
        return signinButton;
    }

    public JButton getSignupButton(){
        return signupButton;
    }
    public JButton getForgotPasswordButton(){
        return forgotPasswordButton;
    }
    public JCheckBox getPasswordVisibilityCheck(){
        return passwordVisibilityCheck;
    }

    public int getCurrentUserID(){
        return currentUserID;
    }
    public JButton getShowUsersButton(){
        return showUsersButton;
    }
    public JTextField getIsbn(){return isbn;}
    public JTextField getAuthor(){return author;}
    public JTextField getTitle2(){return title;}

    public JButton getAddBookButton(){
        return addBookButton;
    }

    public JTextField getUsernameInput(){
        return usernameInput;
    }
    public JPasswordField getPasswordInput(){
        return passwordInput;
    }

    public CardLayout getCard(){
        return card;
    }

    public DefaultTableModel getTableModel(){
        return internalTableModel;
    }
    public int getRunner(){
        return runner;
    }
    public void setRunner(int newRunner){
        this.runner = newRunner;
    }
    public void setTitle2(String s){
        this.title.setText(s);
    }
    public void setAuthor(String s){
        this.author.setText(s);
    }
    public void setIsbn(String s){
        this.isbn.setText(s);
    }

    public JLabel getDisplayUsername() {
        return displayUsername;
    }

    public JLabel getDisplayFirstName() {
        return displayFirstName;
    }

    public JLabel getDisplayJoinDate() {
        return displayJoinDate;
    }

    public JLabel getDisplayEmail() {
        return displayEmail;
    }

    public JLabel getDisplaySecurityQuestion() {
        return displaySecurityQuestion;
    }

    public JLabel getDisplaySecurityQuestionAnswer() {
        return displaySecurityQuestionAnswer;
    }
    public JLabel getText(){
        return text;
    }
    public JLabel getText2(){
        return text2;
    }

    public JCheckBox getLibrarianCheck(){
        return librarianCheck;
    }

    public JPanel getCardContainer(){
        return cardContainer;
    }

    public JLabel getErrorText(){
        return errorText;
    }

    public CardLayout getUsernameCard(){
        return usernameCard;
    }

    public JPanel getUserNameScreen(){
        return usernameScreen;
    }

    public JMenu getMenu(){
        return menu;
    }
    public JMenuBar getMyMenuBar(){
        return menuBar;
    }
    public JTable getTable(){
        return table;
    }

    public JLabel getQuestion(){
        return question;
    }

    public String getSecurityQuestionSelected(){
        return securityQuestionSelected;
    }

    public JButton getCreateAccountButton(){
        return createAccountButton;
    }

    public JLabel getSignUpHeader(){
        return signUpHeader;
    }
    public void setCurrentUserID(int currentUserID){
        this.currentUserID = currentUserID;
    }

    public JTextField getNewFirstName() {
        return newFirstName;
    }

    public JTextField getNewLastName() {
        return newLastName;
    }

    public JTextField getNewEmail() {
        return newEmail;
    }
    public JLabel getUsername(){
        return username;
    }

    public JTextField getNewPassword() {
        return newPassword;
    }
    public JLabel getEmailError(){
        return emailError;
    }
    public JTextField getSecurityQuestionInput(){
        return securityQuestionInput;
    }

    public JLabel getPasswordError(){
        return passwordError;
    }
    public void setReturned(StringBuilder returned){
        this.returned = returned;
    }
    public void setOverdue(StringBuilder overdue){
        this.overdue = overdue;
    }

    public void setSecurityQuestionSelected(String question){
        this.securityQuestionSelected = question;
    }

    public void addShowUsersButtonListener(ActionListener listenForShowUsersButton) {

        showUsersButton.addActionListener(listenForShowUsersButton);
    }
    public void addPassVisibilityCheckListener(ActionListener listenForPassVisibleCheck) {

        passwordVisibilityCheck.addActionListener(listenForPassVisibleCheck);
    }
    public void addSigninButtonListener(ActionListener listenForSigninButton) {

        signinButton.addActionListener(listenForSigninButton);
    }
    public void addSignupButtonListener(ActionListener listenForSignupButton) {

        signupButton.addActionListener(listenForSignupButton);
    }

    public void addForgotPasswordListener(ActionListener listenForForgotPassButton) {

        forgotPasswordButton.addActionListener(listenForForgotPassButton);
    }
    public void addBookButtonListener(ActionListener listenForAddBookButton){
        addBookButton.addActionListener(listenForAddBookButton);
    }
    public void addBookButtonForLibrarianListener(ActionListener listenForAddBookButtonForLibrarian){
        addBookButtonForLibrarian.addActionListener(listenForAddBookButtonForLibrarian);
    }

    public void addMenuItemListener(ActionListener listenForMenuItem){
        menu.getItem(0).addActionListener(listenForMenuItem);
        menu.getItem(1).addActionListener(listenForMenuItem);
        menu.getItem(2).addActionListener(listenForMenuItem);
        menu.getItem(3).addActionListener(listenForMenuItem);
    }
    public void addNextFromReturnBookScreenButtonListener(ActionListener listenForNextFromReturnBookScreenButton){
        nextFromReturnBookScreenButton.addActionListener(listenForNextFromReturnBookScreenButton);
    }

    public void addNextButtonListener(ActionListener listenForNextButton){
        nextButton.addActionListener(listenForNextButton);
    }
    public void addLogoutButtonListener(ActionListener listenForLogoutButton){
        logoutButton.addActionListener(listenForLogoutButton);
    }
    public void addCheckOutButton(ActionListener listenForCheckOutButton){
        checkOutButton.addActionListener(listenForCheckOutButton);
    }

    public void addCreateAccountButtonListener(ActionListener listenForCreateAccountButton){
        createAccountButton.addActionListener(listenForCreateAccountButton);
    }
    public void addLogoutFromTableButtonListener(ActionListener listenForLogoutFromTableButton){
        logoutFromTableButton.addActionListener(listenForLogoutFromTableButton);
    }

    public JPanel createSignUpPage() {
        // Initialize the sign-up page panel with GridBagLayout for flexible component layout
        JPanel signUpPage = new JPanel(new GridBagLayout());
        signUpPage.setPreferredSize(new Dimension(500, 200));
        signUpPage.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        signUpPage.setBackground(Color.WHITE);

        // Labels and text fields for user input during sign up
        JLabel firstName = new JLabel("Enter First Name:");
        JLabel lastName = new JLabel("Enter Last Name:");
        JLabel email = new JLabel("Enter Email:");
        JLabel createNewPassword = new JLabel("Enter Password:");
        createAccountButton = new JButton("Create Account");
        passwordError = new JLabel();
        emailError = new JLabel();

        //Menus for selecting security question
        menuBar = new JMenuBar();
        menu = new JMenu("Select Security Question");
        JMenuItem menuItem1 = new JMenuItem("What was the name of your pet?");
        JMenuItem menuItem2 = new JMenuItem("What was the name of the city you were born in?");
        JMenuItem menuItem3 = new JMenuItem("What is the name of your favorite dish?");
        JMenuItem menuItem4 = new JMenuItem("What is your favorite sport?");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menuBar.add(menu);
        menuBar.setBackground(Color.WHITE);
        menuBar.setBorderPainted(false);

        newFirstName = new JTextField();
        newFirstName.setPreferredSize(new Dimension(40, 20));

        newLastName = new JTextField();
        newLastName.setPreferredSize(new Dimension(40, 20));

        newEmail = new JTextField();
        newEmail.setPreferredSize(new Dimension(40, 20));

        newPassword = new JPasswordField();
        newPassword.setPreferredSize(new Dimension(40, 20));

        securityQuestionInput = new JTextField();
        securityQuestionInput.setPreferredSize(new Dimension(40,20));

        GridBagConstraints constraints = new GridBagConstraints(); //Reference variable to add constraints to the GridBagLayout

        //Adding specific constraints for the GridBagLayout
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        signUpPage.add(firstName, constraints);

        constraints.gridy = 1;
        signUpPage.add(lastName, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        signUpPage.add(newFirstName, constraints);

        constraints.gridy = 1;
        signUpPage.add(newLastName, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        signUpPage.add(email, constraints);

        constraints.gridx = 3;
        signUpPage.add(newEmail, constraints);

        constraints.gridy = 3;
        signUpPage.add(newPassword, constraints);

        constraints.gridx = 0;
        signUpPage.add(createNewPassword, constraints);

        constraints.gridy = 4;
        signUpPage.add(menuBar, constraints);

        constraints.gridx = 3;
        signUpPage.add(securityQuestionInput, constraints);

        // Checkbox for selecting if the account is for a librarian
        librarianCheck = new JCheckBox("Check if Librarian Account");
        librarianCheck.setBackground(Color.WHITE);
        constraints.gridy = 5;
        constraints.gridx = 3;
        signUpPage.add(librarianCheck, constraints);

        constraints.gridy = 6;
        constraints.gridx = 3;
        signUpPage.add(createAccountButton, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        signUpPage.add(passwordError, constraints);

        constraints.gridy = 6;
        constraints.gridx = 0;
        signUpPage.add(emailError, constraints);

        signUpHeader = new JLabel("- - - - - - Sign Up - - - - - -");
        signUpHeader.setVisible(true);
        JPanel signUpPageWrapper = new JPanel(new GridBagLayout()); //Wrapper for the sign up panel
        Color ORANGE = new Color(255, 165, 0);
        signUpPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ORANGE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        signUpPageWrapper.setBackground(Color.LIGHT_GRAY);

        usernameCard = new CardLayout(); //New cardLayout for the specific panel. Switches center panel between the signup page to the page that displays username
        usernameScreen = new JPanel(usernameCard);
        usernameScreen.add(signUpPage, "signingUp");

        constraints = new GridBagConstraints(); //New constraints
        //constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        signUpPageWrapper.add(usernameScreen, constraints);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        signUpPageWrapper.add(signUpHeader);

        JPanel loggedIn = new JPanel(); //Logged in panel that'll display username
        loggedIn.setBackground(Color.WHITE);
        loggedIn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false), BorderFactory.createLineBorder(Color.WHITE, 2, true)));
        loggedIn.setLayout(new GridBagLayout());
        username = new JLabel();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loggedIn.add(username, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        logoutButton = new JButton("Logout");

        question = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 4;
        signUpPage.add(question, constraints);
        question.setVisible(false);

        loggedIn.add(logoutButton, constraints);

        usernameScreen.add(loggedIn, "loginUsername");

        return signUpPageWrapper;
    }

    public JPanel createLoggedInScreen() {
        // Initialize the main panel for the logged-in screen
        JPanel loggedInPanel = new JPanel();
        loggedInPanel.setBackground(Color.WHITE);
        loggedInPanel.setLayout(new GridBagLayout());
        loggedInPanel.setPreferredSize(new Dimension(400, 200));
        loggedInPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Create and set up labels to display user information
        displayUsername = new JLabel();
        displayUsername.setHorizontalAlignment(SwingConstants.CENTER);
        displayFirstName = new JLabel();
        displayFirstName.setHorizontalAlignment(SwingConstants.CENTER);
        displayJoinDate = new JLabel();
        displayJoinDate.setHorizontalAlignment(SwingConstants.CENTER);
        displayEmail = new JLabel();
        displayEmail.setHorizontalAlignment(SwingConstants.CENTER);
        displaySecurityQuestion = new JLabel();
        displaySecurityQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        displaySecurityQuestionAnswer = new JLabel();
        displaySecurityQuestionAnswer.setHorizontalAlignment(SwingConstants.CENTER);

        // Button to proceed to the next screen
        nextButton = new JButton("Next");

        // Setup layout constraints for components in GridBagLayout
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.ipady = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        loggedInPanel.add(displayUsername, constraints);

        constraints.gridy = 1;
        loggedInPanel.add(displayFirstName, constraints);

        constraints.gridy = 2;
        loggedInPanel.add(displayJoinDate, constraints);

        constraints.gridy = 3;
        loggedInPanel.add(displayEmail, constraints);

        constraints.gridy = 4;
        loggedInPanel.add(displaySecurityQuestion, constraints);

        constraints.gridy = 5;
        loggedInPanel.add(displaySecurityQuestionAnswer, constraints);

        constraints.gridy = 6;
        loggedInPanel.add(nextButton, constraints);

        // Wrapper panel to enhance the visual appearance
        JPanel loggedInPageWrapper = new JPanel(new GridBagLayout()); //Wrapper panel for the loggedInPanel
        loggedInPageWrapper.setBackground(Color.lightGray);
        Color PURPLE = new Color(255, 165, 0);
        loggedInPageWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(PURPLE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));

        // Adding the main panel to the wrapper panel
        loggedInPageWrapper.add(loggedInPanel);

        return loggedInPageWrapper; // Return the wrapper panel with the logged-in screen
    }


    public static boolean checkEmailRequirements(String email) throws EmailException {
        // Flags to track presence of '@' and '.' in the email
        boolean containsAtSign = false;
        boolean containsPeriod = false;

        // Loop through each character in the email to check for '@' and '.'
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                containsAtSign = true;
            }
            if (email.charAt(i) == '.') {
                containsPeriod = true;
            }
        }

        // Throw an exception if '@' is missing in the email
        if (!containsAtSign) {
            throw new AtSignMissingException("Error - Email Missing '@' Sign");
        }
        // Throw an exception if '.' is missing in the email
        if (!containsPeriod) {
            throw new PeriodMissingException("Error - Email Missing Period (.) Sign");
        }
        return true;
    }

    public static boolean checkPasswordRequirements(String password) throws PasswordException { //Method to check if password requirements are met
        // Initialize flags for each password requirement
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean characterLimit = false;
        boolean specialCharacter = false;
        boolean numberCharacter = false;

        // Check if the password length is at least 8 characters
        if (password.length() >= 8) {
            characterLimit = true;
        }

        // Iterate over each character of the password to check for different requirements
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                upperCase = true;
            }
            if (Character.isLowerCase(character)) {
                lowerCase = true;
            }
            if (Character.isDigit(character)) {
                numberCharacter = true;
            }
            if (!Character.isLetter(character) && !Character.isDigit(character) && !Character.isWhitespace(character)) {
                specialCharacter = true;
            }
        }

        // Throw exceptions if any of the requirements are not met
        if (!upperCase) {
            throw new UpperCaseCharacterMissing("Error - Password Missing Uppercase");
        } else if (!lowerCase) {
            throw new LowerCaseCharacterMissing("Error - Password Missing Lowercase");
        } else if (!characterLimit) {
            throw new Minimum8CharactersRequired("Error - Minimum of 8 Characters Required");
        } else if (!specialCharacter) {
            throw new SpecialCharacterMissing("Error - Missing Special Character");
        } else if (!numberCharacter) {
            throw new NumberCharacterMissing("Error - Missing Number Character");
        }

        return true;
    }


    public JPanel createSearchScreen() {
        // Set up the table model and row sorter for the search table
        internalTableModel = new DefaultTableModel();
        rowSorter = new TableRowSorter<DefaultTableModel>(internalTableModel); //Used to create new filters for the table(Search filter)
        table = new JTable(internalTableModel);
        table.setRowSorter(rowSorter);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Add columns to the table model
        internalTableModel.addColumn("Title");
        internalTableModel.addColumn("Authors");
        internalTableModel.addColumn("ISBN");

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

        // Create a text field for inputting search filters
        filterText = new JTextField(20);
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                LibraryView.this.updateTableFilter();
            }

            public void removeUpdate(DocumentEvent e) {
                LibraryView.this.updateTableFilter();
            }

            public void changedUpdate(DocumentEvent e) {
                LibraryView.this.updateTableFilter();
            }
        });

        // Create a button for checking out books
        checkOutButton = new JButton("Check Out");
        checkOutButton.setPreferredSize(new Dimension(100, 22));

        // Setup a panel for search filter and checkout button
        JPanel bufferPanel = new JPanel(new GridBagLayout());
        bufferPanel.add(filterText, constraints);
        constraints.gridx = 2;
        bufferPanel.add(checkOutButton, constraints);

        // Add the buffer panel to the main panel
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(bufferPanel, constraints);

        // Labels for displaying messages related to checked out and unavailable books
        text = new JLabel(); //Text for checked out books/errors
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(text, constraints);

        text2 = new JLabel(); //Text for unavailiable books
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(text2, constraints);

        // Set the constraints for the 'Add Book' button
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        addBookButton = new JButton("Add Book");
        panel.add(addBookButton, constraints);  //Add linked to sign in button. If backend.Patron don't show. If librarian show
        addBookButton.setVisible(true);

        // Create a 'Logout' button
        logoutFromTableButton = new JButton("Logout");
        constraints.ipadx = 5;  // Padding in x direction
        constraints.gridx = 3;  // Position on x-axis
        constraints.gridy = 1;  // Position on y-axis
        constraints.anchor = GridBagConstraints.FIRST_LINE_END; // Align to the end of the first line

        // Add the 'Logout' button to the panel
        panel.add(logoutFromTableButton, constraints);

        return panel;

    }

    public JPanel addBooksPage() {
        // Setting up the main panel for adding books
        JPanel addBookPanel = new JPanel(new GridBagLayout());
        addBookPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));
        addBookPanel.setBackground(Color.WHITE);
        addBookPanel.setPreferredSize(new Dimension(250, 150));

        // Creating labels for book details input fields
        JLabel bookTitle = new JLabel("Book Title: ");
        JLabel bookAuthor = new JLabel("Book Author: ");
        JLabel bookISBN = new JLabel("Book ISBN: ");

        // Text fields for entering book details
        title = new JTextField(10);
        author = new JTextField(10);
        isbn = new JTextField(10);

        // Constraints for layout management
        GridBagConstraints constraints = new GridBagConstraints();

        // Adding title label and text field to the panel
        constraints.ipady = 6;
        constraints.gridx = 0;
        constraints.gridy = 0;
        addBookPanel.add(bookTitle, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(title, constraints);

        // Adding author label and text field to the panel
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipady = 6;
        addBookPanel.add(bookAuthor, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(author, constraints);

        // Adding ISBN label and text field to the panel
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.ipady = 6;
        addBookPanel.add(bookISBN, constraints);

        constraints.gridx = 1;
        constraints.ipady = 0;
        addBookPanel.add(isbn, constraints);

        // Button to add the book to the database
        addBookButtonForLibrarian = new JButton("Add");
        addBookButtonForLibrarian.setPreferredSize(new Dimension(65, 25));

        // Adding the button to the panel
        constraints.gridy = 3;
        addBookPanel.add(addBookButtonForLibrarian, constraints);

        // Wrapper panel for aesthetic purposes
        JPanel addBookPanelWrapper = new JPanel(new GridBagLayout());
        addBookPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color ORANGE = new Color(255, 165, 0);
        addBookPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ORANGE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        addBookPanelWrapper.add(addBookPanel);

        return addBookPanelWrapper; // Return the complete panel for adding books
    }

    public void updateTableFilter() { // This method updates the table filter based on the entered text in the search bar.
        RowFilter<DefaultTableModel, Object> updatedFilter = null;

        try {
            // Create a new RowFilter based on the text entered in the filterText field.
            // The regexFilter here is case insensitive ('(?i)') and matches the filterText.
            updatedFilter = RowFilter.regexFilter("(?i)" + filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            // If the regex pattern is not valid (e.g., an incomplete expression), do not update the filter.
            return;
        }

        // Apply the newly created filter to the row sorter.
        // This will update the display of the table to only show rows matching the filter.
        rowSorter.setRowFilter(updatedFilter);
    }

    public JPanel createReturnBookScreen() {
        // Create a panel for the returned books section
        JPanel returnedBooksPanel = new JPanel(new GridBagLayout());
        returnedBooksPanel.setBackground(Color.WHITE);
        returnedBooksPanel.setPreferredSize(new Dimension(400, 200));
        returnedBooksPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true), BorderFactory.createLineBorder(Color.white, 3, true)));

        // Labels to display information about returned and overdue books
        JLabel bookInformation = new JLabel();
        JLabel overdueBooksInformation = new JLabel();

        // Obtain current Patron information from the database

        // Setting text for the labels
        bookInformation.setText("Returned Books: " + returned);
        overdueBooksInformation.setText("Overdue Books: " + overdue);

        // Setup layout constraints
        GridBagConstraints constraints = new GridBagConstraints();

        // Add the labels to the panel
        returnedBooksPanel.add(bookInformation, constraints);
        constraints.gridy = 1;
        returnedBooksPanel.add(overdueBooksInformation, constraints);

        // Wrapper panel for styling and layout
        JPanel returnedBooksPanelWrapper = new JPanel(new GridBagLayout());
        returnedBooksPanelWrapper.setBackground(Color.LIGHT_GRAY);
        Color ORANGE = new Color(255, 165, 0);
        returnedBooksPanelWrapper.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ORANGE, 10, false), BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true)));
        returnedBooksPanelWrapper.add(returnedBooksPanel);

        // Button to navigate to the next screen
        nextFromReturnBookScreenButton = new JButton("Next");
        constraints.gridy = 2;
        returnedBooksPanel.add(nextFromReturnBookScreenButton, constraints);

        return returnedBooksPanelWrapper;
    }

}

