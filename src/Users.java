public class Users implements Comparable<Users> {

    private final String FIRST_NAME;
    private final String SURNAME;
    private int numberOfBooks;

    /**
     * Create a user record with the given
     * first name and surname.
     *
     * @param firstName first name of the user
     * @param surname surname of the user
     */
    Users(String firstName, String surname){
        this.FIRST_NAME = firstName;
        this.SURNAME = surname;
        //set the initial number of books currently hold by the user to zero
        this.numberOfBooks = 0;
    }


    /**
     * CompareTo method.
     * Users are being compared by their surname.
     * If they have the same surname,
     * they will then be compared using their first name.
     *
     * @param user user record
     */
    @Override
    public int compareTo(Users user) {
        int lastNameResult = SURNAME.compareTo(user.SURNAME);
        if (lastNameResult!=0) return lastNameResult;
        return FIRST_NAME.compareTo(user.FIRST_NAME);
    }


    /**
     * Returns a string representation of a user,
     * The string representation is the first name
     * and the surname of the user seperated by
     * a space character.
     *
     * @param user user record
     */
    public void toString (Users user){
        System.out.println(FIRST_NAME +" " + SURNAME);
    }


    /**
     * Increased the number of books the user hold by 1.
     *
     * @param user user record
     */
    public void increaseNumberOfBooks(Users user){
        this.numberOfBooks +=1;
    }


    /**
     * Decreased the number of books the user hold by 1.
     *
     * @param user user record
     */
    public void decreaseNumberOfBooks(Users user){
        this.numberOfBooks -=1;
    }


    /**
     * Return the first part of the name.
     *
     * @return the user's surname
     */
    public String getFIRSTNAME() {
        return FIRST_NAME;
    }

    /**
     * Return the surname of the name.
     *
     * @return the user's surname
     */
    public String getSURNAME() {
        return SURNAME;
    }


    /**
     * Return the number of books the user
     * currently hold.
     *
     * @return number of books the user is currently holding
     */
    public int getNumberOfBooks() {
        return numberOfBooks;
    }


    /**
     * Return the name of the user.
     *
     * @return the user's full name
     */
    public String getName(){
        return FIRST_NAME +" " + SURNAME;
    }
}

