package sorter.control.algorithms;




/**
 * An implementation of an improved version of bubblesort. 
 * It stops if it does a run where no values are stopped 
 * (which means that the sorting is allready done)
 * 
 * See this class as a sample of how other algorithms should be implemented.
 * It does not have to be changed.
 * 
 * @author Sara Stymne
 *
 */
public class ImprovedBubbleSortAlgorithm extends SortAlgorithm {

    /**
	 * Constructs an instance of BubbleSort algorithm with the name set.
	 * 
	 *  All algorithms should have a constructor like this!
	 */
	public ImprovedBubbleSortAlgorithm() {
		super("Improved bubblesort");
	}

    
    /**
	 * Sorts the vector using improved bubblesort.
	 * 
	 * The sort() function should always be the sorting function of each algorithm
	 * 
	 * @see SortAlgorithm#sort()
	 */
	public void sort() {
		boolean done = false;
		for(int i = 1;  i < getElementCount() && !done;  ++i) {
			done = true;
			for(int j = getElementCount()-1;  j >= i;  --j) {
				if (cmp(j,j-1) < 0) {
					swap(j, j-1);
					done = false;
				}
			}
		}
	}
	      
}
