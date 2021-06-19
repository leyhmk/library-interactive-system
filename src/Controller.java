import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {
    private final SortedArrayList<Users> SORTED_USER_LIST = new SortedArrayList<>();
    private final SortedArrayList<Books> SORTED_BOOK_LIST = new SortedArrayList<>();
    //Set the input file location
    private final Scanner IN_FILE = new Scanner(new FileReader(
            "/Users/janice/Documents/8012cw/input.txt"));


    /**
     * Read the information stored in the input file.
     *
     * @return Library Record
     * */
    public Library readFile() {

        int libraryBooks = 0;
        int userNumber =0;
        Library library = new Library(libraryBooks);

        while (IN_FILE.hasNext()) {
            //Read the number of books in the given library
            libraryBooks = IN_FILE.nextInt();
            IN_FILE.nextLine();
            //Create records for all of the books
            library = new Library(libraryBooks);
            for (int i = 0; i < libraryBooks; i++) {
                addBooks();
            }
            //Read the number of library users
            userNumber = IN_FILE.nextInt();
            IN_FILE.nextLine();
            //Create user records
            for (int j = 0; j < userNumber; j++) {
                addUser();
            }
        }
        //Ensure all information in the input file are being recorded
        if (library.getBooksNumber()!= SORTED_BOOK_LIST.size()||(userNumber != SORTED_USER_LIST.size())){
            readFile();
        }
        return library;
    }


    /**
     * Create a book record and add it
     * into the book record's arraylist.
     *
     */
    private void addBooks() {
        String bookName = IN_FILE.nextLine();
        String author = IN_FILE.nextLine();
        Books book = new Books(bookName, author);
        book.splitName(author);
        //Insert the book into the arraylist while
        //preserving the ascending alphabetical order
        SORTED_BOOK_LIST.sortingMethod(book);
    }


    /**
     * Create a user record and add it
     * into user record's arraylist.
     */
    private void addUser() {
        String firstName = IN_FILE.next();
        String lastName = IN_FILE.next();
        Users user = new Users(firstName, lastName);
        SORTED_USER_LIST.sortingMethod(user);
    }


    /**
     * Check if the book is on the library book list
     * with the provided book title and author.
     *
     * @return the matched book record, if found
     * @return null, if not found
     */
    public Books searchBook(String title, String author){
        for (Books b: SORTED_BOOK_LIST){
            if (b.getTITLE().equalsIgnoreCase(title)&&(b.getAUTHOR().equalsIgnoreCase(author))){
                return b;
            }
        }return null;
    }


    /**
     * Check if the user is a valid user
     * with the provided full name.
     *
     * @return return the matched user record, if found
     * @return null, if not found
     */
    public Users searchUser(String firstName, String surname){
        for (Users u : SORTED_USER_LIST){
            if (u.getFIRSTNAME().equalsIgnoreCase(firstName) &&(u.getSURNAME().equalsIgnoreCase(surname))){
                return u;
            }
        }return null;
    }


    /**
     * Print out all of the book records.
     */
    public void bookPrinting() {
        for (Books b : SORTED_BOOK_LIST) {
            b.toString(b);
        }
    }


    /**
     * Print out all of the users records.
     */
    public void userPrinting() {
        for (Users u : SORTED_USER_LIST) {
            u.toString(u);
        }
    }


    /**
     * Check if the user is already holding 3 books
     *
     * @return boolean representation of the user loan status
     */
    public boolean numberOfBookCheck(Users users) {
        return users.getNumberOfBooks() == 3;
    }


    /**
     * Check if the book is on loan
     *
     * @return boolean representation of the book loan status
     */
    public boolean checkLoan(Books book) {
        return book.isOnLoan();
    }


    /**
     * Check if the book and user name provided match
     * the library's borrowing record.
     *
     * @return boolean representation of the matching result
     */
    public boolean userBookMatching(Books book, Users user){
        //Check if the book provided is on loan according to
        //the library borrowing record
        if (book.getBorrower()==null){
            return false;
        }
        return book.getBorrower().getName().equalsIgnoreCase(user.getName());
    }


   /**
    * Set the book loan status to on loan,
    * and add the name of the borrower.
    * Increase the number of book hold by that user by one.
    */
    public void setBorrow(Users user, Books book) {
        for (Books b : SORTED_BOOK_LIST) {
            if (b.getTITLE().equalsIgnoreCase(book.getTITLE())&&
                    (b.getAUTHOR().equalsIgnoreCase(book.getAUTHOR()))) {
                b.setOnLoan(true);
                b.setBorrower(user);
            }
        }
        user.increaseNumberOfBooks(user);
    }


    /**
     * Set the book loan status to available,
     * and delete the previous borrowing record.
     * Decrease the previous borrower's number of book
     * holdings by one.
     */
    public void bookReturn(Books book, Users user) {
        for (Books b : SORTED_BOOK_LIST) {
            if (b.getTITLE().equalsIgnoreCase(book.getTITLE())
                    &&(b.getAUTHOR().equalsIgnoreCase(book.getAUTHOR()))) {
                b.setOnLoan(false);
                b.setBorrower(null);
            }
        }
        user.decreaseNumberOfBooks(user);
    }


    /**
     * Print a reminder note to the output file.
     */
    public void reminderPrint(Books book, PrintWriter pw){
        pw.println("Dear " + book.getBorrower().getName());
        pw.println();
        pw.print(book.getTITLE() +" by " + book.getAUTHOR());
        pw.print(" is requested by another user. Please return the book as soon as possible.");
        pw.println();
        pw.println();
        pw.println("Thank you.");
    }


    /**
     * Controller constructor.
     */
    public Controller() throws FileNotFoundException {
    }
}






