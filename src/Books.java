public class Books implements Comparable<Books> {

    private final String TITLE;
    private final String AUTHOR;
    private boolean onLoan;
    private Users borrower;
    private String firstName;
    private String surname;

    /**
     * Construct a book record with the given book
     * title and author.
     *
     * @param title the book's title
     * @param author the author of the book
     */
    Books(String title, String author){
        this.TITLE = title;
        this.AUTHOR = author;
        //set the book as not being on loan
        this.onLoan = false;
        //set the borrower of the books to null
        this.borrower = null;
    }


    /**
     * The compareTo method.
     * Author are being compared by their surname
     * with alphabetical order.
     * If they have the same surname,
     * they will then be compared by their first name.
     *
     * @param author the author of the book
     */
    @Override
    public int compareTo(Books author) {
        int lastNameResult = surname.compareTo(author.surname);
        if (lastNameResult != 0) return lastNameResult;
        return firstName.compareTo(author.firstName);
    }


    /**
     * This method split the author full name
     * into surname and first name.
     *
     * @param name name of the author
     */
    public void splitName (String name){
        String[] tmpNameList = name.split(" ");
        int size = tmpNameList.length;
        surname = tmpNameList[size -1];
        firstName = tmpNameList[0];
    }


    /**This method override the toString method.
     * Returns a string representation of a book,
     * The string representation be printed
     * in the form of:
     * Author: Book name, On loan or not, and the name of the borrower if on loan.
     *
     * @param book name of the book
     */
    public void toString (Books book){
        if (!onLoan){
            System.out.println(AUTHOR +": "+ TITLE + ", Not on loan");
        }else {
            System.out.println(AUTHOR +": "+ TITLE + ", On loan to " + borrower.getName());
        }

    }


    /**
     * Set the loan status of a book.
     *
     * @param onLoan boolean representation of the loan status
     */
    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }


    /**
     * Set the borrower of a book.
     *
     * @param user the book's borrower
     */
    public void setBorrower(Users user){
        this.borrower = user;
    }


    /**
     * Get the title of the book.
     *
     * @return the book's title
     */
    public String getTITLE() {
        return TITLE;
    }


    /**
     * Get the author of the book.
     *
     * @return the name of the book's author
     */
    public String getAUTHOR() {
        return AUTHOR;
    }


    /**
     * Get the name of the book's borrower.
     *
     * @return the name of the book's borrower
     */
    public Users getBorrower() {
        return borrower;
    }


    /**
     * Check if a book is currently on loan
     *
     * @return boolean representation of the loan status
     */
    public boolean isOnLoan() {
        return onLoan;
    }
}
