import javax.swing.*;
import java.awt.*;

/**
 * Paddle object players use to reflect the ball
 * @author Andrew
 *
 */
public class Paddle extends Rectangle
{
	
	/**
	 * color of the paddle
	 */
	private Color color;
	
	/**
	 * Paddle object constructor
	 * @param xl x location
	 * @param yl y location
	 * @param w width
	 * @param h height
	 * @param c color
	 */
	public Paddle(int xl, int yl, int w, int h, Color c)
	{
		width=w;
		height=h;
		color=c;
		x=xl;
		y=yl;
	}
	
	/**
	 * draws the paddle
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * gets the x location of the paddle
	 * @return x location
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * gets the y location of the paddle
	 * @return y location
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * gets the width of the paddle
	 * @return width
	 */
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * gets the height of the paddle
	 * @return height
	 */
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * gets the color of the paddle
	 * @return color
	 */
	public Color getColor()
	{
		return color;
	}
	
	
}
