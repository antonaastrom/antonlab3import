package sorter.control.algorithms;

import sorter.control.AlgorithmHandler;


/**
 * Abstract base class for all sort algorithms. 
 * All algorithms inherits from this class.
 *
 * The functions here are used by the algorithms.
 * 
 * Does not have to be changed. 
 * 
 * @author Sara Stymne
 *
 */
public abstract class SortAlgorithm {
    
    /**
     * The algorithmHandler that this algorithm belongs to
     */
    protected AlgorithmHandler handler;
    
    
    /**
     * The name of the algorithm
     */
    private final String algorithmName;
    
    
    /**
     * This method should implement the sort algorithm in each subclass.
     */
    abstract protected void sort();
    
      
    /**
     * Sets the name of a sort algorithm
     * 
     * This should be called by subclass constructors
     * 
     * @param name the name of the algorithm
     */
    public SortAlgorithm(String name) {
        this.algorithmName = name;
    }
    
    
    /**
     * Sets the handler of the algorithm,
     * has to be called before the algorithm is used.
     * This is already done in AlgorithmHandler.
     * 
     * @param handler the handler
     */
    public void setHandler(AlgorithmHandler handler) {
        this.handler = handler;
    }
    
    
    
    /**
     * Runs the sorting algorithm
     */
    public void run() {
        if (handler != null) {
            //starts the sorting
            sort();
            //tells the handler that the sorting is done
            handler.finished();   	
        }
    }
    
    
    /**
     * Swaps the values at the two indices in the vector, 
     * and waits for the other algorithms to do 1-3 steps.
     * 
     * @param i the index of the first element
     * @param j the index of the first element
     */
    protected void swap(int i, int j) {
        handler.swap(i, j);
    }
    
    /**
     * Compares the values of the numbers at the two given indeces in the vector,
     * and waits for the other algorithms to do one step.
     * 
     * 
     * @param i the index of the first number to compare
     * @param j the index of the second number to compare
     * @return A negative value if the value at i < value at j.
     *         A positive value if the value at i > value at j.
     *         Zero if the value at i == value at j.
     */
    protected int cmp(int i, int j) {
        return handler.cmp(i, j);
    }
    
    
    /**
     * Returns the algorithmName.
     * 
     * @return the algorithmName.
     */
    public String getAlgorithmName() {
        return algorithmName;
    }
    
    
    /**
     * Gives the size of the current sort vector
     * 
     * @return the size of the sort vector
     */
    protected int getElementCount() {
        return handler.getElementCount();
    }
    
}
