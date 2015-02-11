package sorter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import sorter.control.DataSet;

/**
 * This class is a widget that animates a sort algorithm, by showing the
 * integers as bars of a size corresponding to the size of the integer.
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

	public DataSet getVector() {
		return vector;
	}

	public void setVector(DataSet vector) {
		this.vector = vector;
	}

	/**
	 * The name of the algorithm
	 */
	private String algorithmName;

	private int startWidth = 800;

	private int startHeight = 400;

	private Dimension startDim = new Dimension(startWidth, startHeight);

	int startX = getX() + 4;
	int startY = getY() + 4;

	private boolean algorithmFinished;

	private int cmp1 = -1;

	private int cmp2 = -1;

	private int swap1 = -1;

	private int swap2 = -1;

	/**
	 * Creates an instance of AnimationWidget with a given vector and an
	 * algorithm name
	 * 
	 * it will probably have to do more things!
	 * 
	 * @param vector
	 *            the sortVector to draw
	 * @param algorithmName
	 *            the name of the algorithm
	 */
	public AnimationWidget(DataSet vector, String algorithmName) {
		this.vector = vector;
		this.algorithmName = algorithmName;

		setPreferredSize(startDim);

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawRectangle(g);

		drawName(g);

		double stapleAreaWidth = getWidth() - 6;
		double bottomHeight = getHeight() / 50.0;
		double stapleAreaHeight = getHeight() - bottomHeight;

		for (Integer i = 0; i < vector.getSize(); i++) {
			
			

			drawStaple(g, i, stapleAreaHeight, stapleAreaWidth);
			

		}
		setAlgorithmFinished(false);
		beingSwapped(-1,-1);
		beingCompared(-1,-1);
		
		

	}

	private void drawName(Graphics g) {

		g.setColor(Color.RED);
		g.drawString(algorithmName, startX + 5, startY + 12);

	}

	private void drawRectangle(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawRect(getX() + 1, getY() + 1, getWidth() - 3, getHeight() - 3);

	}

	private void drawStaple(Graphics g, Integer stapleNr,
			double stapleAreaHeight, double stapleAreaWidth) {

		double stapleAreaDynamic = stapleAreaHeight / vector.getMax();
		double spaceWidth = stapleAreaWidth / (vector.getSize() * 10.0);
		double stapleWidth = stapleAreaWidth * 9.0 / (vector.getSize() * 10.0);
		double stapleHeight = vector.get(stapleNr) * stapleAreaDynamic;
		int stapleAreaHeightI = (int) stapleAreaHeight;
		// int stapleWidthI = (int) stapleWidth;
		// int spaceWidthI= (int) spaceWidth;

		if (algorithmFinished) {

			g.setColor(Color.GREEN);
			

		} else if (cmp1 == stapleNr || cmp2 == stapleNr) {
			
			g.setColor(Color.YELLOW);
			
		} else if (swap1 == stapleNr || swap2 == stapleNr) {	
			
			g.setColor(Color.BLUE);
			
		
		} else {
			
			g.setColor(Color.RED);
			
		}

		if ((int) stapleWidth < 1) {

			g.fillRect((int) (startX + (stapleWidth + spaceWidth) * stapleNr),
					(int) (stapleAreaHeightI + startY - stapleHeight), 1,
					(int) stapleHeight);

		} else {

			g.fillRect((int) (startX + (stapleWidth + spaceWidth) * stapleNr),
					(int) (stapleAreaHeightI + startY - stapleHeight),
					(int) stapleWidth, (int) stapleHeight);

		}

	}

	public void setAlgorithmFinished(boolean finished) {

		this.algorithmFinished = finished;
	}


	public void beingCompared(int i, int j) {
		
		
		this.cmp1 = i;
		this.cmp2 = j;
		
	}

	public void beingSwapped(int index1, int index2) {
		// TODO Auto-generated method stub
		
		this.swap1 = index1;
		this.swap2 = index2;
		
	}

}
