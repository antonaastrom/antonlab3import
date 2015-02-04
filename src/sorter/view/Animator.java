package sorter.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sorter.control.Algorithms;
import sorter.control.DataSet;
import sorter.control.DataOrder;
import sorter.control.algorithms.SortAlgorithm;


/**
 * The main class for the sorting animation. 
 * Holds the main window for the animation and 
 * the different algorithms that are animated
 *
 * This class does not have to be changed.
 * 
 * @author Sara Stymne
 *
 */
public class Animator {
	
	/**
	 * The algorithms that are animated
	 */
	private Algorithms algorithms; 
	
	/**
	 * The main copy of the vector of integers that are to be sorted
	 */
	private DataSet sortVector;
	
	/**
	 * The main window
	 */
	private JFrame frame;
	
	/**
	 * The panel for the input part
	 */
	private ButtonPanel buttonArea;
		
	/**
	 * The panel showing the sorting animations
	 */
	private JPanel sortingArea;
	
    
	/**
	 * Creates a new instance of the Animator
	 * that will place the algorithms on top of each other
	 */
	public Animator() {
		this(0,1);
	}
	
	/**
	 * Creates a new instance of the Animator
	 * with the algorithms placed in a grid with size 
	 * XCoord * YCoord
	 * 
	 * @param XCoord the x coordinate of the grid of algorithm widgets
	 * @param YCoord the y coordinate of the grid of algorithm widgets
	 */
	public Animator(int XCoord, int YCoord) {
		sortVector = new DataSet();
		sortVector.scrambleVector();
		algorithms = new Algorithms(); 
		
		frame = new JFrame("Animated Sorting");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonArea = new ButtonPanel(this); 
		sortingArea = new JPanel(new GridLayout(XCoord, YCoord));
		
		frame.add(sortingArea, BorderLayout.CENTER);
		frame.add(buttonArea, BorderLayout.SOUTH);
	}    
	
	/**
	 * reorders the sortVector according to the order specified
	 * if the animation is not running.
	 * 
	 * @param order the desired order of the sortVector
	 */
	public void organizeVector(DataOrder order) {
		
		if (algorithms.animationFinished()) {
			sortVector.orderVector(order);
			algorithms.setVector(sortVector);
			frame.repaint();
		}
	}
	
	/**
	 * Sets the size of the vector
	 * 
	 * @param size
	 */
	public void setVectorSize(int size) {
		sortVector.setSize(size);
	}
	
	/**
	 * Sets the maximum size for the numbers of the vector
	 * (only used when the vector is randomized)
	 * 
	 * @param max
	 */
	public void setVectorMax(int max) {
		sortVector.setMax(max);
	}  
	
	/**
	 * Starts the animation if it is not already running
	 */
	public void runAnimation(int swapWeight)  {
		algorithms.setStepping(false);
		if (algorithms.animationFinished()) {
			algorithms.start(swapWeight);
		}
	}
	
	/**
	 * Steps the animation if it is not already running
	 */
	public void stepAnimation(int swapWeight)  {
		algorithms.setStepping(true);
		if (algorithms.animationFinished()) {
			algorithms.start(swapWeight);
		}
		algorithms.step();
	}
	
	/**
	 * Shows the main frame
	 */
	public void showFrame() {
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Adds a new AlgorithmHandler for the given algorithm
	 * 
	 * @param alg the new algorithm
	 */
	public void addAlgorithm(SortAlgorithm alg) {
		
		//Creates a copy of the vector for the new algorithm
		DataSet vectorClone = (DataSet) sortVector.clone();
		
		//adds the algorithm and its vector copy to the algorithm vector
		algorithms.add(alg, vectorClone);
		
		//adds the animation widget of the new algorithm to the sorting area.
		sortingArea.add(algorithms.getLastWidget());
		
	}

    /**
     * Sets the delay time 
     * that decides how long the break between each step of the animation is
     * 
     * @param dTime the new delay time
     */
    public void setDelayTime(int dTime) {
        algorithms.setDelayTime(dTime);      
    }
			
}
