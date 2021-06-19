public class Library {

    private int  booksNumber;


    /**
     * Construct a library record with the given
     * number of books the library currently hold.
     *
     * @param booksNumber number of books the library hold
     */
    public Library(int booksNumber){
        this.booksNumber=booksNumber;
    }


    /**
     * Get the number of books the library currently hold
     *
     * @return number of books the library hold
     */
    public int getBooksNumber() {
        return booksNumber;
    }
}
