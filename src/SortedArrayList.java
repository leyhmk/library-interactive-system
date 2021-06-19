import java.util.ArrayList;


public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {


    /**
     * Provided a generic method for sorting.
     * Arraylist will be saved in alphabet ascending order.
     * Object will be inserted into the arraylist, while the order of
     * the arraylist is preserved.
     *
     * @param e object in consideration
     */
    public void sortingMethod(E e) {
        if (!this.isEmpty()) {
            int i = 0;
            while (i < this.size()) {
                int result = e.compareTo(this.get(i));
                if (result < 0) {
                    this.add(i, e);
                    break;
                } else if (result == 0) {
                    int secondCompare = e.compareTo(this.get(i));
                    if (secondCompare < 0) {
                        this.add(i, e);
                    } else {
                        this.add(i + 1, e);
                    }
                    break;
                }
                i++;
                if (i == this.size()){
                    this.add(e);
                    break;
                }

            }
        } else {
            this.add(e);
        }
    }

}
