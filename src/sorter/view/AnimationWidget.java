package sorter.view;
import javax.swing.JComponent;

import sorter.control.DataSet;

/**
 * This class is a widget that animates a sort algorithm, by showing
 * the integers as bars of a size corresponding to the size of the integer.
 * 
 * This class will have to be implemented by you!
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
public class AnimationWidget extends JComponent {
	
	/**
	 * A reference to the vector that is sorted, which is animated by this class
	 */
	private DataSet vector;
	
	/**
	 * The name of the algorithm
	 */
	private String algorithmName;
    
    // TODO Add more variables...
	
	
	/**
	 * Creates an instance of AnimationWidget with 
	 * a given vector and an algorithm name
	 * 
	 * it will probably have to do more things!
	 * 
	 * @param vector the sortVector to draw
	 * @param algorithmName the name of the algorithm
	 */
	public AnimationWidget(DataSet vector,
			               String algorithmName) {
		this.vector = vector;
		this.algorithmName = algorithmName;
        
		// TODO You will probably need to do some more stuff here
	}
	
	// TODO Add more methods...
}
