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

	/**
	 * The name of the algorithm
	 */
	private String algorithmName;

	private int startWidth = 800;

	private int startHeight = 400;

	private Dimension startDim = new Dimension(startWidth, startHeight);

	private Graphics g;

	int startX = getX() + 3;
	int startY = getY() + 3;

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
		paintComponents(g);

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		drawRectangle(g);

		drawName(g);

		double stapleAreaWidth = getWidth() - 6;
		double bottomHeight = getHeight() / 50.0;
		double stapleAreaHeight = getHeight() - 6 - bottomHeight;

		for (Integer i = 0; i < vector.size(); i++) {

			drawStaple(g, i, vector, stapleAreaHeight, stapleAreaWidth);



		}

	}

	private void drawName(Graphics g) {

		g.setColor(Color.RED);
		g.drawString(algorithmName, startX + 5, startY + 12);

	}

	private void drawRectangle(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawRect(getX() + 1, getY() + 1, getWidth() - 3, getHeight() - 3);

	}

	private void drawStaple(Graphics g, Integer stapleNr, DataSet vector,
			double stapleAreaHeight, double stapleAreaWidth) {

		double stapleAreaDynamic = stapleAreaHeight / vector.getMax();
		double spaceWidth = stapleAreaWidth / (vector.getSize() * 10.0);
		double stapleWidth = stapleAreaWidth * 9.0 / (vector.getSize() * 10.0);
		double stapleHeight = vector.get(stapleNr) * stapleAreaDynamic;

		g.setColor(Color.RED);


		g.fillRect((int) (startX + (stapleWidth + spaceWidth) * stapleNr),
				(int) (stapleAreaHeight + startY - stapleHeight),
				(int) stapleWidth, (int) stapleHeight);
	

	}

}
