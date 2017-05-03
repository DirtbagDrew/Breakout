import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 * Ball Object
 * @author Andrew
 *
 */
public class Ball extends Rectangle
{
	/**
	 * y velocity of the ball
	 */
	private int dx;
	
	/**
	 * y velocity of the ball
	 */
	private int dy;
	
	/**
	 * color of the ball
	 */
	private Color color;
	
	/**
	 * Ball object constructor
	 * @param x x location
	 * @param y y location
	 * @param radius radius of the ball
	 * @param c color of the ball
	 * @param sp speed
	 */
	public Ball(int x,int y,int radius,Color c, int sp)
	{
		setBounds(x,y,2*radius,2*radius);
		dx=sp;
		dy=-sp;
		color=c;
	}
	
	/**
	 * draws and fills the ball
	 * @param g
	 */
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	/**
	 * moves the ball and sets the barriers for the ball
	 * @param winWidth width of the window
	 * @param winHeight height of the window
	 * @param paddle the paddle being used in the game
	 * @param blocks the blocks being used in the game
	 */
	public void move(int winWidth, int winHeight,Paddle paddle, Block[] blocks)
	{
		//detection for hitting the walls
		//the ball hits the right wall
		if(dx>0&&x>winWidth-width)
		{
			dx=-dx;
		}
		
		//the ball hits the left wall
		else if(dx<0&&x<0)
		{
			dx=-dx;
		}
		
		//the ball hits the ceiling
		else if(dy<0&&y<0)
		{
			dy=-dy;
		}
	
		
		//detection for hitting the paddle
		if(y>=paddle.getMinY()-height&&y<=paddle.getMinY()+4)
		{
			//the ball hits the right side of the paddle
			if(x>=paddle.getCenterX()&&x<=paddle.getMaxX())
			{
				//change x direction if the ball is coming from the right
				if(dx<=0)
				{
					dx=-dx;
				}
				dy=-dy;
				play("lookfor.wav");
			}
			//the ball hits the left side of the paddle
			if(x<paddle.getCenterX()&&x>=paddle.getMinX()-width)
			{
				//change x direction if the ball is coming from the left
				if(dx>0)
				{
					dx=-dx;
					
				}
				play("lookfor.wav");
				dy=-dy;
			}
		}
		
		//detection for hitting blocks
		for(int i =0;i<blocks.length;i++)
		{
			if(blocks[i].getDest()==false)
			{
				if((x<=blocks[i].getMaxX()&&x>=blocks[i].getMaxX()-4)||(x>=blocks[i].getMinX()&&x<=blocks[i].getMinX()+4))
				{
					if(y>=blocks[i].getMinY()&&y<=blocks[i].getMinY())
					{
						dx=-dx;
						play("democracy.wav");
						blocks[i].setDest(true);
					}
				}
				else
				{
					if(dy>0)
					{
						if(blocks[i].contains(x, y+height))
						{
							dy=-dy;
							play("democracy.wav");
							blocks[i].setDest(true);
						}
					}
					else if(dx>0)
					{
						if(blocks[i].contains(x+width, y))
						{
							dy=-dy;
							play("democracy.wav");
							blocks[i].setDest(true);
						}
					}
					else
					{
						if(blocks[i].contains(x, y))
						{
							dy=-dy;
							play("democracy.wav");
							blocks[i].setDest(true);
						}
					}
				}
			}
		}

		
		translate(dx,dy);
	}
	
	/**
	 * plays sounds for the ball object
	 * @param filename the name of the file to be played
	 */
	public static void play(String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();			
		}
		catch(LineUnavailableException e)
		{
			System.out.println("Audio Error");
		}
		catch(IOException e)
		{
			System.out.println("file not found");
		}
		catch(UnsupportedAudioFileException e)
		{
			System.out.println("wrong file type");
		}
	}
}
