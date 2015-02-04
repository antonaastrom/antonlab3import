package sorter.view;

import javax.swing.*;
import javax.swing.event.*;

import sorter.control.DataOrder;

import java.awt.*;
import java.awt.event.*;

/**
 * This class is a panel containing controls for the animation.
 * 
 * It contains several buttons to reorder the vector, fields where
 * the size and max size of the numbers can be changed,
 * radio buttons to change the weight of a swap, a slider 
 * that changes the delay time of the animation  
 * and buttons to start and quit the animation.
 *
 * This class does not have to be changed.
 * But if you want to experiment with graphical user interfaces
 * or want to make it look nicer it is allowed to change it!
 * It is also possible to add more functionality if desired!
 * If you do that you might also have to modify other classes.
 *
 * @author Sara Stymne
 *
 */
public class ButtonPanel extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Button that scrambles the vector
	 */
	private JButton scrambleButton;
	
	/**
	 * Button that randomizes the vector
	 */
	private JButton randomButton;
	
	/**
	 * arranges the vector backwords
	 */
	private JButton backwordButton;
	
	/**
	 * arranges the vector sorted
	 */
	private JButton sortedButton;

	/**
	 * arranges the vector sorted
	 */
	private JButton nearlySortedButton;
    
	/**
	 * Sets the size of the vector
	 */
	private JTextField sizeField;
	
	/**
	 * Sets the maximum size of the numbers in the vector
	 */
	private JTextField maxField;
	
	/**
	 * Runs the animation
	 */
	private JButton runButton;

	/**
	 * Steps the animation
	 */
	private JButton stepButton;
	
	/**
	 * Quits the animation
	 */
	private JButton quitButton;
	
	
	/**
	 * A panel for the top row of buttons
	 **/
	private JPanel topRow;
    
    /**
     * A panel fro the middle row of buttons
     */
	private JPanel middleRow;
    
    /**
     * A panel for the bottom row of buttons
     */
	private JPanel bottomRow;
	
	/**
	 * The animator of this panel
	 */
	private Animator animator;
	
    /**
     * The weight of a swap compared to a comparison
     */
	private int swapWeight;
	
	
	/**
	 * Creates a new ButtonPanel with the standard buttons 
	 *
	 * @param animator the associated animator
	 */
	public ButtonPanel(Animator animator) {
		super();
		this.animator = animator;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		topRow = new JPanel();
		middleRow = new JPanel();
		bottomRow = new JPanel();

		//Creates the buttons
		topRow.setLayout(new FlowLayout());
		bottomRow.setLayout(new FlowLayout());
		
		scrambleButton = new JButton("Scrambled");
		scrambleButton.addActionListener(new ScrambleHandler());
		topRow.add(scrambleButton);
		
		randomButton = new JButton("Randomized");
		randomButton.addActionListener(new RandomHandler());
		topRow.add(randomButton);
		
		nearlySortedButton = new JButton("Nearly sorted");
		nearlySortedButton.addActionListener(new NearlySortedHandler());
		topRow.add(nearlySortedButton);  
		
		backwordButton = new JButton("Decreasing order");
		backwordButton.addActionListener(new BackwordHandler());
		topRow.add(backwordButton);
		
		sortedButton = new JButton("Increasing order");
		sortedButton.addActionListener(new SortedHandler());
		topRow.add(sortedButton);     
		
		
		//Creates the middle row
		middleRow.add(new JLabel("Vector size: "));
		sizeField = new JTextField();
		sizeField.setPreferredSize(new Dimension(40, 28));
		middleRow.add(sizeField);
		
		
		middleRow.add(new JLabel("Max number: "));
		maxField = new JTextField();
		maxField.setPreferredSize(new Dimension(40, 28));
		middleRow.add(maxField);  
		
		middleRow.add(new JLabel("Weight for swaps:"));
		middleRow.add(createSwapWeightChooser());
		swapWeight = 1;
	
        //Creates the bottom row
        bottomRow.add(createDelayTimeSlider());
        
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new QuitHandler());
		bottomRow.add(quitButton);
		
		runButton = new JButton("Run animation");
		runButton.addActionListener(new RunHandler());
		bottomRow.add(runButton);

		stepButton = new JButton("Step animation");
		stepButton.addActionListener(new StepHandler());
		bottomRow.add(stepButton);
		
		
		//Adds all rows
		add(topRow);
		add(middleRow);
		add(bottomRow);
		
		setVisible(true);
		
	}
	
    /**
     * Creates a new delayTimeSlider
     * a slider to choose the delaytime of the animation
     *
     *@return the new delayTimeSlider
     */
    private JPanel createDelayTimeSlider() {
        
        //create a JPanel to hold the slider and a text
        JPanel delayPanel = new JPanel();
        delayPanel.setLayout(
                new BoxLayout(delayPanel, BoxLayout.Y_AXIS));
        
        //Create the label for the text.
        JLabel sliderLabel = new JLabel("delay time (0 - 500)", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Create the slider itself
        JSlider delaySlider = new JSlider(JSlider.HORIZONTAL,
                                          0, 500, 100);
        
        delaySlider.addChangeListener(new DelayHandler());

        //Turn on labels at major tick marks.
        delaySlider.setMajorTickSpacing(20);
        delaySlider.setMinorTickSpacing(5);
        delaySlider.setPaintTicks(true);
        //delaySlider.setPaintLabels(true);

        //Put everything together.
        delayPanel.add(sliderLabel);
        delayPanel.add(delaySlider);
   
        return delayPanel;
    }
    
    
    
	/**
	 * Creates a new swapWeightChooser,
	 * a group of radio buttons that are used to choose the swap weight
	 *
	 *@return the new swapweightchooser
	 */
	private JPanel createSwapWeightChooser() {
		
        //Create a panel for the radio buttons
		JPanel weightPanel = new JPanel(new FlowLayout());
		WeightHandler weightHandler = new WeightHandler();
		
		//Create the radio buttons.
		JRadioButton button1 = new JRadioButton("1");
		button1.setActionCommand("1");
		button1.setSelected(true);
		button1.addActionListener(weightHandler);
		
		JRadioButton button2 = new JRadioButton("2");
		button2.setActionCommand("2");
		button2.addActionListener(weightHandler);
		
		JRadioButton button3 = new JRadioButton("3");
		button3.setActionCommand("3");
		button3.addActionListener(weightHandler);
		
		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(button1);
		group.add(button2);
		group.add(button3);
		
		//add the buttons to their panel
		weightPanel.add(button1);
		weightPanel.add(button2);
		weightPanel.add(button3);
		
		return weightPanel;
	}
	
	/**
	 * Sets a new animator
	 * 
	 * @param animator the new animator
	 */
	public void setAnimator(Animator animator) {
		this.animator = animator;
	}
	
	/**
	 * Runs the animation
	 */
	private void runAnimation() {
		animator.runAnimation(swapWeight);
	}

	/**
	 * Steps the animation
	 */
	private void stepAnimation() {
		animator.stepAnimation(swapWeight);
	}
	
	/**
	 * scrambles the vector, 
	 * reorganizes the order of the numbers with no doubles
	 */
	private void scramble() {
		changeSize();  
		animator.organizeVector(DataOrder.SCRAMBLED);    
	}
	
	/**
	 * randomizes the vector,
	 * sets every integer to a random value between 1 and max, may give doubles
	 */
	private void randomize() {
		changeSize();  
		changeMax();
		animator.organizeVector(DataOrder.RANDOM);    
	}
	
	/**
	 * arranges the vector in a backword sorted order
	 */
	private void backword() {
		changeSize();  
		animator.organizeVector(DataOrder.BACKWORD);    
	}
	
	/**
	 * arranges the vector in a sorted order
	 */
	private void sorted() {
		changeSize();  
		animator.organizeVector(DataOrder.SORTED);    
	}   

    	/**
	 * arranges the vector in a nearly sorted order
	 */
	private void nearlySorted() {
		changeSize();  
		animator.organizeVector(DataOrder.NEARLY_SORTED);    
	}
    
    private void setDelayTime(int delay) {
        animator.setDelayTime(delay);
    }
    
	/**
	 * Changes the size of the vector
	 * if a number between 10 and 100 is given in the max field.
	 * 
	 */
	private void changeSize() {
		try {
			int size = Integer.decode(sizeField.getText());
			if (size >= 10 && size <= 200) {
				animator.setVectorSize(size);
			} else {
				sizeField.setText("");
			}
		} catch (NumberFormatException e) {
			sizeField.setText("");
		}
	}
	
	/**
	 * Changes the maximum size of the numbers in the vector
	 * if a number between 10 and 100 is given in the max field.
	 * 
	 * The max size is only used when the vector is randomized.
	 */
	private void changeMax() {
		try {
			int max = Integer.decode(maxField.getText());
			if (max >= 10 && max <= 200) {
				animator.setVectorMax(max);
			} else {
				maxField.setText("");
			}
		} catch (NumberFormatException e) {
			maxField.setText("");
		}      
	}
	
	/**
	 * Sets the swap weight
	 * 
	 * @param weight the new weight
	 */
	private void setSwapWeight(int weight) {
		swapWeight = weight;
	}
	
	
	/**
	 * ActionListener that scrambles the vector
	 * 
	 * @author Sara Stymne
	 *
	 */
	class ScrambleHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			scramble();
		}
	}
	
	/**
	 * ActionListener that randomizes the vector
	 * 
	 * @author Sara Stymne
	 *
	 */
	class RandomHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			randomize();
		}
	}
	
	/**
	 * ActionListener that organizes the vector backwords
	 * 
	 * @author Sara Stymne
	 *
	 */
	class BackwordHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			backword();
		}
	}
	
	/**
	 * ActionListener that organizes the vector sorted
	 * 
	 * @author Sara Stymne
	 *
	 */
	class SortedHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			sorted();
		}
	}

	
	/**
	 * ActionListener that organizes the vector nearlysorted
	 * 
	 * @author Sara Stymne
	 *
	 */
	class NearlySortedHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			nearlySorted();
		}
	}
    
	/**
	 * ActionListener that starts the application
	 * 
	 * @author Sara Stymne
	 *
	 */
	class RunHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			runAnimation();
		}
	}

	/**
	 * ActionListener that steps the application
	 * 
	 * @author Jonas Lindgren
	 *
	 */
	class StepHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			stepAnimation();
		}
	}
	
	/**
	 * ActionListener that quits the application
	 * 
	 * @author Sara Stymne
	 *
	 */
	class QuitHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			System.exit(0);
		}
	}
	
	/**
	 * ActionListener that changes the swap weight
	 * 
	 * @author Sara Stymne
	 */
	class WeightHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "1") {
				setSwapWeight(1);
			} else if(e.getActionCommand() == "2") {
				setSwapWeight(2);
			} else if(e.getActionCommand() == "3") {
				setSwapWeight(3);
			}
		}
	}
    
    /**
     * ChangeListener that changes the delay time
     * 
     * @author Sara Stymne
     */
    class DelayHandler implements ChangeListener {
        
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int delay = (int)source.getValue();
                setDelayTime(delay);
            }
        }
    }
}
