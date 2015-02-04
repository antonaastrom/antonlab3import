package sorter.control;

import java.util.*;

/**
 * Class for handling a vector of Integers, 
 * that is being sorted in the animation
 *
 * This class does not have to be changed.
 * 
 * @author Sara Stymne
 * @author Jonas Lindgren
 */
public class DataSet extends ArrayList<Integer> {
	// always start with the same random seed for ease of testing
    private Random random = new Random(0);

	
    
    /**
     * Needed because this class implements Serializable
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * The size of the sortVector
     */
    private int size;
    
    /**
     * The maximum size of the numbers in the vector, 
     * only used when the vector is randomized
     */
    private int max;
    
    
    /**
     * Creates an empty sortVector,
     * with size and max set to 50 
     */
    public DataSet() {
      this(50, 50);
    }

    /**
     * Creates an empty vector with size and max specified
     *
     * @param size the size of the vector
     * @param max the max size of the ntegers in the vector (when randomized)
     */
   public DataSet(int size, int max) {
      super();
      this.max = max;
      this.size = size;
    }
    
    /**
     * Changes the size of the vector next time it is reorganized
     * 
     * @param s the new size
     */
    public void setSize(int s) {
        size = s;
    }
    
    /**
     * Changes the maximum size of the numbers in the vector,
     * next time the vector is randomized
     * 
     * @param m the new max size
     */
    public void setMax(int m) {
        max = m;
    }
    
    
    /**
     * Gives the current max of the vector
     * 
     * @return Returns the max.
     */
    public int getMax() {
        return max;
    }

    /**
     * gives the current size of the vector
     * 
     * @return Returns the size.
     */
    public int getSize() {
        return size;
    }

    /**
     * same as getSize(). Exists for consistency with SortAlgorithm
     * 
     * @return the size of the vector
     */
    public int elementCount() {
        return size;
    }
    
    /**
     * Scrambles the vector, with numbers from 1 to the vector size,
     * so that there is no doubles
     */
    public void scrambleVector() {
        clear();	
        for(int i = 1;  i <= size;  ++i) {
            add(i);
        }
        
        for(int i = 0;  i < size;  ++i) {
            int j = random.nextInt(size);
            swap(i, j);
        }
        max = size;
    }
    
    
    /**
     * randomizes the numbers in the vector between 1 and max
     */
    public void randomizeVector() {
        clear();
        for(int i = 0;  i < size;  ++i) {
        	add(random.nextInt(max)+1);
        }
    }
    
    /**
     * Organizes the vector so that it is sorted backwords
     */
    public void backwordVector() {
        clear();
        for(int i = size; i > 0; --i) {
            add(i);
        }
        max = size;
    }
    
    
    /**
     * Organizes the vector sorted
     */
    public void sortedVector() {
        clear();
        for(int i = 1; i <= size; ++i) {
            add(i);
        }    
        max = size;
    }
    
    /**
     * Scrambles the vector a little, i.e. creates a nearly ordered vector,
     * with numbers from 1 to the vector size,
     * so that there is no doubles
     */
    public void scrambleVectorALittle() {
        clear();	
        for(int i = 1; i <= size; i++) {
            add(i);
        }
        
        for(int i = 0; i < size(); ++i) {
            int j = i + random.nextInt(7) - 3;
            if (j >= 0 && j <size())
                swap(i, j);
        }
        max = size;
    }
    
    /**
     * Reorganizes the vector either scrambled, randomized, backwords or sorted.
     * 
     * @param order the desired order of the vector
     */
    public void orderVector(DataOrder order) {
        if (order == DataOrder.SCRAMBLED)
            scrambleVector();
        else if (order == DataOrder.RANDOM) 
            randomizeVector();
        else if (order == DataOrder.SORTED) 
            sortedVector();
        else if (order ==  DataOrder.BACKWORD) 
            backwordVector();
        else if (order ==  DataOrder.NEARLY_SORTED) 
            scrambleVectorALittle();
        
    }
    
    
    /**
     * Swaps the values of the numbers at the two given indeces in 
     * the vector
     * 
     * @param i index 1
     * @param j index 2
     */
    public void swap(int i, int j) {
        Integer tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }
    
    
 
    
    /**
     * Compares the values of the numbers at the two given indices in the vector
     * 
     * @param i the index of the first number to compare
     * @param j the index of the second number to compare
     * @return   Negative value if the value at i < j.
     *           Positive value if the value at i > j.
     *           Zero if the value at i == j.
     */
    public int cmp(int i, int j) {
        return get(i).intValue()-get(j).intValue();
    }
    

    
    /** 
     * Returns a copy of the vector
     * 
     * @return the copy
     */
    public DataSet clone() {
        DataSet copy = new DataSet(size, max);
        for(Integer i : this) {
            copy.add(i.intValue());
        }
        return copy;
    }
    
}
