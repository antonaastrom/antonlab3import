import sorter.control.algorithms.BubbleSortAlgorithm;
import sorter.view.Animator;


/**
 * The class that is used to start the animator.
 *
 * The algorithms that are to be shown have to be added here.
 * 
 * @author Sara Stymne
 *
 */
public class AnimationStarter {
	
    /**
     * The animator used in the application
     */
	private static Animator animator;
    
	
	/**
	 * Main program for the animator. 
	 * The algorithms that are to be shown are added here.
	 * 
	 * @param args not used
	 */
	public static void main(String args[]) {
		
		
		//Creates a new animator that places all algorithm
		//widgets on top of each other
		animator = new Animator();
		
		//The constructor of Animator can also be called
		//with two int arguments to change the grid-configuration of
		//the algorithms, for instance:
		//new Animator(2,2) 
		//to place the algorithms in a 2*2 grid.
		
		      
		//Adds algorithms to the system
		animator.addAlgorithm(new BubbleSortAlgorithm());
		//Add more algorithms here in the same way as for bubble sort
		
        
		//Must be called to show the main frame.
	    //Schedule a job for the event-dispatching thread:
        //showing the animator GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	animator.showFrame(); 
            }
        });
		    
	}

}
