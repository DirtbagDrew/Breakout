import javax.swing.*;
import java.awt.*;

/**
 * Objects to be destroyed by the bouncing ball
 * @author Andrew
 *
 */
public class Block extends Rectangle
{
	/**
	 * color of the block
	 */
	private Color color;
	
	/**
	 * indicator that the block is destroyed
	 */
	private boolean dest;
	
	/**
	 * Block constructor
	 * @param xl x location
	 * @param yl y location
	 * @param h height
	 * @param w width
	 * @param c color
	 */
	public Block(int xl, int yl, int h, int w, Color c)
	{
		x=xl;
		y=yl;
		height=h;
		width=w;
		color=c;
		dest=false;
	}
	
	/**
	 * draws the block
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,width,height);
	}
	
	/**
	 * determines whether the block is destroyed
	 * @return dest
	 */
	public boolean getDest()
	{
		return dest;
	}
	
	/**
	 * sets whether or not the block is destroyed
	 * @param b
	 */
	public void setDest(boolean b)
	{
		dest=b;
	}
}
