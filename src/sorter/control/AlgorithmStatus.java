package sorter.control;
/**
 *
 * Keeps track of how many algorithms there are in the application
 * and how many algorithms that are currently finished.
 *
 * This class does not have to be changed
 *
 * @author Sara Stymne
 *
 */
public class AlgorithmStatus {
	
	/**
	 * The total number of algorithms
	 */
	private int numberOfAlgorithms;
	
	/**
	 * The number of algorithms that are currently finished
	 */
	private int finishedAlgorithms;
	
	/**
	 * creates a new instance of AlgorithmStatus with number of 
	 * algorithms set to 0.
	 */
	public AlgorithmStatus() {
		numberOfAlgorithms = 0;
		finishedAlgorithms = 0;
	}
	
	/**
	 * Checks if all algorithms are finished
	 * 
	 * @return true if they all algorithms are finished, false else
	 */
	public boolean animationFinished() {
		return (finishedAlgorithms == numberOfAlgorithms);
	}
	
	/**
	 * Sets the number of algorithms 
	 * 
	 * @param n the new number of algorithms
	 */
	public void setNumberOfAlgorithms(int n) {    
		numberOfAlgorithms = n;
	}
	
	/**
	 * Returns the current number of algorithms
	 * 
	 * @return the number of algorithms in the system
	 */
	public int getNumberOfAlgorithms() {
		return numberOfAlgorithms;
	}
	
	/**
	 * Sets the number of finished algorithm to 0.
	 *
	 */
	public void resetFinishedAlgorithms() {
		finishedAlgorithms = 0;
	}
	
	/**
	 * Increases the number of finished algorithms with one 
	 *
	 */
	public void increaseFinishedAlgs() {
		finishedAlgorithms++;
	}
}
