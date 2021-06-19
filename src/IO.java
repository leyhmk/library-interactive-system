import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class IO {
    private final Controller CONTROL;
    private final Scanner KEY_IN = new Scanner(System.in);
    //Set the output file location
    private final PrintWriter OUTFILE = new PrintWriter("/Users/janice/Documents/8012cw/output.txt ");


    /**
     * Command line program that takes in alphabetical
     * user input to select options in a menu.
     * The menu will not terminate unless the exit
     * program option is chosen.
     */
    public void mainMenu() {
        CONTROL.readFile();
        System.out.println("Welcome to the Library Interactive System");
        menu();
        char ch = Character.toLowerCase(KEY_IN.next().charAt(0));
        KEY_IN.nextLine();
        while (ch!='f'){
            switch (ch){

                //Display information about all the books in the library
                case 'b':
                    CONTROL.bookPrinting();
                    break;

                //Display information about all the registered users
                case 'u':
                    CONTROL.userPrinting();
                    break;

                //Issued a book to a user
                case 'i':
                    borrow();
                    break;

                //Update the borrowing record when user return a book
                case'r':
                    returnBook();
                    break;
                default:
                    System.out.println("Sorry, this is not an valid option. Please try again.");
            }
            menu();
            ch = Character.toLowerCase(KEY_IN.next().charAt(0));
            KEY_IN.nextLine();
        }
        OUTFILE.close();
        System.out.println("Thank you for using the Library Interactive System. " +
                "Please check the output file for reminder notes.");
    }


    /**
     * The printed menu of the IO
     */
    private static void menu(){
        System.out.println("\n Please select one of the following option and press Enter:");
        System.out.println("f - Exit the programme");
        System.out.println("b - Print all the books information");
        System.out.println("u - Print all the users information");
        System.out.println("i - Record borrowing");
        System.out.println("r - Record return \n");
    }


    /**
     * With the inputted book and user data,
     * the method check if the user are allowed to borrow any more books.
     * If yes, it then check if the book is on loan at the moment.
     * If the book is on loan, it will call the reminderPrint method.
     * If the book is available, and the user is holding less than 3 books,
     * the system will call the setBorrow method.
     */
    private void borrow () {
        System.out.println("The system is going to record a book borrowing.");
        Books book = readBookData();
        Users user = readUserData();
        if (CONTROL.numberOfBookCheck(user)) {
            System.out.println("This user is already holding 3 books");
        } else if (CONTROL.checkLoan(book)) {
            CONTROL.reminderPrint(book,OUTFILE);
        } else {
            CONTROL.setBorrow(user, book);
        }
    }


    /**
     * With the inputted user's full name,
     * the method checks if this user is being registered on the library system.
     *
     * @return return the user's record if found
     */
    private Users readUserData(){
        System.out.println("Please enter the user's name: ");
        String firstName = KEY_IN.next();
        String surname = KEY_IN.next();
        KEY_IN.nextLine();
        Users requiredUser = CONTROL.searchUser(firstName, surname);
        while (requiredUser==null){
            System.out.println("This user has not yet registered on the system. Please try again: ");
            firstName = KEY_IN.next();
            surname = KEY_IN.next();
            requiredUser = CONTROL.searchUser(firstName, surname);
        }
        return requiredUser;
    }


    /**
     * Check if the book is on the library's book list
     * with the given book's title and author.
     *
     * @return book's record if found
     */
    private Books readBookData(){
        System.out.println("Please enter the book's name: ");
        String bookName = KEY_IN.nextLine();
        System.out.println("Please enter the author's name: ");
        String author = KEY_IN.nextLine();
        Books requiredBook = CONTROL.searchBook(bookName,author);
        while (requiredBook==null) {
            System.out.println("This book is not on the library list. Please try again. ");
            System.out.println("Please enter the book's name: ");
            bookName = KEY_IN.nextLine();
            System.out.println("Please enter the author's name: ");
            author = KEY_IN.nextLine();
            requiredBook = CONTROL.searchBook(bookName,author);
        }return requiredBook;
    }


    /**
     * Read the returning book information,
     * and the user returning it.
     * Check if the book has been borrowed by this particular user.
     *
     * If the record does not match, it will return an error message.
     * If the record matches, it then update the stored information accordingly.
     */
    private void returnBook(){
        System.out.println("The system is going to record a book return.");
        Books book = readBookData();
        Users user = readUserData();
        while (!CONTROL.userBookMatching(book,user)){
            System.out.println("This borrowing record does not match our system record. Please try again.");
            book = readBookData();
            user = readUserData();
        }
        CONTROL.bookReturn(book,user);
    }


    /**
     * Create an instance of the Controller, which is
     * private and immutable.
     */
    IO() throws FileNotFoundException {
        this.CONTROL = new Controller();
    }


    /**
     * Main method for running the
     * library interactive system
     */
    public static void main(String[] args) throws FileNotFoundException {
        new IO().mainMenu();
    }
}
